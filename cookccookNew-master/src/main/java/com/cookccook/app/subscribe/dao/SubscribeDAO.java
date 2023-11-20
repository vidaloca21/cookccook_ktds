package com.cookccook.app.subscribe.dao;

import java.util.List;

import com.cookccook.app.subscribe.vo.SubscribeVO;
import com.cookccook.app.subscribe.vo.PaymentsVO;
import com.cookccook.app.subscribe.vo.SubIngVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.subscribe.vo.SubPayVO;

public interface SubscribeDAO {
	
	public List<SubscribeVO> getCurrentSubscribe();
	
	public List<SubPayVO> getCurrentSubAndMember();
	
	public SubscribeVO getOneSubscribeChoice(String subscribeId);
	
	public List<DeliverPlaceVO> getDeliverPlaceByMemberId(String memberId);
	
	public int createNewSubscribePay(SubPayVO subPayVO);

	public SubPayVO getCurrentSubpayByMemberId(String memberId);

	public List<IngredientVO> getIngredientsBySubPayId(String subPayId);
	
	public int cancleOneSubpay(String subPayId);

	public int createNewPayments(PaymentsVO paymentsVO);

	public PaymentsVO getPaymentByImpUid(String impUid);

	public int createNewSubscribe(List<SubscribeVO> subscribes);

	public IngredientVO getIngredientsByIngredientName(String ingredientName);

	public List<SubscribeVO> getSubscribeList(String subChoice);

	public int createNewSubIng(List<SubIngVO> subIngs);

	public List<IngredientVO> getIngredientList();

	public SubscribeVO getOneSubscribe(String subChoice);

	public List<SubscribeVO> getSubscribeListBySubChoice(String subChoice);

	public int deleteSubIngsBySubscribeId(List<SubscribeVO> subscribeList);

	public List<SubIngVO> getSubIngListBySubscribeId(String subscribeId);

	public int createIngredient(IngredientVO ingredientVO);
}
