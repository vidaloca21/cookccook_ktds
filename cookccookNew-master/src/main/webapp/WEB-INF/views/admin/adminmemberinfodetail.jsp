<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 페이지</title>
	<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style type="text/css">
    	
		body {
		    margin: 0;
		    padding: 0;
		    display: flex;
		    flex-direction: column;
		}
		
		#container {
		    display: flex;
		    overflow: hidden;
		}
		
		main {
		    padding: 20px;
		    /* content 배경 색상 background-color: #292929; */
		}
		
		header {
		   /* 헤더 배경 색상  background-color: #222; */
		    background-color: #c5c5d2;
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
		   /* 푸터 배경 색상  background-color: #222; */
		    background-color: #565869;
		    padding: 10px;
		    text-align: center;
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
		    color: #f00; /* 로그아웃 버튼 색상 설정 */
		    text-decoration: none;
		}
		
		#logout:hover {
		    text-decoration: underline; /* 마우스 오버시 밑줄 표시 */
		}
		#content{
			margin-bottom: 100px;
		}
		
		a:hover{
				text-decoration: underline;
		}
		
		p{
			font-size:20px;
			font-weight: bold;
		}
		
		section{
		    overflow: auto;
		}
		#title{
			font-size:40px;
		}
		.admin-control{
			font-weight: bold;
		}
		.admin-control-a1{
			font-weight: bold;
			text-decoration: underline;
		}
    </style>
    <script src="/js/lib/jquery-3.7.1.js"></script>
</head>
<body>
    <div id="container">
		<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
        <main>
            <header>
                <h1>관리자 페이지</h1>
                <div id="user-info">
                	<span id="user-id">회원 아이디 : ${member.memEmail }</span>
                	<a href="adminlogout" id="logout">로그아웃</a>
                </div>
            </header>
             <section>
             	<div id="content">
					<p><h1 id="title">회원 상세 페이지</h1>
					<%-- <a href="/admin/${member.memberId}">
					 <c:out value="${member.memEmail}" />
					</a> --%>
					
					<p>회원ID(FK): ${memberVO.memberId}<br/></p>
					<p>회원 Email: ${memberVO.memEmail}<br/></p>
					<%-- <p>회원 비밀번호 : ${memberVO.memPassword}<br/></p> --%>
					<p>회원 닉네임 : ${memberVO.memNickname}<br/></p>
					<p>회원 이름 : ${memberVO.memName}<br/></p>
					<p>회원 주소 : ${memberVO.memAddress}<br/></p>
					<p>회원 탄생연도: ${memberVO.memBirthyear}<br/></p>
					<p>회원 생일: ${memberVO.memBirthday}<br/></p>
					<p>회원 성별 : ${memberVO.memSex}<br/></p>
					<p>회원 가입날짜 : ${memberVO.memJoinDate}<br/></p>
					<p>회원 탈퇴날짜 : ${memberVO.memUnregistDate}<br/></p>
					<p>회원 프로필 사진 : ${memberVO.originFileName}<br/></p>
					<%-- <p>회원 SALT : ${memberVO.salt}<br/></p> --%>
					<p class="admin-control">회원 등급 : <a class="admin-control-a1" href="/admin/${memberVO.memEmail}/adminupdatemember">
					 <c:out value="${memberVO.role}" />
					</a><br/>
					</p>
					<p>회원 가입방식 : ${memberVO.provider}<br/></p>
					<p class="admin-control">회원 차단 여부 : <a class="admin-control-a1" href="/admin/${memberVO.memEmail}/adminupdatemember">
					 <c:out value="${memberVO.blockYn}" />
						</a><br/>
					</p>
					<p>회원 로그인 횟수 : ${memberVO.loginCnt}<br/></p>
					<p>회원 최근 로그인 성공일 : ${memberVO.latestLoginSuccessDate}<br/></p>
					<p>회원 최근 로그인 실패일 : ${memberVO.latestLoginFailDate}<br/></p>
					<p>회원 최근 허용된 IP : ${memberVO.latestAccessIp}<br/></p>
             	</div>
         	 </section>
        </main>
    </div>
    <footer>
        <p class="text-footer-light-gray">&copy; 2023 COOKCCOOK company 관리자 페이지 </p>
    </footer>
</body>
</html>