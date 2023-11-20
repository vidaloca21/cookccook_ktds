package com.cookccook.app.question.dao;

import java.util.List;

import com.cookccook.app.question.vo.QuestionVO;
import com.cookccook.app.shop.vo.ProductSearchVO;

import jakarta.validation.Valid;


public interface QuestionDAO {
	
	public List<QuestionVO> getQuestionByProductId(String productId);
	
	public int getQuestionAllCount();
	
    public List<QuestionVO> getAllQuestion();
    
    public int createNewQuestion(QuestionVO questionVO);
    
    public int increaseViewCount(String questionId);
    
    public QuestionVO getOneQuestionVO(String questionId);
    
    public int updateQuestion(QuestionVO questionVO);
    
    public int deleteQuestion(String questionId);
    
    public List<QuestionVO> getSubQuestion();

	public List<QuestionVO> getQuestionByMemberId(String memberId);

	public int getAllCntBySeller(String memberId);
	
	public int getNotReQueCntBySeller(String memberId);

	public int getReQueCntBySeller(String memberId);

	public List<QuestionVO> getQuestionBySearch(@Valid ProductSearchVO productSearchVO);
}