package com.cookccook.app.shop.vo;

public class ChoiceVO {

	private String choiceId;
	private String productId;
	private String choName;
	private int choPrice;
	
	private ProductVO productVO;
	
	
	public String getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getChoName() {
		return choName;
	}
	public void setChoName(String choName) {
		this.choName = choName;
	}
	public int getChoPrice() {
		return choPrice;
	}
	public void setChoPrice(int choPrice) {
		this.choPrice = choPrice;
	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
	
}
