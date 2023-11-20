package com.cookccook.app.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.PurchaseVO;


@Repository
public class PurchaseProductDetailDAOImpl extends SqlSessionDaoSupport implements PurchaseProductDetailDAO{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public PurchaseVO getOnePurchase(String purchaseId) {
		return getSqlSession().selectOne("getOnePurchase", purchaseId);
	}

	@Override
	public List<PurchaseVO> getAllPurchase() {
		return getSqlSession().selectList("getAllPurchase");
	}


}
