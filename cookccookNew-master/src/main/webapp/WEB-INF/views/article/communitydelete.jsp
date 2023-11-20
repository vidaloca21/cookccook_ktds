<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 게시글 삭제</title>
</head>
<body>
	<h1>커뮤니티 게시글 삭제</h1>
    	<form:form modelAttribute="articleVO" action="/community/communitydelete" enctype="multipart/form-data" method="post">
    
   		<div>
	        <input type="hidden" name="articleId" value="${articleId}">
	        <p>진짜 지울거야?</p>
	        
    	</div>
        	<input type="submit" value="삭제">
			
    </form:form>
</body>
</html>