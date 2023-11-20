<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원 추천 페이지</title>
</head>
<body>
    <h1>회원 추천 페이지</h1>
    
    <h2>회원이 좋아요한 게시물 목록:</h2>
    <form:form modelAttribute="memRecommendVO" method="get" enctype="multipart/form-data" action="/sellerChart/memRecommend">
        <c:forEach items="${interestId}" var="articleId">
            <li>${articleId}</li>
        </c:forEach>
    </form:form>
    
</body>
</html>
