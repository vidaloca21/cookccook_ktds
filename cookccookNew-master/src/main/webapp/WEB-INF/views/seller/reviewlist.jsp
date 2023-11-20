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

	span {
		vertical-align: baseline;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		//새로고침
		$(".refreshBtn").on('click', function(){
			location.href="/seller/review"
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

		$(".1").html("&#9733; &#9734; &#9734; &#9734; &#9734;");
		$(".2").html("&#9733; &#9733; &#9734; &#9734; &#9734;");
		$(".3").html("&#9733; &#9733; &#9733; &#9734; &#9734;");
		$(".4").html("&#9733; &#9733; &#9733; &#9733; &#9734;");
		$(".5").html("&#9733; &#9733; &#9733; &#9733; &#9733;"); 
	})
</script>
<jsp:include page="../layout/header_seller.jsp"></jsp:include>
	<section>
	<h1 class="font_title">리뷰 조회</h1>
	<div class="saleState">
		<div>
			<p class="inline" style="font-weight: bold;">평점</p>
			<p class="inline">전체평점(${ratingStateVO.ratingAvg}점/${ratingStateVO.allCnt})</p>
			<p class="inline">★1(${ratingStateVO.oneCnt})</p>
			<p class="inline">★2(${ratingStateVO.twoCnt})</p>
			<p class="inline">★3(${ratingStateVO.threeCnt})</p>
			<p class="inline">★4(${ratingStateVO.fourCnt})</p>
			<p class="inline">★5(${ratingStateVO.fiveCnt})</p>
		</div>
		<div><button class="inline refreshBtn"><img src="https://cdn-icons-png.flaticon.com/512/7022/7022719.png"/></button></div>	
	</div>
	<form:form modelAttribute="productSearchVO" method="post" enctype="multipart/form-data">
		<div class="search">
			<div>
				<label for="rating">평점</label>
				<select id="rating" name="rating">
					<option value="">전체</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
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
		<p>총 ${reviewCnt}건의 게시글이 검색되었습니다.</p>
		<div class="questions">
			<c:choose>
				<c:when test="${not empty reviewList }">
					<c:forEach items="${reviewList }" var="reviewVO">
						<div class="questionArea" id="${reviewVO.reviewId}">
							<div class="product"><span>상품명: ${reviewVO.productVO.prdName }</span></div>
							<div class="upperQuestion">
								<div class="writerInfo">
									<div class="memNickname">${reviewVO.memberVO.memNickname}</div>
									<div class="postDate">${reviewVO.revPostDate}</div>
								</div>
								<div class="queTitle">평점: <span class="${reviewVO.rating}"></span></div>
								<div class="queContent">${reviewVO.revContent}</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div>리뷰 내역이 없습니다.</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</section>
<jsp:include page="../layout/footer.jsp"></jsp:include>