package com.cookccook.app.article.dao;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.SearchArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> getAllRecipeArticle();
	public List<ArticleVO> getRecipeArticleByTitle (String title);
	public ArticleVO getOneRecipeArticle(String articleId);
	public ArticleVO createNewRecipeArticle(ArticleVO articleVO);
	public int updateOneRecipeArticle(ArticleVO articleVO);
	public int deleteOneRecipeArticle(String articleId);
	
	public List<ArticleVO> getAllCommunityArticle(SearchArticleVO searchArticleVO);
	public List<ArticleVO> getCommunityArticleByTitle (String title);
	public ArticleVO getOneCommunityArticle(String articleId);
	public int createNewCommunityArticle(ArticleVO articleVO);
	public int updateOneCommunityArticle(ArticleVO articleVO);
	public int deleteOneCommunityArticle(String articleId);
	
	public int increaseViewCount(String articleId);
	public int increaseLikeCount(InterestVO interestVO);
	public int decreaseLikeCount(String interestId);
	public List<InterestVO> getAllInterestByArticleId(String articleId);
	public List<ArticleVO> searchAllRecipeArticle(SearchArticleVO searchArticleVO);
	public List<ArticleVO> searchAllCommunityArticle(SearchArticleVO searchArticleVO);

	public int getArticleAllCount(SearchArticleVO searchArticleVO);
	public int getCommuArticleAllCount(SearchArticleVO searchArticleVO);
	
}
