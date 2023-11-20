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
	
	        var selectedIndex = $(this).index()
	
	        sessionStorage.setItem('selectedMenu', selectedIndex)
	    })
	
	    $(".none_mem, .mem, .icon_bar").click(function() {
	        $(".menu").removeClass("menu_highlight")
	        sessionStorage.removeItem('selectedMenu')
	    })
	
	    var selectedIndex = sessionStorage.getItem('selectedMenu')
	    if (selectedIndex !== null) {
	        $(".menu").eq(selectedIndex).addClass("menu_highlight")
	    }
	
	    var checkIfLoggedIn = function () {
	        if (currentTarget) {
	        }
	    }

        })
    </script>
    
<style type="text/css">


 .sidebar {
 background-color: #FF8C69;
 padding: 20px;
 width: auto;
 height: 100%;
 font-size: 20px;
 font-weight: bold;
 display: flex;
 flex-direction: column;
 justify-content: flex-start;
 text-align: center;
 }
.sidebar_highlight {
  display: flex;
  height: 50px;
  font-size: 20px;
  font-weight: bold;
  background-color: #FF6347;
  align-items: center;
  justify-content: center;
  color: #fff;
  text-align: center;
 } 
 
 .menu-item {
 height:50px;
 margin: 10px;
 }
 .menu-item > a {
  color: #fff;
 
 }
 
</style>
</head>
<body>
	
    <div class="header">
        <div class="icon_bar">
            <a href="/main"><img src="/img/cc_icon.png" id="icon"/></a>
        </div>

        <div class="navi_bar">
            <div class="menu"><a href="/recipe/recipelist">레시피</a></div>
            <div class="menu"><a href="/community/communitylist">커뮤니티</a></div>
            <div class="menu"><a href="/subscribe">구독</a></div>
            <div class="menu"><a href="/shop/product">직거래</a></div>
        </div>
 
        <div class="mem_bar">
        	<sec:authorize access="!isAuthenticated()">
        		<div class=none_mem> 
		            <div id="login"><a href="/member/login">로그인</a></div>
		            <div id="signup"><a href="/member/memberregist">회원가입</a></div>
	            </div>
            </sec:authorize>
            
            <sec:authorize access="isAuthenticated()">
            <div class=mem>
                <div id="logout"><a href="/member/memberlogout">로그아웃</a></div>
                <div id="myinfo"><a href="/member/mypage">내 정보</a></div>
                
                <div id="managementPg"> 
                    <sec:authorize access="hasRole('ADMIN')">
                        <a href="/admin/memberAllList">ADMIN</a>
                        <a href="/shop/product">SELLER</a>
                        <a href="/">쿡꾹으로 돌아가기</a>
                    </sec:authorize>
                </div>
            </div>
            </sec:authorize>

        </div>
    </div>
	<div class="main">
    
    <div class="sidebar">
    
    	<div class="menu-item">
	        <a href="/member/mypage">마이 페이지</a>
	    </div>

	    <div class="menu-item">
	        <a href="/member/myinfoedit">내 정보 수정</a>
	    </div>

	    <div class="menu-item">
	        <a href="/member/myarticle">작성글 조회</a>
	    </div>
		
		<div class="menu-item">
			<a href="/member/shop/orderlist">주문 내역 조회</a>
		</div>	    
		<div class="menu-item">
			<a href="/member/subscribe/mysubs">구독 내역 조회</a>
		</div>	
	</div>
    
    <div class="container">
