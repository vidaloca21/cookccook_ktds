package com.cookccook.app.shop.service;

import java.util.List;

import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

public interface PurchaseService {

	public boolean createNewPurchase(PurchaseVO purchaseVO);
	public boolean createNewPurchaseProduct(List<PurchaseProductVO> purchaseProductVOlist);
	public boolean createNewDeliverPlace(DeliverPlaceVO deliverPlaceVO);
	public boolean cancelPurchaseProduct(String purchaseId);
	public ChoiceVO getOneChoiceByChoiceId(String choiceId);
	public String getCartPk();
	public String getPurchasePk();
}
