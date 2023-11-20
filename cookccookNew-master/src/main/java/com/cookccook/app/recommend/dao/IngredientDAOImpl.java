/**
 * 작성자: 지훈
 * 작성일: 1012
 * 작성내용: dao임쁠 생성
 */
package com.cookccook.app.recommend.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.recommend.vo.ArtIngVO;
import com.cookccook.app.recommend.vo.IngredientVO;

@Repository
public class IngredientDAOImpl extends SqlSessionDaoSupport
							   implements IngredientDAO {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<IngredientVO> getAllIngredientList() {
		return getSqlSession().selectList("getAllIngredientList");
	}

	@Override
	public List<String> getAllIngredientName() {
		return getSqlSession().selectList("getAllIngredientName");
	}
	
	@Override
	public String getIngredientIdByName(String ingredientName) {
		return getSqlSession().selectOne("getIngredientIdByName", ingredientName);
	}
	
	@Override
	public int articeIngredientParser(ArtIngVO artIngVO) {
		return getSqlSession().insert("articeIngredientParser", artIngVO);
	}
}
