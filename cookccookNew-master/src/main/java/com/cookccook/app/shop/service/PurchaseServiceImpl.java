package com.cookccook.app.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.shop.dao.PurchaseDAO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDAO purchaseDAO;
	
	@Transactional
	@Override
	public boolean createNewPurchase(PurchaseVO purchaseVO) {
		int insertCount = purchaseDAO.createNewPurchase(purchaseVO);
		return insertCount >0;
	}
	
	@Transactional
	@Override
	public boolean createNewPurchaseProduct(List<PurchaseProductVO> purchaseProductVOlist) {
		int insertCount = 0;
		for (PurchaseProductVO purchaseProductVO: purchaseProductVOlist) {
			insertCount += purchaseDAO.createNewPurchaseProduct(purchaseProductVO); 
		}
		return insertCount == purchaseProductVOlist.size();
	}
	
	@Transactional
	@Override
	public boolean createNewDeliverPlace(DeliverPlaceVO deliverPlaceVO) {
		int insertCount = purchaseDAO.createNewDeliverPlace(deliverPlaceVO);
		return insertCount >0;
	}
	
	@Transactional
	@Override
	public boolean cancelPurchaseProduct(String purchaseId) {
		int updateCount = purchaseDAO.cancelPurchaseProduct(purchaseId);
		return updateCount >0;
	}
	
	@Override
	public ChoiceVO getOneChoiceByChoiceId(String choiceId) {
		return purchaseDAO.getOneChoiceByChoiceId(choiceId);
	}
	
	@Override
	public String getCartPk() {
		return purchaseDAO.getCartPk();
	}
	
	@Override
	public String getPurchasePk() {
		return purchaseDAO.getPurchasePk();
	}
}
