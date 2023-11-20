package com.cookccook.app.shop.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

@Repository
public class PurchaseDAOImpl extends SqlSessionDaoSupport implements PurchaseDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int createNewPurchase(PurchaseVO purchaseVO) {
		return getSqlSession().insert("createNewPurchase", purchaseVO);
	}
	
	@Override
	public int createNewPurchaseProduct(PurchaseProductVO purchaseProductVO) {
		return getSqlSession().insert("createNewPurchaseProduct", purchaseProductVO);
	}
	
	@Override
	public int createNewDeliverPlace(DeliverPlaceVO deliverPlaceVO) {
		return getSqlSession().insert("createNewDeliverPlace", deliverPlaceVO);
	}
	
	@Override
	public int cancelPurchaseProduct(String purchaseId) {
		return getSqlSession().update("cancelPurchaseProduct", purchaseId);
	}
	
	@Override
	public ChoiceVO getOneChoiceByChoiceId(String choiceId) {
		return getSqlSession().selectOne("getOneChoiceByChoiceId", choiceId);
	}
	
	@Override
	public String getCartPk() {
		return getSqlSession().selectOne("getCartPk");
	}
	
	@Override
	public String getPurchasePk() {
		return getSqlSession().selectOne("getPurchasePk");
	}
	
	
}
