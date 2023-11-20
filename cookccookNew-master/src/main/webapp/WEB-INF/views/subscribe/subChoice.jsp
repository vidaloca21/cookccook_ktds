<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
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
	.cartListInfo > ul > .prdName{
		padding-right: 20px;
		width: 40%;
	}
	.cartListInfo > ul > .subPeriod{
		padding-right: 20px;
		width: 10%;
	}
	.cartListInfo > ul > .totalPrice{
		width: 10%;
		
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
	ul.priceInfo > li#totalPrice {
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
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script type="text/javascript">	
	$().ready(function() {
		var subPrice = parseInt("${subscribeVO.subPrice}");
		var deliverFee = 0;
		var totalPrice = subPrice + deliverFee;
		$("#subPrice").text(subPrice);
		$("#deliverFee").text(deliverFee);
		$("#totalPrice").text(totalPrice);
		$("input[name=payMethod]").change(function(){
			if($("input[name=payMethod]:checked").val() == 'card'){
				$("#cardLabel").removeClass("btn_empty")
				$("#cardLabel").addClass("btn_fill")
				$("#kakaoLabel").removeClass("btn_fill")
				$("#kakaoLabel").addClass("btn_empty")
			}	
			else if($("input[name='payMethod']:checked").val() == 'kakaopay'){
				$("#cardLabel").removeClass("btn_fill")
				$("#cardLabel").addClass("btn_empty")
				$("#kakaoLabel").removeClass("btn_empty")
				$("#kakaoLabel").addClass("btn_fill")
			}
		});
	})

	var IMP = window.IMP; 
	IMP.init("imp38511684");

	function paySuccess(imp_uid) {
		var subscribeId = "${subscribeVO.subscribeId}"
		var memberId = "${member.memberId}"
		var payMethod = $("input[name=payMethod]:checked").val();
		if(payMethod == "kakaopay"){
			payMethod = "카카오페이"
		}else {
			payMethod = "카드"
		}
		var deliverPlaceId = $("input[name=deliverPlace]:checked").val();
		
		var body = 
				{ 
					"memberId" : memberId,
					"subscribeId" : subscribeId,
					"payMethod" : payMethod,
					"deliverPlaceId" : deliverPlaceId,
					"impUid" : imp_uid
				}
		var url = "/subscribe/choice"

		$.post(url, body, function(response) {
			var result = response.result
			if (result) {
				location.href="/subscribe/subpaysuccess";
			}
		})
	} 

	async function requestPay() {
		var payMethod = $("input[name=payMethod]:checked").val();
		var deliverPlaceId = $("input[name=deliverPlace]:checked").val();
		var subPrice = parseInt("${subscribeVO.subPrice}");
		var deliverFee = 0;
		var totalPrice = subPrice + deliverFee;
		var orderNum = creatOrderNum();
		var productName = "${subscribeVO.subChoice}"
		if(productName.match("채소")){
			productName = "그린 식단"
		}else {
			productName = "밸런스 식단"
		}
		
		//사전검증
		try {
			const response = await axios({
			url: "https://api.iamport.kr/payments/prepare",
			method: "post",
			headers: { "Content-Type": "application/json" }, 
			data: {
				merchant_uid: orderNum, // 가맹점 주문번호
				amount: totalPrice, // 결제 예정금액
			}
			});
			// 이제 `response` 객체를 사용하여 API 응답 데이터를 처리할 수 있습니다.
		} catch (error) {
			// 오류 처리
			console.error(error);
		}

		if(payMethod == "card") {
			var pg = "html5_inicis.INIBillTst"
			var pay_method = 'card'
		} else if( payMethod == "kakaopay"){
			var pg = "kakaopay.TC0ONETIME"
			var pay_method = 'card'
		} 
		
		if(payMethod != "" && deliverPlaceId != "" && payMethod != null && deliverPlaceId != null){
			IMP.request_pay({
				pg: pg,
				pay_method : pay_method,
				merchant_uid: orderNum, 
				name : productName,
				amount : totalPrice,
				buyer_email : '${member.memEmail}',
				buyer_name : '${member.memName}',
				buyer_tel : '${member.memPhoneNumber}',
				buyer_addr : '서울특별시 강남구 삼성동',
				buyer_postcode : '123-456'
				}, function (rsp) { // callback
					if (rsp.success) {
						$.ajax({
							url: "/iamport/verify/" + rsp.imp_uid,
							method: "POST",
							data: {
								"impUid": rsp.imp_uid,    // 결제 고유번호
								"merchantUid": orderNum   // 주문번호
							}
						}).done(function (data) {
							// 가맹점 서버 결제 API 성공시 로직
							paySuccess(rsp.imp_uid)
						})
					} else {
						alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
						location.href=`/subscribe/choice?subscribeId=\${subscribeVO.subscribeId}`;
					}
			});
		}else {
			alert("배송지를 입력해주세요.")
		}
	}

	function creatOrderNum() {
		var today = new Date();

		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);

		var dateString = year + month + day;
		return dateString + "-" + rand();
	}
	function rand() {
		return Math.floor(Math.random() * (99999 - 10000 + 1)) + 10000;
	}
</script>
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<div class="container">
	<h1>구독 결제</h1>
	<div class="deliverPlaceInfo">
		<jsp:include page="../shop/deliverPlaceList.jsp"></jsp:include>
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
			<li class="prdName" >구독옵션</li>
			<li class="subPeriod">배송주기</li>
			<li class="totalPrice">금액</li>
		</ul>
		<ul class="cartInfo">
			<c:set var = "str" value = "${subscribeVO.subChoice}"/>
			<li class="prdName">
				<c:choose>
				<c:when test="${fn:contains(str, '채소')}">
					그린 식단
				</c:when>
				<c:otherwise>
					밸런스 식단
				</c:otherwise>
				</c:choose>
			</li>
			<li class="subPeriod">
				<c:choose>
				<c:when test="${fn:contains(str, '2주')}">
					2주 마다
				</c:when>
				<c:otherwise>
					4주 마다
				</c:otherwise>
				</c:choose>
			</li>
			<li class="totalPrice">${subscribeVO.subPrice }</li>
		</ul>
	</div>
	<div id="payInfo">
		<h2>결제 정보</h2>
		<ul class="priceInfo">
			<li >총 상품 금액</li>
			<li id="subPrice"></li>
		</ul>
		<ul class="priceInfo">
			<li>배송비</li>
			<li id="deliverFee"></li>
		</ul>
		<ul class="priceInfo">
			<li>총 결제 비용</li>
			<li id="totalPrice"></li>
		</ul>
		<div id="right-align">
			<ul class="payMethod">
				<li>
					<input type="radio" name="payMethod" id="card" value="card" checked="checked"/>
					<label for="card" class="btn_fill" id="cardLabel">카드결제</label>
				</li>
				<li>
					<input type="radio" name="payMethod" id="kakao" value="kakaopay" />
					<label for="kakao" class="btn_empty" id="kakaoLabel">카카오페이</label>
				</li>
				<li>
				</li>
			</ul>
			<button id="submitBtn" class="btn_fill" onclick="requestPay()" style="cursor: pointer;">결제하기</button>
		</div>
	</div>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>