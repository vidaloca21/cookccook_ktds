<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품문의</title>
    <script src="/js/lib/jquery-3.7.1.js"></script>
	<script type="text/javascript">
	    $().ready(function() {
	    	$.get("/question/${productVO.productId}", function(response) {
	    		let questionList = response.questionList
	    		if (response.questionCnt > 0) {
	                for (var i in questionList) {
	                	if(questionList[i].productId == "${productVO.productId}") {
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
								
								let deleteLink = $("<a href='#'>문의 삭제</a>")
								queContent.append(deleteLink)
								deleteLink.click(function() {
									if(confirm("제출하시겠습니까?")){
										location.href = "question/delete/"+questionList[i].questionId+"";
			                    	}else{
			                    		location.href = "/shop/product/${productId}";
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
			// 화면 이동 방지
	    	const submit_form = document.querySelector("#submitForm")
			const submit_btn = document.querySelector('#submitBtn')
			submit_btn.addEventListener('click', function () {
			    submit_form.submit()
			})
	    	
	    	// 모달팝업
			var modal = document.getElementById("questionModal");
			var btn = document.getElementById("submitBtn");
			var span = document.getElementsByClassName("close")[0];
			btn.onclick = function() {
			  modal.style.display = "block";
			}
			span.onclick = function() {
			  modal.style.display = "none";
			}
			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			  if (event.target == modal) {
			    modal.style.display = "none";
			  }
			}
	    })
    </script>
    <style type='text/css'>
    .questions {
		 font-family: 'NanumGothic';
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

	pre.content {
		margin: 0px;
	}
	
	#questionModifyModal {
		display: none;
	}
	/* The Modal (background) */
	.modal {
	  display: none; /* Hidden by default */
	  position: fixed; /* Stay in place */
	  z-index: 1; /* Sit on top */
	  padding-top: 100px; /* Location of the box */
	  left: 0;
	  top: 0;
	  width: 100%; /* Full width */
	  height: 100%; /* Full height */
	  overflow: auto; /* Enable scroll if needed */
	  background-color: rgb(0,0,0); /* Fallback color */
	  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
	}
	
	/* Modal Content */
	.modal-content {
	  background-color: #fefefe;
	  margin: auto;
	  padding: 20px;
	  border: 1px solid #888;
	  width: 80%;
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
   </style>
</head>
<body>
<div class="questions">
<button onclick="document.getElementById('questionModal').style.display='block'" class="modalBtn">문의하기</button>
<jsp:include page="./questionwrite.jsp">
  	<jsp:param name="productId" value="${productVO.productId}" />
  	<jsp:param name="prdName" value="${productVO.prdName}" />
  	<jsp:param name="memberId" value="${member.memberId}" />
</jsp:include>

</div>
</body>
</html>