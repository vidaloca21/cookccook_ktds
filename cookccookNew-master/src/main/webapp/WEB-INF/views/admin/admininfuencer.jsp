<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<title>관리자 페이지</title>
<script src="/js/lib/jquery-3.7.1.js"></script>
<script type="text/javascript" src="/js/lib/sockjs.min.js"></script>
<script type="text/javascript" src="/js/socket.js"></script>
<sec:authorize access="isAuthenticated()">
      <script type="text/javascript">
		  var userName = "<sec:authentication property='principal.memberVO.memName'/>";
		  var adminId = "<sec:authentication property='principal.memberVO.memberId'/>";
		  var send = undefined;

		  $().ready(function() {
			  $(".btn_infl").click(function() {
				  	let userId = $(this).data("id");
					let memberVO = {
						memberId: userId,
						role: $(this).data("role")
					}
					console.log(memberVO)
					// let memberId = $(this).data("member");
					if(confirm("회원 등급을 변경하시겠습니까?")) {
						console.log(memberVO)
						$.post("/admin/influencerupgrade", memberVO, function(response) {
							if(response.result) {
								//소켓 전송
								changeGrade(send, userName, adminId, userId, "등급이 변경되었습니다.\n재로그인해야 새로운 등급으로 적용됩니다. 다시 로그인하시겠습니까?");
								setTimeout(function() {
									location.reload();
								}, 300);
							}
						})
					}
				})
				
        	  send = connectSocket(userName, adminId, function(send, receiveMessage) {
        		
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
					alert(receiveMessage.message)
        		}
              });
          })
      </script>
    </sec:authorize>
<style type="text/css">
	.container{
		justify-content: flex-start;
	}
	table.table {
		width: 80%;
		border-collapse: collapse;
		border: 1px solid #DDD;
	}
	table.table > thead > tr {
		background-color: #c5c5d2;
	}
	table.table > thead th {
		padding: 10px;
		color: #333;
	}
	table.table th, table.table td {
		border-right: 1px solid #F0F0F0;
		text-align: center;
	}
	table.table th:nth-child(1), table.table td:nth-child(1), 
	table.table th:nth-child(2), table.table td:nth-child(2),
	table.table th:nth-child(7), table.table td:nth-child(7) {
		width: 12%;
	}
	table.table th:nth-child(3), table.table td:nth-child(3),
	table.table th:nth-child(4), table.table td:nth-child(4), 
	table.table th:nth-child(5), table.table td:nth-child(5) {
		width: 9% !important;
	}
	table.table th:nth-child(6), table.table td:nth-child(6),
	table.table th:nth-child(8), table.table td:nth-child(8), 
	table.table th:nth-child(9), table.table td:nth-child(9) {
		width: 9% !important;
	}
	table.table th:last-child,
	table.table td:last-child {
		border-right: none;
		justify-content: center;
		align-items: center;
	}
	table.table td:last-child > * {
		margin: 7px;
	}
	table.table > tbody > tr:nth-child(odd) {
		background-color: #d9d9e3;
	}
	table.table > tbody td {
		padding-right: 10px;
	}
	.btn_infl {
		cursor: pointer;
		min-width: 50%;
		border: none;
	}
</style>
<jsp:include page="../admin/adminsidebar.jsp"></jsp:include>
<div class="header">
	<p class="font_subtitle">인플루언서 조회</p>
</div>
		<div class= "container">
			<h4>인플루언서 목록</h4>
			<table class="table">
				<thead>
					<tr>
						<th>멤버 ID</th>
						<th>이메일</th>
						<th>회원 등급</th>
						<th>닉네임</th>
						<th>전화번호</th>
						<th>차단 상태</th>
						<th>회원 가입일</th>
						<th>작성 게시글 수</th>
						<th>받은 좋아요 수</th>
						<th>권한 수정</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty influencerList}">
							<c:forEach items="${influencerList}" var="member">
								<tr>
									<td>${member.memberId}</td>
									<td>
										<a href="/admin/${member.memEmail}">
											<c:out value="${member.memEmail}" />
										</a>
									</td>
									<td>${member.role}</td>
									<td>${member.memNickname}</td>
									<td>${member.memPhoneNumber}</td>
									<td>${member.blockYn}</td>
									<td>${member.memJoinDate}</td>
									<td></td>
									<td>${member.totalArticle}</td>
									<td>
										<button class="btn_infl" id="downgradeBtn" data-id="${member.memberId}" data-role="USER">인플루언서 해제</button>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="11">등록된 회원이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<h4>인플루언서 대기 목록</h4>
			<table class="table">
				<thead>
					<tr>
						<th>멤버 ID</th>
						<th>이메일</th>
						<th>회원 등급</th>
						<th>닉네임</th>
						<th>전화번호</th>
						<th>차단 상태</th>
						<th>회원 가입일</th>
						<th>작성 게시글 수</th>
						<th>받은 좋아요 수</th>
						<th>권한 수정</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty inflReadyList}">
							<c:forEach items="${inflReadyList}" var="member">
								<tr>
									<td>${member.memberId}</td>
									<td>
										<a href="/admin/${member.memEmail}">
											<c:out value="${member.memEmail}" />
										</a>
									</td>
									<td>${member.role}</td>
									<td>${member.memNickname}</td>
									<td>${member.memPhoneNumber}</td>
									<td>${member.blockYn}</td>
									<td>${member.memJoinDate}</td>
									<td>${member.totalArticle}</td>
									<td>${member.totalLike}</td>
									<td>
										<button class="btn_infl" id="downgradeBtn" data-id="${member.memberId}" data-role="INFLUENCER">인플루언서 등록</button>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="11">등록된 회원이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</body>
</html>