/**
 * 작성자: 지훈
 * 작성일: 1012
 * 작성내용: service 생성
 */
package com.cookccook.app.recommend.service;

import java.util.List;

import com.cookccook.app.recommend.vo.ArtIngVO;
import com.cookccook.app.recommend.vo.IngredientVO;

public interface IngredientService {
	
	public List<IngredientVO> getAllIngredientList();
	
	public List<String> getAllIngredientName();
	public String getIngredientIdByName(String ingredientId);
	
	public boolean articeIngredientParser(ArtIngVO artIngVO);

}
