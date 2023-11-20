package com.cookccook.app.shop.service;

import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.KeywordVO;
import com.cookccook.app.shop.vo.PurchaseListVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

public interface PurchaseProductService {
	
	
	public int getPLAllCount() ;
	
	
	/**
	 * 이용자의 주문 내역을 모두 조회한다.
	 * @return PurchaseProductVO의 주문 Id
	 */
	
	public PurchaseListVO getPurchaseListByMemId(String memberId);
	
	/**
	 * 취소 요청
	 * @param purchaseProductId 취소처리가 되어야하는 주문내역상품의 id 
	 * @param cancelVO 취소 사유등이 담긴 취소 정보
	 * @return 성공한 횟수 반환
	 */
	public boolean sendCancelRequest(CancelVO cancelVO);

		
	/**
	 * 구매 상품 키워드 검색
	 * @param keyword 인풋에 입력한 검색어
	 * @return 검색어를 포함한 상품명(옵션id로 조회)하여 해당 상품이 들어있는 주문id를 가져온다. ??
	 * (getAllPurchaseList 에서 작업된 내용 따오면 될듯)
	 */
	public PurchaseListVO searchPurchaseList(KeywordVO searchKeyword);
	
	
	public PurchaseVO getPurchaseInfoByPurchaseId(String purchaseId);
}
