package com.cookccook.app.shop.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CancelVO {
	
	private String cancelId;
	private String purchaseId;
	@NotEmpty(message="사유를 선택해주세요.")
	private String canExcuse;
	@NotBlank(message="상세 사유를 입력해주세요.")
	private String canDetail;
	
	private PurchaseVO purchaseVO;
	
	
	public String getCancelId() {
		return cancelId;
	}
	public void setCancelId(String cancelId) {
		this.cancelId = cancelId;
	}
	public String getCanExcuse() {
		return canExcuse;
	}
	public void setCanExcuse(String canExcuse) {
		this.canExcuse = canExcuse;
	}
	public String getCanDetail() {
		return canDetail;
	}
	public void setCanDetail(String canDetail) {
		this.canDetail = canDetail;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public PurchaseVO getPurchaseVO() {
		return purchaseVO;
	}
	public void setPurchaseVO(PurchaseVO purchaseVO) {
		this.purchaseVO = purchaseVO;
	}
	

}
