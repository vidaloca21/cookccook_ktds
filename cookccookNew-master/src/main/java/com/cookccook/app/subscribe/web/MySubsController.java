package com.cookccook.app.subscribe.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.subscribe.service.MySubsService;
import com.cookccook.app.subscribe.service.SubscribeService;
import com.cookccook.app.subscribe.vo.IngredientListVO;
import com.cookccook.app.subscribe.vo.PaymentsVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;
import com.cookccook.util.SessionUtil;

@Controller
public class MySubsController {
	
	@Autowired
	private MySubsService mySubsService;
	
	@Autowired
	private SubscribeService subService;
	
	@GetMapping({"/subscribe/mysubs", "/member/subscribe/mysubs"})
	public ModelAndView viewMySubsInfo(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		if(memberVO == null) {
			modelAndView.setViewName("redirect:/member/memberlogin");
			return modelAndView;
		}
		String memberId = member.getMemberId();
		
		SubPayVO getMySubsInfo = mySubsService.getMySubsInfo(memberId);
		SubPayVO getMyNextSubName = mySubsService.getMyNextSubName(memberId);
		List<SubscribeVO> getMyNextSubItem = mySubsService.getMyNextSubItem(memberId);
		SubPayVO getLatestDeliveryDate = mySubsService.getLatestDeliveryDate(memberId);
		IngredientListVO getLatestSubItems = mySubsService.getLatestSubItems(memberId);
		//List<SubPayVO> getPrevSubInfos = mySubsService.getPrevSubInfos(memberId); 
		//IngredientListVO getPrevSubItems = mySubsService.getPrevSubItems(memberId); 
		List<SubPayVO> getPrevHistory = mySubsService.getPrevHistory(memberId); 
		
		List<ArticleVO> getMySubRecom = mySubsService.getMySubRecom(memberId); 
		
		modelAndView.setViewName("subscribe/mysubs");
		if(getMySubsInfo != null) {
			PaymentsVO paymentsVO = subService.getPaymentByImpUid(getMySubsInfo.getImpUid());
			modelAndView.addObject("paymentsVO", paymentsVO);
		}
		
		modelAndView.addObject("getMySubsInfo", getMySubsInfo);
		modelAndView.addObject("getMyNextSubName", getMyNextSubName);
		modelAndView.addObject("getMyNextSubItem", getMyNextSubItem);
		modelAndView.addObject("getLatestDeliveryDate", getLatestDeliveryDate);
		modelAndView.addObject("getLatestSubItems", getLatestSubItems);
		//modelAndView.addObject("getPrevSubInfos", getPrevSubInfos); 
		//modelAndView.addObject("getPrevSubItems", getPrevSubItems); 
		modelAndView.addObject("getPrevHistory", getPrevHistory); 
		
		modelAndView.addObject("getMySubRecom", getMySubRecom); 
		
		return modelAndView;
	}
}
