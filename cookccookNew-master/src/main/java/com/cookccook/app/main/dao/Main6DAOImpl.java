package com.cookccook.app.main.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.RecipeVO;

@Repository
public class Main6DAOImpl extends SqlSessionDaoSupport implements Main6DAO {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<String> getArtIdByAnniv() {
		return getSqlSession().selectList("getArtIdByAnniv");
	}

	@Override
	public List<RecipeVO> getArtInfoByArtId(String articleId) {
		
		return getSqlSession().selectList("getArtInfoByArtId", articleId);
	}

	@Override
	public List<String> getRelatedArtId(String articleId) {
		return getSqlSession().selectList("getRelatedArtId", articleId);
	}
	
	
	public List<String> getRecomByTemp(String temperature) {
		return getSqlSession().selectList("getRecomByTemp", temperature);
	}

}
