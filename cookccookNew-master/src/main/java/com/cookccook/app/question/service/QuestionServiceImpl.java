package com.cookccook.app.question.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.question.dao.QuestionDAO;
import com.cookccook.app.question.vo.QuestionListVO;
import com.cookccook.app.question.vo.QuestionVO;
import com.cookccook.app.question.vo.ReQueStateVO;
import com.cookccook.app.shop.vo.ProductSearchVO;

import jakarta.validation.Valid;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
	public List<QuestionVO> getAllQuestion() {
    	List<QuestionVO> questionListVO = questionDAO.getAllQuestion();
		return questionListVO;
	}
    
    @Transactional
    @Override
	public boolean createNewQuestion(QuestionVO questionVO) {
		int createCount = questionDAO.createNewQuestion(questionVO);
		return createCount > 0;
	}
    
    @Override
    public QuestionVO getOneQuestionVO(String questionId, boolean isIncrease) {
    	if (isIncrease) {
    		int updateCount = questionDAO.increaseViewCount(questionId);
    		if (updateCount == 0) {
    			throw new IllegalArgumentException("잘못된 접근임!");
    		}
    	}
    	QuestionVO questionVO = questionDAO.getOneQuestionVO(questionId);
    	if(questionVO == null) {
    		throw new IllegalArgumentException("잘못된 접근임!!");
    	}
    	return questionVO;
    }
    
    @Transactional
    @Override
    public boolean updateQuestion(QuestionVO questionVO) {
    	int updateCount = questionDAO.updateQuestion(questionVO);
    	return updateCount > 0;
    }
    
    @Override
    public List<QuestionVO> getQuestionByProductId(String productId) {
    	List<QuestionVO> questionListVO = questionDAO.getQuestionByProductId(productId);
		return questionListVO;
    }
    
    @Transactional
	@Override
	public boolean deleteQuestion(String questionId) {
		int deleteCount = questionDAO.deleteQuestion(questionId);
		return deleteCount > 0;
	}


	@Override
	public List<QuestionVO> getSubQuestion() {
		return questionDAO.getSubQuestion();
	}

	@Override
	public List<QuestionVO> getQuestionByMemberId(String memberId) {
		return questionDAO.getQuestionByMemberId(memberId);
	}

	@Override
	public ReQueStateVO getReQueStateBySeller(String memberId) {
		ReQueStateVO queStateVO = new ReQueStateVO();
		queStateVO.setAllCnt(questionDAO.getAllCntBySeller(memberId));
		queStateVO.setNotReQueCnt(questionDAO.getNotReQueCntBySeller(memberId));
		queStateVO.setReQueCnt(questionDAO.getReQueCntBySeller(memberId));
		return queStateVO;
	}

	@Override
	public List<QuestionVO> getQuestionBySearch(@Valid ProductSearchVO productSearchVO) {
		return questionDAO.getQuestionBySearch(productSearchVO);
	}
	
    
    
}

