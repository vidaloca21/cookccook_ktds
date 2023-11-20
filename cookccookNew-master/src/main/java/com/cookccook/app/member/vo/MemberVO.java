package com.cookccook.app.member.vo;

import java.util.List;

import com.cookccook.app.member.vo.validategroup.MemberLoginGroup;
import com.cookccook.app.member.vo.validategroup.MemberRegistGroup;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.util.Mask;
import com.cookccook.util.MaskType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberVO {

	private String memberId;
	
//	@Mask(MaskType.EMAIL)
	@NotBlank(groups = {MemberRegistGroup.class, MemberLoginGroup.class}, message = "이메일을입력해주세요.")
	@Email(groups = MemberRegistGroup.class, message = "이메일형태로입력해주세요.")
	private String memEmail;
	

	@NotBlank(groups = {MemberRegistGroup.class, MemberLoginGroup.class}, message = "비밀번호를입력해주세요.")
	@Size(groups = MemberRegistGroup.class, min = 6, message = "비밀번호는 최소 6글자 이상입력해주세요.")
	private String memPassword;
	
	@NotBlank(groups = MemberRegistGroup.class, message = "비밀번호확인을입력해주세요.")
	private String confirmPassword;
	
	private String memNickname;
	
	@Mask(MaskType.PHONE)
	@Pattern(regexp = "^010[0-9]{8}$", message = "전화번호는 010 이후 8자리여야 합니다.")
	@NotEmpty(message = "전화번호를 입력하세요")
	private String memPhoneNumber;
	
	@Mask(MaskType.NAME)
	@NotBlank(groups = MemberRegistGroup.class, message = "이름을입력해주세요.")
	private String memName;
	
	@Mask(MaskType.ADDRESS)
	private String memAddress;
	
	private String memAgeGroup;
	private String memBirthyear;
	private String memBirthday;
	private String memClass;
	private String memSocialStatus;
	private int memGrade;
	private String memSex;
	private String memJoinDate;
	private String memUnregistDate;
	private String businessNumber;
	
	
	private int totalLike;
	private int totalArticle;
	
	//안 쓸 듯 
	private String memFilepath;
	//추가
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	private String originFileName;
	
	//
	private String provider;
	private String role;
	private String salt;
	
	private String blockYn;

	private int loginCnt;
	private String latestLoginSuccessDate;
	private String latestLoginFailDate;
	private String latestAccessIp;
	private String registDate;
	
	private String ipAddress;
	
	private String memAddressNo;
	
	private String memAddressDetail;
	
	private double sellerRating;
	
	
	
	
	

	
	public double getSellerRating() {
		return sellerRating;
	}
	public void setSellerRating(double sellerRating) {
		this.sellerRating = sellerRating;
	}
	public String getMemAddressDetail() {
		return memAddressDetail;
	}
	public void setMemAddressDetail(String memAddressDetail) {
		this.memAddressDetail = memAddressDetail;
	}
	public String getMemAddressNo() {
		return memAddressNo;
	}
	public void setMemAddressNo(String memAddressNo) {
		this.memAddressNo = memAddressNo;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	private DeliverPlaceVO deliverPlaceVO;
	private List<DeliverPlaceVO> deliverPlaceList;
	
	
	
	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getMemNickname() {
		return memNickname;
	}

	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}

	public String getMemPhoneNumber() {
		return memPhoneNumber;
	}

	public void setMemPhoneNumber(String memPhoneNumber) {
		this.memPhoneNumber = memPhoneNumber;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public String getMemAgeGroup() {
		return memAgeGroup;
	}

	public void setMemAgeGroup(String memAgeGroup) {
		this.memAgeGroup = memAgeGroup;
	}

	public String getMemBirthyear() {
		return memBirthyear;
	}

	public void setMemBirthyear(String memBirthyear) {
		this.memBirthyear = memBirthyear;
	}

	public String getMemBirthday() {
		return memBirthday;
	}

	public void setMemBirthday(String memBirthday) {
		this.memBirthday = memBirthday;
	}

	public String getMemClass() {
		return memClass;
	}

	public void setMemClass(String memClass) {
		this.memClass = memClass;
	}

	public String getMemSocialStatus() {
		return memSocialStatus;
	}

	public void setMemSocialStatus(String memSocialStatus) {
		this.memSocialStatus = memSocialStatus;
	}

	public int getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(int memGrade) {
		this.memGrade = memGrade;
	}

	public String getMemSex() {
		return memSex;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public String getMemJoinDate() {
		return memJoinDate;
	}

	public void setMemJoinDate(String memJoinDate) {
		this.memJoinDate = memJoinDate;
	}

	public String getMemUnregistDate() {
		return memUnregistDate;
	}

	public void setMemUnregistDate(String memUnregistDate) {
		this.memUnregistDate = memUnregistDate;
	}

	public String getMemFilepath() {
		return memFilepath;
	}

	public void setMemFilepath(String memFilepath) {
		this.memFilepath = memFilepath;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getBlockYn() {
		return blockYn;
	}

	public void setBlockYn(String blockYn) {
		this.blockYn = blockYn;
	}

	public int getLoginCnt() {
		return loginCnt;
	}

	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}

	public String getLatestLoginSuccessDate() {
		return latestLoginSuccessDate;
	}

	public void setLatestLoginSuccessDate(String latestLoginSuccessDate) {
		this.latestLoginSuccessDate = latestLoginSuccessDate;
	}

	public String getLatestLoginFailDate() {
		return latestLoginFailDate;
	}

	public void setLatestLoginFailDate(String latestLoginFailDate) {
		this.latestLoginFailDate = latestLoginFailDate;
	}

	public String getLatestAccessIp() {
		return latestAccessIp;
	}

	public void setLatestAccessIp(String latestAccessIp) {
		this.latestAccessIp = latestAccessIp;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	
	public DeliverPlaceVO getDeliverPlaceVO() {
		return deliverPlaceVO;
	}
	public void setDeliverPlaceVO(DeliverPlaceVO deliverPlaceVO) {
		this.deliverPlaceVO = deliverPlaceVO;
	}
	public List<DeliverPlaceVO> getDeliverPlaceList() {
		return deliverPlaceList;
	}
	public void setDeliverPlaceList(List<DeliverPlaceVO> deliverPlaceList) {
		this.deliverPlaceList = deliverPlaceList;
	}
	public int getTotalLike() {
		return totalLike;
	}
	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}
	public int getTotalArticle() {
		return totalArticle;
	}
	public void setTotalArticle(int totalArticle) {
		this.totalArticle = totalArticle;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	
}
