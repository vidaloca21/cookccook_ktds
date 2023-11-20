<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>구매 페이지</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	
	$().ready(function() {
		
		$("input[name=payMethod]").change(function(){
			if($("input[name=payMethod]:checked").val() == 'card'){
				$("#cardLabel").removeClass("btn_empty")
				$("#cardLabel").addClass("btn_fill")
				$("#kakaoLabel").removeClass("btn_fill")
				$("#kakaoLabel").addClass("btn_empty")
			}	
			else if($("input[name='payMethod']:checked").val() == 'kakao'){
				$("#cardLabel").removeClass("btn_fill")
				$("#cardLabel").addClass("btn_empty")
				$("#kakaoLabel").removeClass("btn_empty")
				$("#kakaoLabel").addClass("btn_fill")
			}
		});
		
		// Controller에 전달할 데이터
		let purchaseVO = {};
		//let purchaseProductVOList = $("<form name='purchaseProductVOList' action='/shop/createNewPurchaseProduct' enctype='multipart/form-data' method='post'></form>");
		let purchaseId = "${purchaseId}";
		let deliverFee = 3000;
		// 총 결제 금액
		let sumPrice = 0;
		let prdNameForPurchase = "";
		let purchaseProductVOList = [];
		let cartVOList = [];
		$(".cartListInfo ul.cartInfo").each(function(index, value) {
			var purchaseProductVO = {};
			var cartId = $(this).attr("id");
			var choiceId = $(this).find("li.choiceId").attr("id");
			var quantity = $(this).find("li.quantity").val();
			var totalPrice = $(this).find("li.totalPrice").val();
			purchaseProductVO.purchaseId = purchaseId;
			purchaseProductVO.cartId = cartId;
			purchaseProductVO.choiceId = choiceId;
			purchaseProductVO.quantity = quantity;
			purchaseProductVO.deliverFee = deliverFee;
			purchaseProductVO.purPrdPrice = totalPrice;
			purchaseProductVOList.push(purchaseProductVO);
			cartVOList.push({cartId: cartId});
			sumPrice += totalPrice;
			if (index == 0) {
				prdNameForPurchase = $(this).find("li.prdName").text()
			}
			else {
				prdNameForPurchase += " 외 " + index + "건"
			}
		})
		
		let finalPrice = sumPrice + deliverFee;
		$("#sumPrice").text(sumPrice)
		$("#deliverFee").text(deliverFee)
		$("#finalPrice").text(finalPrice)
		
		$("#buyBtn").click(function(){
			// 결제 정보 바인딩
			let payMethod = $("input:radio[name='payMethod']:checked").val()
			let pgInfo = (payMethod == 'card') ? 'html5_inicis' : 'kakaopay';
			let buyerEmail = $("input:text[name='buyerEmail']").val()
			let buyerName = $("input:text[name='buyerName']").val()
			let buyerTel = $("input:text[name='buyerTel']").val()
			let buyerAddr = $("input:radio[name='deliverPlace']:checked").data("buyerAddr")
			let buyerPostcode = $("input:radio[name='deliverPlace']:checked").data("buyerPostcode")
			// form 전송을 위한 바인딩 (purchaseVO)
			purchaseVO.purchaseId = purchaseId;
			purchaseVO.deliverPlaceId = $("input:radio[name='deliverPlace']:checked").val();
			purchaseVO.deliverMemo = $("input:text[name='deliverMemo']").val();
			purchaseVO.payMethod = payMethod;
			
			var IMP = window.IMP; 
			IMP.init("imp38511684"); 
			IMP.request_pay({
				pg : pgInfo,
				pay_method : payMethod,
				merchant_uid: purchaseId, // 상점에서 관리하는 주문 번호
				name : prdNameForPurchase,
				amount : 100,
				buyer_email : buyerEmail,
				buyer_name : buyerName,
				buyer_tel : buyerTel,
				buyer_addr : buyerAddr,
				buyer_postcode : buyerPostcode,
				m_redirect_url: "{리디렉션 될 URL}"
			}, function(rsp) { // callback
				if ( rsp.success ) {
					$.ajax({
						url: "/iamport/verify/" + rsp.imp_uid,
						method: "POST",
						data: {
							"impUid": rsp.imp_uid,    // 결제 고유번호
							"merchantUid": purchaseId   // 주문번호
						}
					})
					var resultErrorCount = 0;
					var nextUrl = "";
					$.ajax({
						url: "/shop/createNewPurchase",
						method: "post",
						data: purchaseVO,
						success: function(response) {
							if (response.result) {
								$.ajax({
									url: "/shop/deleteCartList",
									type: "post",
									data: JSON.stringify(cartVOList),
									contentType: "application/json",
									success: function(response){
										if(!response.result) {
											Swal.fire({
											title: '에러가 발생하였습니다.',
											icon: 'error',
											})
										}
									}
								})
								$.ajax({
									url: "/shop/createNewPurchaseProduct",
									type: "post",
									data: JSON.stringify(purchaseProductVOList),
									contentType: "application/json",
									success: function(response){
										Swal.fire({
											title: '결제가 완료되었습니다.',
											icon: 'success',
										}).then(() => {
											// 소켓 전송
											buy(send, userName, userId, "${sellerId}", "새로운 주문이 발생했습니다.");
											setTimeout(function() {
												location.href= response.nextUrl
											}, 300);
										})
									}
								})
							}else {
								var msg = '결제에 실패하였습니다.';
								msg += '에러내용 : ' + rsp.error_msg;
								alert(msg);
							}
						}
					})
				}
				else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					alert(msg);
				}
			}) //request_pay
		}) //buybutton.click
}) //document.ready
</script>
<style type='text/css'>
	#orderMemberInfo > .orderMemberInfoArea {
		display: flex;
		flex-direction: row;
	}
	#orderMemberInfo > .orderMemberInfoArea > li {
		display: inline;
		width: 240px;
		height: 40px;
		align-items: center;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > input {
		display: inline;
		width: 280px;
		height: 40px;
	}
	.cartListInfo {
		display: flex;
		flex-direction: column;
		width: 95%;
	}
	.cartListInfo > ul {
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	.cartListInfo > ul > li {
		display: flex;
		padding: 5px;
		height: auto;
	}
	.cartListInfo > ul > li > img {
		width: 100%;
		height: auto;
		border-radius: 5px;
	}
	.cartListInfo > ul > .prdImg{
		width: 10%;
	}
	.cartListInfo > ul > .prdName{
		padding-right: 20px;
		width: 40%;
	}
	.cartListInfo > ul > .prdPrice{
		padding-right: 20px;
		width: 10%;
	}
	.cartListInfo > ul > .choName{
		width: 10%;
	}
	.cartListInfo > ul > .choPrice{
		padding-right: 20px;
		width: 10%;
	}
	.cartListInfo > ul > .quantity{
		padding-right: 20px;
		width: 10%;
	}
	.cartListInfo > ul > .totalPrice{
		width: 10%;
		
	}
	.cartListInfo > ul > .cartId, .memberId, .productId, .choiceId{
		display: none;
	}
	#payInfo {
		width: 95%;
	}
	#payInfo > ul {
		border-bottom: 1px solid #EEE;
	}
	ul.priceInfo {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		padding-right: 30px;
	}
	ul.priceInfo > li {
		display: inline-block;
	}
	ul.priceInfo > li#finalPrice {
		font-size: large;
		font-weight: bolder;
	}
	ul.payMethod {
		display: flex;
		align-items: center;
		margin: 0;
		padding: 0;
	}
	ul.payMethod > li {
		display: flex;
	}
	#payInfo > #right-align {
		display: flex;
		justify-content: space-between;
	}
	input[name=payMethod] {
		visibility: hidden;
		margin: 0;
		padding: 0;
	}
	.payMethod label {
		cursor: pointer;
		margin: 0;
		padding: 0;
	}
	.payMethod #buyBtn {
		cursor: pointer;
		margin-left: 30px;
		padding: 0;
	}
</style>
  	<jsp:include page="../layout/header2.jsp"></jsp:include>
		<h1>상품 구매</h1>
		<div class="deliverPlaceInfo">
			<jsp:include page="./deliverPlaceList.jsp"></jsp:include>
		</div>
		<div id="orderMemberInfo">
			<h2>주문자 정보</h2>
			<ul class="orderMemberInfoArea">
				<li><label for="buyerEmail">주문자 EMAIL</label></li>
				<li><input type="text" name="buyerEmail" value="${member.memEmail }" /></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="buyerName">주문자 성함</label></li>
				<li><input type="text" name="buyerName" value="${member.memName }" /></li>
			</ul>
			<ul class="orderMemberInfoArea">
				<li><label for="buyerTel">주문자 전화번호</label></li>
				<li><input type="text" name="buyerTel" value="${member.memPhoneNumber }" /></li>
			</ul>
		</div>
		<div class="cartListInfo">
			<h2>주문 상품</h2>
			<ul id="${cart.cartId }">
				<li class="prdImg"></li>
				<li class="prdName" >상품명</li>
				<li class="prdPrice">상품 금액</li>
				<li class="choName">상품 옵션</li>
				<li class="choPrice">추가 금액</li>
				<li class="quantity">수량</li>
				<li class="totalPrice">금액</li>
			</ul>
			<c:forEach items="${cartList}" var="cart">
				<ul class="cartInfo" id="${cart.cartId }">
					<li class="cartId" style="display: none;" >${cart.cartId }</li>
					<li class="prdImg"><img src="/shop/product/titleimg/${cart.productVO.prdTitleImgOrigin}"></li>
					<li class="memberId" value="${cart.memberId }" style="display: none;">${cart.memberId }</li>
					<li class="productId" value="${cart.productVO.productId }" style="display: none;" >${cart.productVO.productId }</li>
					<li class="prdName" value="${cart.productVO.prdName }" >${cart.productVO.prdName }</li>
					<li class="prdPrice" value="${cart.productVO.prdPrice }" >${cart.productVO.prdPrice }</li>
					<li class="choiceId" id="${cart.choiceId }" style="display: none;" >${cart.choiceId }</li>
					<li class="choName" value="${cart.choiceVO.choName }" >${cart.choiceVO.choName }</li>
					<li class="choPrice" value=${cart.choiceVO.choPrice } >${cart.choiceVO.choPrice }</li>
					<li class="quantity" value=${cart.quantity } >${cart.quantity }</li>
					<li class="totalPrice" value=${cart.totalPrice }>${cart.totalPrice }</li>
				</ul>
			</c:forEach>
		</div>
		<div id="payInfo">
			<h2>결제 정보</h2>
			<ul class="priceInfo">
				<li >총 상품 금액</li>
				<li id="sumPrice"></li>
			</ul>
			<ul class="priceInfo">
				<li>배송비</li>
				<li id="deliverFee"></li>
			</ul>
			<ul class="priceInfo">
				<li>총 결제 비용</li>
				<li id="finalPrice"></li>
			</ul>
			<div id="right-align">
				<ul class="payMethod">
					<li>
						<input type="radio" name="payMethod" id="card" value="card" checked="checked"/>
						<label for="card" class="btn_empty" id="cardLabel">카드결제</label>
					</li>
					<li>
						<input type="radio" name="payMethod" id="kakao" value="kakao" />
						<label for="kakao" class="btn_empty" id="kakaoLabel">카카오페이</label>
					</li>
					<li>
					</li>
				</ul>
				<button id="buyBtn" class="btn_fill" style="cursor: pointer;">구매</button>
			</div>
		</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
