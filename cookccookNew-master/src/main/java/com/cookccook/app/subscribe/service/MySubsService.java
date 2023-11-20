package com.cookccook.app.subscribe.service;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.subscribe.vo.IngredientListVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;

public interface MySubsService {
	
	public SubPayVO getMySubsInfo(String memberId);
	
	public SubPayVO getMyNextSubName(String memberId);
	
	public List<SubscribeVO> getMyNextSubItem(String memberId);

	public SubPayVO getLatestDeliveryDate(String memberId);
	
	public IngredientListVO getLatestSubItems(String memberId);
	
	public List<SubPayVO> getPrevSubInfos(String memberId);
	
	public IngredientListVO getPrevSubItems(String memberId);
	
	public List<ArticleVO> getIngRecom(String memberId);
	
	public List<SubPayVO> getPrevHistory(String memberId);
	
	public List<ArticleVO> getMySubRecom(String memberId);
	
}
