package com.cookccook.app.shop.vo;
import com.cookccook.app.member.vo.MemberVO;

public class WishlistVO {
	
	private String wishlistId;
	private String memberId;
	private String productId;

	private MemberVO memberVO;
	private ProductVO productVO;
	
	
	public String getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(String wishlistId) {
		this.wishlistId = wishlistId;
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