package com.cookccook.app.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.article.dao.InterestDAO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.recommend.dao.RecommDAO;

@Service
public class InterestServiceImpl implements InterestService {

   @Autowired
   private InterestDAO interestDAO;
   
   @Override
   public List<String> getInterestIdList(String memeberId) {
      return interestDAO.getInterestIdList(memeberId);
   }
   
   @Override
   public boolean addNewInterest(InterestVO interestVO) {
      int insertCount = interestDAO.addNewInterest(interestVO);
      return insertCount >0;
   }
   
   @Override
   public boolean deleteOneInterest(InterestVO interestVO) {
      int deleteCount = interestDAO.deleteOneInterest(interestVO);
      return deleteCount >0;
   }
   
   @Override
   public List<String> getAllInterestIdList() {
      return interestDAO.getAllInterestIdList();
   }
   
   @Override
   public List<String> getAllRecipeInterestList() {
      return interestDAO.getAllRecipeInterestList();
   }
}
