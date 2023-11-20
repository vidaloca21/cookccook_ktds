package com.cookccook.config.websocket;

import org.springframework.web.socket.WebSocketSession;

public class ChatUser {

	private WebSocketSession session;
	private String userName;
	private String userId;

	public ChatUser(WebSocketSession session, String userName, String userId) {
		this.session = session;
		this.userName = userName;
		this.userId = userId;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		WebSocketSession otherSession = (WebSocketSession) obj;
		return this.session.getId().equals(otherSession.getId());
	}

}