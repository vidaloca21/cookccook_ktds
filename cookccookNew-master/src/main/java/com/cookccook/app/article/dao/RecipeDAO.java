/**
 * 김형관
 * 20231013
 * 레시피 CRUD
 */
package com.cookccook.app.article.dao;

import java.util.List;

import com.cookccook.app.article.vo.RecipeVO;

public interface RecipeDAO {

	public List<RecipeVO> getAllIngredientDetails();
	
	public String getIngredientDetailByArticleId(String articleId);
	
	public int createNewRecipe(RecipeVO recipeVO);
	
	public RecipeVO getOneRecipe(String articleId);
	
	public List<String> getAllRecipeImg();
}
