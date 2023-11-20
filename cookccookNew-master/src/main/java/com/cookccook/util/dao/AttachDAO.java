package com.cookccook.util.dao;

import java.util.List;

import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.util.vo.AttachVO;

public interface AttachDAO {

	public List<AttachVO> getAllAttachList();
	public int createNewAttach(AttachVO attachVO);
	public List<RecipeVO> getAllImageUrl();
	public List<RecipeVO> getAllPostImageUrl();
	public int addFilePathToRecipeVO(RecipeVO recipeVO);
	public String getOneAttachImg(String articleId);
}
