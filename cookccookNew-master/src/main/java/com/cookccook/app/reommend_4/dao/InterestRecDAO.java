package com.cookccook.app.reommend_4.dao;

import java.util.List;

import com.cookccook.app.reommend_4.vo.InterestRecVO;


public interface InterestRecDAO {
	
	public List<InterestRecVO> allInterestRecommend(String memberId);
	

}
