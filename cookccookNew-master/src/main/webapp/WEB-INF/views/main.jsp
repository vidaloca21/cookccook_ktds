<%--
  Created by IntelliJ IDEA.
  User: krait4g
  Date: 2023-10-02
  Time: 오후 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>COOKCCOOK</h1>
<h2>메인 페이지입니다.</h2>
<jsp:include page="./membermenu.jsp"></jsp:include>
<div class ="grid">
		<div class="right-align">
			<a href="/admin/adminChart">관리자 페이지로 가기</a>
		</div>
		<div class="right-align">
			<a href="/member/mypage">멤버 페이지로 가기</a>
		</div>
		<div>
			<a href="/recipe/recipelist">목록</a>
		</div>
		<div>
			<a href="community/communitylist">커뮤니티</a>
		</div>
</div>
</body>
</html>
