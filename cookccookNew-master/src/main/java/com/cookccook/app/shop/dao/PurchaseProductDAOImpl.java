package com.cookccook.app.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.KeywordVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

@Repository
public class PurchaseProductDAOImpl extends SqlSessionDaoSupport
									implements PurchaseProductDAO {
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int getPLAllCount() {
		return getSqlSession().selectOne("com.cookccook.app.shop.dao.PurchaseProductDAO.getPLAllCount");
	}
	
	
	@Override
	public List<PurchaseVO> getPurchaseListByMemId(String memberId) {
		return getSqlSession().selectList("getPurchaseListByMemId", memberId);
	}

	@Override
	public int updateCancelStatus(PurchaseProductVO purchaseProductVO) {
		return getSqlSession().update("updateCancelStatus", purchaseProductVO);
	}

	@Override
	public int createCancelRequest(CancelVO cancelVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("createCancelRequest", cancelVO);
	}
	

	@Override
	public List<PurchaseVO> searchPurchaseList(KeywordVO keywordVO) {
		return getSqlSession().selectList("searchProducts", keywordVO);
		
	}

	@Override
	public PurchaseVO getPurchaseInfoByPurchaseId(String purchaseId) {
		return getSqlSession().selectOne("getPurchaseInfoByPurchaseId", purchaseId);
	}
}
