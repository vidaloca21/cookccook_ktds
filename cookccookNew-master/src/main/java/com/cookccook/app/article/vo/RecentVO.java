package com.cookccook.app.article.vo;

import com.cookccook.app.member.vo.MemberVO;

public class RecentVO {

	private String recentId;
	private String memberId;
	private String articleId;
	private String recentViewTime;
	
	private MemberVO memberVO;
	private ArticleVO articleVO;
	
	
	public String getRecentId() {
		return recentId;
	}
	public void setRecentId(String recentId) {
		this.recentId = recentId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getRecentViewTime() {
		return recentViewTime;
	}
	public void setRecentViewTime(String recentViewTime) {
		this.recentViewTime = recentViewTime;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public ArticleVO getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(ArticleVO articleVO) {
		this.articleVO = articleVO;
	}
	
}
