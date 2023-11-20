package com.cookccook.app.article.service;

import java.util.List;

import com.cookccook.app.article.vo.InterestVO;

public interface InterestService {

   public List<String> getInterestIdList(String memeberId);
   
   public boolean addNewInterest(InterestVO interestVO);
   public boolean deleteOneInterest(InterestVO interestVO);
   public List<String> getAllInterestIdList();
   
   public List<String> getAllRecipeInterestList();
}
