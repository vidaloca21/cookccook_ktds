<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<style type="text/css">
	section {
		max-height: 80vh;
		overflow: auto;
	} 

	.saleState {
		background-color: #FFF1E0;
		padding: 10px 15px;
		margin: 10px 0;
		display: flex;
	    justify-content: space-between;
	}

	p, label {
		padding-right: 10px;
	}

	.refreshBtn {
		background-color: rgba(0,0,0,0);
		padding: 0;
		border: none;
	}

	.refreshBtn > img{
		width: 20px;
		height: 20px;
	}

	.search {
		background-color: #FFF1E0;
		height: 120px;
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-template-rows: 1fr 1fr 1fr;
		align-items: center;
	    justify-items: stretch;
		padding: 10px 15px;
	}

	.search_btn {
		grid-column: 2 / 3;
		text-align: right;
	}

	.btn_group{
		margin-top: 10px;
	}

	.right_text{
		text-align: right;
	}

	.inline {
		display: inline;
	}

	table {
		width: 100%;
		border-top: 1px solid #d3d3d3;
		border-collapse: collapse;
		text-align: center;
	}

	th {
		background-color: rgba(251,143,103,0.8);
		border-bottom: 1px solid #C0C0C0;
		padding: 10px;
	}

	td {
		border-bottom: 1px solid #d3d3d3;
		padding: 10px;
		font-weight: normal;
	}

	.shortcut-wrppaer {
		padding: 10px 0;
	}

	.write-btn {
		width: 80px;
		height: 30px;
		float: right;
	}

	ul.page-nav {
		margin: 0px;
		padding: 0px;
		text-align: center;
	}

	ul.page-nav > li {
		display: inline-block;
		padding: 10px;
		color: #333;
	}

	.number{
		text-decoration: underline;
	}
	
	#inputBtn {
		cursor: pointer;
		width: 150px;
		height: 40px;
	}
	.upperQuestion{
		border-top: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
	}

	.queWrite {
		padding-left: 40px;
		display: flex;
		flex-direction: column;
		width: 90%;
	}

	.queWrite label{
		display: inline;
		padding-top: 10px;
	}

	.questions .writerInfo {
		display: flex;
		justify-content: space-between;
		align-items: baseline;
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

	.lowerQuestion{
		padding-left: 20px;
	}

	.product {
		background-color: rgba(251,143,103,0.8);
	}
	
	.block {
		display: block;
	}

	.queWriteArea {
		grid-column: 1 / 3;
		display: none;
	}
	
	#queWriteAnchor {
		margin: 10px;
	}

	.btn-group {
		padding-top: 10px;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		//새로고침
		$(".refreshBtn").on('click', function(){
			location.href="/seller/question"
		})

		//question
		$(".queTitle").click(function() {
			if( $(this).next().is(":visible") ){
				$(this).next().slideUp();
			}else{
				$(this).next().slideDown();
			}
		})

		$("#revWriteAnchor").click(function() {
			if ($(".reviewWriteArea").is(":visible")) {
				$(".reviewWriteArea").slideUp();
			} else {
				$(".reviewWriteArea").slideDown();
			}
		})
		
		$(document).on('click', '#queWriteAnchor', function() {
			if( $(this).parents().children(".queWriteArea").is(":visible") ){
				$(this).parents().children(".queWriteArea").slideUp();
			}else{
				$(this).parents().children(".queWriteArea").slideDown();
			}
		})
		$(document).on('click', '.saveBtn', function() {
			$(this).parent("#queWriteArea").css("display", "none");
		})

		$(document).on('click', '.deleteLink', function() {
			var questionId = $(this).val();
			if(confirm("정말 삭제하시겠습니까?")){
				let url = "/question/delete/"+questionId
				$.get(url, function(response){
					if(response.result){
						$(this).closest("#queWriteAnchor").css("display", "block")
						location.href="/seller/question"
					}
				})
			}
		})
	})
	

</script>
<jsp:include page="../layout/header_seller.jsp"></jsp:include>
	<section>
	<h1 class="font_title">문의 조회</h1>
	<div class="saleState">
		<div>
			<p class="inline" style="font-weight: bold;">답변상태</p>
			<p class="inline">전체문의(${queStateVO.allCnt})</p>
			<p class="inline">미답변(${queStateVO.notReQueCnt})</p>
			<p class="inline">답변완료(${queStateVO.reQueCnt})</p>
		</div>
		<div><button class="inline refreshBtn"><img src="https://cdn-icons-png.flaticon.com/512/7022/7022719.png"/></button></div>	
	</div>
	<form:form modelAttribute="productSearchVO" method="post" enctype="multipart/form-data">
		<div class="search">
			<div>
				<label for="reState">답변상태</label>
				<select id="reState" name="reState">
					<option value="">전체</option>
					<option value="1">미답변</option>
					<option value="2">답변완료</option>
				</select>
			</div>
			<div>
				<label for="prd_name">상품명</label>
				<input id="prd_name" type="text" name="prdName" value='${productSearchVO.prdName}'/>
			</div>
			<div>
				<label for="product_id">상품번호</label>
				<input id="product_id" type="text" name="productId"/>
			</div>
			<div>
				<label for="start_regist_date">작성기간</label>
				<input id="start_regist_date" type="date" name="startRegistDate" value='${productSearchVO.startRegistDate}'/>
				~
				<input id="end_regist_date" type="date" name="endRegistDate" value='${productSearchVO.endRegistDate}'/>
			</div>
			<div class="search_btn">
				<input type="reset" value="초기화" class="btn_empty_s"/>
				<input type="submit" value="검색" class="btn_fill_s"/>
			</div>
		</div>
	</form:form>
	<div>
		<h2 class="font_subtitle">검색결과</h2>
		<p>총 ${questionCnt}건의 게시글이 검색되었습니다.</p>
		<div class="questions">
			<c:choose>
				<c:when test="${not empty questionList }">
					<c:forEach items="${questionList }" var="questionVO">
						<div class="questionArea" id="${questionVO.questionId}">
							<div class="product"><span>상품명: ${questionVO.productVO.prdName}</span></div>
							<div class="upperQuestion">
								<div class="writerInfo">
									<div class="memNickname">${questionVO.memberVO.memNickname}</div>
									<div class="postDate">${questionVO.quePostDate}</div>
								</div>
								<div class="queTitle">${questionVO.queTitle}</div>
								<div class="queContent hidden" style="display: block;">${questionVO.queContent}</div>
							</div>
							<c:choose>
								<c:when test="${not empty questionVO.lowerQuestionVO}">
									<div class="right_text">
										<button id="queWriteAnchor" class="btn_fill_s" style="display: none;">답변 작성하기</button>
									</div>
									<div class="queWriteArea" style="display: none;">
										<form:form modelAttribute="questionVO" method="post" enctype="multipart/form-data" action="/seller/question/write">
											<input type="hidden" name="productId" value="${questionVO.productId}"/>	
											<input type="hidden" name="memberId" value="${member.memberId}"/>
											<div>
												<form:errors path="queTitle" element="div" cssClass="errors"  />
												<form:errors path="queContent" element="div" cssClass="errors"  />
											</div>
											<p style="font-weight: bold; margin: 0; padding-left: 20px;">답변작성</p>
											<div class="queWrite">
												<label for="queTitleInput">문의 제목</label>
												<input type="text" name="queTitle" id="queTitleInput" />
												<label for="queContentInput">문의 내용</label>
												<textarea id="queContentInput" name="queContent"></textarea>
												<div class="btn-group right_text">
													<input type="hidden" name="upperQuestionId" value="${questionVO.questionId}"/>
													<input class="btnNormal saveBtn" type="submit" value="저장" />
												</div>
											</div>
										</form:form>																
									</div>
									<div class="questionArea lowerQuestion" id="${questionVO.lowerQuestionVO.questionId}">
										<div class="writerInfo">
											<div class="memNickname" value="${questionVO.lowerQuestionVO.questionId}">
												 ↳ ${member.memNickname} 
												<button class="btnNormal"><a href='/seller/question/modify?questionId=${questionVO.lowerQuestionVO.questionId}'>문의 수정</a></button>
												<button class="deleteLink btnNormal" value="${questionVO.lowerQuestionVO.questionId}">문의 삭제</button>
											</div>
											<div class="postDate">${questionVO.lowerQuestionVO.quePostDate}</div>
										</div>
										<div class="queTitle">${questionVO.lowerQuestionVO.queTitle}</div>
										<div class="queContent hidden" style="display: block;">${questionVO.lowerQuestionVO.queContent}</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="right_text">
										<button id="queWriteAnchor" class="btn_fill_s">답변 작성하기</button>
									</div>
									<div class="queWriteArea">
										<form:form modelAttribute="questionVO" method="post" enctype="multipart/form-data" action="/seller/question/write">
											<input type="hidden" name="productId" value="${questionVO.productId}"/>	
											<input type="hidden" name="memberId" value="${member.memberId}"/>
											<div>
												<form:errors path="queTitle" element="div" cssClass="errors"  />
												<form:errors path="queContent" element="div" cssClass="errors"  />
											</div>
											<p style="font-weight: bold; margin: 0; padding-left: 20px;">답변작성</p>
											<div class="queWrite">
												<label for="queTitleInput">문의 제목</label>
												<input type="text" name="queTitle" id="queTitleInput" />
												<label for="queContentInput">문의 내용</label>
												<textarea id="queContentInput" name="queContent"></textarea>
												<div class="btn-group right_text">
													<input type="hidden" name="upperQuestionId" value="${questionVO.questionId}"/>
													<input class="btnNormal saveBtn" type="submit" value="저장" />
												</div>
											</div>											
										</form:form>																
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div>문의 내역이 없습니다.</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</section>
<jsp:include page="../layout/footer.jsp"></jsp:include>