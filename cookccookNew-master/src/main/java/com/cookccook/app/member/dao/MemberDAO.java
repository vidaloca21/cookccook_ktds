package com.cookccook.app.member.dao;



import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.vo.SubPayVO;

public interface MemberDAO {
    public int getEmailCount(String email);

    public int createOrUpdate(MemberVO memberVO);

    public int createNewMember(MemberVO memberVO);

    //진짜 쿼리에서 지우는 Delete
    public int deleteMe(String email);
    
    public int deleteMember(String email);

    public MemberVO getMemberByEmail(String email);

    public List<MemberVO> getAllMember();

    public int getMemberAllCount();
    
    public MemberVO getOneMember(String memberEmail);
    
    public int memberUpdateRoleandStatus(MemberVO memberVO);
    
    public int memberUpdateInfo(MemberVO memberVO);
    
    public int updateLatestLoginSuccessDate(String email);
    
    public List<ArticleVO> selectMemberArticles(String memberId);
    
    public int updateIpAddress(MemberVO memberVO);
   
    public List<MemberVO> getInflReadyMember();
    public List<MemberVO> getAllInfluencer();
    
    public int updateInfluencer(MemberVO memberVO);
    
    public int updateLoginCnt(String email);
    
    public int memberLoginCount(MemberVO memberVO);
    
    public List<MemberVO> getAllSellerMembers();
    
    public List<ProductVO> getAllProductList();
    public List<ProductVO> getProductAllListSort(String sortColumn);
    public List<SubPayVO> getSubPayMemberList();
    
    public int createNewSeller(MemberVO memberVO);
    public int sellerUpdateInfo(MemberVO memberVO);
    public List<MemberVO> getAllPreSellers();
    public int updatePresellerToSeller(MemberVO memberVO);
    public int deleteMemberByMemberId(MemberVO memberVO);
}
