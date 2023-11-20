<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>커뮤니티 게시글 작성 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<script src="/js/lib/jquery-3.7.1.js"></script>

<script type="text/javascript">
	$().ready(function() {
		$("#form").click(function() {
			console.log("클릭")
		})
		var editor = GenerateEditor("content");
		
		editor.then(function(data) {
			data.setData("${articleVO.content}");
			
			$(".save-btn").click(function(event) {
				console.log(data.getData());
				event.preventDefault();
				$("input.content").val(data.getData())
				$("#articleVO").submit();
			})	
		})
		
	})
</script>

<style>
    .title-sentence {
        display: block;
        width: 500px;
        height: 35px;
    }

    .title,
    .content {
        font-size: 1.5em;
        margin-bottom: 5px;
    }

    .save-btn {
        display: inline-block;
        background-color: #FB8F67;
        width: 80px;
        height: 30px;
        text-align: center;
        padding-top: 1px;
        border-radius: 5px;
        color: #fff;
        border: none;
        float: right;
        cursor: pointer;
        margin-right: 7px;
        margin-top: 20px;
    }

    .cancel-btn {
        display: inline-block;
        background-color: #FB8F67;
        width: 80px;
        height: 25px;
        text-align: center;
        padding-top: 5px;
        border-radius: 5px;
        color: #fff;
        border: none;
        float: right;
        font-size: 13.5px;
        cursor: pointer;
        margin-top: 20px;
    }
</style>
<jsp:include page="../layout/header2.jsp"></jsp:include>
    <h1>게시글 수정</h1>
    <form:form modelAttribute="articleVO" method="post" enctype="multipart/form-data" action="/community/communitywrite">
    
        <div class="section-wrapper">
            <p class="title">제목</p>
            <input type="text" name="title" required class="title-sentence" value="${articleVO.title}">
        </div>
        
        <div class="section-wrapper">
            <p class="content">내용</p>
            <div id="content"></div>
        </div>
        <input type="hidden" name="content" class="content" />
        <a href="/community/communitylist" class="cancel-btn">취소</a>
        <button type="button" class="save-btn">저장</button>
    </form:form>
   
<jsp:include page="../layout/footer.jsp"></jsp:include>