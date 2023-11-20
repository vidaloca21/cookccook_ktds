<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>관리자 페이지</title>
<style type="text/css">
	.container{
		justify-content: flex-start;
	}
	table.table {
		width: 80%;
		border-collapse: collapse;
		border: 1px solid #DDD;
	}

	table.table > thead > tr {
		background-color: #c5c5d2;
	}

	table.table > thead th {
		padding: 10px;
		color: #333;
	}

	table.table th, table.table td {
		border-right: 1px solid #F0F0F0;
		text-align: center;
	}

	table.table th:last-child,
	table.table td:last-child {
		border-right: none;
	}

	table.table > tbody > tr:nth-child(odd) {
		background-color: #d9d9e3;
	}

	table.table > tbody td {
		padding-right: 10px;
	}

</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
	<p class="font_subtitle">인플루언서 수정</p>
</div>
	<div class= "container">
		<table class="table">
			<thead>
				<tr>
					<th>멤버 ID</th>
					<th>이메일</th>
					<th>회원 등급</th>
					<th>닉네임</th>
					<th>전화번호</th>
					<th>차단 상태</th>
					<th>회원 가입일</th>
					<th>작성 게시글 수</th>
					<th>받은 좋아요 수</th>
					<th>권한 수정</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty inflReadyList}">
						<c:forEach items="${inflReadyList}" var="member">
							<tr>
								<td>${member.memberId}</td>
								<td>
									<a href="/admin/${member.memEmail}">
										<c:out value="${member.memEmail}" />
									</a>
								</td>
								<td>${member.role}</td>
								<td>${member.memNickname}</td>
								<td>${member.memPhoneNumber}</td>
								<td>${member.blockYn}</td>
								<td>${member.memJoinDate}</td>
								<td></td>
								<td>${member.totalLike}</td>
								<td>
									<form modelAttribute="memberVO" action="/admin/influencerupgrade" method="post">
										<input type="hidden" name="memberId" value="${member.memberId}" />
										<input type="hidden" name="role" value="INFLUENCER" />
										<input type="submit" value="등업" />

									</form>

								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="11">대기 중인 회원이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>