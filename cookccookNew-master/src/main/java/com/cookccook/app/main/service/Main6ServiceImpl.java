package com.cookccook.app.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.main.dao.Main6DAO;

@Service
public class Main6ServiceImpl implements Main6Service {
	
	@Autowired
	public Main6DAO main6Dao;

	@Override
	public List<RecipeVO> getAnnivRecom() {
		List<String> annivRecomByArtId = main6Dao.getArtIdByAnniv();
		List<RecipeVO> annivRecomList = new ArrayList<>();

		for (String articleId : annivRecomByArtId) {
		    List<RecipeVO> recipeDataList = main6Dao.getArtInfoByArtId(articleId);

		    // 여기서 recipeDataList에는 RecipeVO 데이터의 리스트가 포함되어 있을 것입니다.

		    if (recipeDataList != null && !recipeDataList.isEmpty()) {
		        RecipeVO recipeVO = recipeDataList.get(0); // 첫 번째 RecipeVO 데이터 가져옴

		        // ArticleVO의 title 설정 (예를 들면, recipeDataList에서 가져온 데이터)
		        String title = recipeVO.getArticleVO().getTitle();
		        String attImgSmall = recipeVO.getAttImgSmall();
		        
		        // RecipeVO의 articleId 및 attImgSmall 설정
		        recipeVO.setArticleId(articleId);
		        recipeVO.setTitle(title);
		        recipeVO.setAttImgSmall(attImgSmall); // 이 값을 실제 데이터로 설정

		        annivRecomList.add(recipeVO);
		    }
		}
		
		return annivRecomList;
	}

	@Override
	public List<RecipeVO> getTagRecom(String articleId) {
		List<String> tagRecomByArtId = main6Dao.getRelatedArtId(articleId);
		List<RecipeVO> tagRecomList = new ArrayList<>();
		
		for (String recomArticleId : tagRecomByArtId) {
		    List<RecipeVO> recipeDataList = main6Dao.getArtInfoByArtId(recomArticleId);

		    if (recipeDataList != null && !recipeDataList.isEmpty()) {
		        RecipeVO recipeVO = recipeDataList.get(0); 
		        
		        String title = recipeVO.getArticleVO().getTitle();
		        String attImgSmall = recipeVO.getAttImgSmall();
		        
		        recipeVO.setArticleId(articleId);
		        recipeVO.setTitle(title);
		        recipeVO.setAttImgSmall(attImgSmall); 

		        tagRecomList.add(recipeVO);
		    }
		}
		return tagRecomList;
	}

	@Override
	public List<RecipeVO> getRecomByTemp(String temperature) {
		List<String> getArtIdByTemp  = main6Dao.getRecomByTemp(temperature);
		List<RecipeVO> tempRecomList = new ArrayList<>();
		
		for (String RecomArtIds : getArtIdByTemp) {
			List<RecipeVO> recipeDataList = main6Dao.getArtInfoByArtId(RecomArtIds);
			
			if (recipeDataList != null && !recipeDataList.isEmpty()) {
				RecipeVO recipeVO = recipeDataList.get(0);
				
				String title = recipeVO.getArticleVO().getTitle();
				String attImgSmall = recipeVO.getAttImgSmall();
				
				recipeVO.setArticleId(RecomArtIds);
				recipeVO.setTitle(title);
				recipeVO.setAttImgSmall(attImgSmall);
				
				tempRecomList.add(recipeVO);
			}
		}
		return tempRecomList;
	}
	
}
