package com.cookccook.app.shop.dao;

import java.util.List;

import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.KeywordVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

public interface PurchaseProductDAO {
	

	public int getPLAllCount();
	
	public List<PurchaseVO> getPurchaseListByMemId(String memberId);
	
	public int updateCancelStatus(PurchaseProductVO purchaseProductVO);
	
	public int createCancelRequest(CancelVO cancelVO);
	
	/**
	 * 구매상품 키워드 검색 (조회)
	 * @param keyword 키워드를 받아온다
	 * @return 키워드에 맞는 상품이 들어있는 주문id를 가져온다.
	 */
	public List<PurchaseVO> searchPurchaseList(KeywordVO keywordVO);
	
	
	public PurchaseVO getPurchaseInfoByPurchaseId(String purchaseId);
	

}
