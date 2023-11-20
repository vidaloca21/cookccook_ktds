package com.cookccook.app.shop.vo;

import java.util.List;

public class WishlistListVO {
	
	private int WishListCnt;
	private List<WishlistVO> WishListList;
	
	
	public int getWishListCnt() {
		return WishListCnt;
	}
	public void setWishListCnt(int wishListCnt) {
		WishListCnt = wishListCnt;
	}
	public List<WishlistVO> getWishListList() {
		return WishListList;
	}
	public void setWishListList(List<WishlistVO> wishListList) {
		WishListList = wishListList;
	}

}
