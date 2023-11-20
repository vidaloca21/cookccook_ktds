package com.cookccook.app.review.service;

import java.util.List;

import com.cookccook.app.review.vo.RatingStateVO;
//import com.cookccook.app.review.vo.ReviewListVO;
import com.cookccook.app.review.vo.ReviewVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

public interface ReviewService {
	
	public ReviewVO getOneReview(String reviewId);
	
	public List<ReviewVO> getAllReviews();
	
	public List<ReviewVO> getReviewByProductId(String productId);
	public List<ReviewVO> getReviewOnSubscribe(); 
//	public ReviewListVO searchReviewByKeyword(String keyword);
	
	public boolean createNewReview(ReviewVO reviewVO);
	
	public boolean modifyOneReview(ReviewVO reviewVO);
	
	public boolean deleteOneReview(String reviewId);
	
	public List<PurchaseProductVO> getMemberPurchaseHistory(String memberId);

	public List<ReviewVO> getReviewByMemberId(String memberId);

	public List<ReviewVO> getReviewBySearch(@Valid ProductSearchVO productSearchVO);

	public RatingStateVO getRatingStateBySeller(String memberId);
}
