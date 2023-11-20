package com.cookccook.app.member.vo;

import com.cookccook.app.recommend.vo.IngredientVO;

public class AllergyVO {

	private String allergyId;
	private String memberId;
	private String ingredientId;
	
	private MemberVO memberVO;
	private IngredientVO ingredientVO;
	
	
	public String getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
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
