package com.cookccook.app.question.vo;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.seller.vo.SellerVO;
import com.cookccook.app.shop.vo.ProductVO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class QuestionVO {
	
	private String questionId;
	private int questionType;
	private String memberId;
	private String productId;
	private String upperQuestionId;
	private int queCategory;
	@NotBlank(message = "제목입력 ㄱ")
	private String queTitle;
	@NotBlank(message = "내용 입력 ㄱ")
	private String queContent;
	
	private String quePostDate;
	private String queEditDate;
	private String queHiddenDate;
	
	private MemberVO memberVO;
//	private SellerVO sellerVO;
	private ProductVO productVO;
	private QuestionVO lowerQuestionVO;
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
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
	public String getUpperQuestionId() {
		return upperQuestionId;
	}
	public void setUpperQuestionId(String upperQuestionId) {
		this.upperQuestionId = upperQuestionId;
	}
	public int getQueCategory() {
		return queCategory;
	}
	public void setQueCategory(int queCategory) {
		this.queCategory = queCategory;
	}
	public String getQueTitle() {
		return queTitle;
	}
	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}
	public String getQueContent() {
		return queContent;
	}
	public void setQueContent(String queContent) {
		this.queContent = queContent;
	}
	public String getQuePostDate() {
		return quePostDate;
	}
	public void setQuePostDate(String quePostDate) {
		this.quePostDate = quePostDate;
	}
	public String getQueEditDate() {
		return queEditDate;
	}
	public void setQueEditDate(String queEditDate) {
		this.queEditDate = queEditDate;
	}
	public String getQueHiddenDate() {
		return queHiddenDate;
	}
	public void setQueHiddenDate(String queHiddenDate) {
		this.queHiddenDate = queHiddenDate;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
//	public SellerVO getSellerVO() {
//		return sellerVO;
//	}
//	public void setSellerVO(SellerVO sellerVO) {
//		this.sellerVO = sellerVO;
//	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	public QuestionVO getLowerQuestionVO() {
		return lowerQuestionVO;
	}
	public void setLowerQuestionVO(QuestionVO lowerQuestionVO) {
		this.lowerQuestionVO = lowerQuestionVO;
	}
	
}
