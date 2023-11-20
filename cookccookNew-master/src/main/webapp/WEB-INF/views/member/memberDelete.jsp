<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<title>회원 탈퇴 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
		#content{
        	text-align: center;
	        display: flex;
	        flex-direction: column;
	        align-items: center;
	        padding: 20px;
	        border: 1px solid #ccc;
	        border-radius: 10px;
	        background-color: #f0f0f0;
        }
		
		.delete-title{
			font-size: 32px;
	        font-weight: bold;
	        text-align: center;
	        color: #333; 
	        margin-top: 10px;
		}
		
		#deleteMemberForm{
			
		}
		.form-group-a {
		    margin-top: 80px;
		}
		.form-group-b {
		    margin-top: 20px;
		}
		
		
</style>

<script src="/js/lib/jquery-3.7.1.js"></script>
<jsp:include page="../layout/header2.jsp"></jsp:include>

	<div id = "content" style="display: flex; justify-content: center; align-items: center;">
		<form method="post" action="/member/memberDelete">
				<div>
					<h1 class="delete-title" > 회원 탈퇴 </h1>
				</div>		
				<div id="deleteMemberForm">
			            <h3>회원 탈퇴 약관</h3>
			            <p>1. 회원 탈퇴를 신청하실 때에는 신청서에 본인의 아이디와 비밀번호를 정확하게 기재하셔야 합니다.</p>
			            <p>2. 회원 탈퇴를 진행하시면 회원 정보와 모든 데이터가 삭제됩니다.</p>
			            <p>3. 탈퇴한 계정은 복구가 불가능하며, 삭제된 데이터는 복구할 수 없습니다.</p>
			            <p>4. 회원 탈퇴 시 발생하는 모든 책임은 회원 본인에게 있습니다.</p>
			    	<div class="form-group-a"> 
			            <label for="password">현재 비밀번호:</label>
		   				<input type="password" name="password" id="password" required>
		    		</div>
		    		
		    		<div class="form-group-b">	
		    			<input type="submit" value="계정 삭제">
					</div>
				</div>
		</form>
	</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>
