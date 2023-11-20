<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page import="com.cookccook.app.shop.vo.PurchaseVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>주문 내역</title>
<link rel="stylesheet" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>

<script type="text/javascript">
$().ready(function() {
	$(".cancelReqBtn").click(function() {
		location.href = "" + $(this).attr("id");
	})

})

</script>

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

 .purchaseList {
   display: flex;
   flex-direction: row;
   padding-left: 0;
   width: 1040px;
   height: 180px;
   align-items: center;
   border-bottom: 1px solid #cccccc;
 }
 .purchaseList > li {
   display: flex;
 }
 .purchaseList > li:first-child {
   width: 160px;
   height: 160px;
   font-size: 14px;
 }
 .purchaseList > li:first-child img{
   width: 140px;
   height: 140px;
 }
 .purchaseList > li:nth-child(2){
   width: 320px;
 }
 .purchaseList > li:nth-child(3){
   width: 160px;
 }
 .purchaseList > li:nth-child(3) > ul {
   align-items: flex-end;
 }
 .purchaseList > li:nth-child(4){
    justify-content: right;
    width: 80px;
   }
   .purchaseList > li:nth-child(5) {
   justify-content: right;
   width: 100px;
}

 .purchaseList > li > ul {
   display: flex;
   flex-direction: column;
   padding-left: 10px;
 }
 .purchaseList > li > ul > li{
   display: flex;
   margin-bottom: 8px;
 }
</style>
	<jsp:include page="../layout/${header_type}.jsp"></jsp:include>
         <div>
            <h2>주문내역</h2>
            <form:form modelAttribute="keywordVO"
                  method="post" 
                  action="/shop/orderlist">
               <input type="text" id="search" name="searchKeyword" placeholder="상품 이름 검색" />
               <input type="submit" value="검색"/>
            </form:form>
         </div>
         <c:choose>
            <c:when test="${not empty getPurchaseListByMemId.purchaseList}">
               <div>
                  <select id="filter">
                     <option>최신순</option>
                     <option>오래된순</option>
                  </select>

                  <div class="purchaseInfo">
                     <c:forEach items="${getPurchaseListByMemId.purchaseList}" var="purchaseVO">
                        <c:forEach items="${purchaseVO.purchaseProductList}" var="purchaseProductVO">
                           <ul class="purchaseList">
                                 <li>
                                    <ul>
                                       <li>
                                          <img src="/shop/product/titleimg/${purchaseProductVO.choiceVO.productVO.prdTitleImgOrigin}"/>
                                       </li>
                                       <li>
                                          <div class="align">${purchaseProductVO.state}</div>
                                          <div class="align">${purchaseProductVO.deliverState}</div>
                                       </li>
                                    </ul> 
                                 </li>
                                 <li>
                                    <ul>
                                       <li>
                                          <div>${purchaseProductVO.choiceVO.productVO.prdName}</div>
                                       </li>
                                       <li>
                                          <div>옵션: ${purchaseProductVO.choiceVO.choName}</div>
                                       </li>
                                       <li>
                                          <div>주문일: ${purchaseVO.purchaseDate}</div>
                                       </li>
                                    </ul>
                                 </li>
                                 <li>
                                    <ul>
                                       <li>
                                          ${purchaseProductVO.choiceVO.productVO.prdPrice}원
                                       </li>
                                       <li>
                                          ${purchaseProductVO.choiceVO.choPrice}원
                                       </li>
                                       <li>
                                          <div class="align">
                                             <c:choose>
                                                <c:when test="${purchaseProductVO.deliverFee == 0}">배송비: 무료 </c:when>
                                                <c:otherwise>배송비: ${purchaseProductVO.deliverFee}원 </c:otherwise>
                                             </c:choose>
                                          </div>
                                       </li>
                                    </ul>
                                 </li>
                                 <li>
                                    ${purchaseProductVO.quantity}개
                                 </li>
                                 <li>
                                    ${purchaseProductVO.purPrdPrice + purchaseProductVO.deliverFee}원
                                 </li>
                                 <li>
                                    <ul>
                                       <li>
                                          <a class="btn_empty" href="/shop/orderlist/${purchaseVO.purchaseId}" style="height: 20px;">주문 상세 보기</a>
                                       </li>
                                       <li>
                                          <a class="btn_empty" href="/shop/product/${purchaseProductVO.choiceVO.productVO.productId}" style="height: 20px;">리뷰 작성</a>
                                       </li>
                                       <li>
                                          <a href="/shop/tracking/" style="display: none;">배송 추적</a>
                                       </li>
                                       <li>
                                          <c:choose>
                                             <c:when test="${purchaseProductVO.purCancelStatus eq true}">
                                                <a class="btn_empty" href="#" id="${purchaseVO.purchaseId}" style="display: none;">취소 요청</a>
                                             </c:when>
                                             <c:otherwise>
                                                <a class="btn_empty" href="/shop/orderlist/cancelrequest?purchaseId=${purchaseVO.purchaseId}" style="height: 20px;">취소 요청</a>
                                             </c:otherwise>
                                          </c:choose>
                                       </li>
                                    </ul>
                                 </li>
                           </ul>   
                        </c:forEach>
                     </c:forEach>
                  </div>
               </div>
            </c:when>
            <c:when test="${empty searchProducts}">
               <div>주문 내역이 존재하지 않습니다.</div>
            </c:when>
         </c:choose>
	<jsp:include page="../layout/footer.jsp"></jsp:include>