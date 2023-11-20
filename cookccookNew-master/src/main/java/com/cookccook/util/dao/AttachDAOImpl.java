package com.cookccook.util.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.util.vo.AttachVO;

@Repository
public class AttachDAOImpl extends SqlSessionDaoSupport implements AttachDAO {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<AttachVO> getAllAttachList() {
		return getSqlSession().selectList("getAllAttachList");
	}

	@Override
	public int createNewAttach(AttachVO attachVO) {
		int insertCount = getSqlSession().insert("createNewAttach" , attachVO);
		return insertCount;
	}
	
	@Override
	public List<RecipeVO> getAllImageUrl() {
		return getSqlSession().selectList("getAllImageUrl");
	}
	
	@Override
	public int addFilePathToRecipeVO(RecipeVO recipeVO) {
		return getSqlSession().update("addFilePathToRecipeVO", recipeVO);
	}
	
	@Override
	public String getOneAttachImg(String articleId) {
		return getSqlSession().selectOne("getOneAttachImg", articleId);
	}
	
	@Override
	public List<RecipeVO> getAllPostImageUrl() {
		return getSqlSession().selectList("getAllPostImageUrl");
	}

}
