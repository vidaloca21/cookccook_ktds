<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <title>주문 상세 정보 페이지</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
</script>
    <style type="text/css">
	 .grid_container {
	    display: grid;
	    grid-template-columns: 20% 50% 30%;
	    gap: 20px;
	 }
	 .grid_container > .prd_info {
	 align-items: center;
	 }
	 .grid_container > .prd_info > div {
	 
	 margin: 5px;
	 }
	 .grid_container > .prd_img {
	 
	 height: 250px;
	 width: 300px;
	 }

	 .prd_img > img {
		width: 200px;
		overflow: hidden;
	 }
	 .btn_area {
 	 display: grid;
     align-content: center;
     justify-items: center;
	 }
	 .btn_area > .button {
    justify-items: center;
    text-align: center;
    margin-top: 10px;
    margin-bottom: 10px;
	    
	 }
	  a {
     text-decoration: none;
     color: inherit;
 	}
	 .align {
	    display: inline-block;
	    
	 }
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
 </style>
<jsp:include page="../layout/${header_type}.jsp"></jsp:include>
	<div class="header_info">
		<h1>주문 상세 정보</h1>
		<div>
			<h3>주문번호: ${purchaseVO.purchaseId}</h3>
		</div>
		<div>
			<h3>주문일: ${purchaseVO.purchaseDate}</h3>
		</div>
	</div>
	<div>
		<h4>주문 정보</h4>
	</div>
	<div class="grid_container">
		<!-- purchaseList(List<PurchaseVO>) 를 반복한다. purchaseID 만큼 출력됨. -->
		<c:choose>
			<c:when test="${not empty purchaseVO.purchaseProductList}">
				<c:forEach items="${purchaseVO.purchaseProductList}" var="purchaseProduct">
				<!-- 상품 이미지 정보 추가 필요  -->
				<div class="prd_img">
					<img src="/shop/product/titleimg/${purchaseProduct.choiceVO.productVO.prdTitleImgOrigin}"/>
				</div>
				<div class="prd_info">
					<div>${purchaseProduct.choiceVO.productVO.prdName} </div>
					<div class="align">${purchaseProduct.purPrdPrice}원 |</div>
					<div class="align">구매 수량: ${purchaseProduct.quantity}개</div>
					<div>
						<div>옵션: ${purchaseProduct.choiceVO.choName}</div>
					</div>
					<div>
						<div class="align">${purchaseProduct.state}</div>
						<div class="align">${purchaseProduct.deliverState}</div>
						<div></div>
					</div>
				</div>
				<div class="btn_area">
					<div class="button">
						<a href="/review/${productVO.productId}/write/">리뷰 작성</a>
					</div>
					<div class="button">
						<a href="/shop/tracking/${productVO.productId}">배송 추적</a>
					</div>
					<div class="button">
						<a href="/shop/orderlist/cancelrequest?purchaseId=${purchaseVO.purchaseId}">취소 요청</a>
					</div>
				</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div> 존재하지 않는 페이지 입니다.</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="deliverInfo">
		<h2>배송지 정보</h2>
		<table>
			<tr>
				<td>배송지 주소</td>
				<td>${purchaseVO.deliverPlaceVO.delAddress} ${purchaseVO.deliverPlaceVO.delDetailAddress}</td>
			</tr>
			<tr>
				<td>배송 수령인</td>
				<td>${purchaseVO.deliverPlaceVO.recipient}</td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>${purchaseVO.deliverPlaceVO.recipientPhoneNumber}</td>
			</tr>
			<tr>
				<td>배송메모</td>
				<td>${purchaseVO.deliverMemo}</td>
			</tr>
		</table>
	</div>
	<div id="orderMemberInfo">
		<h2>주문자 정보</h2>
		<ul class="orderMemberInfoArea">
			<li><label for="buyerEmail">주문자 EMAIL</label></li>
			<li>${purchaseVO.deliverPlaceVO.memberVO.memEmail}</li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="buyerName">주문자 성함</label></li>
			<li>${purchaseVO.deliverPlaceVO.memberVO.memName}</li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="buyerTel">주문자 전화번호</label></li>
			<li>${purchaseVO.deliverPlaceVO.memberVO.memPhoneNumber}</li>
		</ul>
	</div>

	<div id="payInfo">
		<h2>결제 정보</h2>
		<ul class="priceInfo">
			<li >총 상품 금액</li>
			<li id="sumPrice">${purchaseVO.totalPurPrdPrice}원</li>
		</ul>
		<ul class="priceInfo">
			<li>배송비</li>
			<li id="deliverFee">${purchaseVO.totalDeliverFee}원</li>
		</ul>
		<ul class="priceInfo">
			<li>결제 수단</li>
			<li id="deliverFee">${purchaseVO.payMethod}</li>
		</ul>
		<ul class="priceInfo">
			<li>총 결제 비용</li>
			<li id="finalPrice">
				<c:choose>
					<c:when test="${empty purchaseVO.totalDeliverFee}">
						<p>무료</p>
					</c:when>
					<c:otherwise>
						<p> 상품 구매 총액: <c:out value="${purchaseVO.totalPurPrdPrice+purchaseVO.totalDeliverFee}"/>원</p>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>


<jsp:include page="../layout/footer.jsp"></jsp:include>