package com.cookccook.app.shop.service;


import com.cookccook.app.shop.vo.PurchaseListVO;
import com.cookccook.app.shop.vo.PurchaseVO;

public interface PurchaseProductDetailService {
	
	public PurchaseListVO getAllPurchase();

	/**
	 * 파라미터로 전달받은 id 로 주문건을 조회한다.
	 * @param purchaseId 조회할 주문건의 id
	 * @return 주문건 정보
	 */
	public PurchaseVO getOnePurchase(String purchaseId);
	

}
