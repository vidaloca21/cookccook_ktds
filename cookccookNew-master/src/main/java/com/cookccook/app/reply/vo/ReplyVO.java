package com.cookccook.app.reply.vo;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.member.vo.MemberVO;

public class ReplyVO {

	private String replyId;
	private String memberId;
	private String articleId;
	private String upperReplyId;
	private String replyContent;
	private String replyPostDate;
	private String replyEditDate;
	private String replyHiddenDate;
	
	private MemberVO memberVO;
	private ArticleVO articleVO;
	private ReplyVO replyVO;
	
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
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
	public String getUpperReplyId() {
		return upperReplyId;
	}
	public void setUpperReplyId(String upperReplyId) {
		this.upperReplyId = upperReplyId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyPostDate() {
		return replyPostDate;
	}
	public void setReplyPostDate(String replyPostDate) {
		this.replyPostDate = replyPostDate;
	}
	public String getReplyEditDate() {
		return replyEditDate;
	}
	public void setReplyEditDate(String replyEditDate) {
		this.replyEditDate = replyEditDate;
	}
	public String getReplyHiddenDate() {
		return replyHiddenDate;
	}
	public void setReplyHiddenDate(String replyHiddenDate) {
		this.replyHiddenDate = replyHiddenDate;
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
	public ReplyVO getReplyVO() {
		return replyVO;
	}
	public void setReplyVO(ReplyVO replyVO) {
		this.replyVO = replyVO;
	}
	
}
