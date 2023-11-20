<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<title>회원 목록</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout_as.css">
<style type="text/css">
	a:link, a:hover, a:active, a:visited {
		/*color: #333;*/
		text-decoration: none;
	}

	table.table {
		border-collapse: collapse;
		border: 1px solid #DDD;
	}

	table.table > thead > tr {
		background-color: #FFF;
	}

	table.table > thead th {
		padding: 10px;
		color: #333;
	}

	table.table th, table.table td {
		border-right: 1px solid #F0F0F0;
	}

	table.table th:last-child,
	table.table td:last-child {
		border-right: none;
	}

	table.table > tbody > tr:nth-child(odd) {
		background-color: #F5F5F5;
	}

	table.table > tbody tr:hover {
		background-color: #FAFAFA;
	}

	table.table > tbody td {
		padding: 10px;
		color: #333;
	}

	div.grid {
		display: grid;
		grid-template-columns: 1fr;
		grid-template-rows: 28px 28px 1fr 28px;
		row-gap: 10px;
	}
	div.grid div.right-align {
		text-align: right;
	}
	ul.horizontal-list {
	   padding: 0px;
	   margin: 0px;
	}
	ul.horizontal-list li {
	   display: inline;
    }
    
    /* #admin-exceldownload{
    	
    } */
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<jsp:include page="../layout/header_admin.jsp"></jsp:include>
	<div class="grid">
	    
		<%-- <div class="right-align">
    		총 ${memberListVO.memberCnt} 명의 회원이 검색되었습니다.
		</div> --%>
		<table class="table">
			<thead>
				<tr>
					<th>멤버 ID</th>
					<th>이메일</th>
					<th>회원 등급</th>
					<th>이름</th>
					<th>닉네임</th>
					<th>전화번호</th>
					<th>차단 상태</th>
					<th>회원 가입일</th>
					<th>회원 탈퇴일</th>
					<th>생일</th>
					<th>회원 가입 방식</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
			    <c:choose>
			        <c:when test="${not empty memberListVO.memberList}">
			            <c:forEach items="${memberListVO.memberList}" var="member">
		                    <tr>
		                        <td>${member.memberId}</td>
		                        <td>
		                            <a href="/admin/${member.memEmail}">
		                                <c:out value="${member.memEmail}" />
		                            </a>
		                        </td>
		                        <td>${member.role}</td>
		                        <td>${member.memName}</td>
		                        <td>${member.memNickname}</td>
		                        <td>${member.memPhoneNumber}</td>
		                        <td>${member.blockYn}</td>
		                        <td>${member.memJoinDate}</td>
		                        <td>${member.memUnregistDate}</td>
		                        <td>${member.memBirthday}</td>
		                        <td>${member.provider}</td>
		                        <td>${member.memAddress}</td>
		                    </tr>
		                </c:forEach>
			        </c:when>
			        <c:otherwise>
			            <tr>
			                <td colspan="11">등록된 회원이 없습니다.</td>
			            </tr>
			        </c:otherwise>
			    </c:choose>
			</tbody>
		</table>
		
			<!-- <div id="admin-exceldownload">
			    <a href="/admin/memberpage/excel/download">회원 목록 엑셀 다운로드</a>
			</div>  -->
		
	</div>
	
<jsp:include page="../layout/footer.jsp"></jsp:include>