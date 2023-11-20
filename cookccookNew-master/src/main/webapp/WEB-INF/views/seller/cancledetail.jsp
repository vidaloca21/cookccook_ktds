<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	div.errors {
		background-color: #FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	#orderMemberInfo > .orderMemberInfoArea {
		display: flex;
		flex-direction: row;
	}
	#orderMemberInfo > .orderMemberInfoArea > li {
		display: flex;
		height: 40px;
		align-items: center;
		margin-top: 10px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li:first-child {
		width: 200px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li:last-child {
		width: 400px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > input {
		display: inline;
		width: 400px;
		height: 40px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > textarea {
		display: inline;
		width: 400px;
		height: 40px;
	}
	#orderMemberInfo > .orderMemberInfoArea > li > select {
		display: inline;
		width: 400px;
		height: 40px;
	}
	#inputBtn {
		cursor: pointer;
		width: 150px;
		height: 40px;
	}
</style>
</head>
<body>
	<div id="orderMemberInfo">
		<h2>취소 상세 조회</h2>
		<ul class="orderMemberInfoArea">
			<li><label for="canExcuse">취소사유</label></li>
			<li><input type="text" id="canExcuse" name="canExcuse" value="${cancelVO.canExcuse}" readonly/></li>
		</ul>
		<ul class="orderMemberInfoArea">
			<li><label for="canDetail">취소상세사유</label></li>
			<li><textarea id="canDetail" name="canDetail" readonly>${cancelVO.canDetail}</textarea></li>
		</ul>
	</div>
</body>
</html>
