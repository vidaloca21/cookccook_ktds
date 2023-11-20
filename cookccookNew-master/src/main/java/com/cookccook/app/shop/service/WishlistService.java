package com.cookccook.app.shop.service;

import java.util.List;
import java.util.Map;

import com.cookccook.app.shop.vo.WishlistVO;

public interface WishlistService {

	public List<String> getWishlistProductByMember(String memberId);
	
	public WishlistVO getOneWishlistVOByProductId(WishlistVO wishlistVO);
	
	public Map<String, Object> editWishlist(WishlistVO wishlistVO);
}
