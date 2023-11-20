/**
 * 작성자: 지훈
 * 작성일: 1012
 * 작성내용: dao 생성
 */
package com.cookccook.app.recommend.dao;

import java.util.List;

import com.cookccook.app.recommend.vo.ArtIngVO;
import com.cookccook.app.recommend.vo.IngredientVO;

public interface IngredientDAO {
	
	public List<IngredientVO> getAllIngredientList();
	public List<String> getAllIngredientName();
	public String getIngredientIdByName(String ingredientName);
	public int articeIngredientParser(ArtIngVO artIngVO);
}
