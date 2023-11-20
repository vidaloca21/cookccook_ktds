package com.cookccook.app.subscribe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.subscribe.dao.MySubsDAO;
import com.cookccook.app.subscribe.vo.IngredientListVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;

@Service
public class MySubsServiceImpl implements MySubsService {
	
	@Autowired
	private MySubsDAO mySubsDao;

	@Override
	public SubPayVO getMySubsInfo(String memberId) {
		SubPayVO subPayVO = mySubsDao.getMySubsInfo(memberId);
		
		return subPayVO;
	}

	@Override
	public SubPayVO getMyNextSubName(String memberId) {
		SubPayVO subPayVO = mySubsDao.getMyNextSubName(memberId);
		return subPayVO;
	}

	@Override
	public List<SubscribeVO> getMyNextSubItem(String memberId) {
		List<SubscribeVO> SubscribeList = mySubsDao.getMyNextSubItem(memberId);
		return SubscribeList;
	}

	@Override
	public SubPayVO getLatestDeliveryDate(String memberId) {
		SubPayVO subPayVO = mySubsDao.getLatestDeliveryDate(memberId);
		return subPayVO;
	}

	@Override
	public IngredientListVO getLatestSubItems(String memberId) {
		IngredientListVO ingredientListVO = new IngredientListVO();
		ingredientListVO.setIngredientList(mySubsDao.getLatestSubItems(memberId));
		return ingredientListVO;
	}

	@Override
	public List<SubPayVO> getPrevSubInfos(String memberId) {
		
		return mySubsDao.getPrevSubInfos(memberId);
	}

	@Override
	public IngredientListVO getPrevSubItems(String memberId) {
		IngredientListVO ingredientListVO = new IngredientListVO();
		ingredientListVO.setIngredientList(mySubsDao.getPrevSubItems(memberId));
		return ingredientListVO;
	}

	@Override
	public List<ArticleVO> getIngRecom(String memberId) {
		
		return null;
	}

	@Override
	public List<SubPayVO> getPrevHistory(String memberId) {
		List<SubPayVO> getPrevHistory = mySubsDao.getPrevHistory(memberId);
		return getPrevHistory;
		
	}

	@Override
	public List<ArticleVO> getMySubRecom(String memberId) {
		List<ArticleVO> getMySubRecom = mySubsDao.getMySubRecom(memberId);
		
//		for (int i = 0; i < 1;) {
//		if (getMySubRecom==null || getMySubRecom.isEmpty()) {
//			getMySubRecom = mySubsDao.getMySubRecom(memberId);
//		}
//		else if (getMySubRecom != null || !getMySubRecom.isEmpty()) {
//			i ++;
//		}
//		
//		}	
		return getMySubRecom;
		
	}
		
	

}
