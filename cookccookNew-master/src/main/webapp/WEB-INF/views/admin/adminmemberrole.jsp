<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>회원 권한 수정 페이지</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#btn-regist").click(function() {
			let memberVO = {
				memEmail: $("input[name=memEmail]").val(),
				role: $("select[name=role]:selected").val(),
				blockYn: $("select[name=blockYn]:selected").val()
			}
			$.post("/admin/adminmemberrole", memberVO, function(response) {
				if(response.result) {
					alert("성공적으로 변경되었습니다")
					window.close();  
				}
			})
		})
	})
</script>
<style type="text/css">
	.container{
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding-bottom: 200px;
	}
	div.grid {
		display: grid;
		grid-template-columns: 120px 1fr;
		grid-template-rows: 28px 28px 28px 28px 1fr;
		row-gap: 10px;
	}
	label {
		padding-left: 10px;
	}
	div.errors {
		background-color: #FF00004A;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	div.errors:last-child {
		margin-bottom: 10px;
	}
	a:hover{
		text-decoration: underline;
	}
	.membercontrol {
		display: flex;
		width: 50%;
		word-wrap: break-word;
		word-break: keep-all;
		line-height: 1.8;
		align-items: flex-end;
	}
	.right-align{
		grid-column: 1/3;
		display: flex;
		justify-content: flex-end;
	}
	#btn-regist {
		width: 80px;
		height: 40px;
	}
</style>
</head>
<body>
    <div class="container">
		<h4>회원 등급 변경</h4>
		<div class="membercontrol">
			<form:form id="membercontrol" action="/admin/adminmemberrole" modelAttribute="memberVO" method="post">
				<div>
					<form:errors path="memEmail" element="div" cssClass="errors" />
					<form:errors path="role" element="div" cssClass="errors" />
					<form:errors path="blockYn" element="div" cssClass="errors" />
				</div>
				<div class="grid">
					<label for="memEmail">회원 이메일</label>
					<input type="email" name="memEmail" id="memEmail" value="${memberVO.memEmail}" readonly/>
			
					<label for="role">회원 등급</label>
					<select name="role" id="role">
						<option value="USER" ${memberVO.role == 'USER' ? 'selected' : ''}>일반회원</option>
						<option value="SELLER" ${memberVO.role == 'SELLER' ? 'selected' : ''}>판매자</option>
						<option value="INFLUENCER" ${memberVO.role == 'INFLUENCER' ? 'selected' : ''}>인플루언서</option>
						<option value="ADMIN" ${memberVO.role == 'ADMIN' ? 'selected' : ''}>관리자</option>
					</select>
					<label for="blockYn">회원 상태</label>
					<select name="blockYn" id="blockYn">
						<option value="N" ${memberVO.blockYn == 'N' ? 'selected' : ''}>활동</option>
						<option value="Y" ${memberVO.blockYn == 'Y' ? 'selected' : ''}>차단</option>
						<option value="R" ${memberVO.blockYn == 'R' ? 'selected' : ''}>탈퇴</option>
					</select>
					<div class="right-align">
						<button id="btn-regist" >저장</button>
					</div>
				</div>
			</form:form>
		</div>
    </div>
</body>
</html>