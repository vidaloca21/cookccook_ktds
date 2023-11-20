package com.cookccook.app.review.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.review.dao.ReviewDAO;
import com.cookccook.app.review.vo.RatingStateVO;
//import com.cookccook.app.review.vo.ReviewListVO;
import com.cookccook.app.review.vo.ReviewVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

import com.cookccook.app.seller.service.SellerService;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.ProductVO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired
	private SellerService sellerService;
	
	@Override
	public ReviewVO getOneReview(String reviewId) {
		return reviewDAO.getOneReview(reviewId);
	}
	
	@Override
	public List<ReviewVO> getAllReviews() {
		List<ReviewVO> reviewListVO = reviewDAO.getAllReviews();
		return reviewListVO;
	}
	
	@Override
	public List<ReviewVO>  getReviewByProductId(String productId) {
		List<ReviewVO> reviewList = reviewDAO.getReviewByProductId(productId);
		return reviewList;
	}

	@Override
	public List<ReviewVO> getReviewOnSubscribe() {
		List<ReviewVO> reviewListVO = reviewDAO.getReviewOnSubscribe();
		return reviewListVO;
	}
	
//	@Override
//	public ReviewListVO searchReviewByKeyword(String keyword) {
//		ReviewListVO reviewListVO = new ReviewListVO();
//		reviewListVO.setReviewList(reviewDAO.searchReviewByKeyword(keyword));
//		return reviewListVO;
//	}

	@Override
	public boolean createNewReview(ReviewVO reviewVO) {
		int insertCount = reviewDAO.createNewReview(reviewVO);
		String productId = reviewVO.getProductId();
		ProductVO product = sellerService.getOneProductBySeller(productId);
		String memberId = product.getMemberId();
		//sellerService.calculateAverageRating(memberId);
		sellerService.updateSellerRating(memberId);
		return insertCount >0;
	}
	
	@Override
	public boolean modifyOneReview(ReviewVO reviewVO) {
		return reviewDAO.modifyOneReview(reviewVO) >0;
	}
	
	@Override
	public boolean deleteOneReview(String reviewId) {
		int deleteCount = reviewDAO.deleteOneReview(reviewId);
		return deleteCount >0;
	}
	
	@Override
	public List<PurchaseProductVO> getMemberPurchaseHistory(String memberId) {
		List<PurchaseProductVO> purchaseHistory = reviewDAO.getMemberPurchaseHistory(memberId); 
		return purchaseHistory;
	}

	@Override
	public List<ReviewVO> getReviewByMemberId(String memberId) {
		return reviewDAO.getReviewByMemberId(memberId);
	}

	@Override
	public List<ReviewVO> getReviewBySearch(@Valid ProductSearchVO productSearchVO) {
		return reviewDAO.getReviewBySearch(productSearchVO);
	}

	@Override
	public RatingStateVO getRatingStateBySeller(String memberId) {
		RatingStateVO ratingStateVO = new RatingStateVO();
		ratingStateVO.setRatingAvg(reviewDAO.getRatingAvgBySeller(memberId));
		ratingStateVO.setAllCnt(reviewDAO.getAllCntBySeller(memberId));
		ratingStateVO.setOneCnt(reviewDAO.getOneCntBySeller(memberId));
		ratingStateVO.setTwoCnt(reviewDAO.getTwoCntBySeller(memberId));
		ratingStateVO.setThreeCnt(reviewDAO.getThreeCntBySeller(memberId));
		ratingStateVO.setFourCnt(reviewDAO.getfourCntBySeller(memberId));
		ratingStateVO.setFiveCnt(reviewDAO.getfiveCntBySeller(memberId));
		return ratingStateVO;
	}
	
}
