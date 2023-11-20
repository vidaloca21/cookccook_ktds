<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<title>레시피 단건 조회</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<script src="https://unpkg.com/feather-icons"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
   $().ready(function() {

            const id = "${member.memberId}"
            const role = "${member.role}"
            const rcpID = "${articleVO.articleId}";
            if (id == ''){
                $(".reply_area").text("로그인 하시면 댓글 확인이 가능합니다!")
            }
            if (role != 'ADMIN'){
                $(".artEdit").css("display", "none")
            }
          if (id != ''){
                $.get("/api/interest", function(response) {
                    let interestMember = response.interestMember
                if (interestMember.includes(rcpID)) {
                        $(".feather-heart").css("fill", "#FF1211")
                        $(".feather-heart").css("stroke", "#FF1211")
                }
                    let interestTotal = response.interestList
                    let interestCnt = 0;
                    for (var i in interestTotal) {
                        if(interestTotal[i] == rcpID) {
                            interestCnt++;
                        }
                    }
                    $("#likeCnt").text(interestCnt)
             })
          }
            $(".feather-heart").click(function(){
            if (id == '') {
                Swal.fire({
						title: '로그인을 먼저 진행해주세요',
						text: "확인을 누르시면 로그인 페이지로 이동합니다",
						icon: 'warning',
						showCancelButton: true,
						confirmButtonColor: '#FB8F67',
						cancelButtonColor: '#D4D4D4',
						confirmButtonText: '확인',
						cancelButtonText: '취소',
						//reverseButtons: true, // 버튼 순서 거꾸로
						}).then((result) => {
						if (result.isConfirmed) {
							location.href = "/member/memberlogin"	
						}
				})
            }
            else {
               $.post("/api/interest/add", rcpID, function(response) {
                    if (response.action == "add") {
                        Swal.fire({
                            icon: 'success',
                            title: '좋아요 목록에 추가되었습니다',
                        });
                        setTimeout(function() {
                            location.reload()
                        }, 1800);
                    }
                    else if (response.action == "delete") {
                        Swal.fire({
                            icon: 'success',
                            title: '좋아요 목록에서 삭제되었습니다',
                        });
                        setTimeout(function() {
                            location.reload()
                        }, 1800);
                    }
                    else {
                        Swal.fire({
                            icon: 'error',
                            title: '문제가 발생하였습니다',
                            text: '관리자에게 문의하세요',
                        });
                        setTimeout(function() {
                            location.reload()
                        }, 1800);
                    }
               })
            }
             })

        $.get("/api/recomm/ingredient", function(response) {
            console.log(response)
            let articleByIngName = [];
            for (var i=0; i < 5; i++) {
                let recipeCard = $("<div class='rcpCard'></div>")
                let recipeTitle = $("<div class='recipeTitle'></div>")
                let linkDOM = $("<a href='/recipe/"+response.articleList[i].articleId+"' />")
                let imageDOM = $("<img id='"+response.articleList[i].articleId+"' class='rcpImg' src='"+response.articleList[i].recipeVO.attImgSmall+"'/>")
                let titleDOM = $("<p>"+response.articleList[i].recipeVO.recipeName+"</p>")
                linkDOM.append(imageDOM)
                recipeCard.append(linkDOM)
                recipeCard.append(titleDOM)
                $("#relatedRecipeList").append(recipeCard)
            }
            $("#nameTop").append(response.ingName)
        })

        $.get("/api/related/${articleVO.articleId}", function(response) {
            let relList = response.result
            for (var i in relList) {
                var relArticleId = relList[i].articleVO.articleId
                var relImage = relList[i].attImgSmall
                var relTitle = relList[i].articleVO.title
                var rcpCard = $("<div class='rcpCard'></<div>")
                rcpCard.append("<a href='/recipe/"+relArticleId+"'><img src='"+relImage+"' /></a>")
                rcpCard.append("<p>"+relTitle+"</p>")
                $("#relatedRecipeList").append(rcpCard)
            }
        })
        $(".feedback-btn").click(function() {
            Swal.fire({
                title: '후기를 남기러 갈까요?',
                text: "확인을 누르시면 글 작성 페이지로 이동합니다",
                showCancelButton: true,
                imageUrl: '/img/cc_logo.png',
                imageWidth: 160,
                imageHeight: 160,
                confirmButtonColor: '#FB8F67',
                cancelButtonColor: '#D4D4D4',
                imageAlt: 'Custom image',
                confirmButtonText: '확인',
                cancelButtonText: '취소',
                //reverseButtons: true, // 버튼 순서 거꾸로
                }).then((result) => {
                if (result.isConfirmed) {
                    location.href="/community/communitywrite";
                }
            })

        })

        $.get("/api/relatedProduct/" +rcpID, function(response) {
				if (response.result.length == 0) {
					$.get("/api/intrecomm/" +id, function(response) {
                        console.log(response)
						$("#ingName").text("이런 요리도 시도해보세요!")
						let articleByIngName = [];
						for (var i=0; i < 5; i++) {
							let recipeCard = $("<div class='rcpCard'></div>")
							let recipeTitle = $("<div class='recipeTitle'></div>")
							let linkDOM = $("<a href='/recipe/"+response.articleList[i].articleId+"' />")
							let imageDOM = $("<img id='"+response.articleList[i].articleId+"' class='rcpImg' src='"+response.articleList[i].recipeVO.attImgSmall+"'/>")
							let titleDOM = $("<p>"+response.articleList[i].recipeVO.recipeName+"</p>")
							linkDOM.append(imageDOM)
							recipeCard.append(linkDOM)
							recipeCard.append(titleDOM)
							$("#relatedProductList").append(recipeCard)
						}
						$("#nameTop").append(response.ingName)
					})	

				}
				else {
					let relList = response.result
					$("#ingName").text("연관상품 #" +response.result[0].prdName)
					for (var i in relList) {
						var productId = relList[i].productId
						var prdTitleImgOrigin = relList[i].prdTitleImgOrigin
						var prdName = relList[i].prdName
						var rcpCard = $("<div class='rcpCard'></<div>")
						rcpCard.append("<a href='/shop/product/"+productId+"'><img src='/shop/product/titleimg/"+prdTitleImgOrigin+"' /></a>")
						rcpCard.append("<p>"+prdName+"</p>")
						$("#relatedProductList").append(rcpCard)
					}
				}
        })

        //최근 본 레시피 localStorage에 저장하기
        var recipe_tit = "${articleVO.title}"
        var recipe_img = $("#recipeImage > img").attr("src")
        var recipe_id = "${articleVO.articleId}"
        let recipeList = getWithExpiry('recipeList')
        if ( recipeList == null){
            recipeList = []
        };
        recipeList.unshift({img: recipe_img, id: recipe_id, title: recipe_tit})
        const newRecipeList = recipeList.filter( //중복값 제거
            (arr, index, callback) => index === callback.findIndex(t => t.id === arr.id)
        );
        console.log("recipe: " ,newRecipeList)
        setWithExpiry('recipeList', JSON.stringify(newRecipeList), 86400000)
    });
    function setWithExpiry(key, value, ttl) {
        const now = new Date()
        const item = {
            value: value,
            expiry: now.getTime() + ttl,
        }
        localStorage.setItem(key, JSON.stringify(item))
    }
    function getWithExpiry(key) {
        const itemStr = localStorage.getItem(key)
        if (!itemStr) {
            return null
        }
        const item = JSON.parse(itemStr)
        const now = new Date()
        if (now.getTime() > item.expiry) {
            localStorage.removeItem(key)
            return null
        }
        return JSON.parse(item.value)
    }

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
        height: 600px;
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
        }
    
        .main-like {
            text-align: right;
        }
    }
}

.feedback-btn {
   width: 90px;
   height: 30px;
   float: right;
    background-color: #FB8F67;
    color: #fff;
    border: none;
    border-radius: 10px;
    text-align: center;
    align-items: center;
    font-size: 17px;
    font-weight: bold;
    padding: 5px;
    cursor: pointer;
}
#recipeImage > img {
    height: 600px;
    object-fit:cover;
}
.ingredient-wrapper,
.product-recommed-wrapper,
.tip-wrapper,
.progress-wrapper,
.related-wrapper,
.reply-wrapper {
    padding: 25px 30px;
}

.progress-wrapper {
    .progress-list {
        width: 100%;
        
        .item {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            width: 100%;
            height: 250px;
        }

        .progress-img {
            width: 30%;
            height: 180px;
            display: inline;
            margin: auto 0;
            border-radius: 10%;
            object-fit:cover;
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
.relatedRecipeList, .relatedProductList {
    display: flex;
    flex-direction: row;
    align-items: center;
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
   object-fit:cover
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
#mdfyBtn {
    display: flex;
    justify-content: flex-end;
}
#mdfyBtn input {
    font-size: 14px;
    width: 80px;
    height: 30px;
    margin-left: 4px;
}

</style>
<jsp:include page="../layout/header2.jsp"></jsp:include>

<div class="container">

   <div id="recipeGrid">
        <!-- 젤 위 -->
        <div class="section main-wrapper">
            <div class="inner-wrapper">
                <div id="recipeImage">
                    <img src="${articleVO.recipeVO.attImgSmall}" width="600px" height="300px"/>
                    <div class="main-overlap">
                        <li class="main-counter">${articleVO.viewCnt}</li>
                        <li class="main-like"><i class="feather-heart" data-feather="heart"></i><span id="likeCnt"></span></li>
                    </div>
                </div>
                <div id="recipeInfo" style="display: flex; justify-content: space-between;">
                    <ul class="main-wrapper">
                        <li class="main-title">${articleVO.title}</li>
                        <li class="main-date">${articleVO.postDate}</li>
                        <form:form modelAttribute="interestVO" method="get" enctype="multipart/form-data" action="/article/like">
                            <input type="hidden" name="articleId" value="${articleVO.articleId}"/>
                            <input type="hidden" name="memberId" value="A011"/>
                         </form:form>
                    </ul>
                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_INFLUENCER')">
	                    <div>
	                    	<button class="feedback-btn" style="margin-top: 15px">후기 작성</button>
	                    </div>
                    </sec:authorize>
                </div>
            </div>
        </div>

        <!-- 상품 추천 -->
        <div class="section product-recommed-wrapper">
            <h3 id="">보고 계신 요리의 연관 상품</h3>
            <div id="relatedProductList"></div>
        </div>
        <!-- 재료 -->
      <div class="section ingredient-wrapper">
            <h3 class="section-title">재료</h3>
            <p>${articleVO.recipeVO.ingredientDetails}</p>
        </div>
        <!-- 팁 -->
        <div class="section tip-wrapper">
            <h3 class="section-title">팁</h3>
            <p>${articleVO.recipeVO.recipeTip}</p>
        </div>


        <!-- 순서 -->
        <div class="section progress-wrapper">
            <h3 class="section-title">조리 순서</h3>
            <ul class="progress-list">
                <li class="item">
                    <p class="progress-text">${articleVO.recipeVO.manual01}</p>
                    <c:choose>
                        <c:when test="${articleVO.articleType eq 1}">
                            <img src="${articleVO.recipeVO.manualImg01}" class="progress-img">
                        </c:when>
                        <c:otherwise>
                            <img src="/recipe/contentimg/${articleVO.recipeVO.manualImg01}" class="progress-img">
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="item">
                    <p class="progress-text">${articleVO.recipeVO.manual02}</p>
                    <img src="${articleVO.recipeVO.manualImg02}" class="progress-img">
                </li>
                <c:if test="${not empty articleVO.recipeVO.manual03}">
                    <li class="item">
                        <p class="progress-text">${articleVO.recipeVO.manual03}</p>
                        <img src="${articleVO.recipeVO.manualImg03}" class="progress-img">
                    </li>
                </c:if>
                <c:if test="${not empty articleVO.recipeVO.manual04}">
                    <li class="item">
                        <p class="progress-text">${articleVO.recipeVO.manual04}</p>
                        <img src="${articleVO.recipeVO.manualImg04}" class="progress-img">
                    </li>
                </c:if>
                <c:if test="${not empty articleVO.recipeVO.manual05}">
                    <li class="item">
                        <p class = "progress-text">${articleVO.recipeVO.manual05}</p>
                        <img src="${articleVO.recipeVO.manualImg05}" class="progress-img">
                    </li>
                </c:if>
                <c:if test="${not empty articleVO.recipeVO.manual06}">
                    <li class="item">
                        <p class="progress-text">${articleVO.recipeVO.manual06}</p>
                        <img src="${articleVO.recipeVO.manualImg06}" class="progress-img">
                    </li>
                </c:if>
            </ul>
        </div>

        <div id="mdfyBtn">
            <form action="/recipe/recipemodify/${articleVO.articleId}">
                <input class="artEdit btn_empty" type="submit" value="게시글 수정" />
            </form>
            <form action="/recipe/recipedelete/${articleVO.articleId}">
                <input class="artEdit btn_empty" type="submit" value="게시글 삭제" />
            </form>
        </div>

        <!-- 연관레시피 -->
        <div class="section related-wrapper">
            <h3>이런 요리는 어떠세요?</h3>
            <div id="relatedRecipeList"></div>
        </div>

        <!-- 댓글창 -->
        <div class="section reply-wrapper">
            <h3 class="section-title">댓글</h3>
            <div class="reply_area">
                <jsp:include page="../reply/reply.jsp"></jsp:include>
            </div>
        </div>

        <script>
            feather.replace();
        </script>

   </div>
</div>
      
   <jsp:include page="../layout/footer.jsp"></jsp:include>
