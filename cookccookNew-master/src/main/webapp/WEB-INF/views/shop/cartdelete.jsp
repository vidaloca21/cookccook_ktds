<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 상품 삭제</title>
</head>
<body>
	<h1>장바구니 상품 삭제</h1>
    <form:form modelAttribute="cartVO" action="/cart/delete" enctype="multipart/form-data" method="post">
    
    	<div>
	        <input type="hidden" name="memberId" value="A031">
	        <input type="hidden" name="cartId" value="AR51">
	        <p>진짜 지울거야?</p>
	        <label for="choiceId">옵션</label>
	        <input type="text" id="choiceId" name="choiceId"
	               value="${cartVO.choiceId}">
	         <label for="quantity">수량</label>
	        <input type="number" id="quantity" name="quantity"
	               value="${cartVO.quantity}">
    	</div>
        <input type="submit" value="어디 한 번 삭제해봐">
    </form:form>
</body>
</html>