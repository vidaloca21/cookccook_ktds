package com.cookccook.app.review.vo;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.vo.ProductVO;

import jakarta.validation.constraints.NotBlank;

public class ReviewVO {
	
	private String reviewId;
	private int reviewType;
	private String memberId;
	private String productId;
	@NotBlank(message = "본문을 입력하세요")
	private String revContent;
	private int rating;
	private String revPostDate;
	private String revEditDate;
	private String revHiddenDate;
	
	private MemberVO memberVO;
	private ProductVO productVO;
	
	
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public int getReviewType() {
		return reviewType;
	}
	public void setReviewType(int reviewType) {
		this.reviewType = reviewType;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRevContent() {
		return revContent;
	}
	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getRevPostDate() {
		return revPostDate;
	}
	public void setRevPostDate(String revPostDate) {
		this.revPostDate = revPostDate;
	}
	public String getRevEditDate() {
		return revEditDate;
	}
	public void setRevEditDate(String revEditDate) {
		this.revEditDate = revEditDate;
	}
	public String getRevHiddenDate() {
		return revHiddenDate;
	}
	public void setRevHiddenDate(String revHiddenDate) {
		this.revHiddenDate = revHiddenDate;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	
}
