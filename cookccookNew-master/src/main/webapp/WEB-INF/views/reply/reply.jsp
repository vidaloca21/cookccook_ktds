<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글</title>
<style>
   
   div.grid > div.replies {
      display: grid;
      grid-column: 1 / 3;
   }
   div.replies > .write-reply {
      margin-top: 10px;
      display: grid;
      grid-template-columns: 1fr 80px;
      grid-template-rows: 1fr 40px;
      row-gap: 10px;
      column-gap: 10px;
      align-items: center;
   }
   div.replies > .write-reply > textarea {
      height: 150px;
      display: grid;
      grid-column: 1 / 3;
      border: none;
   }
   
   div.replies > .reply-items {
      display: grid;
      grid-template-columns: 1fr;
      grid-template-rows: 1fr;
      row-gap: 10px;
   }
   pre.content {
      margin: 20px 0;
      width: 700px;
      overflow: hidden;
      word-break: break-all;
      word-wrap: wrap;
   }
   .reply {
      margin-top: 20px;
   }
   .reply > div {
      display: grid;
      grid-template-columns: 1fr; 
      grid-template-rows: 1fr;
      width: inherit;
   }   
   .author {
      font-size: 17px;
      font-weight: bold;
      color: red;
   }
   .datetime {
      font-size: 15.5px;
      grid-row: 2;
   }
   .crtdt,
   .mdfydt,
   .modify-reply,
   .delete-reply,
   .re-reply {
      display: inline;
      font-size: 15.5px;
      margin-right: 5px;
   }
   
   .btn_fill {
   width:700px;
   height: 35px;
   margin-top: 15px;
   }
   .btn_empty {
   width: 80px;
   margin-top: 15px;
   }
</style>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript">

$().ready(function() {
   // 댓글 수정
   var modifyReply = function(event) {
      var reply = $(event.currentTarget).closest(".reply")
      var replyId = reply.data("reply-id")
      
      // 작성되어있던 댓글의 내용
      var content = reply.find(".content").text()
      $("#txt-reply").val(content)
      $("#txt-reply").focus()

      // 수정모드로 변경.
      $("#txt-reply").data("mode", "modify")
      $("#txt-reply").data("target", replyId)
   }

   // 댓글 삭제
   var deleteReply = function(event) {
      var reply = $(event.currentTarget).closest(".reply")
      var replyId = reply.data("reply-id")

      // txt-reply에 할당된 data-변수를 제거.
      $("#txt-reply").removeData("mode")
      $("#txt-reply").removeData("target")

      if ( confirm("댓글을 삭제하시겠습니까?") ) {
         $.get(`/reply/replydelete/\${replyId}`, function(response) {
            // 삭제 성공 여부를 받아온다.
            // 삭제에 성공했다면
            if (response.result) {
               // 댓글을 다시 조회한다.
               loadReplies()
               // 댓글 입력 칸은 비워준다.
               $("#txt-reply").val("")
            }
         })
      }
   }

   // 대댓글 등록
   var reReply = function(event) {
      var reply = $(event.currentTarget).closest(".reply")
      var replyId = reply.data("reply-id")

      $("#txt-reply").data("mode", "re-reply")
      $("#txt-reply").data("target", replyId)
      $("#txt-reply").focus()
   }

   // 댓글 조회
   var loadReplies = function() {
      // 댓글 목록 삭제.
      $(".reply-items").html("")
      
      // 댓글 조회.
      // `` <- backtick template literal 변수 바인딩 \${}
      $.get("/reply/${articleVO.articleId}", function(response) {
         console.log(response)
         var replies = response.reply // <-- Java: List, JS: Array
         for (var i = 0; i < replies.length; i++) {
            // 배열에서 댓글 하나를 가져온다.
            var reply = replies[i]
            
            // 댓글이 표현될 template을 만들어준다.
            var replyTemplate = 
               `<div class="reply" data-reply-id="\${reply.replyId}" style="padding-left: \${(reply.level - 1) * 40}px">
                  <div class="author">
                     \${reply.memberVO.memNickname}
                  </div>
                  <div class="datetime">
                     <span class="crtdt">등록: \${reply.replyPostDate}</span>
                     \${reply.replyEditDate != reply.replyPostDate ? 
                        `<span class="mdfydt">수정: \${reply.replyEditDate}</span>`
                        : ""}
                  </div>
                  <div class="buttons">
                     \${reply.memberId == response.memberId ? 
                        `<div>
                           <span class="modify-reply">수정</span>
                           <span class="delete-reply">삭제</span>
                           <span class="re-reply">답변하기</span>
                        </div>`
                        : `<div>
                           <span class="re-reply">답변하기</span>
                        </div>`}
                  </div>
                     <p class="content">\${reply.replyContent}</pre>
               </div>`
               var replyDom = $(replyTemplate)
               replyDom.find(".modify-reply").click(modifyReply)
               replyDom.find(".delete-reply").click(deleteReply)
               replyDom.find(".re-reply").click(reReply)

               $(".reply-items").append(replyDom)
         }
      })
   }

   loadReplies()

   // 등록버튼 클릭
   $("#btn-save-reply").click(function() {
      // 작성한 댓글 내용을 가져온다.
      var reply = $("#txt-reply").val().trim()
      // 저장 모드? 등록, 수정 구분하는 구분자 가져온다.
      var mode = $("#txt-reply").data("mode")
      // 대댓글일 경우, 상위 댓글의 아이디를 가져온다.
      var target = $("#txt-reply").data("target")

      // 댓글 내용을 입력했다면 등록을 진행한다.
      if (reply != "") {
         // Ajax 요청을 위한 데이터를 생성한다.
         var body = { "replyContent": reply }
         // 등록 URL을 생성한다.
         var url = `/reply/${articleVO.articleId}`

         // 대댓글일 경우 부모댓글 ID를 데이터에 넣어준다.
         if (mode == "re-reply") {
            body.upperReplyId = target
         }

         // 댓글 수정일 경우 URL을 변경한다.
         if (mode == "modify") {
            url = `/reply/replymodify/\${target}`
         }

         // 등록을 진행한다.
         $.post(url, body, function(response) {
            // 댓글 등록 및 수정의 결과를 받아온다
            var result = response.result
            // 댓글 등록 및 수정이 성공했다면 댓글을 다시 조회해온다.
            if (result) {
               loadReplies()
               $("#txt-reply").val("")
               $("#txt-reply").removeData("mode")
               $("#txt-reply").removeData("tarRget")
            }
         })
      }
   })

   // 취소버튼 클릭
   $("#btn-cancel-reply").click(function() {
      $("#txt-reply").val("")
      $("#txt-reply").removeData("mode")
      $("#txt-reply").removeData("target")
   })
});
</script>
</head>
<body>

<div class="replies font-content">
   <div class="reply-items"></div>
   <div class="write-reply " >
      <textarea id="txt-reply"></textarea>
      <button id="btn-save-reply" class="btn_fill">등록</button>
      <button id="btn-cancel-reply" class="btn_empty" style="height:35px;">취소</button>
   </div>
</div>

</body>
</html>