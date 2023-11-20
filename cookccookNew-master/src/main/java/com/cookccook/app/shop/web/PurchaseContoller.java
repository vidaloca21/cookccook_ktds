package com.cookccook.app.shop.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.CartService;
import com.cookccook.app.shop.service.ProductService;
import com.cookccook.app.shop.service.PurchaseService;
import com.cookccook.app.shop.vo.CartVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;
import com.cookccook.app.subscribe.service.SubscribeService;
import com.cookccook.util.SessionUtil;

@Controller
public class PurchaseContoller {

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private SubscribeService subService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	@PostMapping("/shop/order/direct")
	public ModelAndView makeNewOrderDirect(@RequestParam String choiceId
										 , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		if(choiceId == null) {
			modelAndView.setViewName("redirect:/shop/cart/"+member.getMemberId());
			return modelAndView;
		}
		String productId = productService.getOneProductByChoiceId(choiceId);
		ProductVO product = productService.getOneProduct(productId);
		ChoiceVO choiceVO = purchaseService.getOneChoiceByChoiceId(choiceId);
		CartVO tempCartVO = new CartVO();
		tempCartVO.setMemberId(member.getMemberId());
		tempCartVO.setChoiceVO(choiceVO);
		tempCartVO.setChoiceId(choiceId);
		tempCartVO.setQuantity(1);
		tempCartVO.setCartId(purchaseService.getCartPk());
		tempCartVO.setProductVO(product);
		tempCartVO.setTotalPrice(product.getPrdPrice());
		List<CartVO> cartList = new ArrayList<>();
		cartList.add(tempCartVO);
		modelAndView.setViewName("shop/ordermake");
		modelAndView.addObject("cartList", cartList);
		modelAndView.addObject("member", member);
		modelAndView.addObject("sellerId", product.getMemberId());
		modelAndView.addObject("purchaseId", purchaseService.getPurchasePk());
		return modelAndView;
	}
	
	@PostMapping("/shop/order")
	public ModelAndView makeNewOrder(@RequestParam List<String> orderData
									, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		if(orderData == null) {
			modelAndView.setViewName("redirect:/shop/cart/"+member.getMemberId());
			return modelAndView;
		}
		List<CartVO> cartList = new ArrayList<>(); 
		for(String cartId: orderData) {
			CartVO cartVO = cartService.getOneCartByCartId(cartId);
			cartList.add(cartVO);
		}
//		Gson gson = new Gson();
//		List<PurchaseProductVO> purchaseProductListVO = gson.fromJson(orderData.trim(), new TypeToken<ArrayList<PurchaseProductVO>>(){}.getType() );;
		modelAndView.setViewName("shop/ordermake");
		modelAndView.addObject("cartList", cartList);
		modelAndView.addObject("member", member);
		modelAndView.addObject("purchaseId", purchaseService.getPurchasePk());	
		return modelAndView;
	}
	
	@ResponseBody
	@PostMapping("/deliverPlace/create")
	public Map<String, Object> createNewDeliverPlace(@ModelAttribute DeliverPlaceVO deliverPlaceVO
													, Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		deliverPlaceVO.setMemberId(member.getMemberId());
		boolean isSuccess = purchaseService.createNewDeliverPlace(deliverPlaceVO);
		if (isSuccess) {
			resultMap.put("result", true);
		}
		else {
			resultMap.put("result", false);
		}
		return resultMap;
	}
	
	@GetMapping("/deliverPlace/create")
	public String addAddress(Authentication memberVO) {
		return "shop/createNewDeliverPlace2";
	}
	
	@GetMapping("/deliverPlace/newadress")
	public String viewAddressEdit(Authentication memberVO) {
		return "tools/jusoPopup";
	}

	@PostMapping("/deliverPlace/newadress")
	public String doAddressEdit(Authentication memberVO) {
		return "tools/jusoPopup";
	}
	
	@ResponseBody
	@PostMapping("/shop/createNewPurchase")
	public Map<String, Object> createNewPurchase(@ModelAttribute PurchaseVO purchaseVO
												, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		purchaseVO.setMemberId(member.getMemberId());
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = purchaseService.createNewPurchase(purchaseVO);
		if (!isSuccess) {
			resultMap.put("result", false);
			resultMap.put("nextUrl", "");
			return resultMap;
		}
		resultMap.put("result", true);
		return resultMap;
	}
	
	@ResponseBody
	@PostMapping("/shop/createNewPurchaseProduct")
	public Map<String, Object> createNewPurchaseProduct(@RequestBody List<PurchaseProductVO> purchaseProductVOlist) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = purchaseService.createNewPurchaseProduct(purchaseProductVOlist);
		resultMap.put("result", isSuccess);
		resultMap.put("nextUrl", "/shop/orderlist");
		return resultMap;
	}

	
}
	