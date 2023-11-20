<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<title>커뮤니티 게시판</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type='text/css'>
.container {
	overflow: hidden;
}

table {
	width: 100%;
	border-top: 1px solid #d3d3d3;
	border-collapse: collapse;
	text-align: center;
}

th {
	background-color: rgba(251,143,103,0.8);
	border-bottom: 1px solid #C0C0C0;
	padding: 10px;
}

td {
	border-bottom: 1px solid #d3d3d3;
	padding: 10px;
	font-weight: normal;
}

.download-btn-container {
    text-align: center;
}

 ul.page-nav {
    margin: 0px;
    padding: 0px;
    text-align: center;
}

ul.page-nav > li {
    display: inline-block;
    padding: 10px;
    color: #333;
}

ul.page-nav > li.active > a {
    color: #F00;
    font-weight: bold;
}

.shortcut-wrppaer {
	padding: 10px 0;
}

.action-container {
    display: flex;
    align-items: center;
	justify-content: space-between;
	margin-bottom: 10px;
	height: 55px;
	
}


.write-btn {
	width: 50px;
	height: 50px;
	cursor: pointer;
	position: fixed;
    bottom: 50px; 
    right: 50px; 
    z-index: 999;
    }
    
.admin {
	background-color: #ffe5da;
}
select[name=searchType] {
    display: none;
}
form {
    display: flex;
    padding-left: 63px;
	margin-top: 10px;
}
input[type=search] {
    width: 600px;
    padding: 10px;
    border: 3px solid #FB8F67;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
    border-right: 0px;
    outline: none;
    height: 60px;
    font-size: 17px;
    
}
#search-btn {
    width: 75px;
    background-image: url("/img/search_orange_icon.png");
    background-size: contain;
    background-repeat: no-repeat;
	background-color: white;
    display: inline-block;
    padding: 10px;
    border: 3px solid #FB8F67;
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
    border-left: 0px;
    outline: none;
}

</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	
	 function movePage(pageNo = 0) {
        $("#pageNo").val(pageNo)
        
        $("#search-form").attr({
           "method": "get",
           "action": "/community/communitylist"
        }).submit()
    }
    $().ready(function() {
       $("#search-btn").click(function() {
    	   movePage()
       })    
       $("#excelDownload").click(function() {
    	   downloadPage()
       })
       function downloadPage(pageNo = 0) {
           $("#pageNo").val(pageNo)
           
            $("#search-form").attr({
               "method": "get",
               "action": "/community/excel/download"
            }).submit()
       }
    })
</script>
	<jsp:include page="../layout/header2.jsp"></jsp:include>

		<div id="panel">
 			<form id="search-form">
	            <select name="searchType">
		            <option value="subject" ${searchArticleVO.searchType eq 'subject' ? 'selected' : '' } selected>제목</option>
		            <option value="content" ${searchArticleVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
	            </select>
	            <input type="search" name="searchKeyword" placeholder="검색어를 입력하세요." value="${searchArticleVO.searchKeyword}" />
	            <input type="hidden" id="pageNo" name="pageNo" />
	            <button id="search-btn"></button>
        	</form>
			<div class="panel-actions">
				<div class="action-container">
					<span style="margin-left: 10px;"> ▷ 총 ${articleList.articleCnt} 개의 게시물이 있습니다.</span>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_INFLUENCER')">
							<a href="/community/communitywrite" >
							<img src="/img/write_icon.png" class="write-btn"/></a>
							
					</sec:authorize>
				</div>
			</div>
		</div>
		
		<div>
			<table>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>게시일</th>
					<th>조회수</th>
					<th>좋아요</th>
				</tr>
			<c:forEach items="${articleList.articleList}" var="article" varStatus="loop">
				<tr class="${article.memberVO.role}">
					<td class="number">${(loop.index + 1) + (searchArticleVO.listSize * searchArticleVO.pageNo)}</td>
					<td class="title"><a href="/community/${article.articleId}">${article.title}</a>
					</td>
					<td class="writer">${article.memberVO.memNickname}</td>
					<td class="postDate">${article.postDate}</td>
					<td class="viewCnt">조회: ${article.viewCnt}</td>
					<td class="likeCnt">좋아요: ${article.likeCnt}</td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
		<div>
			<div>
				<ul class="page-nav">
				    <c:if test="${not empty articleList.articleList}">
					    <!-- 이전 페이지 그룹 -->
					    <c:if test="${searchArticleVO.hasPrevGroup}">
					        <li>
					            <a href="javascript:void(0)" onclick="movePage(0)">처음</a>
					        </li>
					        <li>
					            <a href="javascript:void(0)" onclick="movePage(${searchArticleVO.prevGroupStartPageNo})">이전</a>
					        </li>
					    </c:if>
					    
	                    <c:forEach begin="${searchArticleVO.groupStartPageNo}"
	                               end="${searchArticleVO.groupEndPageNo}"
	                               step="1"
	                               var="p">
							<li class="${searchArticleVO.pageNo eq p ? "active": ""}">
								<a href="javascript:void(0)" onclick="movePage(${p})">${p + 1}</a>
							</li>
	                    </c:forEach>
	                    
	                    <!-- 다음 페이지 그룹 -->
	                    <c:if test="${searchArticleVO.hasNextGroup}">
	                        <li>
	                            <a href="javascript:void(0)" onclick="movePage(${searchArticleVO.nextGroupStartPageNo})">다음</a>
	                        </li>
	                        <li>
	                            <a href="javascript:void(0)" onclick="movePage(${searchArticleVO.pageCount-1})">마지막</a>
	                        </li>
	                    </c:if>
                    </c:if>
				</ul>
			</div>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<div class="download-btn-container">
		        <a href="/community/excel/download" class="download-btn">엑셀 다운로드</a>
			</div>
		</sec:authorize>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
