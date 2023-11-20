<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 조회</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<link rel="stylesheet" href="/css/product/lib/productContainer.css">
<script src="https://kit.fontawesome.com/63879a02ce.js" crossorigin="anonymous"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		const id = '${member.memberId}';
		const productId = "${productVO.productId}";
		let choicePrice = 0;
		$.ajax({
				url: "/product/choice/${productVO.productId}",
				method: "get",
				success: function(response) {
					for (var i in response) {
						let choiceId = response[i].choiceId
						choicePrice = response[i].choPrice
						let choiceList = $("<option></option>");
						choiceList.val(choiceId);
						choiceList.data("choiceId", choiceId)
						choiceList.data("price", choicePrice)
						if ( choicePrice > 0) {
							choicePrice = choicePrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원"
							choiceList.text(response[i].choName + " (+" + choicePrice + ")");
						}
						else {
							choiceList.text(response[i].choName);
						}
						$(".choiceSelect").append(choiceList);
					}
				}
		})
		
		let totalPrice = ${productVO.prdPrice };
		$("#totalPrice").text(totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원");
		
		$("select[name=productChoice]").change(function(){
			let choicePirce = $(this).children("option:selected").data("price");
			let totalPrice = ${productVO.prdPrice };
			totalPrice = totalPrice + choicePirce
			$("#totalPrice").text(totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원");
		})
		$(".questions").hide();
		$("#queWriteAnchor").hide();
		$("#viewReview").click(function() {
			$("#viewReview").attr('class','viewActive');
			$("#viewQuestion").attr('class','viewInactive');
			$("#revWriteAnchor").show();
			$("#queWriteAnchor").hide();
			$(".reviewWriteArea").hide();
			$(".queWriteArea").hide();
			$(".reviews").show();
			$(".questions").hide();
		})
		$("#viewQuestion").click(function() {
			$("#viewReview").attr('class','viewInactive');
			$("#viewQuestion").attr('class','viewActive');
			$("#queWriteAnchor").show();
			$("#revWriteAnchor").hide();
			$(".reviewWriteArea").hide();
			$(".queWriteArea").hide();
			$(".questions").show();
			$(".reviews").hide();
			$("#queContent").hide();
		})
		
		$.ajax({
					url: "/review/${productVO.productId}",
					method: "get",
					success: function(response) {
					let avgRating = response.avgRating;
					$("#star-rate").css("width", (avgRating / 5 *90) +'px');
					$("#number-rate").text(avgRating);
					},
				});
		
		//review
		$.ajax({
			url: "/review/${productVO.productId}",
			method: "get",
			success: function(response) {
				let reviewList = response.reviewList
				if (response.reviewCnt > 0) {
					for (var i in reviewList) {
						var mdfyLink = $("<span></span>");
						var revDeleteLink = $("<span></span>");
						let revContent = $("<div class='revContent'></div>")
						let memNickname = $("<div class='memNickname'></div>")
						memNickname = memNickname.text(reviewList[i].memberVO.memNickname)
						let revPostDate = $("<div class='revPostDate'></div>")
						revPostDate = revPostDate.text(reviewList[i].revPostDate)
						let rating = $("<div class='rating'></div>")
						rating = rating.text(reviewList[i].rating)
						if(id == reviewList[i].memberVO.memberId) {
							mdfyLink = $("<a href='/review/modify?reviewId="+reviewList[i].reviewId+"' >리뷰수정</a>")								
							revDeleteLink = $("<a href=''>리뷰 삭제</a>")
						}
						
						let leftDiv = $("<div class='leftDiv'></div>")
						leftDiv.append(memNickname);
						leftDiv.append(revPostDate);
						leftDiv.append(rating);
						
						let rightDiv = $("<div class='rightDiv' value='"+reviewList[i].memberVO.memberId +"'></div>")
						rightDiv.append(mdfyLink)
						rightDiv.append(revDeleteLink)
						let writerInfo = $("<div class='writerInfo'></div>")
						writerInfo.attr("id", reviewList[i].reviewId)
						writerInfo.append(leftDiv)
						writerInfo.append(rightDiv)
						revContent = revContent.text(reviewList[i].revContent)
						
						
						revDeleteLink.click(function() {
							let revId = $(this).parent().parent().attr("id")
							if(confirm("제출하시겠습니까?")){
								location.href = "/review/delete/"+revId;
							}else{
								location.href = "/shop/product/${productId}";
							}
						})

						$(".reviews").append(writerInfo)
						$(".reviews").append(revContent)
					}
				}else {
						$(".reviews").append("<p>아직 작성된 리뷰가 없습니다!</p>");
				} 

			}
		})
		if (id == '') {
			$.get("/api/purchasehistory", function(response) {
			let userPurchaseList = [];
			for (var i in response.result) {
				userPurchaseList.push(response.result[i].choiceVO.productId)
			}
			if (!userPurchaseList.includes(productId)) {
				$("#revWriteAnchor").css("visibility", "hidden");
			}
			})
		}
		
		$("#revWriteAnchor").click(function() {
			if (id == '') {
				Swal.fire({
					title: '로그인을 먼저 진행해주세요',
					text: "확인을 누르시면 로그인 페이지로 이동합니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					
					}).then((result) => {
					if (result.isConfirmed) {
						location.href = "/member/memberlogin"	
					}
				})
			}
			else {
				if ($(".reviewWriteArea").is(":visible")) {
					$(".reviewWriteArea").slideUp();
				} else {
					$(".reviewWriteArea").slideDown();
				}
			}
		})
		
		$(".close").click(function() {
			$(".reviewWriteArea").slideUp();
			$(".queWriteArea").slideUp();
		})
		
		//question
		$.get("/question/${productVO.productId}", function(response) {
			let questionList = response.questionList
			if (response.questionCnt > 0) {
				for (var i in questionList) {
					console.log(response)
						let upperQuestionId = questionList[i].upperQuestionId
						if(upperQuestionId == null) {
							let memNickname = $("<div class='memNickname'></div>")
							memNickname = memNickname.text(questionList[i].memberVO.memNickname)
							let quePostDate = $("<div class='postDate'></div>")
							quePostDate = quePostDate.text(questionList[i].quePostDate)

							let leftDiv = $("<div class='leftDiv'></div>")
							leftDiv.append(memNickname);
							leftDiv.append(quePostDate);
							
							let queTitle = $("<div class='queTitle'></div>")
							queTitle = queTitle.text(questionList[i].queTitle)
							let queContent = $("<div class='queContent hidden'></div>")
							queContent = queContent.text(questionList[i].queContent)
							
							let mdfyLink = $("<a href='/question/modify?questionId="+questionList[i].questionId+"'>문의 수정</a>")								
							let deleteLink = $("<a href=''>문의 삭제</a>")
							deleteLink.click(function() {
								if(confirm("제출하시겠습니까?")){
									location.href = "/question/delete/"+questionList[i].questionId;
								}
							})
							let rightDiv = $("<div class='rightDiv' value='"+questionList[i].memberId +"'></div>")
							rightDiv.append(mdfyLink)
							rightDiv.append(deleteLink)

							let questionArea = $("<div class='questionArea'></div>")
							let writerInfo = $("<div class='writerInfo'></div>")
							writerInfo.attr("id", questionList[i].questionId)
							writerInfo.append(leftDiv)
							writerInfo.append(rightDiv)
							questionArea.append(writerInfo)
							questionArea.append(queTitle)
							questionArea.append(queContent)
							$(".questions").append(questionArea)
						}
						else {
							let admNickname = $("<div class='memNickname'></div>")
							admNickname = admNickname.text(questionList[i].memberVO.memNickname)
							let ansPostDate = $("<div class='postDate'></div>")
							ansPostDate = ansPostDate.text(questionList[i].quePostDate)
							
							let writerInfo = $("<div class='writerInfo'></div>")
							writerInfo.append(admNickname);
							writerInfo.append(ansPostDate);
							
							let ansTitle = $("<div class='queTitle'></div>")
							ansTitle = ansTitle.text(questionList[i].queTitle)
							let ansContent = $("<div class='queContent hidden'></div>")
							ansContent = ansContent.text(questionList[i].queContent)
							
							let mdfyLink = $("<button id='mdfyBtn' class='modalBtn'>문의수정</button>")								
							ansContent.append(mdfyLink)
							$("#mdfyBtn").click(function() {
								$("#questionModifyModal").css("display","flex")
							});
							
							let deleteLink = $("<a href='/question/delete/"+questionList[i].questionId+"''>문의 삭제</a>")
							ansContent.append(deleteLink)
							
							let answerArea = $("<div class='answerArea'></div>")
							answerArea.append(writerInfo)
							answerArea.append(ansTitle)
							answerArea.append(ansContent)
							$("#"+ upperQuestionId).after(answerArea)
						}
				}
				
				$(".queTitle").click(function() {
					if( $(this).next().is(":visible") ){
						$(this).next().slideUp();
					}else{
						$(this).next().slideDown();
					}
				})
			}
			else {
				$(".questions").append("<p>문의 내역이 없습니다.</p>");
			} 
		})
		$("#queWriteAnchor").click(function() {
			if (id == '') {
				Swal.fire({
					title: '로그인을 먼저 진행해주세요',
					text: "확인을 누르시면 로그인 페이지로 이동합니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					
					}).then((result) => {
					if (result.isConfirmed) {
						location.href = "/member/memberlogin"	
					}
				})
			}
			else {
				if( $(".queWriteArea").is(":visible") ){
					$(".queWriteArea").slideUp();
				}else{
					$(".queWriteArea").slideDown();
				}
				
			}
		})
		
		//wishlist
		if (id != ''){
			$.get("/shop/wish", function(response) {
				let wishlistProduct = response.wishlistProduct
				if (wishlistProduct.includes(productId)) {
					$("#wishlistBtn").attr("class", "btn_fill")
				}
				else {
					$("#wishlistBtn").attr("class", "btn_empty")
				}
			})
		}
		$("#wishlistBtn").click(function(){
			if (id == '') {
				Swal.fire({
					title: '로그인을 먼저 진행해주세요',
					text: "확인을 누르시면 로그인 페이지로 이동합니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					
					}).then((result) => {
					if (result.isConfirmed) {
						location.href = "/member/memberlogin"	
					}
				})
			}
			else {
				$.post("/shop/wish/add", productId, function(response) {
					if (response.action == "add") {
						Swal.fire({
							icon: 'success',
							title: '찜 목록에 추가되었습니다',
						});
						setTimeout(function() {
							window.location.reload()
						}, 1800);
					} else {
						Swal.fire({
							icon: 'success',
							title: '찜 목록에서 삭제되었습니다',
						});
						setTimeout(function() {
							window.location.reload()
						}, 1800);
					}
				})
			}
		})
		
		//cart
		$("#cartBtn").click(function() {
			if (id == '') {
				Swal.fire({
					title: '로그인을 먼저 진행해주세요',
					text: "확인을 누르시면 로그인 페이지로 이동합니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					
					}).then((result) => {
					if (result.isConfirmed) {
						location.href = "/member/memberlogin"	
					}
				})
			}
			else {
				let data = {choiceId : $('select[name=productChoice]').val()}
				$.post("/shop/cart/add", data, function(response) {
					if (response.result) {
						window.location.replace("/shop/cart/${member.memberId}");
					}
					else {
						if (response.action == "exist") {
							Swal.fire({
								title: '기존 장바구니에 상품이 있습니다',
								text: "확인을 누르시면 장바구니로 이동합니다",
								icon: 'warning',
								showCancelButton: true,
								confirmButtonColor: '#FB8F67',
								cancelButtonColor: '#D4D4D4',
								confirmButtonText: '확인',
								cancelButtonText: '취소',
								//reverseButtons: true, // 버튼 순서 거꾸로
								}).then((result) => {
								if (result.isConfirmed) {
									window.location.replace("/shop/cart/${member.memberId}");
								}
							})
						}
						else {
							alert("실패");
						}
					}
				})
			}	
			})
			
			// 바로구매
		$("#buyBtn").click(function(){
			if (id == '') {
				Swal.fire({
					title: '로그인을 먼저 진행해주세요',
					text: "확인을 누르시면 로그인 페이지로 이동합니다",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#FB8F67',
					cancelButtonColor: '#D4D4D4',
					confirmButtonText: '확인',
					cancelButtonText: '취소',
					//reverseButtons: true, // 버튼 순서 거꾸로
					}).then((result) => {
					if (result.isConfirmed) {
						location.href = "/member/memberlogin"	
					}
				})
			}
			else {
				let choiceId = $('select[name=productChoice]').val()
				let orderData =  $("<input type='hidden' name='choiceId' readonly/>'")
				orderData.val(choiceId)
				$("#directOrder").append(orderData)
				$("#directOrder").submit();
			}
		})
		

		//연관 레시피
		$.get("/api/relatedRecipe/" +productId, function(response) {
			if (response.result.length == 0) {
				$.get("/api/recomm/ingredient", function(response) {
					$("#ingName").text("이런 요리도 시도해보세요!")
					let articleByIngName = [];
					for (var i=0; i < 5; i++) {
						let recipeCard = $("<div class='rcpCard'></div>")
						let recipeTitle = $("<div class='recipeTitle'></div>")
						let linkDOM = $("<a href='/recipe/"+response.articleList[i].articleId+"' />")
						let imageDOM = $("<img id='"+response.articleList[i].articleId+"' class='rcpImg' src='"+response.articleList[i].recipeVO.attImgSmall+"'/>")
						let titleDOM = $("<p>"+response.articleList[i].recipeVO.recipeName+"</p>")
						linkDOM.append(imageDOM)
						recipeCard.append(linkDOM)
						recipeCard.append(titleDOM)
						$("#relatedRecipeList").append(recipeCard)
					}
					$("#nameTop").append(response.ingName)
				})	

			}
			else {
				let relList = response.result
				$("#ingName").text(response.result[0].artIngVOList[0].ingredientVO.ingredientName +"로 만드는 레시피")
				for (var i in relList) {
					var relArticleId = relList[i].articleId
					var relImage = relList[i].attImgSmall
					var relTitle = relList[i].recipeName
					var rcpCard = $("<div class='rcpCard'></<div>")
					rcpCard.append("<a href='/recipe/"+relArticleId+"'><img src='"+relImage+"' /></a>")
					rcpCard.append("<p>"+relTitle+"</p>")
					$("#relatedRecipeList").append(rcpCard)
				}
			}
		})
	})
</script>
<style type="text/css">
	#buyBtn, #cartBtn, #wishlistBtn {
		cursor: pointer;
	}
	#avgRating {
		display: flex;
		flex-direction: row;
	}
	#star-back {
		display: flex;
		position:absolute;
		width: 90px;
		overflow:hidden;
		color:#cdcdcd;
	}
	#star-rate {
		display: flex;
		position:absolute;
		width: 90px;
		overflow:hidden;
		white-space:nowrap;
	}
	#number-rate {
		position: relative;
		left: 100px;
	}
	#revWriteAnchor, #queWriteAnchor {
		background-color: inherit;
		border: none;
		cursor: pointer;
	}
	.reviews {
		border-top: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
	}
	.reviews .writerInfo {
		display: flex;
		border-top: 1px solid #ccc;
		margin-bottom: 10px;
		justify-content: space-between;
		align-items: baseline;
	}
	.leftDiv, .rightDiv {
		display: flex;
		align-items: baseline;
	}
	.rightDiv {
		width: 160px;
		justify-content: space-evenly;
	}
	.reviews .writerInfo .memNickname, .revPostDate, .rating {
		display: inline;
		margin: 10px 10px 0px 10px;
		padding: 0;
	}
	.reviews .writerInfo .memNickname {
		display: inline-block;
		color: #c46a33;
		font-size: 15px;
		padding-bottom: 5px;
	}
	.reviews .writerInfo .revPostDate {
		display: inline-block;
		color: #777;
		font-size: 12px;
	}
	.reviews .revContent {
		font-size: 16px;
		margin: 0px 10px 10px 10px;
		padding-left: 10px;
	}
	.questions {
		border-top: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
	}
	.questions .writerInfo {
		display: flex;
		justify-content: space-between;
		align-items: baseline;
		border-top: 1px solid #ccc;
		margin-bottom: 10px;
	}
	.questions .writerInfo .memNickname, .postDate {
		display: inline;
		margin: 10px 10px 0px 10px;
		padding: 0;
	}
	.questions .writerInfo .memNickname {
		display: inline-block;
		color: #c46a33;
		font-size: 15px;
		padding-bottom: 5px;
	}
	.questions .writerInfo .postDate {
		display: inline-block;
		color: #777;
		font-size: 12px;
	}
	.questions .queTitle {
		cursor:pointer;
		font-size: 16px;
		margin: 0px 10px 10px 10px;
		padding-left: 10px;
	}
	.questions .queContent {
		font-size: 16px;
		margin: 0px 10px 10px 10px;
		padding-left: 10px;
	}
	.questions .answerArea {
		background-color: #EEE;
		padding-bottom: 5px;	
	}
	.questions .answerArea > div {
		padding-left: 30px;
	}
	.questions .hidden {
		display: none;
		font-size: 16px;
		margin: 0px 10px 10px 10px;
		padding-left: 10px;
	}
	div.grid > div.replies {
		display: grid;
		grid-column: 1 / 3;
	}

	div.replies > .write-reply {
		margin-top: 10px;
		display: grid;
		grid-template-columns: 1fr 80px;
		grid-template-rows: 1fr 40px;
		row-gap: 10px;
		column-gap: 10px;
		align-items: center;
	}

	div.replies > .write-reply > textarea {
		height: 150px;
		display: grid;
		grid-column: 1 / 3;
	}

	div.replies > .reply-items {
		display: grid;
		grid-template-columns: 1fr;
		grid-template-rows: 1fr;
		row-gap: 10px;
	}

	/* review write */
	.reviewWriteArea, .queWriteArea {
		grid-column: 1 / 3;
		display: none;
	}
	
	/* The Close Button */
	.close {
	  color: #aaaaaa;
	  float: right;
	  font-size: 28px;
	  font-weight: bold;
	}
	
	.close:hover,
	.close:focus {
	  color: #000;
	  text-decoration: none;
	  cursor: pointer;
	}
	#reviewGrid {
		display: grid;
		grid-template-columns: 1fr 80px ;
		grid-template-rows: 40px 40px 1fr 1fr;
		row-gap: 10px;
	}
	#reviewWriter{
		grid-column: 1/2;
        grid-row: 1/2;
	}
	#reviewRating{
        grid-column: 1/2;
        grid-row: 2/3;
	}
	#textArea {
        grid-column: 1/2;
        grid-row: 3/4;
	}
	#textArea > #revContent {
		width: 90%;
	}
	#submitBtn {
        grid-column: 2/3;
        grid-row: 3/4;
    }
	div.queWrite {
		display: flex;
		flex-direction: column;
		width: 90%;
	}
	div.queWrite > div.btn-group {
		display: flex;
	}
	div.right-align {
		display: flex;
		justify-content: flex-end;
	}
	.queWrite label {
		padding-top: 10px;
	}
	.queWrite button, .queWrite input, .queWrite textarea {
		padding: 10px;
	}
	div.errors {
		background-color:#FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	div.errors:last-child {
		margin-bottom: 15px;
	}
	.relatedRecipe {
		grid-column: 1/3;
	}
	.relatedRecipeList {
    display: flex;
    flex-direction: row;
    align-items: center;
}
.rcpCard {
    display: inline-block;
    width: 120px;
    height: 200px;
    margin-left: 10px;
    margin-right: 10px;
}
.rcpCard > a> img {
    width: 120px;
	height: 120px;
	object-fit:cover
}
    </style>
   	<jsp:include page="../layout/header2.jsp"></jsp:include>
		<div id="productGrid">
			<div id="titleImage">
			    <img src="/shop/product/titleimg/${productVO.prdTitleImgOrigin}" alt="">
			</div>
			<div id="productInfo">
			    <ul>
			        <li>${productVO.prdName }</li>
			        <li>${productVO.prdOrigin }</li>
			        <li>${selNickname }</li>
			        <li id="avgRating">
			        	<div>
				        	<div id="star-back">
				        		<i class='fa-regular fa-star'></i>
				        		<i class='fa-regular fa-star'></i>
				        		<i class='fa-regular fa-star'></i>
				        		<i class='fa-regular fa-star'></i>
				        		<i class='fa-regular fa-star'></i>
				        	</div>
				        	<div id="star-rate">
				        		<i class='fa-solid fa-star'></i>
				        		<i class='fa-solid fa-star'></i>
				        		<i class='fa-solid fa-star'></i>
				        		<i class='fa-solid fa-star'></i>
				        		<i class='fa-solid fa-star'></i>
				        	</div>
			        	</div>
			        	<span id="number-rate"></span>
			        </li>
			        <li><select class="choiceSelect" name="productChoice"></select></li>
			        <li id="priceArea">
			        	<div>총 상품 금액</div>
			        	<div id="totalPrice"></div>
			        </li>
			        <li><form id="directOrder" action="/shop/order/direct" name="choiceId" method="post"></form><button class="btn_fill" id="buyBtn">구매하기</button></li>
			        <li><button class="btn_empty" id="cartBtn">장바구니</button>
			        <button class="btn_empty" id="wishlistBtn">찜하기</button></li>
			    </ul>
			</div>
			<div id="mainContent">
				<img src="/shop/product/contentimg/${productVO.prdContentImgOrigin}" alt="">
			<div class="relatedRecipe">
				<h3><span id="ingName"></span></h3>
				<div id="relatedRecipeList"></div>
			</div>
			</div>
			<div id="revQueArea">
				<button class="viewSomething viewActive" id="viewReview"  width="100%">리뷰</button>	
				<button class="viewSomething viewInactive" id="viewQuestion" width="100%">문의</button>	
					<div id="writeAnchor">
						<button id="revWriteAnchor">리뷰 작성하기</button>
						<button id="queWriteAnchor">문의 작성하기</button>
					</div>	
					<div class="reviews">
					</div>
					<div class="questions">
					</div>
					<div class="reviewWriteArea">
						<span class="close">&times;</span>
						<form:form modelAttribute="reviewVO" method="post" enctype="multipart/form-data" action="/review/write">
						   <div>
							   <form:errors path="revContent" element="div" cssClass="errors"  />
						   </div>	
						   <div id="reviewGrid">
							   <div id="reviewWriter">
								   <h3>리뷰 작성</h3>
								   <input type="hidden" name="reviewType" value=2 />
								   <input type="hidden" name="productId" value="${productId }"/>
									<input type="hidden" name="memberId" value="${member.memberId }"/>
							   </div>
							   <div id="reviewRating">
								   <label for="rating">별점</label>
								   <input type="radio" name="rating" value=1 />1
								   <input type="radio" name="rating" value=2 />2
								   <input type="radio" name="rating" value=3 />3
								   <input type="radio" name="rating" value=4 />4
								   <input type="radio" name="rating" value=5 checked="checked" />5
							   </div>
							   <div id="textArea">
								   <textarea id="revContent" name="revContent">${reviewVO.revContent}</textarea>
							   </div>
							   <div id="submitBtn">
								   <input class="btn_fill" style="width: 80px; height: 40px; cursor: pointer;" type="submit" value="저장" />
							   </div>
						   </div>
					   </form:form>
				   </div>
				<div class="queWriteArea">
					<span class="close">&times;</span>
					<h3>문의 작성</h3>
					<form:form modelAttribute="questionVO" method="post" enctype="multipart/form-data" action="/question/write">
						<input type="hidden" name="productId" value="${productVO.productId }"/>
						<input type="hidden" name="memberId" value="${member.memberId}"/>
					<div>
						<form:errors path="queTitle" element="div" cssClass="errors"  />
						<form:errors path="queContent" element="div" cssClass="errors"  />
					</div>
					<div class="queWrite">
						<span>상품명: ${productVO.prdName }</span>
						<label for="queTitleInput">문의 제목</label>
						<input type="text" name="queTitle" id="queTitleInput" value="${questionVO.queTitle}" />
						<label for="queContentInput">문의 내용</label>
						<textarea id="queContentInput" name="queContent" style="height: 100%;">${questionVO.queContent}</textarea>
					</div>											
					<div class="btn-group">
						<input type="hidden" name="upperQuestionId" value="${upperQuestionId}"/>
						<div class="right-align">
							<input class="btn_fill" style="width: 80px; height: 40px; cursor: pointer;" type="submit" value="저장" />
						</div>
					</div>
				</form:form>																
				</div>
		   	</div>
		</div>

	<jsp:include page="../layout/footer.jsp"></jsp:include>
