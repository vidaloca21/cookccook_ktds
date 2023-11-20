package com.cookccook.app.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.PurchaseProductDetailService;
import com.cookccook.app.shop.vo.PurchaseProductListVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseListVO;
import com.cookccook.app.shop.vo.PurchaseVO;
import com.cookccook.util.SessionUtil;


@Controller
public class PurchaseProductDetailController {

	@Autowired
	PurchaseProductDetailService purchaseProductDetailService;
	
	@GetMapping("/shop/orderlist/{purchaseId}")
	public ModelAndView viewOnePurchase(@PathVariable String purchaseId
										,Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId=member.getMemberId();
		

		ModelAndView modelAndView = new ModelAndView();
		
		//회원정보가 없으면 메인화면으로 보내라
		if (memberVO == null) {
			modelAndView.setViewName("redirect:/shop/orderlist");
			return modelAndView; 
		}
		PurchaseListVO purchaseListVO = purchaseProductDetailService.getAllPurchase();
		PurchaseVO purchaseVO = purchaseProductDetailService.getOnePurchase(purchaseId);
		
		if(purchaseVO ==null) {
			throw new IllegalArgumentException("존재하지 않는 주문내역 건 입니다.");

		}
		modelAndView.setViewName("shop/productpurchase");
		modelAndView.addObject("purchaseVO",purchaseVO);
		modelAndView.addObject("purchaseList",purchaseListVO);
		
		return modelAndView;
	}

}
