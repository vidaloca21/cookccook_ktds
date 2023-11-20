package com.cookccook.app.reommend_4.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.reommend_4.service.InterestRecService;
import com.cookccook.app.reommend_4.vo.InterestRecVO;

@Controller
public class InterestRecController {
	
	@Autowired
	private InterestRecService interestRecService;

	@ResponseBody
	@GetMapping("/api/intrecomm/{memberId}")
	public Map<String, Object> allInterestRecommend(@PathVariable String memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		List<InterestRecVO> interestRecListVO = interestRecService.allInterestRecommend(memberId);
		resultMap.put("interestRecListVO", interestRecListVO);
		return resultMap;
	}

}
