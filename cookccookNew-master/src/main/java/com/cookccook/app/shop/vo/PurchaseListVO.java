package com.cookccook.app.shop.vo;

import java.util.List;

public class PurchaseListVO {
	
	private List<PurchaseVO> purchaseList;
	
	private int purchaseCnt;

	public List<PurchaseVO> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseVO> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public int getPurchaseCnt() {
		return purchaseCnt;
	}

	public void setPurchaseCnt(int purchaseCnt) {
		this.purchaseCnt = purchaseCnt;
	}

}
