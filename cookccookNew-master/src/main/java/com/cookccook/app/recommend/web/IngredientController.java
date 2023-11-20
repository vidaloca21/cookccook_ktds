/**
 * 작성자: 지훈
 * 작성일: 1012
 * 작성내용: controller 생성
 */
package com.cookccook.app.recommend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cookccook.app.article.service.RecipeService;
import com.cookccook.app.recommend.service.IngredientService;
import com.cookccook.app.recommend.vo.ArtIngVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.util.IngredientKeywordMaker;
import com.cookccook.util.IngredientParser;

/**
 * 테스트용
 */
@RestController
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/ingredient/all")
	public Map<String, Object> getAllIngredientList() {
		Map<String, Object> resultMap = new HashMap<>();
		List<IngredientVO> ingredientVOList = ingredientService.getAllIngredientList();
		resultMap.put("result", ingredientVOList);
		return resultMap;
	}
	
	@GetMapping("/ingredient/parse")
	public Map<String, Object> ingParseMap() {
		Map<String, Object> resultMap = new HashMap<>();
		IngredientParser ingParser = new IngredientParser();
		resultMap = ingParser.ingredientMapper(ingredientService, recipeService);
		return resultMap;
	}
	
	/**
	 * ART_ING TABLE Insert를 위한 URL
	 * 실행하지 마세요!!!!!!!!!!!!!!!!!!!
	 */
	@GetMapping("/ingredient/parse/start")
	public String articeIngredientParser() {
		Map<String, Object> resultMap = new HashMap<>();
		IngredientParser ingParser = new IngredientParser();
		resultMap = ingParser.ingredientMapper(ingredientService, recipeService);
		resultMap.entrySet()
				 .forEach(entry -> {
					 ArtIngVO artIngVO = new ArtIngVO();
					 String key = entry.getKey();
					 artIngVO.setArticleId(key);
					 List<String> value = (List<String>) entry.getValue();
					 for(String ingredientId: value) {
						 artIngVO.setIngredientId(ingredientId);
						 ingredientService.articeIngredientParser(artIngVO);
					 }
				 });
		return "tools/success";
	}
	
	@GetMapping("/ingredient/keyword")
	public Map<String, String> getIngredientByKeyword() {
		IngredientKeywordMaker ikm = new IngredientKeywordMaker();
		Map<String, String> resultMap = new HashMap<>();
		String keyword = "돼지ㅣ고기";
		String result = ikm.keywordArrayMapper(ingredientService, keyword);
		resultMap.put(keyword, result);
		return resultMap;
	}
}
