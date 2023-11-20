package com.cookccook.app.reommend_4.service;

import java.util.List;

import com.cookccook.app.reommend_4.vo.InterestRecVO;


public interface InterestRecService {
	
	public List<InterestRecVO> allInterestRecommend(String memberId);

}
