<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<link rel="stylesheet" type="text/css" href="styles.css">
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	/* 다크 배경 색상background-color: #121212;  */
	/* 텍스트 색상  color: #fff; */
	display: flex;
	flex-direction: column;
	height: 100vh;
}

#container {
	display: flex;
	flex: 1;
	overflow: hidden;
}

#left-sidebar {
	/* 왼쪽 사이드바 배경 색상  background-color: #1a1a1a; */
	width: 250px;
	text-align: center;
}

#left-sidebar img {
	max-width: 80%; /* 이미지의 최대 너비를 부모 요소에 맞게 설정 */
	height: auto; /* 이미지의 높이는 자동으로 조절 */
	margin: 20px;
}

ul {
	list-style: none;
}

.menubar {
	list-style: none;
	padding: 20px 0;
	margin: 0;
}

#left-sidebar ul>li {
	color: #fff;
	margin: 0px;
	height: 40px;
	text-align: center;
}

.menubar div {
	color: #fff;
	padding: 10px 0;
}

main {
	flex: 1;
	padding: 20px;
	/* content 배경 색상 background-color: #292929; */
}

header {
	/* 헤더 배경 색상  background-color: #222; */
	padding: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

header h1 {
	margin: 0;
	font-size: 24px;
}

/* section {
		display: none;
	} */

/* 선택한 메뉴에 따라 컨텐츠를 표시 */
#container:target section {
	display: block;
}

footer {
	/* 푸터 배경 색상 background-color: #222;  */
	padding: 10px;
	text-align: center;
}

#user-info {
	display: flex;
	align-items: center;
}

#user-id {
	margin-right: 10px;
}

#logout {
	color: #f00; /* 로그아웃 버튼 색상 설정 */
	text-decoration: none;
}

#logout:hover {
	text-decoration: underline; /* 마우스 오버시 밑줄 표시 */
}

#company_logo {
	width: 125px;
	height: 125px;
}

nav {
	background-color: #FB8F67;
	height: 100%;
}

.submenu {
	background-color: #FFF1E0;
	display: none;
	padding: 0;
}

.submenu > li {
	width: 100%;
	text-align: center;
	line-height: 40px;
}

.submenu a {
	display: block;
	width: 100%;
	height: 100%;
	color: #FB8F67;
	margin: 0;
}
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/super-build/ckeditor.js"></script>
<script src="/js/Editor.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".menubar > li").click(function() {
			if( $(this).children(".submenu").is(":visible") ){
				$(this).children(".submenu").slideUp();
				setTimeout(() => {
					$(this).css("height", "40px");
				}, 350);
			}else{
				let ulHeight = $(this).children(".submenu").children("li").length*40 + 40 +"px"
				$(this).css("height", ulHeight)
				$(this).children(".submenu").slideDown();
			}
		})

	})
</script>
</head>
<body>
	<div id="container">
		<aside id="left-sidebar">
			<a href="/seller/productlist"> <img src="/img/cc_icon.png"
				alt="회사 로고" id="company_logo">
			</a>
			<nav>
				<ul class="menubar">
					<li>
						<div>상품관리</div>
						<ul class="submenu">
							<li><a href="/seller/productlist">상품조회</a></li>
							<li><a href="/seller/productcreate">신규상품등록</a></li>
						</ul>
					</li>
					<li>
						<div>주문관리</div>
						<ul class="submenu">
							<li><a href="/seller/orderlist">통합주문관리</a></li>
							<li><a href="/seller/canclelist">취소관리</a></li>
						</ul>
					<li>
						<div>매출통계</div>
						<ul class="submenu">
							<li><a href="/seller/sellerChart">매출통계</a></li>
						</ul>
					</li>
					<li>
						<div>고객관리</div>
						<ul class="submenu">
							<li><a href="/seller/review">리뷰관리</a></li>
							<li><a href="/seller/question">문의관리</a></li>
						</ul>
					</li>
					<li>
						<div>내 정보 관리</div>
						<ul class="submenu">
							<li><a href="/seller/mypage">내 정보</a></li>
							<li><a href="/seller/myinfoedit">내 정보 수정</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</aside>
		<main>
			<header>
				<h1></h1>
				<div id="user-info">
					<span id="user-id">회원 아이디 : ${member.memEmail}</span> <a
						href="/member/memberlogout" id="logout">로그아웃</a>
				</div>
			</header>