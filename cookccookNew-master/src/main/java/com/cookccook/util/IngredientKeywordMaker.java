package com.cookccook.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cookccook.app.recommend.service.IngredientService;

@Component
public class IngredientKeywordMaker {
	
	
	public String keywordArrayMapper(IngredientService ingredientService, String keyword) {
		List<Integer> keywordParse = new ArrayList<>(); 
		for (int i=0; i<keyword.length(); i++) {
			int uniBase = keyword.charAt(i) - 44032;
			if ( uniBase >= 0) {
				char chosung = (char)(uniBase / 28 / 21);
				char jungsung = (char)(uniBase / 28 % 21);
				char jongsung = (char)(uniBase % 28);
				if (chosung < 19) {
					keywordParse.add((int)chosung);
				}
				if (jungsung < 21) {
					keywordParse.add((int)jungsung);
				}
				if (jongsung < 28) {
					keywordParse.add((int)jongsung);
				}
			}
		}
		List<String> ingredientNameList = ingredientService.getAllIngredientName();
		for(String ingredientName: ingredientNameList) {
			List<Integer> ingredientNameParse = new ArrayList<>(); 
			for (int i=0; i<ingredientName.length(); i++) {
				int uniBase = ingredientName.charAt(i) - 44032;
				char chosung = (char)(uniBase / 28 / 21);
				char jungsung = (char)(uniBase / 28 % 21);
				char jongsung = (char)(uniBase % 28);
				ingredientNameParse.add((int)chosung);
				ingredientNameParse.add((int)jungsung);
				ingredientNameParse.add((int)jongsung);
			}
			int distance = 0;
			if (ingredientNameParse.size() == keywordParse.size()) {
				for(int i=0; i<keywordParse.size(); i++ ) {
					if (keywordParse.get(i) != ingredientNameParse.get(i)) {
						distance += 1;
					}
				}
				if (distance < 2) {
					return ingredientName;
				}
			}
		}
		return keyword;
	}

}
