package com.cookccook.app.subscribe.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.app.subscribe.vo.PaymentsVO;
import com.cookccook.app.subscribe.vo.SubIngVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;

@Repository
public class SubscribeDAOImpl extends SqlSessionDaoSupport
							  implements SubscribeDAO {
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<SubscribeVO> getCurrentSubscribe() {
		return getSqlSession().selectList("getCurrentSubscribe");
	}
	
	@Override
	public List<SubPayVO> getCurrentSubAndMember() {
		return getSqlSession().selectList("getCurrentSubAndMember");
	}
	
	@Override
	public List<DeliverPlaceVO> getDeliverPlaceByMemberId(String memberId) {
		return getSqlSession().selectList("getDeliverPlaceByMemberId", memberId);
	}
	
	@Override
	public SubscribeVO getOneSubscribeChoice(String subscribeId) {
		return getSqlSession().selectOne("getOneSubscribeChoice", subscribeId);
	}
	
	@Override
	public int createNewSubscribePay(SubPayVO subPayVO) {
		return getSqlSession().insert("createNewSubscribePay", subPayVO);
	}

	@Override
	public SubPayVO getCurrentSubpayByMemberId(String memberId) {
		return getSqlSession().selectOne("getCurrentSubpayByMemberId", memberId);
	}

	@Override
	public List<IngredientVO> getIngredientsBySubPayId(String subPayId) {
		return getSqlSession().selectList("getIngredientsBySubPayId", subPayId);
	}

	@Override
	public int cancleOneSubpay(String subPayId) {
		return getSqlSession().update("cancleOneSubpay", subPayId);
	}

	@Override
	public int createNewPayments(PaymentsVO paymentsVO) {
		return getSqlSession().insert("createNewPayments", paymentsVO);
	}

	@Override
	public PaymentsVO getPaymentByImpUid(String impUid) {
		return getSqlSession().selectOne("getPaymentByImpUid", impUid);
	}

	@Override
	public int createNewSubscribe(List<SubscribeVO> subscribes) {
		int cnt = 0;
		for(int i = 0; i < 3; i++) {
			if(getSqlSession().insert("createNewSubscribe", subscribes.get(i)) > 0)
				cnt++;
		}
		return cnt;
	}

	@Override
	public IngredientVO getIngredientsByIngredientName(String ingredientName) {
		return getSqlSession().selectOne("getIngredientsByIngredientName", ingredientName);
	}

	@Override
	public List<SubscribeVO> getSubscribeList(String subChoice) {
		return getSqlSession().selectList("getSubscribeList", subChoice);
	}

	@Override
	public int createNewSubIng(List<SubIngVO> subIngs) {
		int cnt = 0;
		for(int i = 0; i < subIngs.size(); i++) {
			if(getSqlSession().insert("createNewSubIng", subIngs.get(i)) > 0)
				cnt++;
		}
		return cnt;
	}

	@Override
	public List<IngredientVO> getIngredientList() {
		return getSqlSession().selectList("getIngredientList");
	}

	@Override
	public SubscribeVO getOneSubscribe(String subChoice) {
		return getSqlSession().selectOne("getOneSubscribe", subChoice);
	}

	@Override
	public List<SubscribeVO> getSubscribeListBySubChoice(String subChoice) {
		return getSqlSession().selectList("getSubscribeListBySubChoice", subChoice);
	}

	@Override
	public int deleteSubIngsBySubscribeId(List<SubscribeVO> subscribeList) {
		int cnt = 0;
		for(int i = 0; i < subscribeList.size(); i++) {
			if(getSqlSession().delete("deleteSubIngsBySubscribeId", subscribeList.get(i).getSubscribeId()) > 0)
				cnt++;
		}
		return cnt;
	}

	@Override
	public List<SubIngVO> getSubIngListBySubscribeId(String subscribeId) {
		return getSqlSession().selectList("getSubIngListBySubscribeId", subscribeId);
	}

	@Override
	public int createIngredient(IngredientVO ingredientVO) {
		return getSqlSession().insert("createIngredient", ingredientVO);
	}
}
