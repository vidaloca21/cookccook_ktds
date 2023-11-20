package com.cookccook.app.subscribe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.subscribe.dao.SubscribeDAO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;
import com.cookccook.app.subscribe.vo.PaymentsVO;
import com.cookccook.app.subscribe.vo.SubIngVO;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	private SubscribeDAO subscribeDAO;
	
	@Override
	public List<SubscribeVO> getCurrentSubscribe() {
		List<SubscribeVO> subListVO = subscribeDAO.getCurrentSubscribe();
		return subListVO;
	}
	
	@Override
	public List<DeliverPlaceVO> getDeliverPlaceByMemberId(String memberId) {
		List<DeliverPlaceVO> deliverPlaceList = subscribeDAO.getDeliverPlaceByMemberId(memberId);
		return deliverPlaceList;
	}
	
	@Override
	public SubscribeVO getOneSubscribeChoice(String subscribeId) {
		SubscribeVO subscribeVO = subscribeDAO.getOneSubscribeChoice(subscribeId);
		return subscribeVO;
	}
	
	@Transactional
	@Override
	public boolean createNewSubscribePay(SubPayVO subPayVO) {
		int insertCount = subscribeDAO.createNewSubscribePay(subPayVO);
		return insertCount >0;
	}

	@Override
	public SubPayVO getCurrentSubpayByMemberId(String memberId) {
		SubPayVO subPayVO = subscribeDAO.getCurrentSubpayByMemberId(memberId);
		return subPayVO;
	}

	@Override
	public List<IngredientVO> getIngredientsBySubPayId(String subPayId) {
		List<IngredientVO> ingredientList = subscribeDAO.getIngredientsBySubPayId(subPayId);
		return ingredientList;
	}

	@Override
	public boolean checkSubscribable(String memberId) {
		SubPayVO subPayVO = subscribeDAO.getCurrentSubpayByMemberId(memberId);
		if(subPayVO != null) {
			return false;
		}
		return true;
	}

	@Transactional
	@Override
	public boolean cancleOneSubpay(String subPayId) {
		int updateCount = subscribeDAO.cancleOneSubpay(subPayId);
		return updateCount > 0;
	}

	@Transactional
	@Override
	public boolean createNewPayments(PaymentsVO paymentsVO) {
		int insertCount = subscribeDAO.createNewPayments(paymentsVO);
		return insertCount > 0;
	}

	@Override
	public PaymentsVO getPaymentByImpUid(String impUid) {
		PaymentsVO paymentsVO = subscribeDAO.getPaymentByImpUid(impUid);
		return paymentsVO;
	}

	@Transactional
	@Override
	public boolean createNewSubscribe(List<SubscribeVO> subscribes) {
		int insertCount = subscribeDAO.createNewSubscribe(subscribes);
		return insertCount >= 3;
	}

	@Override
	public IngredientVO getIngredientsByIngredientName(String ingredientName) {
		IngredientVO ingredientVO = subscribeDAO.getIngredientsByIngredientName(ingredientName);
		return ingredientVO;
	}

	@Override
	public List<SubscribeVO> getSubscribeList(String subChoice) {
		List<SubscribeVO> subscribeList = subscribeDAO.getSubscribeList(subChoice);
		return subscribeList;
	}

	@Transactional
	@Override
	public boolean createNewSubIng(List<SubIngVO> subIngs) { //
		int insertCount = subscribeDAO.createNewSubIng(subIngs);
		return insertCount >= subIngs.size();
	}
	
	@Transactional
	@Override
	public boolean createIngredient(IngredientVO ingredientVO) { //
		int insertCount = subscribeDAO.createIngredient(ingredientVO);
		return insertCount > 0;
	}

	@Override
	public List<IngredientVO> getIngredientList() {
		List<IngredientVO> ingredientList = subscribeDAO.getIngredientList();
		return ingredientList;
	}

	@Override
	public SubscribeVO getOneSubscribe(String subChoice) {
		SubscribeVO subscribeVO  = subscribeDAO.getOneSubscribe(subChoice);
		return subscribeVO;
	}

	@Override
	public List<SubscribeVO> getSubscribeListBySubChoice(String subChoice) {
		List<SubscribeVO> subscribeList = subscribeDAO.getSubscribeListBySubChoice(subChoice);
		return subscribeList;
	}

	@Transactional
	@Override
	public boolean deleteSubIngsBySubscribeId(List<SubscribeVO> subscribeList) {
		int deleteCount = subscribeDAO.deleteSubIngsBySubscribeId(subscribeList);
		return deleteCount >= subscribeList.size();
	}

	@Override
	public List<SubIngVO> getSubIngListBySubscribeId(String subscribeId) {
		List<SubIngVO> subIngList = subscribeDAO.getSubIngListBySubscribeId(subscribeId);
		return subIngList;
	}
}
