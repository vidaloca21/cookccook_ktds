package com.cookccook.app.shop.vo;

public class ProductSearchVO {
	private String productId;
	private String prdName;
	private int saleState;
	private int reState;
	private int rating;
	private String startRegistDate;
	private String endRegistDate;
	private String memberId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public int getSaleState() {
		return saleState;
	}
	public void setSaleState(int saleState) {
		this.saleState = saleState;
	}
	public String getStartRegistDate() {
		return startRegistDate;
	}
	public void setStartRegistDate(String startRegistDate) {
		this.startRegistDate = startRegistDate;
	}
	public String getEndRegistDate() {
		return endRegistDate;
	}
	public void setEndRegistDate(String endRegistDate) {
		this.endRegistDate = endRegistDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getReState() {
		return reState;
	}
	public void setReState(int reState) {
		this.reState = reState;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
