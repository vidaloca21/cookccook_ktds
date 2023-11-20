<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <title>관리자 HOME 페이지 입니다.</title>
   <link rel="stylesheet" type="text/css" href="/css/layout/lib/layout_as.css">
     <style type="text/css">
        #sidebar{
           background-color: #007bff;
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
     
     
     </style>
   <script src="/js/lib/jquery-3.7.1.js"></script>
      
<div id = "content">
    <h1>관리자님 환영합니다.</h1>

<h2>관리자만 입장할 수 있습니다.</h2>
<a href="/admin/memberAllList">회원 전체 조회 페이지로 가기</a><br>
<a href="/admin/adminmemberrole">관리자 회원 권한 수정 페이지</a><br>
<a href="/main">메인 페이지로 가기</a><br>
<a href="/admin/adminChart">통계페이지로 가기</a><br>
<a href="/admin/adminarticle"> 관리자 글작성 목록</a><br>
	<h2>관리자만 입장할 수 있습니다.</h2>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>