package com.cookccook.app.shop.vo;

import java.util.List;

public class PurchaseProductListVO {
	
	private List<PurchaseProductVO> purchaseProductList;
	
	private int purPrdCnt;

	
	
	
	public List<PurchaseProductVO> getPurchaseProductList() {
		return purchaseProductList;
	}

	public void setPurchaseProductList(List<PurchaseProductVO> purchaseProductList) {
		this.purchaseProductList = purchaseProductList;
	}

	public int getPurPrdCnt() {
		return purPrdCnt;
	}

	public void setPurPrdCnt(int purPrdCnt) {
		this.purPrdCnt = purPrdCnt;
	}

	

}
