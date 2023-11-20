<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 작성</title>
<style type="text/css">
	div.que-grid {
		display: grid;
		grid-template-columns: 80px 1fr;
		grid-template-rows: 28px 28px 28px 320px 1fr;
		row-gap: 10px;
	}

	div.que-grid > div.btn-group {
		display: grid;
		grid-column: 1 / 3;

	}

	div.que-grid div.right-align {
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
	    background-color: #FF00004A;
	    opacity: 0.8;
	    padding: 10px;
	    color: #333;
	}
	
	div.errors:last-child {
	    margin-bottom: 10px;
	}
</style>
</head>
<body>
<!-- Modal -->
<div id="questionModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<span class="close">&times;</span>
		<h1>문의 작성하는 페이지</h1>
		<form:form modelAttribute="questionVO" method="post" enctype="multipart/form-data" action="/question/write">
			<input type="hidden" name="productId" value="<%=request.getParameter("productId") %>"/>
			<input type="hidden" name="memberId" value="<%=request.getParameter("memberId") %>"/>
	 		<input type="hidden" name="questionType" value=9 />
		<div>
			<form:errors path="queTitle" element="div" cssClass="errors"  />
			<form:errors path="queContent" element="div" cssClass="errors"  />
		</div>
		<div class="que-grid">
			<span>상품명:</span>
			<span><%=request.getParameter("prdName") %></span>
			<label for="queTitleInput">제목</label>
			<input type="text" name="queTitle" id="queTitleInput" value="${questionVO.queTitle}" />
			
			<label for="queContentInput">내용</label>
			<textarea id="queContentInput" name="queContent" style="height: 100%;">${questionVO.queContent}</textarea>
		</div>											
		<div class="btn-group">
			<input type="hidden" name="upperQuestionId" value="${upperQuestionId}"/>
			<div class="right-align">
				<input type="submit" value="저장" />
			</div>
		</div>
	</form:form>																
	
		</div>
	</div>
</body>
</html>