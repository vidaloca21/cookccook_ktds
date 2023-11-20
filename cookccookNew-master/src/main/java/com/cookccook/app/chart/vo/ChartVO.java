package com.cookccook.app.chart.vo;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.app.shop.vo.PurchaseVO;

public class ChartVO {

	private int rank;
	private String year;
	private String month;
	private int totalQuantity;
	private int totalPrice;
	private String productId;
	private String prdName;
	private String purchaseDate;

	private String memberId;
	private String memName;
	
	private String period;
	private String seperator;

	private List<MemberVO> memberVOList;
	private List<ProductVO> productVO;
	private List<ChoiceVO> choiceVO;
	private List<PurchaseVO> purchaseVO;
	private List<PurchaseProductVO> purchaseProductVO;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPrdName() {
		return prdName;
	}

	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public List<MemberVO> getMemberVOList() {
		return memberVOList;
	}

	public void setMemberVOList(List<MemberVO> memberVOList) {
		this.memberVOList = memberVOList;
	}

	public List<ProductVO> getProductVO() {
		return productVO;
	}

	public void setProductVO(List<ProductVO> productVO) {
		this.productVO = productVO;
	}

	public List<ChoiceVO> getChoiceVO() {
		return choiceVO;
	}

	public void setChoiceVO(List<ChoiceVO> choiceVO) {
		this.choiceVO = choiceVO;
	}

	public List<PurchaseVO> getPurchaseVO() {
		return purchaseVO;
	}

	public void setPurchaseVO(List<PurchaseVO> purchaseVO) {
		this.purchaseVO = purchaseVO;
	}

	public List<PurchaseProductVO> getPurchaseProductVO() {
		return purchaseProductVO;
	}

	public void setPurchaseProductVO(List<PurchaseProductVO> purchaseProductVO) {
		this.purchaseProductVO = purchaseProductVO;
	}
	
	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSeperator() {
		return seperator;
	}

	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}
	
}
