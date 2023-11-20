<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수량 수정</title>
</head>
<body>
	<h1>상품 수량 수정</h1>
    <form:form modelAttribute="cartVO" action="/cart/edit" enctype="multipart/form-data" method="post">
        
        <div>
	        <input type="hidden" name="memberId"
	               value="A031">
            <input type="hidden" name="cartId"
	               value="AR51">
            
	               
	        <label for="choiceId">옵션</label>
	        <input type="text" id="choiceId" name="choiceId"
	               value="${cartVO.choiceId}">
	               
	        <label for="quantity">수량</label>
	        <input type="number" id="quantity" name="quantity"
	               value="${cartVO.quantity}">
        </div>
        <input type="submit" value="어디 한 번 수정해봐">
    </form:form>
</body>
</html>