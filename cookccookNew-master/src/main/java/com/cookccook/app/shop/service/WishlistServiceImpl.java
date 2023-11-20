package com.cookccook.app.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.shop.dao.WishlistDAO;
import com.cookccook.app.shop.vo.WishlistVO;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistDAO wishlistDAO;
	
	@Override
	public List<String> getWishlistProductByMember(String memberId) {
		return wishlistDAO.getWishlistProductByMember(memberId);
	}

	@Override
	public WishlistVO getOneWishlistVOByProductId(WishlistVO wishlistVO) {
		return wishlistDAO.getOneWishlistVOByProductId(wishlistVO);
	}

	@Transactional
	@Override
	public Map<String, Object> editWishlist(WishlistVO wishlistVO) {
		Map<String, Object> resultMap = new HashMap<>();
		String memberId = wishlistVO.getMemberId();
		String productId = wishlistVO.getProductId();
		List<String> userWishlist = wishlistDAO.getWishlistProductByMember(memberId);
		if (userWishlist.contains(productId)) {
			int count = wishlistDAO.deleteExistWishFromWishList(wishlistVO);
			resultMap.put("result", count>0);
			resultMap.put("action", "delete");
		}
		else {
			int count = wishlistDAO.addNewWishToWishList(wishlistVO);
			resultMap.put("result", count>0);
			resultMap.put("action", "add");
		}
		return resultMap;
	}
}
