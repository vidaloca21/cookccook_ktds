package com.cookccook.app.article.service;

import java.util.List;

import com.cookccook.app.article.vo.RecipeVO;

public interface RecipeService {

	public List<RecipeVO> getAllIngredientDetails();
	
	public String getIngredientDetailByArticleId(String articleId);
	
	public boolean createNewRecipe(RecipeVO recipeVO);
	
	public RecipeVO getOneRecipe(String articleId);
	
}
