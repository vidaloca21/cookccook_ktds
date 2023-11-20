<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴 성공</title>
<style>
		.success-container {
		    position: relative;
		    text-align: center;
		}
		
		.success-image {
		    max-width: 100%;
		    height: auto;
		}
		
		.front-message {
		    position: absolute;
		    top: 35%;
		    left: 50%;
		    transform: translate(-50%, -50%);
		    color: white;
		    font-size: 24px;
		    background: rgba(0, 0, 0, 0.5);
		    padding: 10px;
		}

		.main-link {
		    position: absolute;
		    top: 500px;
		    left: 800px;
		    color: black;
		    font-size: 40px;
		    cursor: pointer;
		}
		.main-hello {
		    position: absolute;
		    top: 600px;
		    left: 800px;
		    color: #FF8C69;
		    font-size: 40px;
		    cursor: pointer;
		}
		
</style>
</head>
<div class="success-container">
    <a href="/main">
        <img class="success-image" src="/img/food_background.jpg" alt="Success delete">
        <div class="front-message">정상적으로 회원 탈퇴 처리 되셨습니다.<br> 페이지를 클릭하면 메인페이지로 이동합니다</div>
        <div class="main-link">메인페이지로 이동</div>
        <div class="main-hello">다음에 또 만나요 ~ ^ㅡ^</div>
    </a>
</div>
</html>