package com.cookccook.app.member.vo;

import com.cookccook.app.recommend.vo.IngredientVO;

public class PreferIngredientVO {
	
	private String preferIngredientId;
	private String memberId;
	private String ingredientId;
	
	private MemberVO memberVO;
	private IngredientVO ingredientVO;
	
	
	public String getPreferIngredientId() {
		return preferIngredientId;
	}
	public void setPreferIngredientId(String preferIngredientId) {
		this.preferIngredientId = preferIngredientId;
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
