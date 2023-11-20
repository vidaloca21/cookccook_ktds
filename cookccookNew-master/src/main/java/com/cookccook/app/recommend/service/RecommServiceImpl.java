package com.cookccook.app.recommend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.article.dao.ArticleDAO;
import com.cookccook.app.article.dao.RecipeDAO;
import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.article.vo.SearchArticleVO;
import com.cookccook.app.recommend.dao.RecommDAO;
import com.cookccook.app.recommend.vo.DailyRankVO;
import com.cookccook.app.recommend.vo.ScoreVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.util.IngredientKeywordMaker;
import com.cookccook.util.NLPSearchUtil;

@Service
public class RecommServiceImpl implements RecommService {
	
	@Autowired
	private RecommDAO recommDAO;
	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private RecipeDAO recipeDAO;
	@Autowired
	private IngredientKeywordMaker ingredientKeywordMaker;
	@Autowired 
	private IngredientService ingredientService;
	@Autowired
	private NLPSearchUtil nlpSearchUtil;
	
	@Override
	public ArticleListVO searchRecommArticleByIngredient(SearchArticleVO searchArticleVO) {
		ArticleListVO articleListVO = new ArticleListVO();
		String keyword = searchArticleVO.getSearchKeyword();
		String result = ingredientKeywordMaker.keywordArrayMapper(ingredientService, keyword);
		List<ArticleVO> articleList = getArticleByIngName(result);
		int listsize = articleList.size();
		articleListVO.setArticleList(articleList);
		articleListVO.setArticleCnt(listsize);
		return articleListVO;
	}
	
	
	@Override
	public ArticleListVO searchRecommArticleBySentence(SearchArticleVO searchArticleVO) {
			String sentence = searchArticleVO.getSearchKeyword();
			List<String> keywordList = nlpSearchUtil.nlpSearchResult(sentence);
			List<String> tempList = new ArrayList<>();
			for (String keyword: keywordList) {
				tempList.addAll(getArticleIdByCuisineName(keyword));
				tempList.addAll(getArticleIdByAnniversaryName(keyword));
				tempList.addAll(getArticleIdByTagName(keyword));
				String ingKeyword = ingredientKeywordMaker.keywordArrayMapper(ingredientService, keyword);
				tempList.addAll(getArticleIdByIngName(ingKeyword));
			}
			Set<String> tempSet = new HashSet<>(tempList);
			List<String> articleIdList = new ArrayList<>(tempSet);
			List<ArticleVO> articleList = new ArrayList<>();
			for(String articleId: articleIdList) {
				articleList.add(articleDAO.getOneRecipeArticle(articleId));
			}
			ArticleListVO articleListVO = new ArticleListVO();
			articleListVO.setArticleCnt(articleList.size());
			articleListVO.setArticleList(articleList);
		return articleListVO;
	}
	
	
	@Override
	public List<ArticleVO> getArticleByIngName(String keyword) {
		return recommDAO.getArticleByIngName(keyword);
	}
	
	@Override
	public List<ArticleVO> getArticleByTagName(String keyword) {
		return recommDAO.getArticleByTagName(keyword);
	}
	
	@Override
	public List<ArticleVO> getArticleByAnniversaryName(String keyword) {
		return recommDAO.getArticleByAnniversaryName(keyword);
	}
	
	@Override
	public List<String> getAllTagTitle() {
		return recommDAO.getAllTagTitle();
	}
	
	@Override
	public List<RecipeVO> recommendRecipeOnProduct(String productId) {
		return recommDAO.recommendRecipeOnProduct(productId);
	}
	
	@Override
	public List<ProductVO> recommendProductOnRecipe(String articleId) {
		return recommDAO.recommendProductOnRecipe(articleId);
	}
	
	@Override
	public List<DailyRankVO> getDailyRank() {
		List<DailyRankVO> dailyRankList = recommDAO.getDailyRank();
		for (DailyRankVO dailyrank: dailyRankList) {
			String articleId = dailyrank.getArticleId();
			RecipeVO recipe = recipeDAO.getOneRecipe(articleId);
			dailyrank.setRecipeVO(recipe);
		}
		return dailyRankList;
	}
	
	@Transactional
	@Override
	public boolean addScore(ScoreVO scoreVO) {
		int insertCount = recommDAO.addScore(scoreVO);
		return insertCount >0;
	}
	
	@Override
	public List<String> getArticleIdByTagName(String keyword) {
		return recommDAO.getArticleIdByTagName(keyword);
	}
	@Override
	public List<String> getArticleIdByIngName(String keyword) {
		return recommDAO.getArticleIdByIngName(keyword);
	}
	@Override
	public List<String> getArticleIdByAnniversaryName(String keyword) {
		return recommDAO.getArticleIdByAnniversaryName(keyword);
	}
	@Override
	public List<String> getArticleIdByCuisineName(String keyword) {
		return recommDAO.getArticleIdByCuisineName(keyword);
	}
	
}
