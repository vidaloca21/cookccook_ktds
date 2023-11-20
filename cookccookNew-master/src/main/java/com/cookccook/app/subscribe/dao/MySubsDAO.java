package com.cookccook.app.subscribe.dao;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;

public interface MySubsDAO {
	
	public SubPayVO getMySubsInfo(String memberId);
	
	/*
	 * 상품 배송 정보 (다음)
	 */
	public SubPayVO getMyNextSubName(String memberId);
	
	/*
	 * 상품 배송 정보 (이번)
	 */
	public List<SubscribeVO> getMyNextSubItem(String memberId);

	public SubPayVO getLatestDeliveryDate (String memberId);
	
	public List<IngredientVO> getLatestSubItems(String memberId);
	
	public List<SubPayVO> getPrevSubInfos(String memberId);
	
	public List<IngredientVO> getPrevSubItems(String memberId);
	
	public List<ArticleVO> getIngRecom(String memberId);
	
	public List<SubPayVO> getPrevHistory(String memberId);
	
	public List<ArticleVO> getMySubRecom(String memberId);
}
