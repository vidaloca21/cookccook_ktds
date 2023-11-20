package com.cookccook.app.question.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.question.service.QuestionService;
import com.cookccook.app.question.vo.QuestionVO;
import com.cookccook.app.shop.service.ProductService;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.util.SessionUtil;

import jakarta.validation.Valid;

@Controller
public class QuestionController {
	
	@Autowired
    private QuestionService questionService;
	
	@Autowired
	private ProductService productService;
	
    
	@ResponseBody
	@RequestMapping("/question/{productId}")
	public Map<String, Object> getQuestionByProductId(@PathVariable String productId) {
		List<QuestionVO> questionList = questionService.getQuestionByProductId(productId);
		Map<String, Object> resultMap = new HashMap<>();
		if (questionList.size() == 0) {
			resultMap.put("questionCnt", questionList.size());
			resultMap.put("questionList", null);
			return resultMap;
		}
		resultMap.put("questionCnt", questionList.size());
		resultMap.put("questionList", questionList);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("/question/subscribe")
	public Map<String, Object> getSubQuestion() {
		List<QuestionVO> questionList = questionService.getSubQuestion();
		Map<String, Object> resultMap = new HashMap<>();
		if (questionList.size() == 0) {
			resultMap.put("questionCnt", questionList.size());
			resultMap.put("questionList", null);
			return resultMap;
		}
		resultMap.put("questionCnt", questionList.size());
		resultMap.put("questionList", questionList);
		return resultMap;
	}
	
	//? 아 이거 리스폰스바디 쓸줄 모르는 지훈이 만듦 기억났음
	@ResponseBody
	@RequestMapping("/question/all")
    public List<Map<String, Object>> viewQuestionList() {
    	List<QuestionVO> questionList = questionService.getAllQuestion();
    	List<Map<String, Object>> questionMapList = new ArrayList<>();
		for (QuestionVO question: questionList) {
			Map<String, Object> questionMapper = new HashMap<>();
			questionMapper.put("questionId", question.getQuestionId());
			questionMapper.put("questionType", question.getQuestionType());
			questionMapper.put("memberId", question.getMemberId());
			questionMapper.put("memNickname", question.getMemberVO().getMemNickname());
			questionMapper.put("productId", question.getProductId());
			questionMapper.put("upperQuestionId", question.getUpperQuestionId());
			questionMapper.put("queCategory", question.getQueCategory());
			questionMapper.put("queTitle", question.getQueTitle());
			questionMapper.put("queContent", question.getQueContent());
			questionMapper.put("quePostDate", question.getQuePostDate());
			questionMapper.put("queEditDate", question.getQueEditDate());
			questionMapper.put("queHiddenDate", question.getQueHiddenDate());
			questionMapList.add(questionMapper);
		}
    	return questionMapList;
	}
	
	@GetMapping("/question/write")
    public String viewQuestionWritePage(@ModelAttribute QuestionVO questionVO, Authentication memberVO) {
		return "question/questionwrite";
    }
    
	@PostMapping("/question/write")
    public ModelAndView doQuestionWrite(@Valid @ModelAttribute QuestionVO questionVO
    									, BindingResult bindingResult
    									, Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		
		// Validation 체크한 것 중 실패한 것이 있다면.
				if (bindingResult.hasErrors()) {
					// 화면을 보여준다.
					// 게시글 등록은 하지 않는다.
					modelAndView.setViewName("question/questionwrite");
					modelAndView.addObject("questionwrite", questionVO);
					return modelAndView;
				}
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if (member == null) {
			modelAndView.setViewName("redirect:/member/memberlogin");
			return modelAndView;
		}
		if (member.getRole().equals("ADMIN")) {
			questionVO.setQuestionType(9);
		}
		boolean isSucces = questionService.createNewQuestion(questionVO);
		if(isSucces) {
			if(questionVO.getProductId() == null) {
				modelAndView.setViewName("redirect:/subscribe/info");
			}
			else {
				modelAndView.setViewName("redirect:/shop/product/" +questionVO.getProductId());
			}
			modelAndView.addObject("member", member);
			return modelAndView;
		}
		else {
			modelAndView.setViewName("question/questionWrite");
			modelAndView.addObject("questionWrite", questionVO);
			modelAndView.addObject("member", member);
			return modelAndView;
		}
    	
    }
	
	@GetMapping("/question/modify")
	public ModelAndView viewQuestionModifyPage(@RequestParam String questionId
												, Authentication memberVO) {
		QuestionVO questionVO = questionService.getOneQuestionVO(questionId, false);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = questionVO.getMemberId();
		ModelAndView modelAndView = new ModelAndView();
		if (questionVO.getProductId() == null) {
			modelAndView.addObject("questionVO", questionVO);
		}
		else {
			String prdName = productService.getOneProduct(questionVO.getProductId()).getPrdName();
			if (member == null) {
				modelAndView.setViewName("main");
				return modelAndView;
			}
			modelAndView.addObject("questionVO", questionVO);
			modelAndView.addObject("prdName", prdName);
		}
		modelAndView.setViewName("question/questionmodify");
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@PostMapping("/question/modify")
	public ModelAndView doQuestionUpdate(@ModelAttribute QuestionVO questionVO
											, Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = questionService.updateQuestion(questionVO);
		if (isSuccess) {
			if(questionVO.getProductId() == null) {
				modelAndView.setViewName("redirect:/subscribe/info");
				return modelAndView;
			}
			else {
				modelAndView.setViewName("redirect:/shop/product/" +questionVO.getProductId());
				return modelAndView;
			}
		}
		else {
			modelAndView.setViewName("question/questionwrite");
			modelAndView.addObject("questionVO", questionVO);
			return modelAndView;
		}
		
	}
	
	@ResponseBody
	@GetMapping("/question/delete/{questionId}")
	public Map<String, Object> dodeleteQuestion(@PathVariable String questionId
									, Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = questionService.deleteQuestion(questionId);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
    	
    
        
}
