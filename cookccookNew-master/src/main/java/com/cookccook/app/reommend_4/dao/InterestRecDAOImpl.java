package com.cookccook.app.reommend_4.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.reommend_4.vo.InterestRecVO;

@Repository
public class InterestRecDAOImpl extends SqlSessionDaoSupport implements InterestRecDAO{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<InterestRecVO> allInterestRecommend(String memberId) {
		return getSqlSession().selectList("allInterestRecommend", memberId);
	}


}
