<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
		</div>
		<div></div>
	</div>
	<div class="footer">
	   	<ul>
		   	<li id="introduction1">
	            상호명: (주)Recipe Introduce Platform | 대표: 2조 | 개인정보보호책임자: 2조 <br>
	            사업자등록번호: 123-45-678910 | 통신판매업신고번호: 제2023-경기수원-00000호<br>
	            사업장소재지: 경기 수원시 팔달구 팔달문로3번길 37 맛있는 빌딩 9층 915호<br>
	        </li>
	        <li id="introduction2">
	            주식회사 kt DS University는 전자상거래 등에서의 소비자보호에 관한 법률에 따른 통신판매중개자로서 중개하는 통신판매에 관하여서는 통신판매의 당사자가 아니며 상품의 주문, 배송 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.
	        </li>
	        <li id="introduction3">
	        	image resources by Freepik
	        </li>
	   	</ul>
	</div>
	
	<sec:authorize access="isAuthenticated()">
	  
      <script type="text/javascript" src="/js/lib/sockjs.min.js"></script>
      <script type="text/javascript" src="/js/socket.js"></script>
      
      <script type="text/javascript">
          var userName = "<sec:authentication property='principal.memberVO.memName'/>";
          var userId = "<sec:authentication property='principal.memberVO.memberId'/>";
          var send = undefined;
          $().ready(function() {
			Toast = Swal.mixin({
				toast: true,
				position: 'top-right',
				iconColor: 'white',
				customClass: {
					popup: 'colored-toast'
				},
				showConfirmButton: false,
				timer: 3000,
				timerProgressBar: true
			})
        	send = connectSocket(userName, userId, function(send, receiveMessage) {
        		
        		/**
        		  ReceiveMessage 형식
        		  {
        			roomName: "채팅방이름",
        			sendType: "소켓전달 방식", <-- com.cookccook.config.websocket.ChatType 참고
        			userName: "메시지를 전달한 회원 이름",
        			userEmail: "메시지를 전달한 회원 이메일",
        			sendToMe: 나에게 보낸것인지 여부 (true/false),
        			message: "메시지내용",
        			to: "메시지를 전달할 대상의 이메일"
        		  }
        		**/
        		var chatType =  receiveMessage.sendType;
        		if (chatType == "notice") {
        			console.log("notice", receiveMessage);
        			$(".header").find(".navi_bar").find(".menu").eq(0).find("a").append("<img id='newRecipeAlert' src='/img/mini_icon.png' />");
        		}
        		else if (chatType == "buy") {
        			console.log("buy", receiveMessage);
					Toast.fire({
						icon: 'success',
						title: receiveMessage.message
					})
        		}
        		else if (chatType == "change-grade") {
        			console.log("change-grade", receiveMessage);
					Swal.fire({
						title: '회원 등급이 변경되었습니다.',
						text: receiveMessage.message,
						icon: 'success',
						showCancelButton: true,
						confirmButtonColor: '#FB8F67',
						cancelButtonColor: '#D4D4D4',
						confirmButtonText: '확인',
						cancelButtonText: '취소',
						//reverseButtons: true, // 버튼 순서 거꾸로
						}).then((result) => {
						if (result.isConfirmed) {
							location.href = "/member/memberlogout"
						}
					})
        		}
              });
          })
      </script>
    </sec:authorize>
<style type="text/css">
	#newRecipeAlert {
		width: 24px;
		height: auto;
		position: absolute;
		top: 84px;
	}
	.colored-toast.swal2-icon-success {
		background-color: #a5dc86 !important;
	}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</body>
</html>
