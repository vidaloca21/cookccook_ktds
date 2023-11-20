package com.cookccook.app.article.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.SearchArticleVO;

@Repository
public class ArticleDAOImpl extends SqlSessionDaoSupport
							implements ArticleDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<ArticleVO> getAllRecipeArticle() {
		return getSqlSession().selectList("getAllRecipeArticle");
	}
	
	@Override
	public List<ArticleVO> getRecipeArticleByTitle(String title) {
		return getSqlSession().selectList("getRecipeArticleByTitle", title);
	}
	
	@Override
	public ArticleVO getOneRecipeArticle(String articleId) {
		return getSqlSession().selectOne("getOneRecipeArticle", articleId);
	}
	
	@Override
	public ArticleVO createNewRecipeArticle(ArticleVO articleVO) {
		getSqlSession().insert("createNewRecipeArticle", articleVO);
		return articleVO;
	}

	@Override
	public int updateOneRecipeArticle(ArticleVO articleVO) {
		return getSqlSession().update("updateOneRecipeArticle", articleVO);
	}

	@Override
	public int deleteOneRecipeArticle(String articleId) {
		return getSqlSession().update("deleteOneRecipeArticle", articleId);
	}


	@Override
	public List<ArticleVO> getAllCommunityArticle(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectList("getAllCommunityArticle", searchArticleVO);
	}

	@Override
	public List<ArticleVO> getCommunityArticleByTitle(String title) {
		return getSqlSession().selectList("getCommunityArticleByTitle", title);
	}

	@Override
	public ArticleVO getOneCommunityArticle(String articleId) {
		return getSqlSession().selectOne("getOneCommunityArticle", articleId);
	}

	@Override
	public int createNewCommunityArticle(ArticleVO articleVO) {
		return getSqlSession().insert("createNewCommunityArticle", articleVO);
	}

	@Override
	public int updateOneCommunityArticle(ArticleVO articleVO) {
		return getSqlSession().update("updateOneCommunityArticle", articleVO);
	}

	@Override
	public int deleteOneCommunityArticle(String articleId) {
		return getSqlSession().update("deleteOneCommunityArticle", articleId);
	}
	
	@Override
	public int increaseViewCount(String articleId) {
		return getSqlSession().update("increaseViewCount", articleId);
	}
	
	@Override
	public int increaseLikeCount(InterestVO interestVO) {
		return getSqlSession().insert("increaseLikeCount", interestVO);
	}
	
	@Override
	public int decreaseLikeCount(String interestId) {
		return getSqlSession().insert("decreaseLikeCount", interestId);
	}
	
	@Override
	public List<InterestVO> getAllInterestByArticleId(String articleId) {
		return getSqlSession().selectList("getAllInterestByArticleId", articleId);
	}
	@Override
	public List<ArticleVO> searchAllRecipeArticle(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectList("searchAllRecipeArticle", searchArticleVO);
	}
	
	@Override
	public List<ArticleVO> searchAllCommunityArticle(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectList("searchAllCommunityArticle", searchArticleVO);
	}

	@Override
	public int getArticleAllCount(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectOne("getArticleAllCount", searchArticleVO);
	}

	@Override
	public int getCommuArticleAllCount(SearchArticleVO searchArticleVO) {
		return getSqlSession().selectOne("getCommuArticleAllCount");
	}
}
