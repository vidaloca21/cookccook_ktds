package com.cookccook.app.subscribe.vo;

import java.util.List;

import com.cookccook.app.recommend.vo.IngredientVO;

public class SubIngVO {

	private String subIngId;
	private String subscribeId;
	private String ingredientId;
	
	private SubscribeVO subscribeVO;
	private IngredientVO ingredientVO;
	private List<SubPayVO> subPayList;
	
	private List<IngredientVO> ingredientList;
	private String subIngOpt;

	public String getSubIngId() {
		return subIngId;
	}
	public void setSubIngId(String subIngId) {
		this.subIngId = subIngId;
	}
	public String getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}
	public String getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}
	public SubscribeVO getSubscribeVO() {
		return subscribeVO;
	}
	public void setSubscribeVO(SubscribeVO subscribeVO) {
		this.subscribeVO = subscribeVO;
	}
	public IngredientVO getIngredientVO() {
		return ingredientVO;
	}
	public void setIngredientVO(IngredientVO ingredientVO) {
		this.ingredientVO = ingredientVO;
	}
	
	public List<SubPayVO> getSubPayList() {
		return subPayList;
	}
	public void setSubPayList(List<SubPayVO> subPayList) {
		this.subPayList = subPayList;
	}
	public String getSubIngOpt() {
		return subIngOpt;
	}
	public void setSubIngOpt(String subIngOpt) {
		this.subIngOpt = subIngOpt;
	}
	public List<IngredientVO> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(List<IngredientVO> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	
	
	
	
}
