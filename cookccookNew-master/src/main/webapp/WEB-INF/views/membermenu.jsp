<%--
  Created by IntelliJ IDEA.
  User: krait4g
  Date: 2023-10-04
  Time: 오전 4:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="right-align">
    <ul class="horizontal-list">
        <sec:authorize access="!isAuthenticated()">
        <li>
            <a href="/member/memberregist">회원가입</a>
        </li>
        <li>
            <a href="/member/memberlogin">로그인</a>
        </li>
        </sec:authorize>
        <!-- 로그인 여부를 체크하는 과정과 사용자 인증정보 노출하는 것 -->
        <sec:authorize access="isAuthenticated()">
        <li style="margin-right: 15px;">
                ${memberName}
            (<sec:authentication property="name"/>)
        </li>
        <li>
            <a href="/member/memberlogout">로그아웃</a>
        </li>
            <li>
                <a href="/member/deletemember">탈퇴</a>
            </li>
        </sec:authorize>
    </ul>
</div>


