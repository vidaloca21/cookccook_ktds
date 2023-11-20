package com.cookccook.app.subscribe.vo;

import java.util.List;

import com.cookccook.app.recommend.vo.IngredientVO;

public class IngredientListVO {
	
	private List<IngredientVO> ingredientList;
//TODO 나중에 배송 상품 들어오면 그거 여기 넣기

	public List<IngredientVO> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<IngredientVO> ingredientList) {
		this.ingredientList = ingredientList;
	}
}
