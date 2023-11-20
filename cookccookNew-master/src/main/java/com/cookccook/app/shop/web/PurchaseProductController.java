

package com.cookccook.app.shop.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.PurchaseProductDetailService;
import com.cookccook.app.shop.service.PurchaseProductService;
import com.cookccook.app.shop.service.PurchaseService;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.KeywordVO;
import com.cookccook.app.shop.vo.PurchaseListVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;
import com.cookccook.util.SessionUtil;

import jakarta.validation.Valid;


@Controller
public class PurchaseProductController {
	
	@Autowired
	private PurchaseProductService purchaseProductService;
	
	@Autowired
	private PurchaseProductDetailService purchaseProductDetailService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	//주문 내역 모두 조회
	   @GetMapping ({"/shop/orderlist", "/member/shop/orderlist"})
	   public ModelAndView viewPurchaseList(Authentication memberVO) {
		  MemberVO member = SessionUtil.getLoginMember(memberVO);
		  String memberId=member.getMemberId();
	      PurchaseListVO getPurchaseListByMemId = purchaseProductService.getPurchaseListByMemId(memberId);
	      ModelAndView modelAndView = new ModelAndView("shop/purchase");
	        //회원정보가 없으면 메인화면으로 보내라
			if (memberVO == null) {
				modelAndView.setViewName("redirect:/");
				return modelAndView; 
			}
			
	      modelAndView.addObject("getPurchaseListByMemId", getPurchaseListByMemId);
	      
	      return modelAndView;
	   }
	   
	   @PostMapping({"/shop/orderlist", "/member/shop/orderlist"})
	   public ModelAndView doPurchaseSearch(@RequestParam String searchKeyword
							  			  , @ModelAttribute KeywordVO keywordVO
							  			  , Authentication memberVO) {
			MemberVO member = SessionUtil.getLoginMember(memberVO);
			String memberId=member.getMemberId();
			keywordVO.setSearchKeyword(searchKeyword);
			keywordVO.setMemberId(memberId); 
			
			PurchaseListVO getPurchaseListByMemId = purchaseProductService.searchPurchaseList(keywordVO);
			PurchaseListVO searchProducts = purchaseProductService.searchPurchaseList(keywordVO);
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("shop/purchase");
			modelAndView.addObject("getPurchaseListByMemId", getPurchaseListByMemId);
			modelAndView.addObject("searchProducts", searchProducts);
			
			return modelAndView;
	   }
	      
	   // 검색 주문내역 모두 조회. 검색은 상품명 기준
//	   @RequestMapping(value = "/shop/orderlist/search/", method = RequestMethod.POST)
//	   public String viewSearchPurchaseList(@RequestParam("searchKeyword") String searchKeyword
//	                                 ,Model model) {
//	      
//	      KeywordVO keywordVO = new KeywordVO();
//	      keywordVO.setSearchKeyword(searchKeyword);
//	      System.out.println(searchKeyword);
//	         PurchaseListVO searchPurchaseList = purchaseProductService.searchPurchaseList(keywordVO);
//	         model.addAttribute("searchPurchaseList", searchPurchaseList);
//
//	      return "shop/search";
//	      
//	   }
	   
	// 리뷰 작성으로 이동
	@GetMapping("/shop/review/write/{productId}") //TODO url 파라미터 크로스체크
	public String viewReviewWritePage() {
		return "shop/review/reviewWrite"; //TODO 파일명 크로스체크
	}
	
	// 배송추적페이지로 이동
	@GetMapping("/shop/tracking/{waybill}") //TODO url 파라미터 크로스체크
	public String viewTrackingPage() {
		return "shop/deliverytracking"; //TODO 파일명 크로스체크
	}
	
	// 취소 요청 페이지로 이동
	@GetMapping("/shop/orderlist/cancelrequest") //TODO URL 파라미터 크로스체크
	public ModelAndView viewCancelRequestPage(@RequestParam String purchaseId) {
		PurchaseVO purchaseVO = purchaseProductService.getPurchaseInfoByPurchaseId(purchaseId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop/cancelrequest");
		modelAndView.addObject("purchaseVO", purchaseVO);
		modelAndView.addObject("purchaseProductList", purchaseVO.getPurchaseProductList());
		modelAndView.addObject("purchaseId", purchaseId);
		return modelAndView;
	}
	
	@PostMapping("/shop/orderlist/cancelrequest")
	public ModelAndView doCancelCreate(@Valid @ModelAttribute CancelVO cancelVO 
									 , BindingResult bindingResult
									 , Authentication memberVO) {
		
		ModelAndView modelAndView = new ModelAndView();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		boolean isSuccess = purchaseProductService.sendCancelRequest(cancelVO);
		boolean isUpdateSuccess = purchaseService.cancelPurchaseProduct(cancelVO.getPurchaseId());
	    if (bindingResult.hasErrors() || !isSuccess || !isUpdateSuccess) {
	        modelAndView.setViewName("shop/cancelrequest");
	        modelAndView.addObject("cancelVO", cancelVO);
	        return modelAndView;
	    }
		modelAndView.setViewName("redirect:/shop/orderlist");
		return modelAndView;
	}
}
