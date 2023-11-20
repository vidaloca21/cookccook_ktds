package com.cookccook.app.review.web;

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
import com.cookccook.app.review.service.ReviewService;
import com.cookccook.app.review.vo.ReviewVO;
import com.cookccook.app.seller.service.SellerService;
import com.cookccook.app.shop.service.ProductService;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.util.SessionUtil;

import jakarta.validation.Valid;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 상품의 리뷰를 받아온다
	 * @return 리뷰 목록 JSON List
	 */
//	@RequestMapping("/review/all")
//	public List<Map<String, Object>> viewAllReviews() {
//		List<ReviewVO> reviewList = reviewService.getAllReviews();
//		List<Map<String, Object>> reviewMapList = new ArrayList<>();
//		for (ReviewVO review: reviewList) {
//			Map<String, Object> reviewMapper = new HashMap<>();
//			reviewMapper.put("reviewType", review.getReviewType());
//			reviewMapper.put("productId", review.getProductId());
//			reviewMapper.put("memberId", review.getMemberId());
//			reviewMapper.put("memNickname", review.getMemberVO().getMemNickname());
//			reviewMapper.put("revContent", review.getRevContent());
//			reviewMapper.put("rating", review.getRating());
//			reviewMapper.put("postDate", review.getRevPostDate());
//			reviewMapper.put("editDate", review.getRevEditDate());
//			reviewMapper.put("hiddenDate", review.getRevHiddenDate());
//			reviewMapList.add(reviewMapper);
//		}
//		return reviewMapList;
//	}
//	
	@ResponseBody
	@RequestMapping("/review/{productId}")
	public Map<String, Object> getReviewByProductId(@PathVariable String productId) {
		List<ReviewVO> reviewList = reviewService.getReviewByProductId(productId);
		if (reviewList.size() == 0) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("reviewCnt", reviewList.size());
			resultMap.put("avgRating", 0);
			resultMap.put("reviewList", null);
			return resultMap;
		}
		int totalRating = 0;
		double avgRating = 0;
		for (ReviewVO reviewVO: reviewList) {
			totalRating += reviewVO.getRating();
		}
		avgRating = (totalRating * 10 / reviewList.size())/10.0;
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reviewCnt", reviewList.size());
		resultMap.put("avgRating", avgRating);
		resultMap.put("reviewList", reviewList);
		return resultMap;
	}
	@ResponseBody
	@GetMapping("/review/subscribe")
	public Map<String, Object> getReviewOnSubscrbe() {
		List<ReviewVO> reviewList = reviewService.getReviewOnSubscribe();
		if (reviewList.size() == 0) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("reviewCnt", reviewList.size());
			resultMap.put("avgRating", 0);
			resultMap.put("reviewList", null);
			return resultMap;
		}
		int totalRating = 0;
		double avgRating = 0;
		for (ReviewVO reviewVO: reviewList) {
			totalRating += reviewVO.getRating();
		}
		avgRating = (totalRating * 10 / reviewList.size())/10.0;
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reviewCnt", reviewList.size());
		resultMap.put("avgRating", avgRating);
		resultMap.put("reviewList", reviewList);
		return resultMap;
	}
	
	
	@GetMapping("/review/write")
	public String viewReviewWritePage() {
		return "review/reviewWrite";
	}
	@PostMapping("/review/write")
	public ModelAndView createNewReview(@Valid @ModelAttribute ReviewVO reviewVO
										, BindingResult bindingResult
										, Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		// Validation 체크한 것 중 실패한 것이 있다면.
		if (bindingResult.hasErrors()) {
			// 화면을 보여준다.
			// 게시글 등록은 하지 않는다.
			modelAndView.setViewName("review/reviewWrite");
			modelAndView.addObject("reviewVO", reviewVO);
			return modelAndView;
		}
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if (member == null) {
			modelAndView.setViewName("redirect:/member/memberlogin");
			return modelAndView;
		}
		boolean isSuccess = reviewService.createNewReview(reviewVO);
		if (isSuccess) {
			if (reviewVO.getProductId() == null) {
				modelAndView.setViewName("redirect:/subscribe/info");
			}
			else {
				modelAndView.setViewName("redirect:/shop/product/" + reviewVO.getProductId());
			}
			return modelAndView;
		}
		else {
			modelAndView.setViewName("review/reviewWrite");
			modelAndView.addObject("reviewVO", reviewVO);
			modelAndView.addObject("member", member);
			return modelAndView;
		}
	}
	
	@GetMapping("/review/modify")
	public ModelAndView viewReviewModifyPage(@RequestParam String reviewId
											, Authentication memberVO) {
		ReviewVO reviewVO = reviewService.getOneReview(reviewId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("review/reviewModify");
		modelAndView.addObject("reviewVO", reviewVO);
		modelAndView.addObject("productId", reviewVO.getProductId());
		return modelAndView;
	}
	
	@PostMapping("/review/modify")
	public ModelAndView modifyOneReview(@ModelAttribute ReviewVO reviewVO
										, Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = reviewService.modifyOneReview(reviewVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/shop/product/" + reviewVO.getProductId());
			return modelAndView;
		}
		else {
			modelAndView.setViewName("review/reviewModify");
			modelAndView.addObject("reviewVO", reviewVO);
			return modelAndView;
		}
	}
	
	@GetMapping("/review/delete/{reviewId}")
	public String deleteOneReview(@PathVariable String reviewId) {
		ReviewVO reviewVO = reviewService.getOneReview(reviewId);
		String productId = reviewVO.getProductId();
		boolean isSuccess = reviewService.deleteOneReview(reviewId);
		if (isSuccess) {
			return "/shop/product" +productId;
		}
		else {
			return "redirect:/shop/product" +productId;
		}
	}
	
	
	@ResponseBody
	@GetMapping("/api/purchasehistory")
	public Map<String, Object> getMemberPurchaseHistory(Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		List<PurchaseProductVO> purch = reviewService.getMemberPurchaseHistory(memberId);
		resultMap.put("result", purch);		
		return resultMap;
	}
	 
}