<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
    <style type="text/css">

        .form-group {
            margin-bottom: 15px;
        }
  
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin:5px 0;
            margin-right: 10px;
            border: 1px solid #ccc;
        }
       
        #content{
        	text-align: center;
        	display: flex;
        }
        
        #login-container{
        	height:700px;
		    width:100%;
		    display:flex;
		    justify-content: center;
		    align-items:center;
		    align-content:center;
        }
        
        .loginfont{ 
		    padding-right: 340px;
		    padding-bottom: 30px;
		    font-size: 23px;
		    font-weight:bold;
		}
		
        .email-label{
		    padding-right: 355px;
		    padding-top: 10px;
		    padding-bottom: 10px;
		}
        .password-label{
		    padding-right: 340px;
		    padding-top: 10px;
		    padding-bottom: 10px;
		}
        
        
        #email{
		    display: block;
		    margin-left :auto ;
		    margin-right :auto ;
		    width: 400px;
		    height: 45px;
		}
		#password{
		    display: block;
		    margin-left :auto ;
		    margin-right :auto ;
		    width: 400px;
		    height: 45px;
		}
			
        #login-button{
		    background-color: black;
		    color: white;
		    width: 240px;
		    height: 54px;
		    margin-top: 20px;
		    border:1px solid darkgrey;
		    border-radius:5px;

		}
		
		#login-button:hover {
            background-color: #0056b3;
        }
        
        #join{
		    background-color: white;
		    width: 240px;
		    height: 54px;
		    margin-top: 10px;
		    border:1px solid darkgrey;
		    border-radius:5px;
		
		}
		#join:hover {
            background-color: #0056b3;
        }
		
    	
        div.errors {
            background-color: #FF00004A;
            opacity: 0.8;
            padding: 10px;
            color: #333;
        }
        div.errors:last-child {
            margin-bottom: 15px;
        }
        
    </style>
	<script src="/js/lib/jquery-3.7.1.js"></script>
	<jsp:include page="../layout/header2.jsp"></jsp:include>

<div id ="content">
	<div id="login-container">
		<form:form modelAttribute="memberVO" method="post" action="/member/login-proc">
		    <div class="loginfont">로그인</div>
		    <div>
		        <form:errors path="email" element="div" cssClass="errors"/>
		        <form:errors path="password" element="div" cssClass="errors"/>
		        <c:if test="${not empty message}">
		            <div class="errors">
		                    ${message}
		            </div>
		        </c:if>
		    </div>
		    <div class="form-group">
		        <label for="email" class="email-label">이메일</label>
		        <%-- <input type="email" id="email" name="memEmail" autocomplete="off" value="${memberVO.memEmail}" /> --%>
		        <input type="email" id="email" name="memEmail"  value="${memberVO.memEmail}" />
			</div>
			
			<div class="form-group">
		        <label for="password" class="password-label">비밀번호</label>
		        <%-- <input type="password" id="password" name="memPassword" autocomplete="off" value="${memberVO.memPassword}" /> --%>
		        <input type="password" id="password" name="memPassword" value="${memberVO.memPassword}" />
			</div>
			
			<div>
				<input name="remember-me" type="checkbox" />자동 로그인
			</div>
			<div>
				<input name="remember-id" type = "checkbox" />아이디 저장
			</div>
			
			<!--  csrf 공격 방어를 위해 동적 생성 -->
    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
					
		    <div class="form-group">
                
				<input type="hidden" name="next" value="${next}" />		                
		        <button type="submit" id="login-button">로그인</button>
		        <br>
		        <input id="join" type="button" value="회원가입" onClick="location=href='/member/memberregist'"> 
		        <br>
		        <br>
		        <div>
			         <a href="/oauth2/authorization/google">
			             <img src="/img/google_login.png" alt="구글 로그인" />
			         </a>        
			         <a href="/oauth2/authorization/kakao">
			             <img src="/img/kakao_login_medium_narrow.png" alt="카카오 로그인" />
			         </a>
			   </div>
		   </div>

		 </form:form>
   </div>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>





