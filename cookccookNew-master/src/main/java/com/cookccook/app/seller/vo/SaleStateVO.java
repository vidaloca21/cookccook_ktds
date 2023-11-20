package com.cookccook.app.seller.vo;

public class SaleStateVO {
	private int allCnt;
	private int onSaleCnt;
	private int soldoutCnt;
	private int endSaleCnt;
	
	public int getAllCnt() {
		return allCnt;
	}
	public void setAllCnt(int allCnt) {
		this.allCnt = allCnt;
	}
	public int getOnSaleCnt() {
		return onSaleCnt;
	}
	public void setOnSaleCnt(int onSaleCnt) {
		this.onSaleCnt = onSaleCnt;
	}
	public int getSoldoutCnt() {
		return soldoutCnt;
	}
	public void setSoldoutCnt(int soldoutCnt) {
		this.soldoutCnt = soldoutCnt;
	}
	public int getEndSaleCnt() {
		return endSaleCnt;
	}
	public void setEndSaleCnt(int endSaleCnt) {
		this.endSaleCnt = endSaleCnt;
	}
}
