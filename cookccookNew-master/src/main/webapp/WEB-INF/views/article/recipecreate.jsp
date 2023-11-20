<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<title>새 레시피 등록</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<script src="https://unpkg.com/feather-icons"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
</script>

<style>
body {
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 0 20px;
	background-color: #fff;
	}

h3 {
	font-size: 24px;
	margin: 0 0 10px;
	}

ul,
li {
    list-style: none;
    padding-left: 0;
	}
	
.section {
    background-color: rgba(0,0,0,0.05);
    padding: 20px 15px;
    margin: 30px 0;

    .section-title {
        width: 100%;
        display: block;
        font-size: 30px;
        font-weight: bolder;
        text-align: left;
        line-height: 1.5;
    }
}

.main-wrapper {
    margin-top: 0;

    #recipeImage {
        width: 100%;
        margin: 0 auto;
        position: relative;
    }

    .inner-wrapper {
        width: 600px;
        overflow: hidden;
        align-items: left;
        margin: 0 auto;
    }

    .main-title {
        font-size: 30px;
        font-weight: bolder;
        text-align: left;
        line-height: 1.2;
        margin-top: 15px;
    }

    .main-date {
        color: #333;
        font-size: 12px;
        line-height: 1.5;
        margin-top: 10px;
    }

    .main-overlap {
        width: 90%;
        height: auto;
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        bottom: 5px;
        z-index: 10;
        display: grid;
        grid-template-columns: repeat(2,1fr);

        .main-counter {
            text-align: left;
            /* font-size: 0;
            &::after {
                content: "좋아요버튼";
                font-size: 0;
                display: block;
                background: url('../layout/KakaoTalk_20231026_203320294.png');
            } */
        }
    
        .main-like {
            text-align: right;
        }
    }
}
.ingredient-wrapper,
.tip-wrapper,
.progress-wrapper,
.related-wrapper,
.reply-wrapper {
    padding: 25px 30px;
}
input[name=recipeName] {
    width: 90%;
    height: 40px;
}
textarea[name=ingredientDetails] {
    width: 90%;
    height: 80px;
}
textarea[name=recipeTip] {
    width: 90%;
    height: 80px;
}
.progress-wrapper {
    .progress-list {
        width: 100%;
        
        .item-create {
            display: flex;
            flex-wrap: wrap;
            width: 100%;
            height: 120px;
        }
        .progress-img {
            width: 30%;
            height: 180px;
            display: inline;
            margin: auto 0;
            border-radius: 10%;
        }

        .progress-text {
            width: 60%;
            display: inline;
            margin: auto 0;
            font-size: 20px;
            font-weight: 500;
        }
    }
}
.item-create > ul:first-child{
    width: 70%;
}
.item-create > ul:last-child {
    width: 30%;
}
.item-create > ul:first-child > li > textarea {
    width: 90%;
}
       
.relatedRecipeList {
    display: flex;
    flex-direction: row;
    align-items: stretch;
}
.rcpCard {
    display: inline-block;
    width: 120px;
    height: 200px;
    margin-left: 10px;
    margin-right: 10px;
}
.rcpCard > a> img {
    width: 120px;
    height: 120px;
    overflow: hidden;
}
.reply-wrapper {

}
    
div.replies {
		display: grid;
		grid-column: 1 / 3;
}
.feather-heart {
    font-size: 12px;
    cursor: pointer;
    position: relative;
    top: 0;
    left: 0;
}

</style>
<jsp:include page="../layout/header2.jsp"></jsp:include>

<div class="container">
	<div id="recipeGrid">

        <form:form modelAttribute="recipeVO" method="post" enctype="multipart/form-data" action="/recipe/recipewrite">
        <!-- 젤 위 -->
        <div class="section main-wrapper">
            <div class="inner-wrapper">
                <div id="recipeImage">
                	<h3>제목 사진 첨부</h3>
                	<input type="file" name="attImgSmall"/>
                </div>
                <div id="recipeInfo">
                    <ul class="main-wrapper">
                        <li class="main-title">
                        	<h3>제목 입력</h3>
                        	<input type="text" name="recipeName" />
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- 재료 -->
		<div class="section ingredient-wrapper">
            <h3 class="section-title">재료 입력</h3>
            <textarea name="ingredientDetails" placeholder="요리 재료를 알려주세요!"></textarea>
        </div>

        <!-- 팁 -->
        <div class="section tip-wrapper">
            <h3 class="section-title">조리 팁</h3>
            <textarea name="recipeTip" placeholder="조리 팁을 알려주세요!"></textarea>
        </div>


        <!-- 순서 -->
        <div class="section progress-wrapper">
            <h3 class="section-title">조리 순서 등록</h3>
             <ul class="progress-list">
                <li class="item-create">
                	<ul>
                		<li><p class="progress-text">순서 1</p></li>
                        <li><textarea name="manual01" placeholder="1. "></textarea></li>
                	</ul>
                	<ul>
                        <li><p class="progress-text">첨부 이미지</p></li>
                        <li><input type="file" name="manualImg01" /></li>
                	</ul>
                </li>
                <li class="item-create">
                	<ul>
                		<li><p class="progress-text">순서 2</p></li>
                        <li><textarea name="manual02" placeholder="2. "></textarea></li>
                	</ul>
                	<ul>
                        <li><p class="progress-text">첨부 이미지</p></li>
                        <li><input type="file" name="manualImg02"/></li>
                	</ul>
                </li>
                <li class="item-create">
                	<ul>
                		<li><p class="progress-text">순서 3</p></li>
                        <li><textarea name="manual03" placeholder="3. "></textarea></li>
                	</ul>
                	<ul>
                        <li><p class="progress-text">첨부 이미지</p></li>
                        <li><input type="file" name="manualImg03" /></li>
                	</ul>
                </li>
                <li class="item-create">
                	<ul>
                		<li><p class="progress-text">순서 4</p></li>
                        <li><textarea name="manual04" placeholder="4. "></textarea></li>
                	</ul>
                	<ul>
                        <li><p class="progress-text">첨부 이미지</p></li>
                        <li><input type="file" name="manualImg04" /></li>
                	</ul>
                </li>
                <li class="item-create">
                	<ul>
                		<li><p class="progress-text">순서 5</p></li>
                        <li><textarea name="manual05" placeholder="5. "></textarea></li>
                	</ul>
                	<ul>
                        <li><p class="progress-text">첨부 이미지</p></li>
                        <li><input type="file" name="manualImg05"/></li>
                	</ul>
                </li>
                <li class="item-create">
                	<ul>
                		<li><p class="progress-text">순서 6</p></li>
                        <li><textarea name="manual06" placeholder="6. "></textarea></li>
                	</ul>
                	<ul>
                        <li><p class="progress-text">첨부 이미지</p></li>
                        <li><input type="file" name="manualImg06"/></li>
                	</ul>
                </li>
            </ul>
		</div>

        <!-- 연관레시피 -->
        <div class="section related-wrapper">
            <h3>연관 키워드 등록</h3>
        </div>
        <input type="submit" value="등록" class="btn_fill" />
    </form:form>

	<jsp:include page="../layout/footer.jsp"></jsp:include>
	</div>
</div>
