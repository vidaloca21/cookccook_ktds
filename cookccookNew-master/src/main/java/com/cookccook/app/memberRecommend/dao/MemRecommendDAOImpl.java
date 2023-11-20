package com.cookccook.app.memberRecommend.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemRecommendDAOImpl extends SqlSessionDaoSupport implements MemRecommendDAO{
	
		
		@Autowired
		@Override
		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			super.setSqlSessionTemplate(sqlSessionTemplate);
		}

		@Override
		public List<String> getMemInterestIdList(String memberId) {
			return getSqlSession().selectList("getMemInterestIdList", memberId);
		}

}
