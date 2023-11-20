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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.CartService;
import com.cookccook.app.shop.vo.CartVO;
import com.cookccook.util.SessionUtil;


@Controller
public class CartController {

	@Autowired
	private CartService cartservice;

	@GetMapping("/shop/cart/{memberId}")
	public String viewCart(@PathVariable String memberId) {
		return "shop/cartlist";
	}
	
	@ResponseBody
	@GetMapping("/shop/cart")
	public Map<String, Object> getAllCartProduct(Authentication memberVO) {
		Map<String, Object> cartMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<CartVO> cartListVO = cartservice.getAllCartProduct(member.getMemberId());
		cartMap.put("cartListVO", cartListVO);
		cartMap.put("memberId", member.getMemberId());
		return cartMap;
	}
	
	@ResponseBody
	@PostMapping("/shop/cart/add")
    public Map<String, Object> doAddCartItem(@ModelAttribute CartVO cartVO
    										, Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String choiceId = cartVO.getChoiceId();
		String memberId = member.getMemberId();
		List<String> cartChoiceList = cartservice.getCartChoiceList(memberId);
		if (cartChoiceList.contains(choiceId)) {
			resultMap.put("action", "exist");
			resultMap.put("result", false);
			return resultMap;
		}
		else {
			cartVO.setChoiceId(choiceId);
			cartVO.setMemberId(memberId);
			boolean isSuccess = cartservice.addNewProductToCart(cartVO);
			resultMap.put("action", "add");
			resultMap.put("result", isSuccess);
		}
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/shop/cart/edit/{cartId}")
    public Map<String, Object> doEditCart(@PathVariable String cartId
			   							, @ModelAttribute CartVO cartVO) {
		boolean isSuccess = cartservice.editProductCountFromCart(cartVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/shop/cart/delete/{cartId}")
    public Map<String, Object> deleteProductFromCart(@PathVariable String cartId
    											   , @ModelAttribute CartVO cartVO) {
		boolean isSuccess = cartservice.deleteExistProductFromCart(cartVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}

	
	@ResponseBody
	@PostMapping("/shop/deleteCartList")
	public Map<String, Object> deleteCartList(@RequestBody List<CartVO> cartVOList) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = cartservice.deleteCartList(cartVOList);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
//	@ResponseBody
//	@GetMapping("/api/cart/{memberId}")
//	public List<Map<String, Object>> getAllCartItem(@PathVariable String memberId) {
//		List<Map<String, Object>> cartMapList = new ArrayList<>();
//		List<CartVO> cartListVO = cartservice.getAllCartProduct(memberId);
//		for (CartVO cartVO: cartListVO) {
//			Map<String, Object> cartMapper = new HashMap<>();
//			cartMapper.put("cartId", cartVO.getCartId());
//			cartMapper.put("memberId", cartVO.getMemberId());
//			cartMapper.put("choiceId", cartVO.getChoiceId());
//			cartMapper.put("quantity", cartVO.getQuantity());
//			cartMapper.put("cartHiddenDate", cartVO.getCartHiddenDate());
//			cartMapList.add(cartMapper);
//		}
//		return cartMapList;
//	}
}
