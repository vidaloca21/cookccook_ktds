<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    </style>
</head>
<body>
    <div id="container">
        <aside id="left-sidebar">
        	<a href="/admin/adminhome">
        		<img src="/img/cc_icon.png" alt="회사 로고" id ="company_logo">
            </a>
            <nav>
                <ul>
                    <li><a href="/main">메인 페이지</a></li>
                    <li><a href="/admin/adminmemberalllist">회원 전체 조회</a></li>
                    <li><a href="/admin/adminmemberrole">회원 권한 관리</a></li>
                    <li><a href="/admin/adminchartview">통계</a></li>
                    <li><a href="/admin/adminarticle">작성 글 목록</a></li>
                    <li><a href="/admin/adminmypage">내 정보</a></li>
                    <li><a href="/admin/adminupdateinfo">내 정보 수정</a></li>
                </ul>
            </nav>
        </aside>
        <main>
            <header>
                <h1>관리자 페이지</h1>
                <div id="user-info">
                	<span id="user-id">회원 아이디 : ${member.memEmail }</span>
                	<a href="adminlogout" id="logout">로그아웃</a>
                </div>
            </header>
             <section>
          </section>
        </main>
    </div>
    <footer>
        <p>&copy; 2023 COOKCCOOK company 관리자 페이지 </p>
    </footer>
</body>
</html>