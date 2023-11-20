package com.cookccook.app.article.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.InterestVO;

@Repository
public class InterestDAOImpl extends SqlSessionDaoSupport implements InterestDAO{
   
   @Autowired
   @Override
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
      super.setSqlSessionTemplate(sqlSessionTemplate);
   }
   
   @Override
   public List<String> getInterestIdList(String memberId) {
      return getSqlSession().selectList("getInterestIdList", memberId);
   }
   
   @Override
   public int addNewInterest(InterestVO interestVO) {
      return getSqlSession().insert("addNewInterest", interestVO);
   }
   
   @Override
   public int deleteOneInterest(InterestVO interestVO) {
      return getSqlSession().delete("deleteOneInterest", interestVO);
   }
   
   @Override
   public List<String> getAllInterestIdList() {
      return getSqlSession().selectList("getAllInterestIdList");
   }
   
   @Override
   public List<String> getAllRecipeInterestList() {
      return getSqlSession().selectList("getAllRecipeInterestList");
   };
}
