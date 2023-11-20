package com.cookccook.app.subscribe.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.app.subscribe.vo.SubscribeVO;

@Repository
public class MySubsDaoImpl extends SqlSessionDaoSupport
							implements MySubsDAO{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public SubPayVO getMySubsInfo(String memberId) {
		return getSqlSession().selectOne("getMySubsInfo", memberId);
	}

	@Override
	public SubPayVO getMyNextSubName(String memberId) {
		return getSqlSession().selectOne("getMyNextSubName", memberId);
	}

	@Override
	public List<SubscribeVO> getMyNextSubItem(String memberId) {
		return getSqlSession().selectList("getMyNextSubItem", memberId);
	}
	
	@Override
	public SubPayVO getLatestDeliveryDate(String memberId) {
		return getSqlSession().selectOne("getLatestDeliveryDate", memberId);
	}

	@Override
	public List<IngredientVO> getLatestSubItems(String memberId) {
		return getSqlSession().selectList("getLatestSubItems", memberId);
	}

	@Override
	public List<SubPayVO> getPrevSubInfos(String memberId) {
		return getSqlSession().selectList("getPrevSubInfos", memberId);
	}

	@Override
	public List<IngredientVO> getPrevSubItems(String memberId) {
		return getSqlSession().selectList("getPrevSubItems", memberId);
	}

	@Override
	public List<ArticleVO> getIngRecom(String memberId) {
		return getSqlSession().selectList("getIngRecom", memberId);
	}

	@Override
	public List<SubPayVO> getPrevHistory(String memberId) {
		return getSqlSession().selectList("getPrevHistory", memberId);
	}

	@Override
	public List<ArticleVO> getMySubRecom(String memberId) {
		return getSqlSession().selectList("getMySubRecom", memberId);
	}
	
	

}
