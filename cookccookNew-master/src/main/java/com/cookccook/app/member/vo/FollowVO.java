package com.cookccook.app.member.vo;

public class FollowVO {
	
	private String followId;
	private String memberId;
	private String followingMemberId;

	private MemberVO memberVO;
	private MemberVO followingMemberVO;
	
	
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFollowingMemberId() {
		return followingMemberId;
	}
	public void setFollowingMemberId(String followingMemberId) {
		this.followingMemberId = followingMemberId;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public MemberVO getFollowingMemberVO() {
		return followingMemberVO;
	}
	public void setFollowingMemberVO(MemberVO followingMemberVO) {
		this.followingMemberVO = followingMemberVO;
	}
	
}