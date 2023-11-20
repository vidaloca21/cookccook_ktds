<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 손안의 요리책 플랫폼 - COOKCCOOK</title>
    <link rel="stylesheet" href="/css/layout/lib/layout.css">
	<script src="/js/lib/jquery-3.7.1.js"></script>
	<script type="text/javascript">

	$().ready(function() {
		const id = "${member.memberId}"
		let today = new Date();   
		let todayString = "";
		let month = today.getMonth() + 1;  // 월
		let date = today.getDate();  // 날짜
		if (date < 10) {
			todayString = month + '-0' +date
		}
		else {
			todayString = month + '-' +date
		}
		let isBirthday = (todayString == "${member.memBirthday}".substring(5))
		if (isBirthday) {
			//alert("")
		}
		$.get("/api/recomm/ingredient", function(response) {
			console.log(response)
			let articleByIngName = [];
			for (var i=0; i < 5; i++) {
				let recipeCard = $("<div class='recipeCard'></div>")
				let recipeTitle = $("<div class='recipeTitle'></div>")
				let linkDOM = $("<a href='/recipe/"+response.articleList[i].articleId+"' />")
				let imageDOM = $("<img id='"+response.articleList[i].articleId+"' class='rcpImg' src='/article/titleimg/"+response.articleList[i].recipeVO.titleImgPath+"'/>")
				let titleDOM = $("<p>"+response.articleList[i].recipeVO.recipeName+"</p>")
				linkDOM.append(imageDOM)
				recipeCard.append(linkDOM)
				recipeCard.append(titleDOM)
				$("#rcpTop").append(recipeCard)
			}
			$("#nameTop").append(response.ingName)
		})
		$.get("/api/recomm/tagname", function(response) {
			let articleByTagName = [];
			for (var i=0; i < 5; i++) {
				let recipeCard = $("<div class='recipeCard'></div>")
				let recipeTitle = $("<div class='recipeTitle'></div>")
				let linkDOM = $("<a href='/recipe/"+response.articleList[i].articleId+"' />")
				let imageDOM = $("<img src='/article/titleimg/"+response.articleList[i].recipeVO.titleImgPath+"' />")
				let titleDOM = $("<p>"+response.articleList[i].recipeVO.recipeName+"</p>")
				linkDOM.append(imageDOM)
				recipeCard.append(linkDOM)
				recipeCard.append(titleDOM)
				$("#rcpMid").append(recipeCard)
			}
			$("#nameMid").append(response.tagName)
		})
		$.get("/api/recomm/tagname", function(response) {
			let articleByTagName = [];
			for (var i=0; i < 5; i++) {
				let recipeCard = $("<div class='recipeCard'></div>")
				let recipeTitle = $("<div class='recipeTitle'></div>")
				let linkDOM = $("<a href='/recipe/"+response.articleList[i].articleId+"' />")
				let imageDOM = $("<img src='/article/titleimg/"+response.articleList[i].recipeVO.titleImgPath+"' />")
				let titleDOM = $("<p>"+response.articleList[i].recipeVO.recipeName+"</p>")
				linkDOM.append(imageDOM)
				recipeCard.append(linkDOM)
				recipeCard.append(titleDOM)
				$("#rcpBot").append(recipeCard)
			}
			$("#nameBot").append(response.tagName)
		})
		
		$("input[name=searchType]").change(function(){
			if($("input[name=searchType]:checked").val() == 'ingredient'){
				$("#ingLabel").removeClass("btn_empty")
				$("#ingLabel").addClass("btn_fill")
				$("#nlpLabel").removeClass("btn_fill")
				$("#nlpLabel").addClass("btn_empty")
			}	
			else if($("input[name=searchType]:checked").val() == 'nlpSearch'){
				$("#ingLabel").removeClass("btn_fill")
				$("#ingLabel").addClass("btn_empty")
				$("#nlpLabel").removeClass("btn_empty")
				$("#nlpLabel").addClass("btn_fill")
			}
		});

		$.get("/api/sellerChart/memRecommend", function(response) {
			let articleByLike = [];
			for (var i=0; i < 5; i++) {
				let recipeCard = $("<div class='recipeCard'></div>")
				let recipeTitle = $("<div class='recipeTitle'></div>")
				let linkDOM = $("<a href='/recipe/"+response.difference[i].articleId+"' />")
				let imageDOM = $("<img src='/article/titleimg/"+response.difference[i].titleImgPath+"' />")
				let titleDOM = $("<p>"+response.difference[i].recipeName+"</p>")
				linkDOM.append(imageDOM)
				recipeCard.append(linkDOM)
				recipeCard.append(titleDOM)
				$("#rcpLike").append(recipeCard)
			}
			//$("#nameTop").append(response.targetMemberId)
		})
		
		$.get("/api/dailyrank", function(response) {
			let articleBydailyrank = [];
			for (var i=0; i < 5; i++) {
				let recipeCard = $("<div class='recipeCard'></div>")
				let recipeTitle = $("<div class='recipeTitle'></div>")
				let linkDOM = $("<a href='/recipe/"+response.result[i].articleId+"' />")
				let imageDOM = $("<img src='/article/titleimg/"+response.result[i].recipeVO.titleImgPath+"' />")
				let titleDOM = $("<p>"+response.result[i].recipeVO.recipeName+"</p>")
				linkDOM.append(imageDOM)
				recipeCard.append(linkDOM)
				recipeCard.append(titleDOM)
				$("#rcpDaily").append(recipeCard)
			}
			$("#dailyLike").append(response.result)
		})
		$.get("/api/mysubsrecomm", function(response) {
			let articleBymysubsrecomm = [];
			for (var i=0; i < 5; i++) {
				let recipeCard = $("<div class='recipeCard'></div>")
				let recipeTitle = $("<div class='recipeTitle'></div>")
				let linkDOM = $("<a href='/recipe/"+response.recipeList[i].articleId+"' />")
				let imageDOM = $("<img src='/article/titleimg/"+response.recipeList[i].recipeVO.titleImgPath+"' />")
				let titleDOM = $("<p>"+response.recipeList[i].recipeVO.title+"</p>")
				linkDOM.append(imageDOM)
				recipeCard.append(linkDOM)
				recipeCard.append(titleDOM)
				$("#rcpMysub").append(recipeCard)
			}
			$("#mysubsreLike").append(response.result)
		})
    })
</script>
    <style type='text/css'>
	#searchMain {
		display:flex;
		justify-content: center;
		font-size: 20px;
		height: 60px;

	}
	.recipeList {
		display: flex;
		flex-direction: column;
		margin-top: 10px;
	}
	.recipeArea {
		display: flex;
		flex-direction: row;
		justify-content: space-evenly;
		width: 100%;
	}
	.recipeCard {
		width: 18%;
		height: 240px;
		cursor: pointer;
		text-align: center;
	}
	.recipeCard > a > img {
		width: 90%;
		height: 200px;
		object-fit:cover;
		border-radius: 20px;
		transition: 0.1s;
	}
	
	.recipeCard > a > img:hover {
		width: 160px;
		height: 220px;
	}

	p {
		margin-block-start: inherit;
		FONT-WEIGHT: BOLD;
		FONT-SIZE: 16PX;
		text-align: center;
		color: #444;
		letter-spacing: 0.9;
		margin-top: 5px;
	}
	
	input[name=searchType] {
		visibility: hidden;
		margin: 0;
		padding: 0;
	}
	#ingLabel, #nlpLabel {
		width: 70px;
		height: 50px;
		cursor: pointer;
		margin-right: 1px;
		display: none;
	}
	input[type=search] {
		width: 700px;
		padding: 10px;
		border: 3px solid #FB8F67;
		border-top-left-radius: 10px;
		border-bottom-left-radius: 10px;
		border-right: 0px;
		outline: none;
		font-size: 16px;
	}

	#search-btn {
		width: 73px;
		background-image: url("/img/search_orange_icon.png");
		background-size: 75px;
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
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<div class="container">
		<form method="post" action="/recipe/search">
			<div id="searchMain">
				<input type="hidden" name="searchType" id="nlpSearch" value="nlpSearch" />
				<input type="search" name="searchKeyword" placeholder="어떤 레시피를 원하세요?" />
				<button id="search-btn"></button>
			</div>
		</form>
		<div class="recipeList">
			<h2>추천 태그 #<span id="nameBot"></span></h2>
			<div class="recipeArea" id="rcpBot"></div>
		</div>
		<div class="recipeList">
			<h2>추천 재료 #<span id="nameTop"></span></h2>
			<div class="recipeArea" id="rcpTop"></div>
		</div>
		<div class="recipeList">
			<h2>추천 태그 #<span id="nameMid"></span></h2>
			<div class="recipeArea" id="rcpMid"></div>
		</div>
		<sec:authorize access="isAuthenticated()">
		<div class="recipeList">
			<h2>좋아요 기반 추천<span id="nameLike"></span></h2>
			<div class="recipeArea" id="rcpLike"></div>
		</div>
		</sec:authorize>
		<div class="recipeList">
			<h2>데일리 랭크<span id="dailyLike"></span></h2>
			<div class="recipeArea" id="rcpDaily"></div>
		</div>
		<sec:authorize access="isAuthenticated()">
		<div class="recipeList">
			<h2>구독 상품 추천<span id="mysubsreLike"></span></h2>
			<div class="recipeArea" id="rcpMysub"></div>
		</div>
		</sec:authorize>

	</div>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
