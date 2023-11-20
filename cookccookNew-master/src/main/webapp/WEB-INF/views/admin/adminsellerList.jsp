<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>관리자 페이지</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	// $().ready(function() {
	// 	$(".memId").click(function() {
	// 		let memID = $(this).data("id");
	// 		window.open("/admin/adminmemberrole/"+memID, '회원 등급 변경', 'width=600px,height=400px,scrollbars=yes');
	// 	})
	// })
</script>
<style type="text/css">
	.container {
		display: flex;
		flex-direction: column;
	}
	table.table {
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
        width: auto;
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
		/*color: white;*/
	}
	.memberAlllist-title{
		font-size: 30px;
	}
	.memId {
		cursor: pointer;
	}
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
    <p class="font_subtitle">판매자 회원 조회</p>
</div>
<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th>멤버 ID</th>
				<th>이메일</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>평점</th>
				<th>전화번호</th>
				<th>주소</th>
                <th>사업자 번호</th>
                <th>차단 상태</th>
                <th>아이피 주소</th>
                <th>최근 접속일</th>
				<th>회원 가입일</th>
				<th>회원 탈퇴일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty memberList}">
                    <c:forEach items="${memberList}" var="member" >
						<tr>
							<td>${member.memberId}</td>
							<td>
								<span class="memId" data-id="${member.memEmail}"><c:out value="${member.memEmail}" /></span></a>
							</td>
							<td>${member.memName}</td>
							<td>${member.memNickname}</td>
                            <td>${member.sellerRating}</td>
							<td>${member.memPhoneNumber}</td>
							<td>${member.memAddress}</td>
                            <td>${member.businessNumber}</td>
							<td>${member.blockYn}</td>
                            <td>${member.ipAddress}</td>
                            <td>${member.latestLoginSuccessDate}</td>
							<td>${member.memJoinDate}</td>
							<td>${member.memUnregistDate}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="13">등록된 회원이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
</body>
</html>