package com.cookccook.app.question.vo;

import java.util.List;

public class QuestionListVO {
	
	private int questionCnt;
	private List<QuestionVO> questionList;
	
	
	public int getQuestionCnt() {
		return questionCnt;
	}
	public void setQuestionCnt(int questionCnt) {
		this.questionCnt = questionCnt;
	}
	public List<QuestionVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionVO> questionList) {
		this.questionList = questionList;
	}
	
	

}
