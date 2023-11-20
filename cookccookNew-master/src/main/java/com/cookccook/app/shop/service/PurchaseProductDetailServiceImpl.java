package com.cookccook.app.shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.shop.dao.PurchaseProductDetailDAO;
import com.cookccook.app.shop.vo.PurchaseListVO;
import com.cookccook.app.shop.vo.PurchaseVO;

@Service
public class PurchaseProductDetailServiceImpl implements PurchaseProductDetailService {

	@Autowired
	private PurchaseProductDetailDAO purchaseProductDetailDAO;
	

	@Override
	public PurchaseListVO getAllPurchase() {
		PurchaseListVO purchaseListVO = new PurchaseListVO();
		purchaseListVO.setPurchaseList(purchaseProductDetailDAO.getAllPurchase());
		return purchaseListVO;
	}

	@Override
	public PurchaseVO getOnePurchase(String purchaseId) {
		PurchaseVO purchaseVO = purchaseProductDetailDAO.getOnePurchase(purchaseId);
		
	
		
		
		/*if (purchaseVO != null) {
			
			int totalPurPrdPrice = 0;
			int totalDeliverFee = 0;
			
			List<PurchaseProductVO> purchaseProductList = purchaseVO.getPurchaseProductList();
			if (purchaseProductList != null) {
				
				for (PurchaseProductVO purchaseProductVO : purchaseProductList) {
					totalDeliverFee += purchaseProductVO.getDeliverFee();
					totalPurPrdPrice += purchaseProductVO.getPurPrdPrice();
				}
			}
			
			purchaseVO.setTotalDeliverFee(totalDeliverFee);
			purchaseVO.setTotalPurPrdPrice(totalPurPrdPrice);
		}*/
		
		
		return purchaseVO;
	}

}
