package com.cookccook.app.reommend_4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.reommend_4.dao.InterestRecDAO;
import com.cookccook.app.reommend_4.vo.InterestRecVO;


@Service
public class InterestRecServiceImpl implements InterestRecService{
	
	@Autowired
	private InterestRecDAO interestRecDAO;

	@Override
	public List<InterestRecVO> allInterestRecommend(String memberId) {
		List<InterestRecVO> interestRecListVO = interestRecDAO.allInterestRecommend(memberId);
		return interestRecListVO;
	}

}
