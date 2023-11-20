package com.cookccook.app.subscribe.service;

import java.util.List;

import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;
import com.cookccook.app.subscribe.vo.PaymentsVO;
import com.cookccook.app.subscribe.vo.SubIngVO;

public interface SubscribeService {

	public List<SubscribeVO> getCurrentSubscribe();
	
	public List<DeliverPlaceVO> getDeliverPlaceByMemberId(String memberId);
	
	public SubscribeVO getOneSubscribeChoice(String subscribeId);
	
	public boolean createNewSubscribePay(SubPayVO subPayVO);

	public List<IngredientVO> getIngredientsBySubPayId(String subPayId);

	public boolean checkSubscribable(String memberId);
	
	public boolean cancleOneSubpay(String subPayId);

	public boolean createNewPayments(PaymentsVO paymentsVO);

	public PaymentsVO getPaymentByImpUid(String impUid);

	public boolean createNewSubscribe(List<SubscribeVO> subscribes);

	public IngredientVO getIngredientsByIngredientName(String ingredientName);

	public List<SubscribeVO> getSubscribeList(String subChoice);

	public boolean createNewSubIng(List<SubIngVO> subIngs);

	public List<IngredientVO> getIngredientList();

	public SubscribeVO getOneSubscribe(String subChoice);

	public List<SubscribeVO> getSubscribeListBySubChoice(String subChoice);

	public boolean deleteSubIngsBySubscribeId(List<SubscribeVO> subscribeList);

	public List<SubIngVO> getSubIngListBySubscribeId(String subscribeId);

	public boolean createIngredient(IngredientVO ingredientVO);

	public SubPayVO getCurrentSubpayByMemberId(String memberId);
}
