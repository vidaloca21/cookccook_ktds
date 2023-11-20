/**
 * 김형관
 * 20231010
 * 찜CRUD
 */
package com.cookccook.app.shop.dao;

import java.util.List;

import com.cookccook.app.shop.vo.WishlistVO;

public interface WishlistDAO {
	
	/**
	 * 회원의 찜 목록 조회
	 */
	public List<String> getWishlistProductByMember(String memberId);
	
	public WishlistVO getOneWishlistVOByProductId(WishlistVO wishlistVO);
	/**
	 * 회원의 찜 목록에 새로운 찜을 추가한다
	 */
	public int addNewWishToWishList (WishlistVO wishlistVO);
	
	/**
	 * 기존의 찜을 회원의 찜 목록에서 삭제한다
	 */
	public int deleteExistWishFromWishList (WishlistVO wishlistVO);
}
