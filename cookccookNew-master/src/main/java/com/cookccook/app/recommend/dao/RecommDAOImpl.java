package com.cookccook.app.recommend.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.article.vo.SearchArticleVO;
import com.cookccook.app.recommend.vo.DailyRankVO;
import com.cookccook.app.recommend.vo.ScoreVO;
import com.cookccook.app.shop.vo.ProductVO;

@Repository
public class RecommDAOImpl extends SqlSessionDaoSupport implements RecommDAO {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<ArticleVO> searchRecommArticle(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectList("searchRecommArticle", searchArticleVO);
	}
	
	@Override
	public int getRecommCount(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectOne("getRecommCount", searchArticleVO);
	}
	
	@Override
	public List<ArticleVO> getArticleByIngName(String keyword) {
		return getSqlSession().selectList("getArticleByIngName", keyword);
	}
	@Override
	public List<ArticleVO> getArticleByTagName(String keyword) {
		return getSqlSession().selectList("getArticleByTagName", keyword);
	}
	@Override
	public List<ArticleVO> getArticleByAnniversaryName(String keyword) {
		return getSqlSession().selectList("", keyword);
	}
	@Override
	public List<String> getAllTagTitle() {
		return getSqlSession().selectList("getAllTagTitle");
	}
	
	@Override
	public List<RecipeVO> recommendRecipeOnProduct(String productId) {
		return getSqlSession().selectList("recommendRecipeOnProduct", productId);
	}
	
	@Override
	public List<ProductVO> recommendProductOnRecipe(String articleId) {
		return getSqlSession().selectList("recommendProductOnRecipe", articleId);
	}
	
	@Override
	public List<DailyRankVO> getDailyRank() {
		return getSqlSession().selectList("getDailyRank");
	}
	
	@Override
	public int addScore(ScoreVO scoreVO) {
		return getSqlSession().insert("addScore", scoreVO);
	}
	@Override
	public List<String> getArticleIdByTagName(String keyword) {
		return getSqlSession().selectList("getArticleIdByTagName", keyword);
	}
	@Override
	public List<String> getArticleIdByIngName(String keyword) {
		return getSqlSession().selectList("getArticleIdByIngName", keyword);
	}
	@Override
	public List<String> getArticleIdByAnniversaryName(String keyword) {
		return getSqlSession().selectList("getArticleIdByAnniversaryName", keyword);
	}
	
	@Override
	public List<String> getArticleIdByCuisineName(String keyword) {
		return getSqlSession().selectList("getArticleIdByCuisineName", keyword);
	}
}
