package com.cookccook.app.shop.dao;

import java.util.List;

import com.cookccook.app.shop.vo.PurchaseVO;

public interface PurchaseProductDetailDAO {
	
	/**
	 * 파라미터로 전달받은 주문내역건의 ID 정보를 조회한다.
	 * @param purchaseProductId 주문내역 ID 
	 * @return
	 */
	public PurchaseVO getOnePurchase(String purchaseId);
	
	public List<PurchaseVO> getAllPurchase();

}
