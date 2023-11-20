package com.cookccook.app.shop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.WishlistService;
import com.cookccook.app.shop.vo.WishlistVO;
import com.cookccook.util.SessionUtil;

@Controller
public class WishlistController {

	@Autowired
	private WishlistService wishlistservice;
	
	@GetMapping("/shop/wish/{memberId}")
	public String viewWishlist(@PathVariable String memberId) {
		return "shop/wishlist";
	}
	
	@ResponseBody
	@GetMapping("/shop/wish")
	public Map<String, Object> getWishListByMember(Authentication memberVO) {
		Map<String, Object> wishlistMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<String> wishlistProduct = wishlistservice.getWishlistProductByMember(member.getMemberId());
		wishlistMap.put("wishlistProduct", wishlistProduct);
		wishlistMap.put("memberId", member.getMemberId());
		return wishlistMap;
	}
	
	@GetMapping("/shop/wish/add")
	public String doAddNewWish(Authentication memberVO) {
		return "shop/wishlistadd";
	}
	
	@ResponseBody
	@PostMapping("/shop/wish/add")
	public Map<String, Object> doAddNewWish(@RequestBody String productId,
											Authentication memberVO) {
    	MemberVO member = SessionUtil.getLoginMember(memberVO);
    	WishlistVO wishlistVO = new WishlistVO();
    	wishlistVO.setMemberId(member.getMemberId());
    	wishlistVO.setProductId(productId);
    	Map<String, Object> resultMap = wishlistservice.editWishlist(wishlistVO);
		return resultMap;
	}
	
//	@GetMapping("/shop/wish/delete/{wishlistId}")
//	public String doDeleteWish(@PathVariable String wishlistId) {
//		return "shop/wishlistdelete";
//	}
	
//	@ResponseBody
//	@PostMapping("/shop/wish/delete/{wishlistId}")
//	public Map<String, Object> doDeleteWish(@PathVariable String wishlistId,
//											@ModelAttribute WishlistVO wishlistVO) {
//		
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("result", isSuccess);
//		return resultMap;
//	}
}
