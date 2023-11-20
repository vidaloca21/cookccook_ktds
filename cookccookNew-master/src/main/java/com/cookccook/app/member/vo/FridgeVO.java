package com.cookccook.app.member.vo;

import com.cookccook.app.recommend.vo.IngredientVO;

public class FridgeVO {

	private String fridgeId;
	private String memberId;
	private String ingredientId;
	private String registDate;
	
	private MemberVO memberVO;
	private IngredientVO ingredientVO;
	
	
	public String getFridgeId() {
		return fridgeId;
	}
	public void setFridgeId(String fridgeId) {
		this.fridgeId = fridgeId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public IngredientVO getIngredientVO() {
		return ingredientVO;
	}
	public void setIngredientVO(IngredientVO ingredientVO) {
		this.ingredientVO = ingredientVO;
	}
	
}