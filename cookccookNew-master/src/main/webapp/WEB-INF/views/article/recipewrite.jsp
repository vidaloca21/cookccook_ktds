<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성 페이지</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#form").click(function() {
			console.log("클릭")
		})
	})
</script>
</head>
<body>
    <h1>게시글 작성</h1>
    <form:form modelAttribute="articleVO" method="post" enctype="multipart/form-data" action="/recipe/recipewrite">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required>
        <br>
        
        <label for="content">내용</label>
        <textarea id="content" name="content" rows="5" required></textarea>
        <br>
        
        <button type="submit">저장</button>
    </form:form>
</body>
</html>