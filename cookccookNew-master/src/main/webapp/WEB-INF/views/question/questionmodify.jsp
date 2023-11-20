<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품문의 수정</title>
<style type="text/css">
	div.grid {
		display: grid;
		grid-template-columns: 80px 1fr;
		grid-template-rows: 28px 28px 28px 28px 320px 1fr;
		row-gap: 10px;
	}

	div.grid > div.btn-group {
		display: grid;
		grid-column: 1 / 3;

	}

	div.grid div.right-align {
		text-align: right;
	}

	label {
		padding-left: 10px;
	}

	button, input, textarea {
		padding: 10px;
	}

	input[type=file] {
		padding: 0px;
	}
	
	div.errors {
		background-color:#FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	
	div.errors:last-child {
		margin-bottom: 15px;
	
	}
</style>
</head>
<body>

	<h1>문의 수정하는 페이지</h1>
		 <form:form  modelAttribute="questionVO" method="post" enctype="multipart/form-data" action="/question/modify">
			 <div>
				 <form:errors path="queTitle" element="div" cssClass="errors"  />
				 <form:errors path="queContent" element="div" cssClass="errors"  />
			</div>
				<input type="hidden" name="questionId" value="${questionVO.questionId}"/>
			<c:if test="${not empty questionVO.productId}">
				<input type="hidden" name="productId" value="${questionVO.productId}"/>
				<label for="prdName">상품명: ${prdName}</label>
			</c:if>
			<div class="grid">
				<label for="queTitle">제목</label>
				<input type="text" name="queTitle" id="queTitle" value="${questionVO.queTitle}" />
				<label for="memberId">Id</label>
				<input type="text" name="memberId" id="memberId" value="${memberId}" readonly/>
	
				<label for="queContent">내용</label>
				<textarea id="queContent" name="queContent" style="height: 300px;">${questionVO.queContent}</textarea>
			</div>
			<div class="btn-group">
				<div class="right-align">
					<input type="submit" value="수정" />
				</div>
			</div>
		</form:form>
</body>
</html>