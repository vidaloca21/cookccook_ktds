/**
 * 작성자: 현지훈
 * 작성일: 231007
 * 작업내용: 리뷰 CRUD 구현
 */
package com.cookccook.app.review.dao;

import java.util.List;

import com.cookccook.app.review.vo.ReviewVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

public interface ReviewDAO {
	
	public ReviewVO getOneReview(String reviewId);
	public int getAllReviewCount();
	public List<ReviewVO> getAllReviews(); 
	
	/**
	 * 해당 상품의 리뷰 수를 조회
	 * @param productId 상품의 ID
	 * @return 해당 상품의 리뷰 개수
	 */
	public int getReviewCountByProductId(String productId);
	
	/**
	 * 해당 상품의 리뷰 목록을 조회
	 * @param productId 상품의 ID
	 * @return 해당 상품의 리뷰 목록
	 */
	public List<ReviewVO> getReviewByProductId(String productId);
	
	/**
	 * 구독 리뷰를 조회
	 * @return 구독 리뷰 목록
	 */
	public List<ReviewVO> getReviewOnSubscribe();
	
	/**
	 * 리뷰 내용 키워드로 검색
	 * @param keyword
	 * @return
	 */
	public List<ReviewVO> searchReviewByKeyword(String keyword);

	/**
	 * 새로운 리뷰를 작성
	 * @param reviewVO 작성한 리뷰 내용
	 * @return 작성한 리뷰의 수
	 */
	public int createNewReview(ReviewVO reviewVO); 
	public int modifyOneReview(ReviewVO reviewVO);
	public int deleteOneReview(String reviewId);
	
	public List<PurchaseProductVO> getMemberPurchaseHistory(String memberId);
	public List<ReviewVO> getReviewByMemberId(String memberId);
	public List<ReviewVO> getReviewBySearch(@Valid ProductSearchVO productSearchVO);
	
	public int getAllCntBySeller(String memberId);
	public int getOneCntBySeller(String memberId);
	public int getTwoCntBySeller(String memberId);
	public int getThreeCntBySeller(String memberId);
	public int getfourCntBySeller(String memberId);
	public int getfiveCntBySeller(String memberId);
	public double getRatingAvgBySeller(String memberId);
}
