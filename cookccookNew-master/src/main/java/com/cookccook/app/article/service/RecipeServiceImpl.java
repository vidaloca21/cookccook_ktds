package com.cookccook.app.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.article.dao.RecipeDAO;
import com.cookccook.app.article.vo.RecipeVO;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDAO recipeDAO;
	
	@Override
	public List<RecipeVO> getAllIngredientDetails() {
		return recipeDAO.getAllIngredientDetails();
	}
	
	@Override
	public String getIngredientDetailByArticleId(String articleId) {
		return recipeDAO.getIngredientDetailByArticleId(articleId);
	}
	
	@Override
	public boolean createNewRecipe(RecipeVO recipeVO) {
		int isSuccess = recipeDAO.createNewRecipe(recipeVO);
		return isSuccess >0;
	}

	@Override
	public RecipeVO getOneRecipe(String articleId) {
		return recipeDAO.getOneRecipe(articleId);
	}
}
