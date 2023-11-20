package com.cookccook.app.main.dao;

import java.util.List;

import com.cookccook.app.article.vo.RecipeVO;

public interface Main6DAO {
	
	/**
	 * sysdate 기준 7일 이내 기념일 기준 관련 articleID 목록을 받아온다.
	 * @return articleId 리스트
	 */
	public List<String> getArtIdByAnniv () ;
	
	/**
	 * 받아온 string articleId 목록을 받아 레시피 게시글을 조회한다.
	 * @param articleId 받아온 string articleId 목록을받는다.
	 * @return 게시글 썸네일, 레시피 타이틀
	 */
	public List<RecipeVO> getArtInfoByArtId (String articleId);
	
	public List<String> getRelatedArtId (String articleId);
	
	public List<String> getRecomByTemp(String temperature);
}
