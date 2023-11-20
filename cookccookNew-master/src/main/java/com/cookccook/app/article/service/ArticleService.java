package com.cookccook.app.article.service;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.SearchArticleVO;

public interface ArticleService {
	

	// pagination을 위해 수정
	public ArticleListVO getAllRecipeArticle(SearchArticleVO searchArticleVO);
	public List<ArticleVO> getRecipeArticleByTitle(String title);
	public ArticleVO getOneRecipeArticle(String articleId, boolean isIncrease);
	public ArticleVO createNewRecipeArticle(ArticleVO articleVO);
	public boolean updateOneRecipeArticle(ArticleVO articleVO);
	public boolean deleteOneRecipeArticle(String articleId);
	
	public ArticleListVO getAllCommunityArticle(SearchArticleVO searchArticleVO);
	public List<ArticleVO> getCommunityArticleByTitle (String title);
	public ArticleVO getOneCommunityArticle(String articleId, boolean isIncrease);
	public boolean createNewCommunityArticle(ArticleVO articleVO);
	public boolean updateOneCommunityArticle(ArticleVO articleVO);
	public boolean deleteOneCommunityArticle(String articleId);
	
	public boolean increaseLikeCount(InterestVO interestVO);
	public boolean decreaseLikeCount(String interestId);
	public List<InterestVO> getAllInterestByArticleId(String articleId);
	
}
