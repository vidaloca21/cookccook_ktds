package com.cookccook.app.member.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.member.vo.MemberListVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.member.vo.MemberVOList;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.vo.SubPayVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {
	public boolean checkAvailableEmail(String email);

    public boolean createNewMember(MemberVO memberVO);

    public boolean deleteMe(String email);

    public MemberListVO getAllMember();
    
    public boolean adminUpdateMember(MemberVO memberVO);
    
    public boolean memberUpdateInfo(MemberVO memberVO, MultipartFile file);
    
    public MemberVO getOneMember(String memberEmail);
    
    public MemberVO getOneAdminMember(String memEmail);
    
    public MemberVO getOneMemberEdit(String memberEmail);
    
    public boolean deleteMember(String email);
 
    public List<ArticleVO> getMemberArticles(String memberId);
    
    public void updateIpAddress(String memberEmail, String ipAddress);
    
    public boolean verifyPassword(String memberEmail, String currentPassword);
    
    public boolean updatePassword(String memberEmail, String newPassword, MemberVO memberVO);
    
    public List<MemberVO> getInflReadyMember();
    public List<MemberVO> getAllInfluencer();
    
    public boolean updateInfluencer(MemberVO memberVO);
    
    public void updateLoginCnt(String email);
    
    public boolean adminMemberLoginCount(MemberVO memberVO);
    
    public List<MemberVO> getAllSellerMembers();
    
    public List<ProductVO> getAllProductList();
    public List<ProductVO> getProductAllListSort(String sortColumn);
    public List<SubPayVO> getSubPayMemberList();
    public boolean createNewSeller(MemberVO memberVO);
    public boolean sellerUpdateInfo(MemberVO memberVO);
    public List<MemberVO> getAllPreSellers();
    public boolean updatePresellerToSeller(List<MemberVO> memberVOList);
    public boolean denySeller(List<MemberVO> memberVOList);
}
