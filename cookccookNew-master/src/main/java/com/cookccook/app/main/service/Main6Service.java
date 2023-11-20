package com.cookccook.app.main.service;

import java.util.List;

import com.cookccook.app.article.vo.RecipeVO;

public interface Main6Service {
	
	public List<RecipeVO> getAnnivRecom();
	
	public List<RecipeVO> getTagRecom(String articleId);
	
	public List<RecipeVO> getRecomByTemp(String temperature) ;

}
