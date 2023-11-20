<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page import="com.cookccook.app.shop.vo.PurchaseVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
<style type="text/css">

 
 .grid_container {
    display: grid;
    grid-template-columns: 20% 50% 30%;
	gap: 10px;

 }

 .brn_area {
 align-content: center;}
 
 .btn_area > button {
    justify-items: center;
    text-align: center;

 }
 
	button:disabled,
	button[disabled]{
  border: 1px solid #999999;
  background-color: #cccccc;
  color: #666666;
}
 
 a {
     text-decoration: none;
     color: inherit;
 }

 .align {
    display: inline-block;
 }

</style>

</head>
<body>
	<div>
		<h2>주문내역</h2>
		<form:form modelAttribute="keywordVO"
			   method="post" 
			   action="/shop/orderlist/search">
			<input type="text" name="searchKeyword" placeholder="상품 이름 검색">
			
			<button type="submit" name="submit" >검색</button>
		</form:form>
		
		<c:choose>
			<c:when test="${not empty searchPurchaseList}">
				<div>
					<select id="filter">
						<option>최신순</option>
						<option>오래된순</option>
					</select>
			  		<div class="grid_container">
						<c:forEach items="${searchPurchaseList.purchaseList}" var="purchaseVO">
							<div>
								<div><h3>${purchaseVO.purchaseId}</h3></div>
							</div>
							<div>
								<div>주문일-${purchaseVO.purchaseDate}</div>
							</div>
							<div>
								<a href="/shop/orderlist/${purchaseVO.purchaseId}">주문 상세 보기</a>
							</div>
							<c:forEach items="${purchaseVO.purchaseProductList}" var="purchaseProductVO">
							<div>
								<div class="align">${purchaseProductVO.state}</div>
								<div class="align">${purchaseProductVO.deliverState}</div>
								<div>사진 첨부<img/></div>
							</div>
							<div>
								<div class=prd_info>
									<div>
										<div>${purchaseProductVO.choiceVO.productVO.prdName}</div>
										<div>옵션: ${purchaseProductVO.choiceVO.choName}</div>
										<div>
											<div class="align">${purchaseProductVO.purPrdPrice}원</div>
											<div class="align">${purchaseProductVO.quantity}개</div>
										</div>
										<div>
											<div class="align">배송비</div>
											<div class="align">
												<c:choose>
													<c:when test="${purchaseProductVO.deliverFee == 0}"> 무료 </c:when>
													<c:otherwise> ${purchaseProductVO.deliverFee}원 </c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="btn_area">
								<button>
									<a href="/shop/review/write/">리뷰 작성</a>
								</button>
								<button>
									<a href="/shop/tracking/">배송 추적</a>
								</button>
								
								<c:choose>
									<c:when test="${purchaseProductVO.purCancelStatus eq true}">
										<button disabled> 취소 요청</button>
									</c:when>
									<c:otherwise>
										<button><a href="/shop/orderlist/${purchaseProductVO.purchaseProductId}/cancelrequest">취소 요청</a></button>
									</c:otherwise>
								</c:choose>
							</div>
							</c:forEach>
						</c:forEach>
					</div>
				</div>

			</c:when>
				
			<c:when test="${empty searchPurchaseList}">
				<p>일치하는 상품이 없습니다.</p>
				<div><a href="/shop/orderlist">주문 내역으로 돌아가기</a></div>
			</c:when>

		</c:choose>
	</div>

</body>
</html>