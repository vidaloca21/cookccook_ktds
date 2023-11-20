<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style type="text/css">
</style>
</head>
<body>
	<h1>게시글 수정 페이지</h1>
		 <form:form  modelAttribute="articleVO" method="post" enctype="multipart/form-data" action="/recipe/recipemodify">
			
			<div>
			<input type="hidden" name="articleId" id="articleId"
					   value="${articleVO.articleId}" />
				<label for="title">제목</label>
				<input type="text" name="title" id="title"
					   value="${articleVO.title}" />

				<label for="content">내용</label>
				<textarea id="content" name="content" style="height: 300px;">${articleVO.content}</textarea>
			</div>
			
			<div>
				<div class="right-align">
					<input type="submit" value="수정" />
				</div>
			</div>
			
		</form:form>
</body>
</html>