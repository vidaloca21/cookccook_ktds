package com.cookccook.app.shop.vo;

import com.cookccook.app.member.vo.MemberVO;

public class DeliverPlaceVO {
	
	private String deliverPlaceId;
	private String memberId;
	private String delAlias;
	private String delAddress;
	private String delDetailAddress;
	private String zipCode;
	private String recipient;
	private String recipientPhoneNumber;
	
	private MemberVO memberVO;
	
	
	public String getDeliverPlaceId() {
		return deliverPlaceId;
	}
	public void setDeliverPlaceId(String deliverPlaceId) {
		this.deliverPlaceId = deliverPlaceId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getDelAlias() {
		return delAlias;
	}
	public void setDelAlias(String delAlias) {
		this.delAlias = delAlias;
	}
	public String getDelAddress() {
		return delAddress;
	}
	public void setDelAddress(String delAddress) {
		this.delAddress = delAddress;
	}
	public String getDelDetailAddress() {
		return delDetailAddress;
	}
	public void setDelDetailAddress(String delDetailAddress) {
		this.delDetailAddress = delDetailAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

}
