<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>관리자 회원 권한 수정 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<link rel="stylesheet" type="text/css" href="styles.css">
<style type="text/css">
	.container {
		display: flex;
		flex-direction: column;
		text-align: left;
	}
	div.grid {
		display: grid;
		grid-template-columns: 200px 1fr;
		grid-template-rows: 28px 28px 28px 28px 1fr;
		row-gap: 20px;
	}
	div.grid > div.btn-group {
		display: grid;
		grid-column: 1 / 3;
	}
	div.grid div.right-align {
		text-align: right;
	}
	label {
		padding-left: 10px;
	}
	button, input {
		padding: 10px;
		border: 1px solid #CCC;
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
	.available {
		background-color: #0F03;
	}
	.unusable {
		background-color: #F003;
	}
	.info-button{
		padding-left:10px;
		font-weight: bold;
	}
	label{
		font-weight: bold;
	}
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
	<div class="container">
		<h2>회원 ID : ${memberVO.memEmail}</h2>
		<form:form modelAttribute="memberVO" method="post">
			<div>
				<form:errors path="role" element="div" cssClass="errors" />
				<form:errors path="blockYn" element="div" cssClass="errors" />
			</div>
			<div class="grid">
				<%-- <input type="text" name="role" id="role" value="${memberVO.role}" /> --%>
				<label for="role">현재 ROLE: ${member.role}</label>
				<select name="role" id="role">
					<option value="USER" ${memberVO.role == 'USER' ? 'selected' : ''}>USER</option>
					<option value="SELLER" ${memberVO.role == 'SELLER' ? 'selected' : ''}>SELLER</option>
					<option value="INFLUENCER" ${memberVO.role == 'INFLUENCER' ? 'selected' : ''}>INFLUENCER</option>
					<option value="ADMIN" ${memberVO.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
					<%-- <option value="" ${memberVO.memSex == null ? 'selected' : ''}>선택 안 함</option> --%>
				</select>
				
				<p class="info-button">USER : 일반 회원 <br/></p> 
				<p class="info-button">SELLER : 판매자 회원 <br/></p>
				<p class="info-button">INFLUENCER : 인플루언서<br/></p>
				<p class="info-button">ADMIN : 관리자<br/></p>
				
				
				<label for="blockYn">현재 회원 상태 : ${member.blockYn}</label>
				<%-- <input type="text" name="blockYn" id="blockYn" value="${memberVO.blockYn}" /> --%>
				<select name="blockYn" id="blockYn">
					<option value="N" ${memberVO.blockYn == 'N' ? 'selected' : ''}>N</option>
					<option value="Y" ${memberVO.blockYn == 'Y' ? 'selected' : ''}>Y</option>
					<option value="R" ${memberVO.blockYn == 'R' ? 'selected' : ''}>R</option>
					<%-- <option value="" ${memberVO.memSex == null ? 'selected' : ''}>선택 안 함</option> --%>
				</select>
				
				<p class="info-button">N : 일반 회원 <br/></p> 
				<p class="info-button">Y : 차단 회원 <br/></p>
				<p class="info-button">R : 탈퇴 회원<br/></p>
				
				<div class="btn-group">
					<div class="right-align">
						<input id="btn-regist" type="submit" value="등록" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>






				
