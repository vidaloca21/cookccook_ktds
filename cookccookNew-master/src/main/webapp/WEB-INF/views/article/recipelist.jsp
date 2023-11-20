<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<title>전체 게시글 조회</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<style type='text/css'>
div {
    margin-bottom: 5px;
}
#recipeGrid {
    display: grid;
    grid-template-columns: 203px 203px 203px 203px 203px;
    grid-template-rows: minmax(360px, auto) minmax(360px, auto);
    gap: 10px;
    justify-content: space-evenly;
}

.recipeItem {
    display: flex;
    flex-direction: column;
    margin: 0 50px 15px;
    padding: 10px;
    text-align: center;
    width: 180px;
    margin-top: 0;
    margin-left: 0;
    margin-right: 0;
    transition: 0.1s;
}
.recipeItem:hover {
    width: 202px;
}
.recipeImgArea{
    width: 100%;
    height: 200px;
    overflow: hidden;
}
.recipeImgArea > img {
    width: 100%;
    height: 100%;
    object-fit:cover;
    cursor: pointer;
    border-radius: 20px;

}

.fa-heart {
   cursor: pointer;
}
   
.write-btn {
    width: 79px;
    height: 23px;
    background-color: #FB8F67;
    color: #fff;
    border: none;
    border-radius: 10px;
    text-align: center;
    align-items: center;
    font-size: 18px;
    font-weight: bold;
    padding: 5px;
    cursor: pointer;
    margin-top: 15;
    margin-bottom: 15;
    margin-right: 100;
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
.feather-heart {
    font-size: 12px;
    cursor: pointer;
    position: relative;
}

#recent_view_box {
    border: solid 1px #aaa;
    position: fixed;
    right: 10px;
    top: 300px;
    padding: 10px;
    text-align: center;
    background-color: #fff;
}

h5 {
    font-size: 10pt;
    margin: 0px;
}

.recent_list {
    list-style: none;
    padding-left: 0px;
    margin: 0;
    width: 110px;
}

.recent_list li {
    margin: 20px 0;
    position: relative;
}

.recent_list p{
    margin: 0;
}

.recent_list img{
    width: 100px;
    height: 100px;
}

.removeRecipe {
    position: absolute;
    right: 5px;
}

.inline{
    display: inline;
}
.next-button, .prev-button {
    font-weight: bold;
}

#panel {
    display: flex;
    flex-direction: column;
}
.panel-actions {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    height: 55px;
}
select[name=searchType] {
    display: none;
}
form {
    display: flex;
    margin-top: 10px;
    justify-content: center;
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
<script src="https://unpkg.com/feather-icons"></script>
<script src="https://kit.fontawesome.com/63879a02ce.js" crossorigin="anonymous"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">

    function movePage(pageNo = 0) {
        $("#pageNo").val(pageNo)
        $("#search-form").attr({
           "method": "get",
           "action": "/recipe/recipelist"
        }).submit()
    }
    $().ready(function() {
    	<c:if test="${code eq 'new'}">
    	    recipeWrite(send, userName, userId, "");
    	</c:if>
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
               "action": "/recipe/excel/download"
            }).submit()
       }
       
       $.get("/api/interest", function(response) {
         let interestArticle = response.interestList
         $(".feather-heart").each(function() {
                if (response.interestMember != null) {
                    let memberInterestArticle = response.interestMember
                    $(".feather-heart").each(function() {
                        if (memberInterestArticle.includes($(this).attr("id")) ) {
                            $(this).css("fill", "#FF1211")
                            $(this).css("stroke", "#FF1211")
                        }
                    })
                }
         })
      })
      $(".feather-heart").click(function() {
            if("${member.memberId}" == "") {
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
         const articleId = $(this).attr("id");
         $.post("/api/interest/add", articleId, function(response) {
            if (response.result) {
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
      })
        $(".rcpImg").click(function() {
            location.href = "/recipe/" + $(this).data("name")
        })
    })
    
    //최근 본 레시피
    $().ready(function() {
        // localStorage.clear();
        let recipeList = JSON.parse(localStorage.getItem('recipeList'))
        let limit = 3; 
        let currentPage = 1
        if(maxPage(recipeList, limit) > 1) {
            $(".pagination-container").append('<div class="next-button inline">></div>')
        }
        setPageOf(currentPage, limit, recipeList)
        $(document).on('click', '.prev-button', function(){
            currentPage =  parseInt($(".number-button").text())
            if (currentPage > 1) {
                if(currentPage - 1 == 1){ 
                    $(".prev-button").remove()
                }
                if(maxPage(recipeList, limit) > 1) {
                    $(".pagination-container").append('<div class="next-button inline">></div>')
                }
                currentPage -= 1;
                $(".number-button").text(currentPage)
                setPageOf(currentPage, limit, recipeList);
            }
        });
        $(document).on('click', '.next-button', function(){
            currentPage =  parseInt($(".number-button").text())
            if (currentPage < maxPage(recipeList, limit)) {
                if(currentPage == 1){ 
                    $(".pagination-container").prepend('<div class="prev-button inline"><</div>')
                }
                if(currentPage + 1 == maxPage(recipeList, limit)){ 
                    $(".next-button").remove() 
                }
                currentPage += 1;
                $(".number-button").text(currentPage)
                setPageOf(currentPage, limit, recipeList);
            }
        });
        $(document).on('click', '.removeRecipe', function() {
            currentPage =  parseInt($(".number-button").text())
            //local에서도 데이터 삭제
            let recipeTitel = $(this).parent().children("p").text()
            for (var i in recipeList) {
                if(recipeList[i].title == recipeTitel){
                    recipeList.splice(i, 1)
                    localStorage.setItem('recipeList', JSON.stringify(recipeList))
                }
            }
            $(this).parent().remove();
            setPageOf(currentPage, limit, recipeList);
      })
    })
    function maxPage(recipeList, limit){
        return Math.ceil(recipeList.length / limit);
    }

    function setPageOf(pageNumber, limit, recipeList) {
        $(".recent_list > li").remove(); // ul 리스트 내부를 비워줌
        if(recipeList[limit * (pageNumber - 1)] == null){ 
            pageNumber -= 1;
            $(".number-button").text(pageNumber)
            if(pageNumber == 1){
                $(".prev-button").remove()
            }
        }
        if(recipeList[limit * (pageNumber - 1) + 3] == null){
            $(".next-button").remove() 
        }
            for (let i = limit * (pageNumber - 1) + 1;
                i <= limit * (pageNumber - 1) + 3 && i <= recipeList.length;
                i++
            ) {
                let item = $("<li></li>")
                let recipe_url = $('<a href="/recipe/'+ recipeList[i-1].id +'""></a>')
                let recipe_img = $('<img src='+ recipeList[i-1].img +' />')
                recipe_url.append(recipe_img)
                let recipe_tit = $("<p></p>")
                recipe_tit.append(recipeList[i-1].title)
                item.append('<button class="removeRecipe">x</button>')
                item.append(recipe_url)
                item.append(recipe_tit)
                $(".recent_list").append(item)
            }
        
    }
</script>
<jsp:include page="../layout/header2.jsp"></jsp:include>
    <div id="recent_view_box">
        <h5><최근 본 레시피></h5>
        <ul class="recent_list"></ul>
        <div class="pagination-container">
            <p class="number-button inline">1</p>
        </div>
    </div>

    <div id="panel">
        <form id="search-form">
            <select name="searchType">
               <option value="subject" ${searchArticleVO.searchType eq 'subject' ? 'selected' : ''}>제목</option>
               <option value="content" ${searchArticleVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
            </select>
            <input type="search" name="searchKeyword" placeholder="검색어를 입력하세요." value="${searchArticleVO.searchKeyword}" />
            <input type="hidden" id="pageNo" name="pageNo" />
            <button id="search-btn"></button>
        </form>
        <div class="panel-actions">
           <div id="recipeCnt">
               <span> ▷ 총 ${articleList.articleCnt} 개의 게시물이 있습니다. </span>
           </div>
           <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_INFLUENCER')">
            <form action="/recipe/recipewrite">
                <a href="/recipe/recipewrite" class="write-btn">글쓰기</a>
            </form>
         </sec:authorize>
        </div>
    </div>
    <div id="recipeGrid">
          <c:choose>
              <c:when test="${not empty articleList.articleList}">
                  <c:forEach items="${articleList.articleList}" var="article">
                      <div class="recipeItem">
                            <div class="recipeImgArea">
                                <img class="rcpImg" data-name="${article.articleId}" src="/article/titleimg/${article.recipeVO.titleImgPath}" width="100px"/>
                            </div>
                          <div>${article.title}</div>
                          <div>${article.recipeVO.recipeName}</div>
                          <div class="writer">${article.memberVO.memNickname}</div>
                          <div class="postDate">${article.postDate}</div>
                          <div class="ViewCnt">조회: ${article.viewCnt}</div>
                            <div class="likeCnt"> <i id="${article.articleId}" class="feather-heart" data-feather="heart"></i>${article.likeCnt}</div>
                      </div>
                  </c:forEach>
              </c:when>
                <c:otherwise>
                    <h4>검색 결과가 없습니다!</h4>
                </c:otherwise>
          </c:choose>
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
              <a href="/recipe/excel/download" class="download-btn">엑셀 다운로드</a>
         </div>
      </sec:authorize>
        <script>
            feather.replace();
        </script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>      

<c:if test="${code eq 'new'}">
<script type="text/javascript">
$().ready(function() {
	var waitSocket = setInterval(function() {
		console.log(send == undefined, "대기중...")
		if (send) {
			recipeWrite(send, userName, userId, "");
			clearInterval(waitSocket);
			console.log(send == undefined, "연결완료")
		}
	}, 300); 
});
</script>
</c:if>

