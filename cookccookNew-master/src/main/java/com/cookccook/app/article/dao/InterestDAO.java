package com.cookccook.app.article.dao;

import java.util.List;

import com.cookccook.app.article.vo.InterestVO;

public interface InterestDAO {

   public List<String> getInterestIdList(String memberId);
   
   public int addNewInterest(InterestVO interestVO);
   
   public int deleteOneInterest(InterestVO interestVO);

   public List<String> getAllInterestIdList();
   
   public List<String> getAllRecipeInterestList();
}
