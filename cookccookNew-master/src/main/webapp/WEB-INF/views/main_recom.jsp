<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 손안의 요리책 플랫폼-COOKCCOOK</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<style type="text/css">
        .recom_wrapper {
        width: 100%;
        height: 350px;
        overflow-x: auto; 
        white-space: nowrap; 
    }

    .recom_div {
        display: inline-block; 
        width: 300px; 
        margin-right: 10px; 
    }

    .art_img img {
        max-width: 300px; 
        height: 300px;
        padding: 10px;
        align-items: center;
    }
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    // annivRecomList 가져오기
    $.get("/annivRecomList", function(data) {
        // annivRecomList 처리
        data.forEach(function(recipe) {
            var div = $("<div>").addClass("recom_div");
            $("<div>").addClass("art_img").html('<img src="' + recipe.attImgSmall + '">').appendTo(div);
            $("<div>").addClass("recip_name").text(recipe.articleVO.title).appendTo(div);
            $(".recom_wrapper#annivRecom").append(div);
        });
    });

    // tagRecomList 가져오기 (articleId는 원하는 값으로 변경)
    var articleId = "ART000015";
    $.get("/tagRecomList/" + articleId, function(data) {
        // tagRecomList 처리
        data.forEach(function(recipe) {
            var div = $("<div>").addClass("recom_div");
            $("<div>").addClass("art_img").html('<img src="' + recipe.attImgSmall + '">').appendTo(div);
            $("<div>").addClass("recip_name").text(recipe.articleVO.title).appendTo(div);
            $(".recom_wrapper#tagRecom").append(div);
        });
    });
});
</script>
</head>
<body>
	<!-- 기념일 기준 추천 -->
	<div class="recom_wrapper">
		<c:forEach items="${annivRecomList}" var = "annivRecipeList">
			<div class="recom_div">
				<div class="art_img"><img src="${annivRecipeList.attImgSmall}"/></div>
				<div class="recip_name">${annivRecipeList.articleVO.title}</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="recom_wrapper">
		<c:forEach items="${tagRecomList}" var = "tagRecipeList">
			<div class="recom_div">
				<div class="art_img"><img src="${tagRecipeList.attImgSmall}"/></div>
				<div class="recip_name">${tagRecipeList.articleVO.title}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>