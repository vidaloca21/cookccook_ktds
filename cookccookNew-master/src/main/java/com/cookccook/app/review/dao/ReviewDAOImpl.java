package com.cookccook.app.review.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.review.vo.ReviewVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

@Repository
public class ReviewDAOImpl extends SqlSessionDaoSupport
						   implements ReviewDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public ReviewVO getOneReview(String reviewId) {
		return getSqlSession().selectOne("getOneReview", reviewId);
	}
	
	@Override
	public int getAllReviewCount() {
		return getSqlSession().selectOne("getAllReviewCount");
	}
	
	@Override
	public List<ReviewVO> getAllReviews() {
		return getSqlSession().selectList("getAllReviews");
	}
	
	@Override
	public int getReviewCountByProductId(String productId) {
		return getSqlSession().selectOne("getReviewCountByProductId", productId);
	}

	@Override
	public List<ReviewVO> getReviewByProductId(String productId) {
		return getSqlSession().selectList("getReviewByProductId", productId);
	}
	
	@Override
	public List<ReviewVO> getReviewOnSubscribe() {
		return getSqlSession().selectList("getReviewOnSubscribe");
	}
	
	@Override
	public List<ReviewVO> searchReviewByKeyword(String keyword) {
		return getSqlSession().selectList("searchReviewByKeyword", keyword);
	}

	@Override
	public int createNewReview(ReviewVO reviewVO) {
		return getSqlSession().insert("createNewReview", reviewVO);
	}
	
	@Override
	public int modifyOneReview(ReviewVO reviewVO) {
		return getSqlSession().update("modifyOneReview", reviewVO);
	}
	
	@Override
	public int deleteOneReview(String reviewId) {
		return getSqlSession().update("deleteOneReview", reviewId);
	}
	
	@Override
	public List<PurchaseProductVO> getMemberPurchaseHistory(String memberId) {
		return getSqlSession().selectList("getMemberPurchaseHistory", memberId);
	}

	@Override
	public List<ReviewVO> getReviewByMemberId(String memberId) {
		return getSqlSession().selectList("getReviewByMemberId", memberId);
	}

	@Override
	public List<ReviewVO> getReviewBySearch(@Valid ProductSearchVO productSearchVO) {
		return getSqlSession().selectList("getReviewBySearch", productSearchVO);
	}

	@Override
	public int getOneCntBySeller(String memberId) {
		return getSqlSession().selectOne("getOneCntBySeller", memberId);
	}

	@Override
	public int getTwoCntBySeller(String memberId) {
		return getSqlSession().selectOne("getTwoCntBySeller", memberId);
	}

	@Override
	public int getThreeCntBySeller(String memberId) {
		return getSqlSession().selectOne("getThreeCntBySeller", memberId);
	}

	@Override
	public int getfourCntBySeller(String memberId) {
		return getSqlSession().selectOne("getfourCntBySeller", memberId);
	}

	@Override
	public int getfiveCntBySeller(String memberId) {
		return getSqlSession().selectOne("getfiveCntBySeller", memberId);
	}

	@Override
	public double getRatingAvgBySeller(String memberId) {
		return getSqlSession().selectOne("getRatingAvgBySeller", memberId);
	}

	@Override
	public int getAllCntBySeller(String memberId) {
		return getSqlSession().selectOne("getRatingAllCntBySeller", memberId);
	}
}
