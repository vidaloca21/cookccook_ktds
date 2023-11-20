package com.cookccook.app.shop.dao;

import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

public interface PurchaseDAO {
	
	public int createNewPurchase(PurchaseVO purchaseVO);
	public int createNewPurchaseProduct(PurchaseProductVO puchaseProductVO);
	public int createNewDeliverPlace(DeliverPlaceVO deliverPlaceVO);
	public int cancelPurchaseProduct(String purchaseId);
	public ChoiceVO getOneChoiceByChoiceId(String choiceId);
	public String getCartPk();
	public String getPurchasePk();
}
