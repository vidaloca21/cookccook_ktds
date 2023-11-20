package com.cookccook.app.memberRecommend.vo;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.member.vo.MemberVO;

public class MemRecommendVO {
	
	private List<MemberVO> memberVOList;
	private List<InterestVO> interestVOList;
	private List<ArticleVO> articleVOList;
	
	private String articleId;
	private String memberId;
	private String InterestId;
	
	public List<MemberVO> getMemberVOList() {
		return memberVOList;
	}
	public void setMemberVOList(List<MemberVO> memberVOList) {
		this.memberVOList = memberVOList;
	}
	public List<InterestVO> getInterestVOList() {
		return interestVOList;
	}
	public void setInterestVOList(List<InterestVO> interestVOList) {
		this.interestVOList = interestVOList;
	}
	public List<ArticleVO> getArticleVOList() {
		return articleVOList;
	}
	public void setArticleVOList(List<ArticleVO> articleVOList) {
		this.articleVOList = articleVOList;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getInterestId() {
		return InterestId;
	}
	public void setInterestId(String interestId) {
		InterestId = interestId;
	}
	

}
