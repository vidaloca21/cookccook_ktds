package com.cookccook.app.article.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.RecipeVO;

@Repository
public class RecipeDAOImpl extends SqlSessionDaoSupport
						   implements RecipeDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<RecipeVO> getAllIngredientDetails() {
		return getSqlSession().selectList("getAllIngredientDetails");
	}
	
	@Override
	public String getIngredientDetailByArticleId(String articleId) {
		return getSqlSession().selectOne("getIngredientDetailByArticleId", articleId);
	}
	
	@Override
	public int createNewRecipe(RecipeVO recipeVO) {
		return getSqlSession().insert("createNewRecipe", recipeVO);
	}

	@Override
	public RecipeVO getOneRecipe(String articleId) {
		return getSqlSession().selectOne("getOneRecipe",articleId);
	}
	
	@Override
	public List<String> getAllRecipeImg() {
		return getSqlSession().selectList("getAllRecipeImg");
	}
}
