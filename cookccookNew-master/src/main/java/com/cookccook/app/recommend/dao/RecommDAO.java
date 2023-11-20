package com.cookccook.app.recommend.dao;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.article.vo.SearchArticleVO;
import com.cookccook.app.recommend.vo.DailyRankVO;
import com.cookccook.app.recommend.vo.ScoreVO;
import com.cookccook.app.shop.vo.ProductVO;

public interface RecommDAO {

	public List<ArticleVO> searchRecommArticle(SearchArticleVO searchArticleVO);
	public int getRecommCount(SearchArticleVO searchArticleVO);
	public List<ArticleVO> getArticleByIngName(String keyword);
	public List<ArticleVO> getArticleByTagName(String keyword);
	public List<ArticleVO> getArticleByAnniversaryName(String keyword);
	public List<String> getAllTagTitle();
	public List<RecipeVO> recommendRecipeOnProduct(String productId);
	public List<ProductVO> recommendProductOnRecipe(String articleId);
	public List<DailyRankVO> getDailyRank();
	public int addScore(ScoreVO scoreVO);
	public List<String> getArticleIdByTagName(String keyword);
	public List<String> getArticleIdByIngName(String keyword);
	public List<String> getArticleIdByAnniversaryName(String keyword);
	public List<String> getArticleIdByCuisineName(String keyword);
}
