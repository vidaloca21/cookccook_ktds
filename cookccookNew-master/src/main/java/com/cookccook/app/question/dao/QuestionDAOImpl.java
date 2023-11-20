package com.cookccook.app.question.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.question.vo.QuestionVO;
import com.cookccook.app.shop.vo.ProductSearchVO;

import jakarta.validation.Valid;


@Repository
public class QuestionDAOImpl extends SqlSessionDaoSupport implements QuestionDAO {
	
	@Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    	super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
	@Override
	public int getQuestionAllCount() {
		return getSqlSession().selectOne("getQuestionAllCount");
	}
	
	@Override
	public List<QuestionVO> getAllQuestion() {
		return getSqlSession().selectList("getAllQuestion");
	}

	@Override
	public int createNewQuestion(QuestionVO questionVO) {
		return getSqlSession().insert("createNewQuestion", questionVO);
	}
	
	@Override
	public int increaseViewCount(String questionId) {
		return getSqlSession().update("increaseViewCount", questionId);
	}

	@Override
	public QuestionVO getOneQuestionVO(String questionid) {
		return getSqlSession().selectOne("getOneQuestionVO", questionid);
	}

    @Override
    public int updateQuestion(QuestionVO questionVO) {
        return getSqlSession().update("updateQuestion", questionVO);
    }
    
    @Override
    public List<QuestionVO> getQuestionByProductId(String productId) {
    	return getSqlSession().selectList("getQuestionByProductId", productId);
    }

	@Override
	public int deleteQuestion(String questionId) {
		return getSqlSession().delete("deleteQuestion", questionId);
	}

	
	@Override
	public List<QuestionVO> getSubQuestion() {
		return getSqlSession().selectList("getSubQuestion");
}

	@Override
	public List<QuestionVO> getQuestionByMemberId(String memberId) {
		return getSqlSession().selectList("getQuestionByMemberId", memberId);
	}

	@Override
	public int getAllCntBySeller(String memberId) {
		return getSqlSession().selectOne("getAllCntBySeller", memberId);
	}
	
	@Override
	public int getNotReQueCntBySeller(String memberId) {
		return getSqlSession().selectOne("getNotReQueCntBySeller", memberId);
	}

	@Override
	public int getReQueCntBySeller(String memberId) {
		return getSqlSession().selectOne("getReQueCntBySeller", memberId);
	}

	@Override
	public List<QuestionVO> getQuestionBySearch(@Valid ProductSearchVO productSearchVO) {
		return getSqlSession().selectList("getQuestionBySearch", productSearchVO);
	}
}
	
	