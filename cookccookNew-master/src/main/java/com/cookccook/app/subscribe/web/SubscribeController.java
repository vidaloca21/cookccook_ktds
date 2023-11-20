package com.cookccook.app.subscribe.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.subscribe.service.SubscribeService;
import com.cookccook.app.subscribe.vo.CreateSubIngVO;
import com.cookccook.app.subscribe.vo.PaymentsVO;
import com.cookccook.app.subscribe.vo.SubIngVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;
import com.cookccook.util.SessionUtil;

@Controller
public class SubscribeController{
	
	@Autowired
	private SubscribeService subService;
	
	/**
	 * 구독 메인페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/subscribe")
	public String subscribeMain(Model model) {
		return "subscribe/subMain";
	}
	
	/**
	 * 구독 메뉴페이지
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/subscribe/info")
	public ModelAndView subscribeInfo(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		if(member != null) {
			String role = member.getRole();
			modelAndView.addObject("role", role);
		}
		List<SubscribeVO> subListVO = subService.getCurrentSubscribe();
		List<SubIngVO> subIngGreenList = new ArrayList<>();
		List<SubIngVO> subIngBalanceList = new ArrayList<>();
		for(int i = 0; i < subListVO.size(); i++) {
			if(subListVO.get(i).getSubChoice().contains("채소")) {
				subIngGreenList = subService.getSubIngListBySubscribeId(subListVO.get(i).getSubscribeId());
			}else {
				subIngBalanceList = subService.getSubIngListBySubscribeId(subListVO.get(i).getSubscribeId());
			}
		}
		modelAndView.setViewName("subscribe/subInfo");
		modelAndView.addObject("subListVO", subListVO);
		modelAndView.addObject("subIngGreenList", subIngGreenList);
		modelAndView.addObject("subIngBalanceList", subIngBalanceList);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	/**
	 * 회원의 구독 여부 확인 
	 * @param memberVO
	 * @return
	 */
	@ResponseBody
	@GetMapping("/subscribe/info/subscribable")
	public Map<String, Object> checkSubscribable(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		boolean isSubscribable = subService.checkSubscribable(member.getMemberId());
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSubscribable);
		return resultMap;
	}
	
	/**
	 * 구독 결제 페이지
	 * @param subscribeId
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/subscribe/choice")
	public ModelAndView subscribeInfoBySubscribeId(@RequestParam String subscribeId, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		if(memberVO == null) {
			modelAndView.setViewName("redirect:/member/memberlogin");
			return modelAndView;
		}
		SubscribeVO subscribeVO = subService.getOneSubscribeChoice(subscribeId);
		modelAndView.setViewName("subscribe/subChoice");
		modelAndView.addObject("subscribeVO", subscribeVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	/**
	 * 배송지 정보를 ajax로 받아오기 위한 url
	 * @return
	 */
	@ResponseBody
	@GetMapping("/deliverPlace")
	public Map<String, Object> viewDeliverPlaceList(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<DeliverPlaceVO> deliverPlaceList = subService.getDeliverPlaceByMemberId(member.getMemberId());
		Map<String, Object> deliverPlaceMap = new HashMap<>();
		deliverPlaceMap.put("deliverPlaceList", deliverPlaceList);
		return deliverPlaceMap;
	}
	
	/**
	 * 결제API. subpay테이블에 새로운 결제정보 추가
	 * @param subpayVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/subscribe/choice")
	public Map<String, Object> doCreateNewSubPay(@ModelAttribute SubPayVO subpayVO
												, Authentication memberVO) {
		boolean isSuccess = subService.createNewSubscribePay(subpayVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}

	/**
	 * payments테이블에 주문번호, 결제id 추가(환불할 때 필요)
	 * @param paymentsVO
	 * @param impUid
	 * @param memberVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/iamport/verify/{impUid}")
	public Map<String, Object> doCreateNewPayments(@ModelAttribute PaymentsVO paymentsVO
												, @PathVariable String impUid
		  										, Authentication memberVO
		  										) {
		boolean isSuccess = subService.createNewPayments(paymentsVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	/**
	 * 결제성공 시, 결제완료 페이지 url
	 * @return
	 */
	@GetMapping("/subscribe/subpaysuccess")
	public ModelAndView subpaySuccess(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		SubPayVO subPayVO = subService.getCurrentSubpayByMemberId(member.getMemberId());
		List<IngredientVO> ingredientList = subService.getIngredientsBySubPayId(subPayVO.getSubPayId());
		SubscribeVO subscribeVO = subService.getOneSubscribeChoice(subPayVO.getSubscribeId());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("subscribe/subSuccess");
		modelAndView.addObject("subPayVO", subPayVO);
		modelAndView.addObject("ingredientList", ingredientList);
		modelAndView.addObject("subscribeVO", subscribeVO);
		return modelAndView;
	}
	
	/**
	 * 구독 취소 및 환불
	 * @param subPayId
	 * @param memberVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/subscribe/mysubs/cancelrequest/{subPayId}")
	public Map<String, Object> doCancleSubPay(@PathVariable String subPayId, Authentication memberVO) {
		
		boolean isSuccess = subService.cancleOneSubpay(subPayId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	/**
	 * 다음달 구독메뉴(Subscribe테이블) 추가
	 * @param subChoice
	 * @return
	 */
	@ResponseBody
	@PostMapping("/subscribe/createsub")
	public Map<String, Object> doCreateSubscribe(@RequestParam("subChoice") String subChoice) {
		List<SubscribeVO> subscribes = new ArrayList<>();
		
		SubscribeVO subscribeVO1 = new SubscribeVO();
		SubscribeVO subscribeVO2 = new SubscribeVO();
		SubscribeVO subscribeVO3 = new SubscribeVO();
		if(subChoice.equals("green")) {
			subscribeVO1.setSubChoice("2주 채소 상");
			subscribeVO1.setSubPrice(7500);
			subscribeVO2.setSubChoice("2주 채소 하");
			subscribeVO2.setSubPrice(7500);
			subscribeVO3.setSubChoice("4주 채소");
			subscribeVO3.setSubPrice(7500);
		}else {
			subscribeVO1.setSubChoice("2주 육류 상");
			subscribeVO1.setSubPrice(10000);
			subscribeVO2.setSubChoice("2주 육류 하");
			subscribeVO2.setSubPrice(10000);
			subscribeVO3.setSubChoice("4주 육류");
			subscribeVO3.setSubPrice(10000);
		}
		subscribes.add(subscribeVO1);
		subscribes.add(subscribeVO2);
		subscribes.add(subscribeVO3);
		boolean isSuccess = subService.createNewSubscribe(subscribes);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	/**
	 * 다음달 구독메뉴의 재료(SubIng테이블) 추가
	 * @param createSubIngs
	 * @return
	 */
	@ResponseBody
	@PostMapping("/subscribe/createsubing/{subChoice}")//
	public Map<String, Object> doCreateSubIng(@PathVariable String subChoice
											, @RequestBody List<CreateSubIngVO> createSubIngs) {
		
		List<SubIngVO> subIngs = new ArrayList<>();
		for(int i = 0; i < createSubIngs.size(); i++) {
			IngredientVO ingredientVO = subService.getIngredientsByIngredientName(createSubIngs.get(i).getIngredientName());
//			if(ingredientVO == null) {
//				ingredientVO = new IngredientVO();
//				ingredientVO.setIngredientName(createSubIngs.get(i).getIngredientName());
//				boolean isSuccess1 = subService.createIngredient(ingredientVO);
//				if(!isSuccess1) {
//					return null;
//				}
//				ingredientVO = subService.getIngredientsByIngredientName(createSubIngs.get(i).getIngredientName());
//			}
			List<SubscribeVO> subscribeList = subService.getSubscribeList(subChoice);
			for(int j = 0; j < subscribeList.size(); j++) {
				SubIngVO subIngVO = new SubIngVO();
				subIngVO.setSubscribeId(subscribeList.get(j).getSubscribeId());
				subIngVO.setIngredientId(ingredientVO.getIngredientId());
				subIngVO.setSubIngOpt(createSubIngs.get(i).getSubIngOpt());
				subIngs.add(subIngVO);
			}
		}
		boolean isSuccess2 = subService.createNewSubIng(subIngs);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess2);
		
		return resultMap;
	}
	
	/**
	 * 재료정보 가져오기(자동완성을 위한 리스트)
	 * @return
	 */
	@ResponseBody
	@GetMapping("/subscribe/Ingredient")
	public Map<String, Object> getIngredientList() {
		List<IngredientVO> ingredientList = subService.getIngredientList();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("ingredientList", ingredientList);
		return resultMap;
	}

	/**
	 * 다음달 구독메뉴 불러오기
	 * @param subChoice
	 * @return
	 */
	@ResponseBody
	@GetMapping("/subscribe/nextsubinfo/{subChoice}")
	public Map<String, Object> viewNextSubscribeInfo(@PathVariable String subChoice) {
		SubscribeVO subscribeVO = subService.getOneSubscribe(subChoice);
		List<SubIngVO> subIngList = subService.getSubIngListBySubscribeId(subscribeVO.getSubscribeId());
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("subIngList", subIngList);
		return resultMap;
	}
	
	/**
	 * 다음달 구독메뉴 재료 수정하기
	 * @param subChoice
	 * @return
	 */
	@ResponseBody
	@PostMapping("/subscribe/deletesubinfo")
	public Map<String, Object> doDeleteSubIng(@RequestParam("subChoice") String subChoice) {
		List<SubscribeVO> subscribeList = new ArrayList<>();
		if(subChoice.equals("green")) {
			subscribeList = subService.getSubscribeListBySubChoice("채소");
		}else {
			subscribeList = subService.getSubscribeListBySubChoice("육류");
		}
		boolean isSuccess = subService.deleteSubIngsBySubscribeId(subscribeList);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}
}

