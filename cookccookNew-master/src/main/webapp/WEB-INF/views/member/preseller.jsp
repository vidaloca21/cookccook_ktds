<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 승인 대기중</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	#inform {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 60vh;
	}
	#inform ul {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	#inform ul li {
		display: flex;
		align-items: center;
		justify-content: center;
		margin-top: 20px;
		font-size: 20px;
	}
	.bold {
		font-weight: bold;
	}
	#pararell {
		margin-left: 10px;
		margin-right: 10px;
	}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<div class="header">
		<div class="icon_bar">
			<img src="/img/cc_icon.png" id="icon" />
		</div>

		<div class="navi_bar">
			<div class="menu">
				레시피
			</div>
			<div class="menu">
				커뮤니티
			</div>
			<div class="menu">
				구독
			</div>
			<div class="menu">
				직거래
			</div>
		</div>
	</div>
	<div class="main">
		<div></div>
		<div class="container">
<div id="inform">
<ul>
	<li><span class="bold">${member.memNickname}&nbsp;</span> 회원님,</li>
	<li>판매 회원 가입 신청이 완료되어 관리자 승인 대기 중입니다.</li>
	<li>승인 결과는 메일로 안내 드릴 예정입니다.</li>
	<li>안내 드릴 메일 주소는 <span class="bold">&nbsp;${member.memEmail }&nbsp;</span>입니다.</li>
	<li>빠른 시일 내에 처리 결과를 알려드리겠습니다.</li>
	<li>감사합니다.</li>
	<li></li>
	<li>
		<div id="pararell">
			<a href="/member/mypage">내 정보</a>
			<a href="/member/memberlogout">로그아웃</a>
		</div>
	</li>
</ul>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>





