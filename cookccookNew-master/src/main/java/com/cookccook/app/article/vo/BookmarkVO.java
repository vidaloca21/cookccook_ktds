package com.cookccook.app.article.vo;

import com.cookccook.app.member.vo.MemberVO;

public class BookmarkVO {

	private String bookmarkId;
	private String memberId;
	private String articleId;
	
	private MemberVO memberVO;
	private ArticleVO articleVO;
	
	
	public String getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(String bookmarkId) {
		this.bookmarkId = bookmarkId;
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
