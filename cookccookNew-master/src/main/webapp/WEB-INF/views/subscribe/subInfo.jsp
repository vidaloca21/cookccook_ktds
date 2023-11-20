<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>구독 소개</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style type="text/css">
	/*모달 css*/
	#modalWrap {
		display: none;
		position: fixed; /* Stay in place */
		z-index: 1; /* Sit on top */
		padding-top: 100px; /* Location of the box */
		left: 0;
		top: 0;
		width: 100%; /* Full width */
		height: 100%; /* Full height */
		overflow: auto; /* Enable scroll if needed */
		background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
	}
	#modalBody {
		width: 500px;
		height: 300px;
		padding: 30px 30px;
		margin: 0 auto;
		border: 1px solid #777;
		background-color: #fff;
		display: flex;
		flex-direction: column;
		gap: 20px;
	}

	/*자동완성 css*/
	.ui-autocomplete {
		z-index:2147483647;
		overflow-x: hidden;
		height: 100px;
		width: 150px;
		background-color: #fff;
		border: 1px solid #777;
		list-style: none;
		padding-left:0px;
	}
	.ui-menu-item div.ui-state-hover,
	.ui-menu-item div.ui-state-active {
		color: #ffffff;
		background-color: #f6B664;
	}
	.ui-helper-hidden-accessible {
		display: none;
	}

	.subMenu {
		background-color: #FB8F67;
		padding: 15px;
	}

	.subInfo {
		background-color: #FFF1E0;
		width: 450px;
		/* list-style: none; */
		padding: 10px 5px;
		margin: 20px;
		text-align: center;
	}

	#choice-form {
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
	}
	
	.fourWeek {
		display: none;
	}

	.right_text{
		text-align: right;
		padding-right: 10px;
	}

	h1{
		color: #FFF1E0;
		margin: 0;
	}

	.ingredient{
		margin: 3px;
	}

	.inline{
		display: inline;
	}

	.btn_empty {
		display: inline !important;
	}

	.ingredientList {
		width: 270px;
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
	#revQueArea {
	grid-row: 3/4;
	grid-column: 1/3;
	display: grid;
	grid-template-columns: 1fr 1fr;
	grid-template-rows: 60px 30px 1fr;
}
#viewSomething {
	background-color: inherit;
	border: 0;
	font-size: 16px;
}
#viewReview {
	grid-column: 1/2;
	cursor: pointer;
}
#viewQuestion {
	grid-column: 2/3;
	cursor: pointer;
}
.viewActive {
	background-color: inherit;
	border: none;
	border-bottom: 5px solid #FF6347;
	font-size: large;
	font-weight: bold;
	color: #FF6347;
}
.viewInactive {
	background-color: inherit;
	border: none;
	font-size: large;
	color: #333;
}
#writeAnchor {
	grid-column: 1/3;
	grid-row: 2/3;
	display: flex;
	justify-content: flex-end;;
}
.reviews {
	grid-column: 1/3;
	grid-row: 3/4;
}
.questions {
	grid-column: 1/3;
	grid-row: 3/4;
}
</style>
<jsp:include page="../layout/header2.jsp"></jsp:include>
	<div class="container">
		<div id="modalWrap">
			<div id="modalBody">
				<div>
					<label for="ingredientName" class="inline font_subtitle">재료추가</label>
					<div class="inline">
						<input id="ingredientName" type="text" name="ingredientName" />
					</div>
					<input type="button" value="추가" id="addIngredient" class="inline btnNormal"/>
				</div>
				<div class="preview">
					<label class="font_subtitle">적용 예정 상품</label>
					<ul class='ingredientList'></ul>
				</div>
				<div class="btn_group right_text">
					<input type='button' id="closeBtn" value="취소" class="btn_fill_s"/>
				</div>
			</div>
		</div>
		<div class="subMenu">
			<h1 class="font_title">이달의 신선 식품</h1>
			<div class="right_text">
				<input id="twoWeek" type="radio" name="subPeriod" value="twoWeek" checked="checked"/>2주 배송
				<input id="fourWeek" type="radio" name="subPeriod" value="fourWeek"/>4주 배송
			</div>
			<div class="subItem">
			<form id="choice-form">
					<c:forEach items="${subListVO}" var="subChoice">
						<c:set var = "str" value = "${subChoice.subChoice}"/>
						<c:choose>
						<c:when test="${fn:contains(str, '2주')}">
							<ul class="subInfo twoWeek">
								<c:choose>
									<c:when test="${fn:contains(str, '채소')}">
										<c:if test='${role eq "ADMIN"}'>
											<button type="button" class="popupBtn btn_fill_s" data-choice="green">익월설정</button>
										</c:if>
										<p class="font_subtitle">그린 식단</p>
										<c:forEach items="${subIngGreenList}" var="subIngVO">
											<p class="ingredient font_content">${subIngVO.ingredientVO.ingredientName} ${subIngVO.subIngOpt}</p>
										</c:forEach>							
									</c:when>
									<c:otherwise>
										<c:if test='${role eq "ADMIN"}'>
											<button type="button" class="popupBtn btn_fill_s" data-choice="balance">익월설정</button>
										</c:if>
										<p class="font_subtitle">밸런스 식단</p>
										<c:forEach items="${subIngBalanceList}" var="subIngVO">
											<p class="ingredient font_content">${subIngVO.ingredientVO.ingredientName} ${subIngVO.subIngOpt}</p>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								<p class="right_text">${subChoice.subPrice}원/회차</p>
								<input type="radio" name="subscribeId" value="${subChoice.subscribeId}" />
							</ul>
						</c:when>
						<c:otherwise>
							<ul class="subInfo fourWeek">
								<c:choose>
									<c:when test="${fn:contains(str, '채소')}">
										<c:if test='${role eq "ADMIN"}'>
											<button type="button" class="popupBtn btn_fill_s" data-choice="green">익월설정</button>
										</c:if>
										<p class="font_subtitle">그린 식단</p>
										<c:forEach items="${subIngGreenList}" var="subIngVO">
											<p class="ingredient font_content">${subIngVO.ingredientVO.ingredientName} ${subIngVO.subIngOpt}</p>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:if test='${role eq "ADMIN"}'>
											<button type="button" class="popupBtn btn_fill_s" data-choice="balance">익월설정</button>
										</c:if>
										<p class="font_subtitle">밸런스 식단</p>
										<c:forEach items="${subIngBalanceList}" var="subIngVO">
											<p class="ingredient font_content">${subIngVO.ingredientVO.ingredientName} ${subIngVO.subIngOpt}</p>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								<p class="right_text">${subChoice.subPrice}원/회차</p>
								<input type="radio" name="subscribeId" value="${subChoice.subscribeId}" />
							</ul>
						</c:otherwise>
						</c:choose>
					</c:forEach>
			</form>
			</div>
			<div class="right_text"><input type='button' onclick="submitfunc()" value="다음" class="btn_empty"></div>
		</div>  
		<!-- 리뷰 -->
		<div class="subscribeReviews">
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
								   <input type="hidden" name="reviewType" value=1 />
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
								   <input type="submit" class="btn_fill" style="width: 80px; height: 40px; cursor: pointer;" value="저장" />
							   </div>
						   </div>
					   </form:form>
				   </div>


				<div class="queWriteArea">
					<span class="close">&times;</span>
					<h3>문의 작성</h3>
					<form:form modelAttribute="questionVO" method="post" enctype="multipart/form-data" action="/question/write">
						<input type="hidden" name="memberId" value="${member.memberId}"/>
					<div>
						<form:errors path="queTitle" element="div" cssClass="errors"  />
						<form:errors path="queContent" element="div" cssClass="errors"  />
					</div>
					<div class="queWrite">
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
		</div>
    <jsp:include page="../layout/footer.jsp"></jsp:include>
<script type="text/javascript">
	$().ready(function() {
		//구독메뉴 보여주기(채소/육류)
		$('input[name=subPeriod]').on('click', function(){
			var period = $('input[name=subPeriod]:checked').val();
			if(period == "twoWeek"){
				$(".twoWeek").css('display','block');
			    $(".fourWeek").css('display','none');
			}else{
				$(".twoWeek").css('display','none');
			    $(".fourWeek").css('display','block');
			}
		});

		//팝업창(익월설정) 열고 닫기
		$(".popupBtn").on('click', function(){
			$("#modalWrap").css('display','block');
		});
		$("#closeBtn").on('click', function(){
			$("#modalWrap").css('display','none');
		});

		//익월설정 데이터가 있으면 보여주기
		$('.popupBtn').on("click", function () {
			$("#submitBtn").remove();
			$("#updateBtn").remove();
			var startSetting = $(this).attr("data-choice");
			if (startSetting == "green") {
				$("#modalBody").attr("data-choice", 'green');
				$(".ingredientList > li").remove();
				$.ajax({
					url: "/subscribe/nextsubinfo/채소",
					method: "get",
					success: function(response) {
						var subIngList = response.subIngList; 
						for (var i in subIngList) {
							var ingredientList = $(".ingredientList")

							var item = $("<li class='right_text'></li>")

							var label = $("<label></label>")
							label.append(subIngList[i].ingredientVO.ingredientName)

							var input = $("<input type='text' name='subIngOpt'/>")
							input.val(subIngList[i].subIngOpt)

							item.append(label)
							item.append(input)
							item.append(`<input type='button' class="removeIngredient btnNormal" value="삭제" />`)

							ingredientList.append(item)
						}
						var modal = $(".btn_group")
						modal.append('<input type="button" id="updateBtn" onclick="updateSubIng()" value="수정" class="btn_empty_s"/>')
					}
				}).fail(function () {
					var modal = $(".btn_group")
					modal.append('<input type="button" id="submitBtn" onclick="createSubscribe()" value="적용" class="btn_empty_s"/>')

				})
			} else {
				$("#modalBody").attr("data-choice", 'balance');
				$(".ingredientList > li").remove();
				$.ajax({
					url: "/subscribe/nextsubinfo/육류",
					method: "get",
					success: function(response) {
						var subIngList = response.subIngList; 
						for (var i in subIngList) {
							var ingredientList = $(".ingredientList")

							var item = $("<li class='right_text'></li>")

							var label = $("<label></label>")
							label.append(subIngList[i].ingredientVO.ingredientName)

							var input = $("<input type='text' name='subIngOpt'/>")
							input.val(subIngList[i].subIngOpt)

							item.append(label)
							item.append(input)
							item.append(`<input type='button' class="removeIngredient btnNormal" value="삭제" />`)

							ingredientList.append(item)
						}
						var modal = $(".btn_group")
						modal.append('<input type="button" id="updateBtn" onclick="updateSubIng()" value="수정" class="btn_empty_s"/>')
					}
				}).fail(function () {
					var modal = $(".btn_group")
					modal.append('<input type="button" id="submitBtn" onclick="createSubscribe()" value="적용" class="btn_empty_s"/>')

				})
			}
		});

		//익월설정 > 적용예정상품 추가/삭제
		$("#addIngredient").click(function() {
			var ingredientList = $(".ingredientList")

			var item = $("<li class='right_text'></li>")

			var ingredientName = $("#ingredientName").val();
			if(ingredientName != null && ingredientName != "") {
				var label = $("<label></label>")
				label.append(ingredientName)

				item.append(label)
				item.append(`<input type='text' name='subIngOpt'/>`)
				item.append(`<input type='button' class="removeIngredient btnNormal" value="삭제" />`)

				ingredientList.append(item)
			}else {
				alert("추가할 재료명을 입력하세요.")
			}
		})
		$(document).on('click', '.removeIngredient', function() {
			$(this).parent().remove();
		})

		//익월설정 > 재료검색(자동완성기능)
		var ingredients = []
		$.ajax({
			url: "/subscribe/Ingredient",
			method: "get",
			success: function(response) {
				let ingredientList = response.ingredientList; 
				for (var i in ingredientList) {
					ingredients.push(ingredientList[i].ingredientName);
				}
			}
		})
		$('#ingredientName').autocomplete({
			source: ingredients,
			focus: function (event, ui) {
				return false;
			},
			select: function (event, ui) {},
			minLength: 1,
			delay: 100,
			autoFocus: true,
		});
		
		//구독리뷰 불러오기
		$.ajax({
			url: "/review/subscribe",
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
							if("${member.memberId}" == reviewList[i].memberVO.memberId) {
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
		                    		location.reload();
		                    	}
							})
	
		                    $(".reviews").append(writerInfo)
		                    $(".reviews").append(revContent)
	                	}
	            	}else {
	            		 $(".reviews").append("<p>아직 작성된 리뷰가 없습니다!</p>");
	            	} 
	
	            }
				// for (var i in response) {
				// 	let reviewMember = $("<li></li>")
				// 	reviewMember = reviewMember.text(response[i].memberVO.memberId)
				// 	let reviewContent = $("<li></li>")
				// 	reviewContent = reviewContent.text(response[i].revContent)
				// 	let reviewRating = $("<li></li>")
				// 	reviewRating = reviewRating.text(response[i].rating)
				// 	let reviewPostDate = $("<li></li>")
				// 	reviewPostDate = reviewPostDate.text(response[i].postDate)
					
				// 	let reviewArea = $("<ul class='reviews'></ul>")
				// 	reviewArea.append(reviewMember);
				// 	reviewArea.append(reviewContent);
				// 	reviewArea.append(reviewRating);
				// 	reviewArea.append(reviewPostDate);
				// 	console.log(reviewArea);
					
				// 	$(".subscribeReviews").append(reviewArea)
				// }
		})
		$.get("/question/subscribe", function(response) {
	    		let questionList = response.questionList
	    		if (response.questionCnt > 0) {
	                for (var i in questionList) {
	                	if(questionList[i].productId == null) {
	                		let upperQuestionId = questionList[i].upperQuestionId
	                		if(upperQuestionId == null) {
			                    let memNickname = $("<div class='memNickname'></div>")
			                    memNickname = memNickname.text(questionList[i].memberVO.memNickname)
			                    let quePostDate = $("<div class='postDate'></div>")
			                    quePostDate = quePostDate.text(questionList[i].quePostDate)

			                    let writerInfo = $("<div class='writerInfo'></div>")
			                    writerInfo.append(memNickname);
			                    writerInfo.append(quePostDate);
			                    
			                    let queTitle = $("<div class='queTitle'></div>")
			                    queTitle = queTitle.text(questionList[i].queTitle)
			                    let queContent = $("<div class='queContent hidden'></div>")
			                    queContent = queContent.text(questionList[i].queContent)
			                    
								let mdfyLink = $("<a href='/question/modify?questionId="+questionList[i].questionId+"'>문의 수정</a>")								
								queContent.append(mdfyLink)
								
								let deleteLink = $("<a href=''>문의 삭제</a>")
								queContent.append(deleteLink)
								deleteLink.click(function() {
									if(confirm("제출하시겠습니까?")){
										$.get("/question/delete/"+questionList[i].questionId+"", function(response) {
											alert("성공!")
										})
			                    	}
								})

			                    let questionArea = $("<div class='questionArea'></div>")
			                    questionArea.append(writerInfo)
			                    questionArea.append(queTitle)
			                  	questionArea.append(queContent)
			                  	questionArea.attr("id", questionList[i].questionId)
			                    $(".questions").append(questionArea)
	                		}
	                		else {
	                			let selNickname = $("<div class='memNickname'></div>")
	                			selNickname = selNickname.text(questionList[i].memberVO.memNickname)
	                			let ansPostDate = $("<div class='postDate'></div>")
			                    ansPostDate = ansPostDate.text(questionList[i].quePostDate)
			                   
			                    let writerInfo = $("<div class='writerInfo'></div>")
			                    writerInfo.append(selNickname);
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
			                    
								let deleteLink = $("<a href='question/delete/"+questionList[i].questionId+"''>문의 삭제</a>")
								ansContent.append(deleteLink)
								
			                    let answerArea = $("<div class='answerArea'></div>")
			                    answerArea.append(writerInfo)
			                    answerArea.append(ansTitle)
			                  	answerArea.append(ansContent)
			                    $("#"+ upperQuestionId).after(answerArea)
	                		}
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

		$("#revWriteAnchor").click(function() {
			if ("${member}" == '') {
				if(confirm("로그인을 먼저 진행해주세요")){
					location.href="/member/login"
				}
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
		$("#queWriteAnchor").click(function() {
			if ("${member}" == '') {
				if(confirm("로그인을 먼저 진행해주세요")){
					location.href="/member/login";
				}
			}
			else {
				if( $(".queWriteArea").is(":visible") ){
					$(".queWriteArea").slideUp();
				}else{
					$(".queWriteArea").slideDown();
				}
				
			}
		})

	})
	//익월설정(SUBSCRIBE&SUB_ING) INSERT
	function createSubscribe() {
		var subChoice = $("#modalBody").attr("data-choice");
		var url = "/subscribe/createsub"
		$.post(url, {subChoice : subChoice}, function(response) {
			var result = response.result
			if(result){
				var url2 = ""
				if(subChoice == "green"){
					url2 = "/subscribe/createsubing/채소"
				}else{
					url2 = "/subscribe/createsubing/육류"
				}
				var productItems = []
				$(".ingredientList li").each(function () {
					var ingredientName = $(this).find("label").text();
					var subIngOpt = $(this).find("input[name='subIngOpt']").val();
					var item = {
						ingredientName: ingredientName,
						subIngOpt: subIngOpt
					};
					productItems.push(item);
				});
				var body = JSON.stringify(productItems);
				$.ajax({
					url: url2,
					method: "post",
					headers: {
						"Content-Type": "application/json"
					},
					data: body,
					success: function(response) {
						alert("적용이 완료되었습니다.")
						$("#modalWrap").css('display','none');
					}
				})
			}
		})
	}

	//익월설정(SUBSCRIBE&SUB_ING) UPDATE
	function updateSubIng() {
		var subChoice = $("#modalBody").attr("data-choice");
		var url = "/subscribe/deletesubinfo"
		$.post(url, {subChoice : subChoice}, function(response) {
			var result = response.result
			if(result){
				var url2 = ""
				if(subChoice == "green"){
					url2 = "/subscribe/createsubing/채소"
				}else{
					url2 = "/subscribe/createsubing/육류"
				}
				var productItems = []
				$(".ingredientList li").each(function () {
					var ingredientName = $(this).find("label").text();
					var subIngOpt = $(this).find("input[name='subIngOpt']").val();
					var item = {
						ingredientName: ingredientName,
						subIngOpt: subIngOpt
					};
					productItems.push(item);
				});
				var body = JSON.stringify(productItems);
				$.ajax({
					url: url2,
					method: "post",
					headers: {
						"Content-Type": "application/json"
					},
					data: body,
					success: function(response) {
						alert("수정이 완료되었습니다.")
						$("#modalWrap").css('display','none');
					}
				})
			}
		})
	}
	
	//MEMBER의 구독여부 확인 후 '/subscribe/choice'로 이동
	function submitfunc() {
		if("${member}" != null && "${member}" != ""){
			let choice = $("input[name=subscribeId]:checked").val()
			if(choice != null){
				var url = "/subscribe/info/subscribable"
				$.get(url, function(response){
						var result = response.result
						if(result) {
							$("#choice-form").attr({
								"method" : "get",
								"action" : "/subscribe/choice"
							}).submit();
						} else {
							if(confirm("이미 구독중입니다. 구독정보로 이동할까요?")){
								location.href="/subscribe/mysubs"
							}
							return false;
						}
				})
			}else{
				alert("식단을 선택해 주세요.")
			}
		}else {
			if(confirm("로그인이 필요한 서비스입니다. 로그인 페이지로 이동할까요?")){
				location.href="/member/memberlogin";
			}
		}
	}

</script>
