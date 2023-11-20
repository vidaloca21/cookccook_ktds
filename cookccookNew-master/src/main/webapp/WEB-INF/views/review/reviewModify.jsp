<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰작성</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#form").click(function() {
			console.log("클릭")
		})
	})
	
</script>
<style type="text/css">
	#reviewGrid {
		display: grid;
		grid-template-columns: 1fr 80px ;
		grid-template-rows: 40px 40px 100px;
		row-gap: 10px;
	}
	#reviewWriter{
		grid-column: 1/2;
        grid-row: 1/2;
	}
	#reviewRating{
        grid-column: 1/2;
        grid-row: 2/3;
	}
	#textArea {
        grid-column: 1/2;
        grid-row: 3/4;
	}
	#textArea > #revContent {
		width: 90%;
	}
	#submitBtn {
        grid-column: 2/3;
        grid-row: 3/4;
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
	
	 <form:form modelAttribute="reviewVO" method="post" enctype="multipart/form-data" action="/review/modify">
		<div>
			<form:errors path="revContent" element="div" cssClass="errors"  />
		</div>	
		<div id="reviewGrid">
			<div id="reviewWriter">
				<h3>리뷰 수정</h3>
				<input type="hidden" name="productId" value="${productId }"/>
				<input type="hidden" name="reviewId" value="${reviewVO.reviewId}"/>
		 		<input type="hidden" name="memberId" value="${reviewVO.memberId }"/>
			</div>
			<div id="reviewRating">
				<label for="rating">별점</label>
				<input type="radio" name="rating" value=1 />1
				<input type="radio" name="rating" value=2 />2
				<input type="radio" name="rating" value=3 />3
				<input type="radio" name="rating" value=4 />4
				<input type="radio" name="rating" value=5 checked="checked" />5
			</div>
			<div id="textArea">
				<textarea id="revContent" name="revContent">${reviewVO.revContent}</textarea>
			</div>
			<div id="submitBtn">
				<input type="submit" value="저장" />
			</div>
		</div>
	</form:form>
</body>
</html>