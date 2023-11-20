<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>좋아요 기반 레시피 추천</title>
</head>
<body>
	<div class="container">	
	    <table class="graph" border="1">
	        <tr>
	            <th>zz</th>
	            <th>zz</th>
	        </tr>
	        <c:forEach items="${interestRecListVO}" var="interestRecListVO">
	            <tr>
	                <td>${interestRecListVO.recipeName}</td>
	                 <td>${interestRecListVO.attImgBig}</td>
	            </tr>
	        </c:forEach>
	    </table>
    </div>
</body>
</html>
