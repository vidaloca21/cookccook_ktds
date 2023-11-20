<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>관리자 페이지</title>
<style type="text/css">
	.container {
		display: block;
		overflow: scroll;
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
		color: #DEDEDE;
		position: sticky;
		top: 0;
		background-color: #565869;
	}

	table.table th, table.table td {
		border-right: 1px solid #F0F0F0;
	}

	table.table th:last-child,
	table.table td:last-child {
		border-right: none;
	}

	table.table > tbody > tr:nth-child(odd) {
		background-color: #d9d9e3;
	}
	
	table.table > tbody td {
		padding: 10px;
		/*color: white;*/
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
	.page-title {
		font-size: 32px;
		font-weight: bold;
		text-align: center;
		color: white; 
		text-transform: uppercase;
		margin-top: 5px;
	}
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
	<p class="font_subtitle">전체 게시글 조회</p>
</div>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>게시글 ID</th>
					<th>게시글 분류</th>
					<th>작성자 ID</th>
					<th>제목</th>
					<th>본문 내용</th>
					<th>조회 수</th>
					<th>좋아요 수</th>
					<th>이벤트 여부</th>
					<th>작성일시</th>
					<th>수정일시</th>
					<th>비공개일시</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty memberArticle}">
						<c:forEach items="${memberArticle}" var="article">
							<tr>
								<td>${article.articleId}</td>
								<c:choose>
									<c:when test="${article.articleType eq 0}">
										<td>커뮤니티</td>
									</c:when>
									<c:otherwise>
										<td>레시피</td>
									</c:otherwise>
								</c:choose>
								<td>${article.memberId}</td>
								<c:choose>
									<c:when test="${article.articleType eq 0}">
										<td><a href="/community/${article.articleId}">${article.title}</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="/recipe/${article.articleId}">${article.title}</a></td>
									</c:otherwise>
								</c:choose>
								<td>${article.content}</td>
								<td>${article.viewCnt}</td>
								<td>${article.likeCnt}</td>
								<td>${article.eventStatus}</td>
								<td>${article.postDate}</td>
								<td>${article.editDate}</td>
								<td>${article.hiddenDate}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">등록된 글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>