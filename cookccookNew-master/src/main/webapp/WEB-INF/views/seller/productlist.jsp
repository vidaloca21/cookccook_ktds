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

	.container {
		overflow: hidden;
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
	.btn_fill_s > a {
		color: #fff;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		//새로고침
		$(".refreshBtn").on('click', function(){
			location.href="/seller/productlist"
		})

		//새창(상품수정) 열고 닫기
		$(".popupBtn").on('click', function(){
			let productId = $(this).text()
			let url = "/seller/productmodify/" + productId
			let name = "상품수정페이지"
			var options = 'top=600, left=500, width=800, height=600, status=no, menubar=no, toolbar=no, resizable=no';
    		window.open(url, name, options);
		});

		//삭제버튼클릭 시
		$('.deleteBtn').on('click', function(){
			let result = [];
			$('input[name="deleteCheck"]:checked').each(function(){
				var chk = $(this).val();
				result.push(chk);
			})
			if(confirm(result.length + "건의 상품을 정말 삭제하시겠습니까?")){
				for(var i in result){
					$.ajax({
						url: "/seller/productdelete/" + result[i],
						method: "get",
						success: function(response) {
							location.href="/seller/productlist"
						}
					})
				}
			}
		})
	})

	function selectAll(selectAll)  {
		const checkboxes = document.getElementsByName('deleteCheck');
		checkboxes.forEach((checkbox) => {
			checkbox.checked = selectAll.checked;
	})

	function getCheckboxValue()  {
		// 선택된 목록에서 value 찾기
		let result = [];
		$('input[name="deleteCheck"]:checked').each(function(){
			var chk = $(this).val();
			result.push(chk);
		})
	}
}
</script>
<jsp:include page="../layout/header_seller.jsp"></jsp:include>
	<section>
	<h1 class="font_title">상품 조회</h1>
	<div class="saleState">
		<div>
			<p class="inline" style="font-weight: bold;">판매상태</p>
			<p class="inline">전체상품(${saleStateVO.allCnt})</p>
			<p class="inline">판매중(${saleStateVO.onSaleCnt})</p>
			<p class="inline">일시품절(${saleStateVO.soldoutCnt})</p>
			<p class="inline">판매종료(${saleStateVO.endSaleCnt})</p>
		</div>
		<div><button class="inline refreshBtn"><img src="https://cdn-icons-png.flaticon.com/512/7022/7022719.png"/></button></div>
	</div>
	<form:form modelAttribute="productSearchVO" method="post" enctype="multipart/form-data">
		<div class="search">
			<div>
				<label for="sale_state">판매상태</label>
				<select id="sale_state" name="saleState" value='${productVO.saleState}'>
					<option value="">전체</option>
					<option value="1">판매중</option>
					<option value="2">일시품절</option>
					<option value="3">판매종료</option>
				</select>
			</div>
			<div>
				<label for="prd_name">상품명</label>
				<input id="prd_name" type="text" name="prdName" value='${productSearchVO.prdName}'/>
			</div>
			<div>
				<label for="product_id">상품번호</label>
				<input id="product_id" type="text" name="productId" value='${productSearchVO.productId}'/>
			</div>
			<div>
				<label for="start_regist_date">등록기간</label>
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
		<p>총 ${productCnt}건의 게시글이 검색되었습니다.</p>
		<c:choose>
			<c:when test="${not empty productList }">
				<table>
					<tr>
						<th><input type="checkbox" class="allCheck" name="deleteCheck" onclick='selectAll(this)'></th>
						<th>상품번호</th>
						<th>판매상태</th>
						<th>상품명</th>
						<th>재고</th>
						<th>판매가</th>
					</tr>
					<c:forEach items="${productList }" var="productVO">
					<tr>
						<td><input type="checkbox" name="deleteCheck" value="${productVO.productId}"></td>
						<td class="number popupBtn">${productVO.productId}</td> <!--<a href="/seller/productmodify/${productVO.productId}"></a>-->
						<td class="title">
							<c:if test="${productVO.saleState == 1}">판매중</c:if>
							<c:if test="${productVO.saleState == 2}">일시품절</c:if>
							<c:if test="${productVO.saleState == 3}">판매종료</c:if>	
						</td>
						<td class="postDate">${productVO.prdName}</td>
						<td class="viewCnt">${productVO.stock}</td>
						<td class="likeCnt">${productVO.prdPrice}원</td>
					</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>조회된 상품이 없습니다.</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="btn_group right_text">
		<button class="btn_empty_s deleteBtn inline" onclick="getCheckboxValue()">상품 삭제</button>
		<button class="btn_fill_s inline"><a href="/seller/productcreate">상품 등록</a></button>
	</div>
	</section>
<jsp:include page="../layout/footer.jsp"></jsp:include>