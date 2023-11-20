package com.cookccook.app.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.DeliverPlaceVO;

@Repository
public class DeliverPlaceDAOImpl extends SqlSessionDaoSupport implements DeliverPlaceDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<DeliverPlaceVO> getAlldeliverPlaceByMemberId(String memberId) {
		return getSqlSession().selectList("getAlldeliverPlaceByMemberId", memberId);
	}

	@Override
	public int addNewDeliverPlace(DeliverPlaceVO deliverPlaceVO) {
		return getSqlSession().insert("addNewDeliverPlace", deliverPlaceVO);
	}

	@Override
	public int updateOneDeliverPlace(DeliverPlaceVO deliverPlaceVO) {
		return getSqlSession().update("deliverPlaceVO", deliverPlaceVO);
	}

	@Override
	public int deleteOneDeliverPlace(String deliverPlaceId) {
		return getSqlSession().delete("deleteOneDeliverPlace", deliverPlaceId);
	}

}
