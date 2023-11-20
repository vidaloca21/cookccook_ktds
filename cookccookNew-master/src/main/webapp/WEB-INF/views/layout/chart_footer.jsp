<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 	</main>
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
        			$(".header").find(".navi_bar").find(".menu").eq(0).find("a").text("레시피 ❤");
        		}
        		else if (chatType == "buy") {
        			console.log("buy", receiveMessage);
        			alert(receiveMessage.message);
        		}
        		else if (chatType == "change-grade") {
        			console.log("change-grade", receiveMessage);
        		}
        		else if (chatType == "leave") {
        			console.log("leave", receiveMessage);
                }
              });
          })
      </script>
    </sec:authorize>
</body>
</html>
