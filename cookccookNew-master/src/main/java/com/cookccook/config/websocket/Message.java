package com.cookccook.config.websocket;

public class Message {

	private String roomName;
	private String sendType;
	private String userName;
	private String userId;
	private boolean sendToMe;
	private String message;

	private String to;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isSendToMe() {
		return sendToMe;
	}

	public void setSendToMe(boolean sendToMe) {
		this.sendToMe = sendToMe;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}