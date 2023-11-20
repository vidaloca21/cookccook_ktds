package com.cookccook.app.shop.vo;

import com.cookccook.app.seller.vo.SellerVO;

public class CalculateVO {
	
	private String calculateId;
	private String memberId;
	private String purchaseProductId;
	private double commission;
	private String calcCompleteDate;
	
	private SellerVO sellerVO;
	private PurchaseProductVO purchaseProductVO;
	
	
	public String getCalculateId() {
		return calculateId;
	}
	public void setCalculateId(String calculateId) {
		this.calculateId = calculateId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPurchaseProductId() {
		return purchaseProductId;
	}
	public void setPurchaseProductId(String purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public String getCalcCompleteDate() {
		return calcCompleteDate;
	}
	public void setCalcCompleteDate(String calcCompleteDate) {
		this.calcCompleteDate = calcCompleteDate;
	}
	public SellerVO getSellerVO() {
		return sellerVO;
	}
	public void setSellerVO(SellerVO sellerVO) {
		this.sellerVO = sellerVO;
	}
	public PurchaseProductVO getPurchaseProductVO() {
		return purchaseProductVO;
	}
	public void setPurchaseProductVO(PurchaseProductVO purchaseProductVO) {
		this.purchaseProductVO = purchaseProductVO;
	}
	

}
