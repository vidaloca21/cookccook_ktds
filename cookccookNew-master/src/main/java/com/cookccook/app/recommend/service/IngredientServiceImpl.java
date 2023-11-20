/**
 * 작성자: 지훈
 * 작성일: 1012
 * 작성내용: service임쁠 생성
 */
package com.cookccook.app.recommend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.recommend.dao.IngredientDAO;
import com.cookccook.app.recommend.vo.ArtIngVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.util.IngredientParser;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Override
	public List<IngredientVO> getAllIngredientList() {
		List<IngredientVO> ingredientVOList = ingredientDAO.getAllIngredientList();
		return ingredientVOList;
	}
	
	@Override
	public List<String> getAllIngredientName() {
		return ingredientDAO.getAllIngredientName();
	}
	
	@Override
	public String getIngredientIdByName(String ingredientId) {
		return ingredientDAO.getIngredientIdByName(ingredientId);
	}

	@Override
	public boolean articeIngredientParser(ArtIngVO artIngVO) {
		int insertCount = ingredientDAO.articeIngredientParser(artIngVO);
		return insertCount >0;
	}
}
