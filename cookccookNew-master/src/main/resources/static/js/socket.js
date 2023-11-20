/**
 * 채팅 관련 JS 라이브러리
 */
var sock = undefined

/**
 * 사용자의 등급을 변경함.
 */
function changeGrade(send, senderName, senderEmail, receiverEmail, message) {
    send({
            roomName: "main",
            sendType: "change-grade",
            userName: senderName,
            userId: senderEmail,
            to: receiverEmail,
            message: message
    })
}

/**
 * 새로운 레시피가 등록됨.
 */
function recipeWrite(send, senderName, senderId, message) {
    send({
            roomName: "main",
            sendType: "notice",
            userName: senderName,
            userId: senderId,
            message: message
    })
}

/**
 * 회원이 판매자의 상품을 구매함.
 */
function buy(send, senderName, senderId, receiverEmail, message) {
    send({
            roomName: "main",
            sendType: "buy",
            userName: senderName,
            userId: senderId,
            to: receiverEmail,
            message: message
    })
}

function connectSocket(userName, userId, receiveCallback) {
    // 채팅서버 연결
    sock = new SockJS("/socket");
    
    // 소켓서버 연결 완료 콜백
    sock.onopen = function() {
        
        // send : connection으로 message를 전달
        // connection이 맺어진 후 가입(JOIN) 메시지를 전달
        send({
                roomName: "main",
                sendType: "connect",
                userName: userName,
                userId: userId,
                message: userName + "님이 로그인 했습니다."
        })
        
        // onmessage : message를 받았을 때의 callback
        sock.onmessage = function(e) {
            var content = JSON.parse(e.data);
            if (content.sendType == "connect" 
            		|| content.sendType == "leave"
            		|| content.sendType == "notice"
            		|| content.sendType == "buy"
            		|| content.sendType == "change-grade" ) {
                receiveCallback(send, content);
            }
        }
    }
    
    // 일반 메시지 전송
    var send = function(message) {
        var msg = {
            roomName : message.roomName,
            sendType : message.sendType,
            userName : userName,
            userId: userId,
            message : message.message
        };
        
        if (message.to) {
			msg.to = message.to;
		}
        
        sock.send(JSON.stringify(msg));
    }
    
    
    return send;
    
}