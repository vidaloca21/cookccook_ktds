package com.cookccook.config.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

	private ChatRoom chatRoom;
	private Gson gson;
	
	public WebSocketHandler() {
		chatRoom = new ChatRoom();
		gson = new Gson();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Message receiveMessage = gson.fromJson(message.getPayload(), Message.class);

		String sendType = receiveMessage.getSendType().toLowerCase(); 
		if (sendType.equals(ChatType.CONNECT)) {
			boolean isAlreadyLogin = chatRoom.isAlreadyLogin(receiveMessage.getUserId());
			chatRoom.enter(session, receiveMessage);
			if (!isAlreadyLogin) {
				// TODO
			}
		}
		else if (sendType.equals(ChatType.RECIPE_WRITE)) {
			//chatRoom.enter(session, receiveMessage);
			chatRoom.sendAll(session, receiveMessage);
		}
		else if (sendType.equals(ChatType.BUY)) {
			//chatRoom.enter(session, receiveMessage);
			chatRoom.sendTo(receiveMessage);
		}
		else if (sendType.equals(ChatType.CHANGE_GRADE)) {
			//chatRoom.sendAll(session, receiveMessage);
			chatRoom.sendTo(receiveMessage);
		}
		else if (sendType.equals(ChatType.LEAVE)) {
			chatRoom.leave(session);
			//chatRoom.sendAll(session, receiveMessage);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String roomName = chatRoom.getMyRoomName(session);
		ChatUser user = chatRoom.getChatUser(session);
		
		if (user != null) {
			Message message = new Message();
			message.setMessage(user.getUserName() + "님이 방을 나갔습니다.");
			message.setRoomName(roomName);
			message.setSendType(ChatType.LEAVE);
			message.setUserId(user.getUserId());
			message.setUserName(user.getUserName());
			
			chatRoom.leave(session);
			//chatRoom.sendAll(session, message);
		}
	}

}