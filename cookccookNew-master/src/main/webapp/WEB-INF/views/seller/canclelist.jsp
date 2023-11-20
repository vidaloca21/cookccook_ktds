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
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		//새로고침
		$(".refreshBtn").on('click', function(){
			location.href="/seller/canclelist"
		})

		//새창(취소상세) 열고 닫기
		$(".popupBtn").on('click', function(){
			let purchaseId = $(this).text()
			let url = "/seller/cancledetail/" + purchaseId
			let name = "취소상세페이지"
			var options = 'top=600, left=500, width=700, height=300, status=no, menubar=no, toolbar=no, resizable=no';
    		window.open(url, name, options);
		});
	})
</script>
<jsp:include page="../layout/header_seller.jsp"></jsp:include>
	<section>
	<h1 class="font_title">취소 요청 조회</h1>
	<div class="saleState">
		<div>
			<p class="inline" style="font-weight: bold;">주문상태</p>
			<p class="inline">전체주문(${purStateVO.allCnt})</p>
			<p class="inline">결제완료(${purStateVO.orderCnt})</p>
			<p class="inline">상품준비중(${purStateVO.preparationCnt})</p>
			<p class="inline">배송중(${purStateVO.deliveringCnt})</p>
			<p class="inline">배송완료(${purStateVO.completeCnt})</p>
			<p class="inline">취소요청(${purStateVO.cancleCnt})</p>
		</div>
		<div><button class="inline refreshBtn"><img src="https://cdn-icons-png.flaticon.com/512/7022/7022719.png"/></button></div>
	</div>
	<form:form modelAttribute="purchaseSearchVO" method="post" enctype="multipart/form-data">
		<div class="search">
			<div>
				<label for="memPhoneNumber">구매자</label>
				<input id="memPhoneNumber" type="tel" name="memPhoneNumber" value='${PurchaseSearchVO.memPhoneNumber}' placeholder="연락처"/>
				<input id="memName" type="text" name="memName" value='${PurchaseSearchVO.memName}' placeholder="성명"/>
			</div>
			<div>
				<label for="purchaseId">주문번호</label>
				<input id="purchaseId" type="text" name="purchaseId" value='${PurchaseSearchVO.purchaseId}'/>
			</div>
			<div>
				<label for="recipientPhoneNumber">수령인</label>
				<input id="recipientPhoneNumber" type="tel" name="recipientPhoneNumber" value='${PurchaseSearchVO.recipientPhoneNumber}' placeholder="연락처"/>
				<input id="recipient" type="text" name="recipient" value='${PurchaseSearchVO.recipient}' placeholder="성명"/>
			</div>
			<div>
				<label for="startDate">조회기간</label>
				<input id="startDate" type="date" name="startDate" value='${PurchaseSearchVO.startDate}'/>
				~
				<input id="endDate" type="date" name="endDate" value='${PurchaseSearchVO.endDate}'/>
			</div>
			<div class="search_btn">
				<input type="reset" value="초기화" class="btn_empty_s"/>
				<input type="submit" value="검색" class="btn_fill_s"/>
			</div>
		</div>
	</form:form>
	<div>
		<h2 class="font_subtitle">검색결과</h2>
		<p>총 ${cancelCnt}건의 게시글이 검색되었습니다.</p>
		<c:choose>
			<c:when test="${not empty cancelList }">
				<table>
					<tr>
						<th>주문번호</th>
						<th>주문상태</th>
						<th>상품명</th>
						<th>옵션</th>
						<th>수량</th>
						<th>판매가</th>
						<th>구매자</th>
					</tr>
					<c:forEach items="${cancelList }" var="purchaseProductVO">
						<tr> 
							<td class="number popupBtn">${purchaseProductVO.purchaseVO.purchaseId}</td> <!--<a href="/seller/productmodify/${productVO.productId}"></a>-->
							<td class="title">취소요청</td>
							<td class="prdName">${purchaseProductVO.choiceVO.productVO.prdName}</td>
							<td class="choice">${purchaseProductVO.choiceVO.choName}</td>
							<td class="quantity">${purchaseProductVO.quantity}</td>
							<td class="purPrdPrice">${purchaseProductVO.purPrdPrice}원</td>
							<td class="memName">${purchaseProductVO.purchaseVO.memberVO.memName}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>조회된 취소요청이 없습니다.</div>
			</c:otherwise>
		</c:choose>
	</div>
	</section>
<jsp:include page="../layout/footer.jsp"></jsp:include>