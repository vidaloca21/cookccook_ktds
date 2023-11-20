package com.cookccook.app.recommend.vo;

import com.cookccook.app.article.vo.RecipeVO;

public class DailyRankVO {

	private String date;
	private String articleId;
	private int totalScore;
	
	private RecipeVO recipeVO;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public RecipeVO getRecipeVO() {
		return recipeVO;
	}
	public void setRecipeVO(RecipeVO recipeVO) {
		this.recipeVO = recipeVO;
	}
	
	
	
}
