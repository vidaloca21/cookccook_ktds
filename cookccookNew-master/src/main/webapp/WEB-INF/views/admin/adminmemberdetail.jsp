<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<title>회원 상세 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">

</style>

<script src="/js/lib/jquery-3.7.1.js"></script>
<jsp:include page="../layout/header_admin.jsp"></jsp:include>
<a href="/main">메인 페이지로 가기</a>
<a href="/admin/home">관리자 페이지로 가기</a>
<h1>Member Detail 회원 상세 페이지</h1>
<p>회원 Email: ${memberVO.memEmail}</p>
<%-- <a href="/admin/${member.memberId}">
 <c:out value="${member.memEmail}" />
</a> --%>

회원ID(FK): ${memberVO.memberId}<br/>
회원 Email: ${memberVO.memEmail}<br/>
회원 비밀번호 : ${memberVO.memPassword}<br/>
회원 닉네임 : ${memberVO.memNickname}<br/>
회원 이름 : ${memberVO.memName}<br/>
회원 주소 : ${memberVO.memAddress}<br/>
회원 탄생연도: ${memberVO.memBirthyear}<br/>
회원 생일: ${memberVO.memBirthday}<br/>
회원 성별 : ${memberVO.memSex}<br/>
회원 가입날짜 : ${memberVO.memJoinDate}<br/>
회원 탈퇴날짜 : ${memberVO.memUnregistDate}<br/>
회원 프로필 사진 : ${memberVO.originFileName}<br/>
회원 SALT : ${memberVO.salt}<br/>
회원 등급 : <a href="/admin/${memberVO.memEmail}/adminupdatemember">
 <c:out value="${memberVO.role}" />
</a><br/>
회원 가입방식 : ${memberVO.provider}<br/>
회원 차단 여부 : <a href="/admin/${memberVO.memEmail}/adminupdatemember">
 <c:out value="${memberVO.blockYn}" />
</a><br/>
회원 로그인 횟수 : ${memberVO.loginCnt}<br/>
회원 최근 로그인 성공일 : ${memberVO.latestLoginSuccessDate}<br/>
회원 최근 로그인 실패일 : ${memberVO.latestLoginFailDate}<br/>
회원 최근 허용된 IP : ${memberVO.latestAccessIp}<br/>

<jsp:include page="../layout/footer.jsp"></jsp:include>