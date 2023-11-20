<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>커뮤니티 게시글 상세 조회</title>
<link rel="stylesheet" type="text/css" href="/css/layout/lib/layout.css">
<script src="https://unpkg.com/feather-icons"></script>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$().ready(function() {
        const role = "${member.role}"
		const id = "${member.memberId}"
        if (('${member.memberId}' == "${articleVO.memberId}") ){
            $(".editBtn").show()
        } else if (role == 'ADMIN'){
            $(".editBtn").show()
        }
        else {
            $(".editBtn").hide()
        }
        console.log(id)
        console.log( "${articleVO.memberId}")
	        const rcpID = "${articleVO.articleId}";
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
						if(confirm("로그인을 먼저 진행해주세요")){
							location.href = "/member/memberlogin"	
						}
					}
					else {
						$.post("/api/interest/add", rcpID, function(response) {
							if (response.action == "add") {
								alert("좋아요 목록에 추가되었습니다.")
							} else {
								alert("좋아요 목록에서 삭제되었습니다.")
							}
							window.location.reload()
						})
					}
	       		})
    })
</script>
<style>
    ul,
    li {
        list-style: none;
        padding-left: 0;
    }

    .commu-list {
        display: block;
        background-color: #FB8F67;
        width: 80px;
        height: 30px;
        text-align: center;
        padding-top: 5px;
        border-radius: 5px;
        color: #fff;
    }

    .commu-title {
        font-size: 60px;
        font-weight: normal;
        line-height: 15px;
    }

    .commu-about {
        position: relative;
    }

    .commu-date,
    .commu-counter,
    .commu-like {
        font-size: 15px;
        color: #888;
        display: inline;
    }

    .commu-date,
    .commu-counter {
        margin-right: 25px;
    }
    .commu-counter,
    .commu-like {
        padding-left: 25px;
        border-left: 1px solid #888;
    }
    .commu-content {
        width: 98%;
        height: 500px;
        overflow: hidden;
        word-break: break-all;
        border: 1px solid #000;
        padding: 1%;
    }
    .edit-wrapper {
        padding-bottom: 20px;
        border-bottom: 1px solid #eee;
    }

    .modify,
    .delete {
        display: inline-block;
        background-color: #FB8F67;
        width: 80px;
        height: 30px;
        text-align: center;
        padding-top: 5px;
        border-radius: 5px;
        color: #fff;
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
    <!-- 목록 -->
    <div>
        <a href="<c:url value='/community/communitylist' />" class="commu-list">목록</a>
    </div>
    <c:if test="${articleVO ne null}">
        <div>
            <h3 class="commu-title">
                ${articleVO.title}
            </h3>
        </div>
        
        <div class="commu-about">
            <p class="commu-date">
                ${articleVO.postDate}
            </p>
            <p class="commu-counter">
                조회수 ${articleVO.viewCnt}
            </p>
            <span class="commu-like">좋아요
                <i class="feather-heart" data-feather="heart"></i>
                <span id="likeCnt"></span>
            </span>
        </div>
    </c:if>
    <div class="commu-content">
            ${articleVO.content}
    </div>
    <jsp:include page="../reply/reply.jsp"></jsp:include>
    <div class="edit-wrapper">
        <a href="/community/communitymodify/${articleVO.articleId}" class="modify editBtn">수정</a>
        <a href="/community/communitydelete/${articleVO.articleId}" class="delete editBtn">삭제</a>
    </div>
    
    <script>
            feather.replace();
        </script>	
    <jsp:include page="../layout/footer.jsp"></jsp:include>
