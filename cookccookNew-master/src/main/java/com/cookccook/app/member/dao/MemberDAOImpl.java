package com.cookccook.app.member.dao;



import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.vo.SubPayVO;

@Repository
public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO {

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int getEmailCount(String email) {
        return getSqlSession().selectOne("getEmailCount", email);
    }

    @Override
    public int createNewMember(MemberVO memberVO) {
        return getSqlSession().insert("createNewMember", memberVO);
    }

    @Override
    public int deleteMe(String email) {
        return getSqlSession().delete("deleteMe", email);
    }

    @Override
    public int deleteMember(String email) {
    	return getSqlSession().update("deleteMember", email);
    }
    
    @Override
    public int createOrUpdate(MemberVO memberVO) {
        return getSqlSession().update("createOrUpdate", memberVO);

    }

    @Override
    public List<MemberVO> getAllMember() {
        return getSqlSession().selectList("getAllMember");
    }

    @Override
    public MemberVO getMemberByEmail(String email) {
        return getSqlSession().selectOne("getMemberByEmail", email);
    }

    @Override
    public int getMemberAllCount(){
        return getSqlSession().selectOne("getMemberAllCount");
    }
    
    @Override
    public int memberUpdateRoleandStatus(MemberVO memberVO){
    	return getSqlSession().update("memberUpdateRoleandStatus", memberVO);
    }
    
    @Override
    public int memberUpdateInfo(MemberVO memberVO) {
    	return getSqlSession().update("memberUpdateInfo", memberVO);
    }
    
    @Override
    public MemberVO getOneMember(String memberEmail) {
    	return getSqlSession().selectOne("getOneMember", memberEmail);
    }
    
    @Override
    public int updateLatestLoginSuccessDate(String email) {
        return getSqlSession().update("updateLatestLoginSuccessDate", email);
    }
    
    //추가
    @Override
    public List<ArticleVO> selectMemberArticles(String memberId) {
        return getSqlSession().selectList("selectMemberArticles", memberId);
    }
    
    //추가
    @Override
    public int updateIpAddress(MemberVO memberVO) {
    	
    	return getSqlSession().update("updateIpAddress", memberVO);
    }

    @Override
    public List<MemberVO> getInflReadyMember() {
    	return getSqlSession().selectList("getInflReadyMember");
    }

    @Override
    public List<MemberVO> getAllInfluencer() {
    	return getSqlSession().selectList("getAllInfluencer");
    }
    
    @Override
    public int updateInfluencer(MemberVO memberVO) {
    	return getSqlSession().update("updateInfluencer", memberVO);
    }
    
    @Override
    public int updateLoginCnt(String email) {
        return getSqlSession().update("updateLoginCnt", email);
    }
    
    @Override
    public int memberLoginCount(MemberVO memberVO){
    	return getSqlSession().update("memberLoginCount", memberVO);
    }
    
    @Override
    public List<MemberVO> getAllSellerMembers() {
        return getSqlSession().selectList("getAllSellerMembers");
    }
    
    @Override
    public List<ProductVO> getAllProductList(){
    	return getSqlSession().selectList("getAllProductList");
    }
    @Override
    public List<ProductVO> getProductAllListSort(String sortColumn){
    	return getSqlSession().selectList("getAllProductListSort", sortColumn);
    }
    @Override
    public List<SubPayVO> getSubPayMemberList() {
    	return getSqlSession().selectList("subMemberList");
    }
    
    @Override
    public int createNewSeller(MemberVO memberVO) {
    	return getSqlSession().insert("createNewSeller", memberVO);
    }
    
    @Override
    public int sellerUpdateInfo(MemberVO memberVO) {
    	return getSqlSession().update("sellerUpdateInfo", memberVO);
    }
    
    @Override
    public List<MemberVO> getAllPreSellers() {
    	return getSqlSession().selectList("getAllPreSellers");
    }
    
    @Override
    public int updatePresellerToSeller(MemberVO memberVO) {
    	return getSqlSession().update("updatePresellerToSeller", memberVO);
    }
    
    @Override
    public int deleteMemberByMemberId(MemberVO memberVO) {
    	return getSqlSession().delete("deleteMemberByMemberId", memberVO);
    }
}
