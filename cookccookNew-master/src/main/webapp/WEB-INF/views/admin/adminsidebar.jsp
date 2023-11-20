<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
<style type="text/css">
@font-face {
    font-family: 'SUIT-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
* {
  color: #2B1512;
  text-decoration: none;
  font-family: 'SUIT-Regular';
  font-size: 16px;
}
body {
  display: grid;
  grid-template-columns: 220px 1fr;
  grid-template-rows: 100px 1fr 80px;
}
a {
	text-decoration: none;
}
a:hover{
  text-decoration: underline;
}
.font_title {
  font-size: 28px;
  font-weight: bold;
}
.font_subtitle {
  font-size: 20px;
  font-weight: bold;
}
.font_content {
  font-size: 16px;
}
.font_mini {
  font-size: 15px;
  color: #555;
}
.font_emphasis {
  font-size: 18px;
  font-weight: bold;
  color: #FF6347;
}
.header {
  grid-row: 1/2;
  grid-column: 2/3;
  background-color: #c5c5d2;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.left-sidebar {
  grid-row: 1/4;
  grid-column: 1/2;
  background-color: #c5c5d2;
  text-align: center;
  margin-right: 10px;
}
.left-sidebar ul {
    list-style: none;
    padding: 0;
}
.left-sidebar ul:last-child{
    position: fixed;
    bottom: 0;
}
.left-sidebar ul li {
    margin: 10px;
    display: flex;
    justify-content: flex-end;
}
#company_logo {
  padding-top: 10px;
  width: 100px;
  height: auto;
}
.subject {
  font-weight: bold;
  margin-top: 10px;
}
.container{
  grid-row: 2/3;
  grid-column: 2/3;
  padding-left: 20px;
}
/* FOOTER */
.footer {
  grid-row: 3/4;
  grid-column: 2/3;
  font-size: 12px;
  max-width: 100%;
  padding: 20px;
  background-color: #565869;
  margin-top: 20px;
  text-align: right;
}
.footer > ul {
	display: flex;
	flex-direction: column;
	align-items: flex-end;
}

.footer > ul > li {
	display: flex;
	flex-direction: column;
	
}
.text-footer-light-gray{
  font-weight: bold;
  color: #CCCCCC;
}
#user-info {
  display: flex;
  align-items: center;
}

#user-id {
  margin-right: 10px;
}

#logout {
  color: #8B0000; /* 로그아웃 버튼 색상 설정 */
  text-decoration: none;
}

#logout:hover {
  text-decoration: underline; /* 마우스 오버시 밑줄 표시 */
}

</style>
</head>
<body>
  <div class="footer">
      <p class="text-footer-light-gray">&copy; 2023 COOKCCOOK company</p>
  </div>
  <div class="left-sidebar">
    <a href="/main">
      <img src="/img/cc_icon.png" alt="회사 로고" id ="company_logo">
    </a>
    <ul>
      <li></li>
      <li><span class="font_title">관리자 페이지</span></li>
      <li></li>
      <li></li>
      <li></li>
      <li class="font_subtitle subject">회원 관리</li>
      <li><a class="font_content" href="/admin/adminmemberalllist">전체 회원 조회</a></li>
      <li><a class="font_content" href="/admin/influencerinfo">인플루언서 조회</a></li>
      <li><a class="font_content" href="#">차단 회원 조회</a></li>
      <li></li>
      <li class="font_subtitle subject">게시글 관리</li>
      <li><a class="font_content" href="/admin/adminarticle">관리자 게시글</a></li>
      <li><a class="font_content" href="/admin/adminarticle">일반회원 게시글</a></li>
      <li><a class="font_content" href="#">인플루언서 게시글</a></li>
      <li></li>
      <li class="font_subtitle subject">직거래 관리</li>
      <li><a class="font_content" href="/admin/adminsellerList">판매자 회원 조회</a></li>
      <li><a class="font_content" href="/admin/adminsellerconfirm">신규 판매자 승인</a></li>
      <li><a class="font_content" href="/admin/adminproductalllist">판매 상품 목록</a></li>
      <li></li>
      <li class="font_subtitle subject">구독 관리</li>
      <li><a class="font_content" href="#">구독 정보 관리</a></li>
      <li><a class="font_content" href="/admin/subMemberList">구독 회원 관리</a></li>
      <li></li>
      <li class="font_subtitle subject">통계 대시보드</li>
      <li><a class="font_content" href="/admin/adminchartview">매출 통계</a></li>
      <li><a class="font_content" href="/admin/adminchartview3">판매량 통계</a></li>
      <li><a class="font_content" href="/admin/adminchartview2">판매자 통계</a></li>
      <li></li>
      <li class="font_subtitle subject">마이페이지</li>
      <li><a class="font_content" href="/admin/adminmypage">내 정보</a></li>
      <li><a class="font_content" href="/admin/adminupdateinfo">내 정보 수정</a></li>
    </ul>
    <ul>
      <li><a href="adminlogout" id="logout">로그아웃</a></li>
    </ul>
</div>