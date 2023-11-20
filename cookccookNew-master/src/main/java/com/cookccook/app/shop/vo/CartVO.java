package com.cookccook.app.shop.vo;

import com.cookccook.app.member.vo.MemberVO;

public class CartVO {

	private String cartId;
	private String memberId;
	private String choiceId;
	private int quantity;
	
	private MemberVO memberVO;
	private ChoiceVO choiceVO;
	private ProductVO productVO;
	private int totalPrice;
	
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public ChoiceVO getChoiceVO() {
		return choiceVO;
	}
	public void setChoiceVO(ChoiceVO choiceVO) {
		this.choiceVO = choiceVO;
	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
