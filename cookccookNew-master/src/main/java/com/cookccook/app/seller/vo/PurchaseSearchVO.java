package com.cookccook.app.seller.vo;

public class PurchaseSearchVO {
	private String purchaseId;
	private int purState;
	private String startDate;
	private String endDate;
	private String memName;
	private String memPhoneNumber;
	private String recipient;
	private String recipientPhoneNumber;
	private String memberId;
	
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public int getPurState() {
		return purState;
	}
	public void setPurState(int purState) {
		this.purState = purState;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPhoneNumber() {
		return memPhoneNumber;
	}
	public void setMemPhoneNumber(String memPhoneNumber) {
		this.memPhoneNumber = memPhoneNumber;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipientPhoneNumber() {
		return recipientPhoneNumber;
	}
	public void setRecipientPhoneNumber(String recipientPhoneNumber) {
		this.recipientPhoneNumber = recipientPhoneNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
