package com.cookccook.app.main.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.main.service.Main6Service;

@RestController
public class Main6Controller {
	
	@Autowired
	private Main6Service main6Service;
	
//	@ResponseBody
//	@GetMapping("/main2")
//	public ModelAndView getRecipeRecomByAnniv () {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("main_recom");
//		
//		List<RecipeVO> annivRecomList = main_6Service.getAnnivRecom();
//		modelAndView.addObject("annivRecomList", annivRecomList);
//		
//		
//		String articleId = "ART000015"; // PathVariable 로 articleId 값 받아와 파라미터에 전달해야함
//		List<RecipeVO> tagRecomList = main_6Service.getTagRecom(articleId);
//		modelAndView.addObject("tagRecomList", tagRecomList); 
//		
//		return modelAndView;
//	}
	@ResponseBody
	@GetMapping("/annivRecomList")
	public List<RecipeVO> getAnnivRecomList() {
	    List<RecipeVO> annivRecomList = main6Service.getAnnivRecom();
	    return annivRecomList;
	}

	@ResponseBody
	@GetMapping("/tagRecomList/{articleId}")
	public List<RecipeVO> getTagRecomList(@PathVariable String articleId) {
	    List<RecipeVO> tagRecomList = main6Service.getTagRecom(articleId);
	    return tagRecomList;
	}
	
//	@GetMapping("/main2")
//	public ModelAndView getRecipeRecomByWeater (Authentication memberVO) {
//		MemberVO member = SessionUtil.getLoginMember(memberVO);
//		String memberIp = member.getLatestAccessIp();
//		
//		ModelAndView modelAndView = new ModelAndView();
//		// TODO 회원 정보 여부는 VIEW 단에서, null 인 경우 서울 날씨 기반. 처리
//				
//		modelAndView.setViewName("main_recom");
//		modelAndView.addObject("memberIp", memberIp);
//		
//		return modelAndView;
//	}
//	
//	
	@GetMapping("/checktest")
	public ModelAndView apiChecker () {
		ModelAndView modelAndView = new ModelAndView("recom_weather");
		return modelAndView;
	}
	
	
	@ResponseBody
	@PostMapping("/checktest")
	public Map<String, Object> getTemp (@RequestBody String temp) {
		Map<String, Object> tempMap = new HashMap<>();
		List<RecipeVO> tempRecomList = main6Service.getRecomByTemp(temp);
		
		tempMap.put("tempRecom", tempRecomList);
		return tempMap;
	}
	
	
}
