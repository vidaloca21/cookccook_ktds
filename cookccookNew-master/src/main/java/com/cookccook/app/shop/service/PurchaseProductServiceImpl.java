package com.cookccook.app.shop.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.shop.dao.PurchaseProductDAO;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.KeywordVO;
import com.cookccook.app.shop.vo.PurchaseListVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

@Service
public class PurchaseProductServiceImpl implements PurchaseProductService {
	
	@Autowired
	private PurchaseProductDAO purchaseProductDAO;
		
	public int getPLAllCount() {
		return purchaseProductDAO.getPLAllCount();
		
		
	}
	@Override
	public PurchaseListVO getPurchaseListByMemId(String memberId) {
		PurchaseListVO purchaseListVO = new PurchaseListVO();
		purchaseListVO.setPurchaseList(purchaseProductDAO.getPurchaseListByMemId(memberId));
		
		return purchaseListVO;
	}
	
	@Override
	public boolean sendCancelRequest(CancelVO cancelVO) {
		//int updateCount = purchaseProductDAO.updateCancelStatus(purchaseProductVO);
		int createCount = purchaseProductDAO.createCancelRequest(cancelVO);
		//int resultCount = updateCount + createCount;
		return createCount > 0;
	}
	

	@Override
	public PurchaseListVO searchPurchaseList(KeywordVO keywordVO) {
		PurchaseListVO purchaseListVO = new PurchaseListVO();
		purchaseListVO.setPurchaseList(purchaseProductDAO.searchPurchaseList(keywordVO));
		return purchaseListVO;
	}

	
	@Override
	public PurchaseVO getPurchaseInfoByPurchaseId(String purchaseId) {
		PurchaseVO purchaseVO = purchaseProductDAO.getPurchaseInfoByPurchaseId(purchaseId);
		return purchaseVO;
	}

}
