package com.cookccook.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cookccook.app.article.dao.RecipeDAO;
import com.cookccook.app.article.service.RecipeService;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.recommend.dao.IngredientDAO;
import com.cookccook.app.recommend.service.IngredientService;
import com.cookccook.app.recommend.vo.IngredientVO;

@Component
public class IngredientParser {

	public Map<String, Object> ingredientMapper(IngredientService ingredientService, RecipeService recipeService) {
		
		//결과를 반환할 Map
		Map<String, Object> resultMap = new HashMap<>();
		
		//추출하고 싶은 List<String> 필요
		List<String> inputString = new ArrayList<>();
		
		//레시피에서 List<String>을 가져와서 inputString에 할당
		List<RecipeVO> recipeList = recipeService.getAllIngredientDetails();
		for (RecipeVO recipe: recipeList) {
			inputString.add(recipe.getIngredientDetails());
		}
		
		//재료 테이블의 데이터 불러오기
		List<IngredientVO> ingredientList = ingredientService.getAllIngredientList();
		List<String> ingredientName = new ArrayList<>();
		for (IngredientVO ingredientVO: ingredientList) {
			ingredientName.add(ingredientVO.getIngredientName());
		}
		
		//파싱
		for(int i = 0; i < inputString.size(); i++) {
			List<String> compareList = new ArrayList<String>();
			StringTokenizer str = new StringTokenizer(inputString.get(i), ", ●:()");
			while(str.hasMoreTokens()) {
				compareList.add(str.nextToken());
			}
			Set<String> ingredientSet = new HashSet<String>();
			for (String ingName: ingredientName) {
				for (String compName: compareList) {
					boolean isEquals = false;
					if(compName.equals(ingName)) {
						isEquals = true;
					}
					if(isEquals == true) {
						ingredientSet.add(ingredientService.getIngredientIdByName(ingName));
					}
				}
			}
			List<String> valueList = new ArrayList<>(ingredientSet);
			resultMap.put(recipeList.get(i).getArticleId(), valueList);
		}
		return resultMap;
	}
}
