package com.cookccook.app.memberRecommend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.memberRecommend.dao.MemRecommendDAO;

@Service
public class MemRecommendServiceImpl implements MemRecommendService{
	
	@Autowired
	public MemRecommendDAO memRecommendDAO;

	@Override
	public List<String> getMemInterestIdList(String memberId) {
		return memRecommendDAO.getMemInterestIdList(memberId);
	}


}
