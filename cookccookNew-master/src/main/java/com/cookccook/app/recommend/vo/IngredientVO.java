package com.cookccook.app.recommend.vo;

import java.util.List;

import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.vo.SubIngVO;

public class IngredientVO {

	private String ingredientId;
	private String ingredientName;
	private int expireDate;
	private int ingClass;
	
	private List<SubIngVO> subIngList;
	private List<ProductVO> productList;
	private List<ArtIngVO> artIngVOList;
	
	public String getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public int getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(int expireDate) {
		this.expireDate = expireDate;
	}
	public int getIngClass() {
		return ingClass;
	}
	public void setIngClass(int ingClass) {
		this.ingClass = ingClass;
	}
	public List<SubIngVO> getSubIngList() {
		return subIngList;
	}
	public void setSubIngList(List<SubIngVO> subIngList) {
		this.subIngList = subIngList;
	}
	public List<ProductVO> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductVO> productList) {
		this.productList = productList;
	}
	public List<ArtIngVO> getArtIngVOList() {
		return artIngVOList;
	}
	public void setArtIngVOList(List<ArtIngVO> artIngVOList) {
		this.artIngVOList = artIngVOList;
	}

	
	
}
