package com.cookccook.config.websocket;

public interface ChatType {

	/**
	 * 소켓 접속
	 */
	public String CONNECT = "connect";
	
	/**
	 * 새로운 레시피 등록
	 */
	public String RECIPE_WRITE = "notice";
	
	/**
	 * 상품 구매
	 */
	public String BUY = "buy";
	
	/**
	 * 등급 변경
	 */
	public String CHANGE_GRADE = "change-grade";
	
	/**
	 * 소켓 끊어짐.
	 */
	public String LEAVE = "leave";
	
}
