<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>관리자 페이지</title>
<style type="text/css">
	.profile-title{
			font-size: 30px;
			font-weight: bold;
	}
	.content{
		margin-bottom: 100px;
		margin-top : 20px;
	}
	.container-resume {
			font-family: 'Noto Sans KR', sans-serif;
			font-weight: 300;
			word-wrap: break-word;
			word-break: keep-all;
			line-height: 1.8
	}	
	.blue {
		color: #3c78d8;
		font-size: 12pt;
	}
	.gray {
		color: gray;
	}
	.profile-img {
		max-height: 180px;
	}
	section {
		max-height: 80vh;
		overflow: auto; 
	}
	.grid-content {
		display:grid;
		grid-template-columns: 150px 1fr;
		grid-template-rows: 20px 20px 20px 20px 20px 20px 20px 20px 20px 20px;
		gap: 10px;
		border: 1px solid #EEE3;
	}
	.grid-content h3 {
		margin: 0px;
		padding: 0px;
		font-size: 12pt;
		background-color: #EEE8;
	}
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="container">
	<div class="container-resume">		
	<!-- Profile Start -->
		<div class="row mt-5">
			<div class="col-md-3 col-sm-12 pb-3 text-md-right text-center">
				<img class="img-fluid rounded profile-img" src="/member/profile-image" alt="Profile">
			</div>
			<div class="col-md-9 col-sm-12">
				<div class="row">
					<div class="col text-center text-md-left">
						<h1 class="blue">닉네임: ${member.memNickname } <small>(${member.role })</small></h1>
					</div>
				</div>
			</div>
		</div >
	<!-- Profile End -->
	<!-- Introduce Start -->
		<div class="row pt-5">
			<div class="col-12 col-md-3">
				<h2 class="blue">Profile</h2>
			</div>
			<div class="col-12 col-md-9">
				<div class="grid-content">
					<h3>아이디(이메일)</h3>
					<div>${member.memEmail}</div>
					<h3>닉네임</h3>
					<div>${member.memNickname }</div>
					<h3>회원 등급</h3>
					<div>${member.role }</div>
					<h3>핸드폰 번호</h3>
					<div>${member.memPhoneNumber }</div>
					<h3>이름</h3>
					<div>${member.memName }</div>
					<h3>주소</h3>
					<div>${member.memAddress }</div>
					<h3>성별</h3>
					<div>${member.memSex }</div>
					<h3>생일</h3>
					<div>${member.memBirthday }</div>
					<h3>회원 가입 날짜</h3>
					<div>${member.memJoinDate }</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>