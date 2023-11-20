<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<script src="/js/lib/jquery-3.7.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/lib/ckeditor.js"></script>
<script src="/js/Editor.js"></script>

    <script type="text/javascript">
        $().ready(function() {

            // 메뉴 처리
            $(".menu").click(function() {
                $(this).addClass("menu_highlight")

                var selectedIndex = $(this).index();

                localStorage.setItem('selectedMenu', selectedIndex)
                console.log(selectedIndex)
            })

            $("#login").click(function() {
                selectedIndex == null;
            })

            var selectedIndex = localStorage.getItem('selectedMenu')
            if (selectedIndex !== null) {
                $(".menu").eq(selectedIndex).addClass("menu_highlight")
            }


            // 계정별 로그인 부분 버튼 처리
            var checkIfLoggedIn = function () {
                if (currentTarget) {
                    
                }
            }

        })
    </script>

</head>
<body>
		
    <div class="header">

        <div class="icon_bar">
            <a href="/main"><img src="/img/cc_icon.png" id="icon"/></a>
        </div>

        <div class="navi_bar"></div>

        <div class="mem_bar">
        	<sec:authorize access="!isAuthenticated()">
        		<div class="none_mem"> 
		            <div id="login"><a href="/member/login">로그인</a></div>
		            <div id="signup"><a href="/member/memberregist">회원가입</a></div>
	            </div>
            </sec:authorize>
            
            <sec:authorize access="isAuthenticated()">
            <div class="mem">
                <div id="logout"><a href="/member/memberlogout">로그아웃</a></div>
                <div id="myinfo"><a href="/member/mypage">내 정보</a></div>
                <div id="managementPg"> 
                    <sec:authorize access="hasRole('ADMIN')">
                        <a href="/admin/memberAllList">ADMIN</a>
                        <a href="seller/productlist">SELLER</a>
                        <a href="/">쿡꾹으로 돌아가기</a>
                    </sec:authorize>
                </div>
            </div>
            </sec:authorize>
        </div>

    </div>
    
	<div class="main">
    
        <div id="sidebar">

            <div class="menu">
                <a href="/admin/memberAllList">회원 관리</a>
            </div>

            <!-- <div class="menu">
                <a href="/admin/adminmemberrole">관리자 권한 수정</a>
            </div> -->
            
            <div class="menu">
                <a href="/admin/adminChart">통계 관리</a>
            </div>	    
        </div>
    <div class="container">