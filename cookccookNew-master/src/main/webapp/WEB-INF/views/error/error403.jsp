<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error 404 </title>
<style>
		.error-container {
		    position: relative;
		    text-align: center;
		}
		
		.error-image {
		    max-width: 100%;
		    height: auto;
		}
		
		.error-message {
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
	    top: 100px;
	    left: 200px;
	    color: white;
	    text-decoration: underline;
	    cursor: pointer;
	}
</style>
</head>
<body>
    <a href="/main">
        <img class="error-image" src="/img/에러페이지.png" alt="Error Image">
        <div class="error-message">잘못된 접근입니다. 올바른 접속방법으로 접속하세요.<br> 페이지를 클릭하면 메인페이지로 이동합니다</div>
        <div class="main-link">메인페이지로 이동</div>
    </a>
</body>
</html>