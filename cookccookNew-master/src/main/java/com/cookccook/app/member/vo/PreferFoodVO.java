package com.cookccook.app.member.vo;

import com.cookccook.app.recommend.vo.CuisineVO;

public class PreferFoodVO {

	private String preferFoodId;
	private String memberId;
	private String cuisineId;
	
	private MemberVO memberVO;
	private CuisineVO cuisineVO;
	
	
	public String getPreferFoodId() {
		return preferFoodId;
	}
	public void setPreferFoodId(String preferFoodId) {
		this.preferFoodId = preferFoodId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCuisineId() {
		return cuisineId;
	}
	public void setCuisineId(String cuisineId) {
		this.cuisineId = cuisineId;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public CuisineVO getCuisineVO() {
		return cuisineVO;
	}
	public void setCuisineVO(CuisineVO cuisineVO) {
		this.cuisineVO = cuisineVO;
	}
	
}
