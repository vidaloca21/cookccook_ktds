<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<title>내가 작성한 글 보기</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type="text/css">
	a:link, a:hover, a:active, a:visited {
		color: #333;
		text-decoration: none;
	}
div.tableGrid {
		height: 600px;
		overflow: scroll;
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
    	#sidebar {
            background-color: #FF8C69;
            color: #fff;
            padding: 20px;
            width: 200px;
            display: flex;
            flex-direction: column;
        }
        
        #content {
            flex: 1;
            padding: 20px;
        }
        
        .menu-item {
            margin: 10px 0;
            text-align: center;
        }

        .menu-item a {
            text-decoration: none;
            color: #fff;
        }
        .page-title {
	        font-size: 32px;
	        font-weight: bold;
	        text-align: center;
	        color: #333; 
	        text-transform: uppercase;
	        margin-top: 5px; /* 상단 여백 조정 */
	    }
        
    
    </style>

<script src="/js/lib/jquery-3.7.1.js"></script>
<jsp:include page="../layout/header_mem.jsp"></jsp:include>
<h2 class="page-title">내가 작성한 글 보기</h2>
	<div class="grid tableGrid">
		<div class="right-align">
			회원님이 작성하신 글은 총 ${memberArticle.size()} 건의 글이 검색되었습니다.
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>게시글 제목</th>
					<th>게시글 제목</th>
					<th>게시글 내용</th>
					<th>작성일시</th>
					<th>수정일시</th>
					<th>비공개일시</th>
					<th>좋아요 수</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			    <c:choose>
			        <c:when test="${not empty memberArticle}">
			            <c:forEach items="${memberArticle}" var="article">
		                    <tr>
		                        <td>${article.articleId}</td>
		                        <td>${article.title}</td>
		                        <td>${article.content}</td>
		                        <td>${article.postDate}</td>
		                        <td>${article.editDate}</td>
		                        <td>${article.hiddenDate}</td>
		                        <td>${article.likeCnt}</td>
		                        <td>${article.viewCnt}</td>
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
			<!-- <div class="right-align">
			    <a href="/admin/memberpage/excel/download">글 목록 엑셀 다운로드</a>
			</div> -->
		
	</div>


<jsp:include page="../layout/footer.jsp"></jsp:include>