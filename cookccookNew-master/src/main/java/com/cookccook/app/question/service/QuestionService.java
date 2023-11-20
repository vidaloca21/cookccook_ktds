package com.cookccook.app.question.service;


import java.util.List;

import com.cookccook.app.question.vo.QuestionVO;
import com.cookccook.app.question.vo.ReQueStateVO;
import com.cookccook.app.shop.vo.ProductSearchVO;

import jakarta.validation.Valid;

public interface QuestionService {
	
	public List<QuestionVO> getAllQuestion();
	
    public boolean createNewQuestion(QuestionVO questionVO); 
    
    public QuestionVO getOneQuestionVO(String questionId, boolean isIncrease);
    
    public boolean updateQuestion(QuestionVO questionVO);
    
    public List<QuestionVO> getQuestionByProductId(String productId);
    
    public boolean deleteQuestion(String questionId);
    
    public List<QuestionVO> getSubQuestion();

	public List<QuestionVO> getQuestionByMemberId(String memberId);

	public ReQueStateVO getReQueStateBySeller(String memberId);

	public List<QuestionVO> getQuestionBySearch(@Valid ProductSearchVO productSearchVO);

}

