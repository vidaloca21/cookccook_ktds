package com.cookccook.app.member.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.beans.FileHandler.StoredFile;
import com.cookccook.app.member.dao.MemberDAO;
import com.cookccook.app.member.vo.MemberListVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.member.vo.MemberVOList;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.exceptions.AlreadyUseException;
import com.cookccook.security.SHA;
import com.cookccook.security.SecuritySHA;
import com.cookccook.util.MaskUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private FileHandler fileHandler;

	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public boolean checkAvailableEmail(String email) {
		int emailCount = memberDAO.getEmailCount(email);
		return emailCount == 0;
	}

	@Override
	public MemberListVO getAllMember() {
		logger.debug(memberDAO.toString());
		logger.debug(memberDAO.getClass().getSimpleName());

		MemberListVO memberListVO = new MemberListVO();
		memberListVO.setMemberCnt(memberDAO.getMemberAllCount());
		memberListVO.setMemberList(memberDAO.getAllMember());
		MaskUtil.masking(memberListVO);
		return memberListVO;
	}

	@Transactional
	@Override
	public MemberVO getOneMember(String memberEmail) {

		MemberVO memberVO = memberDAO.getOneMember(memberEmail);
		MaskUtil.masking(memberVO);
		return memberVO;
	}
	
	@Transactional
	@Override
	public MemberVO getOneMemberEdit(String memberEmail) {
		MemberVO memberVO = memberDAO.getOneMember(memberEmail);
		return memberVO;
	}

	@Transactional
	@Override
	public boolean createNewMember(MemberVO memberVO) {
		int emailCount = memberDAO.getEmailCount(memberVO.getMemEmail());
		if (emailCount > 0) {
			throw new AlreadyUseException(memberVO, "Email이 이미 사용중입니다.");
		}

		SecuritySHA securitySHA = new SecuritySHA();
		String salt = securitySHA.generateSalt();
		String rawPassword = memberVO.getMemPassword();
		String encodedPassword = securitySHA.getEncrypt(rawPassword, salt);
		memberVO.setSalt(salt);
		memberVO.setMemPassword(encodedPassword);

		int insertCount = memberDAO.createOrUpdate(memberVO);
		return insertCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteMe(String email) {
		int deleteCount = memberDAO.deleteMe(email);
		return deleteCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteMember(String email) {
		int deleteMemberCount = memberDAO.deleteMember(email);
		return deleteMemberCount > 0;
	}
	
	
	

	@Transactional
	@Override
	public boolean adminUpdateMember(MemberVO memberVO) {

		int changeCount = memberDAO.memberUpdateRoleandStatus(memberVO);
		return changeCount > 0;
	}

	@Transactional
	@Override
	public boolean memberUpdateInfo(MemberVO memberVO, MultipartFile file) {

		if (file != null && !file.isEmpty()) {
			MemberVO originMemberVO = memberDAO.getOneMember(memberVO.getMemEmail());
			if (originMemberVO != null && originMemberVO.getFileName() != null) {
				File originFile = fileHandler.getStoredFile(originMemberVO.getFileName());
				if (originFile.exists() && originFile.isFile()) {
					originFile.delete();
				}
			}

			StoredFile storedFile = fileHandler.storeFile(file);

			memberVO.setFileName(storedFile.getRealFileName());
			memberVO.setOriginFileName(storedFile.getFileName());
		}
		
		if (memberVO.getMemPassword() != null && memberVO.getMemPassword().length() > 0) {
			SecuritySHA securitySHA = new SecuritySHA();
			String salt = securitySHA.generateSalt();
			String encodedPassword = securitySHA.getEncrypt(memberVO.getMemPassword(), salt);
			memberVO.setSalt(salt);
			memberVO.setMemPassword(encodedPassword);
		}

		// 주소 정보가 비어 있거나 null인 경우 기존 회원의 정보에서 가져오도록 설정
		MemberVO existingMember = memberDAO.getOneMember(memberVO.getMemEmail());
		if (memberVO.getMemAddress() == null || memberVO.getMemAddress().isEmpty()) {
			memberVO.setMemAddress(existingMember.getMemAddress());
		}
		if (memberVO.getMemAddressNo() == null || memberVO.getMemAddressNo().isEmpty()) {
			memberVO.setMemAddressNo(existingMember.getMemAddressNo());
		}
		if (memberVO.getMemAddressDetail() == null || memberVO.getMemAddressDetail().isEmpty()) {
			memberVO.setMemAddressDetail(existingMember.getMemAddressDetail());
		}
		int changeMemberCount = memberDAO.memberUpdateInfo(memberVO);
		return changeMemberCount > 0;
	}

	@Transactional
	@Override
	public MemberVO getOneAdminMember(String memEmail) {
		MemberVO memberVO = memberDAO.getOneMember(memEmail);
		return memberVO;
	}

	@Override
	public List<ArticleVO> getMemberArticles(String memberId) {
		return memberDAO.selectMemberArticles(memberId);
	}

	@Override
	public void updateIpAddress(String memberEmail, String ipAddress) {
		MemberVO memberVO = memberDAO.getOneMember(memberEmail);
		if (memberVO != null) {
			memberVO.setIpAddress(ipAddress);
			memberDAO.updateIpAddress(memberVO);
		}
	}

	// TODO 아직 미완인 상태
	@Override
	public boolean verifyPassword(String memberEmail, String currentPassword) {
		MemberVO existingMember = memberDAO.getMemberByEmail(memberEmail);
		String storedPassword = existingMember.getMemPassword();

		SecuritySHA securitySHA = new SecuritySHA();
		securitySHA.setSalt(existingMember.getSalt());

		// 비밀번호 확인
		boolean isPasswordValid = securitySHA.matches(currentPassword, storedPassword);
		return isPasswordValid;

	}

	@Override
	public boolean updatePassword(String memberEmail, String newPassword, MemberVO memberVO) {

		int changePasswordCnt = memberDAO.memberUpdateInfo(memberVO);

		return changePasswordCnt > 0;

	}
	
	
	@Override
	public List<MemberVO> getInflReadyMember() {
		return memberDAO.getInflReadyMember();
	}
	@Override
	public List<MemberVO> getAllInfluencer() {
		return memberDAO.getAllInfluencer();
	}
	
	@Transactional
	@Override
	public boolean updateInfluencer(MemberVO memberVO) {
		int updateCount = memberDAO.updateInfluencer(memberVO);
		return updateCount > 0;
	}

	@Override
	public void updateLoginCnt(String email) {
	    memberDAO.updateLoginCnt(email);
	} 
	
	@Transactional
	@Override
	public boolean adminMemberLoginCount(MemberVO memberVO) {

		int changeCount = memberDAO.memberLoginCount(memberVO);
		return changeCount > 0;
	}
	
//	@Override
//	public List<MemberVO> getAllSellerMembers() {
//	    return memberDAO.getAllSellerMembers();
//	}
	
	//마스킹 적용 
	@Override
	public List<MemberVO> getAllSellerMembers() {
	    List<MemberVO> members = memberDAO.getAllSellerMembers();
	    for (MemberVO member : members) {
	        MaskUtil.masking(member);
	    }
	    
	    return members;
	}
	
	@Override
	public List<ProductVO> getAllProductList(){
		return memberDAO.getAllProductList();
	}
	@Override
	public List<ProductVO> getProductAllListSort(String sortColumn){
		return memberDAO.getProductAllListSort(sortColumn);
	}
	@Override
	public List<SubPayVO> getSubPayMemberList() {
		return memberDAO.getSubPayMemberList();
	}
	
	@Transactional
	@Override
	public boolean sellerUpdateInfo(MemberVO memberVO) {
		return memberDAO.sellerUpdateInfo(memberVO) >0;
	}
	
	@Transactional
	@Override
	public boolean createNewSeller(MemberVO memberVO) {
		int emailCount = memberDAO.getEmailCount(memberVO.getMemEmail());
		if (emailCount > 0) {
			throw new AlreadyUseException(memberVO, "Email이 이미 사용중입니다.");
		}

		SecuritySHA securitySHA = new SecuritySHA();
		String salt = securitySHA.generateSalt();
		String rawPassword = memberVO.getMemPassword();
		String encodedPassword = securitySHA.getEncrypt(rawPassword, salt);
		memberVO.setSalt(salt);
		memberVO.setMemPassword(encodedPassword);

		int insertCount = memberDAO.createNewSeller(memberVO);
		return insertCount >0;
	}
	
	@Override
	public List<MemberVO> getAllPreSellers() {
		return memberDAO.getAllPreSellers();
	}
	
	@Transactional
	@Override
	public boolean updatePresellerToSeller(List<MemberVO> memberVOList) {
		int updateCount = 0;
		for (MemberVO memberVO: memberVOList) {
			updateCount += memberDAO.updatePresellerToSeller(memberVO);
		}
		return updateCount == memberVOList.size();
	}
	
	@Transactional
	@Override
	public boolean denySeller(List<MemberVO> memberVOList) {
		int deleteCount = 0;
		for (MemberVO memberVO: memberVOList) {
			deleteCount = memberDAO.deleteMemberByMemberId(memberVO);
		}
		return deleteCount == memberVOList.size();
	}
}
