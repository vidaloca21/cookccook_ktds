package com.cookccook.app.shop.vo;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;

public class PurchaseVO {

	private String purchaseId;
	private String memberId;
	private String deliverPlaceId;
	private String deliverMemo;
	private String purchaseDate;
	private String payMethod;
	
	private MemberVO memberVO;
	private DeliverPlaceVO deliverPlaceVO;
	private List<PurchaseProductVO> purchaseProductList;
	private List<PurchaseVO> purchaseList;
	
	private int totalPurPrdPrice;
	private int totalDeliverFee;

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}


	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public MemberVO getMemeberVO() {
		return memberVO;
	}

	public void setMemeberVO(MemberVO memeberVO) {
		this.memberVO = memeberVO;
	}

	public DeliverPlaceVO getDeliverPlaceVO() {
		return deliverPlaceVO;
	}

	public void setDeliverPlaceVO(DeliverPlaceVO deliverPlaceVO) {
		this.deliverPlaceVO = deliverPlaceVO;
	}

	public String getDeliverMemo() {
		return deliverMemo;
	}

	public void setDeliverMemo(String deliverMemo) {
		this.deliverMemo = deliverMemo;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public List<PurchaseProductVO> getPurchaseProductList() {
		return purchaseProductList;
	}

	public void setPurchaseProductList(List<PurchaseProductVO> purchaseProductList) {
		this.purchaseProductList = purchaseProductList;
	}

	public List<PurchaseVO> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseVO> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public int getTotalPurPrdPrice() {
		return totalPurPrdPrice;
	}

	public void setTotalPurPrdPrice(int totalPurPrdPrice) {
		this.totalPurPrdPrice = totalPurPrdPrice;
	}

	public int getTotalDeliverFee() {
		return totalDeliverFee;
	}

	public void setTotalDeliverFee(int totalDeliverFee) {
		this.totalDeliverFee = totalDeliverFee;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDeliverPlaceId() {
		return deliverPlaceId;
	}

	public void setDeliverPlaceId(String deliverPlaceId) {
		this.deliverPlaceId = deliverPlaceId;
	}
	

}
