package com.cookccook.app.subscribe.vo;

import java.util.List;

public class SubscribeVO {

	private String subscribeId;
	private String subChoice;
	private int subPrice;
	private String subStartDate;
	private String subEndDate;
	
	private List<SubIngVO> subIngList;
	private List<SubPayVO> subPayList;
	
	public String getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}
	public String getSubChoice() {
		return subChoice;
	}
	public void setSubChoice(String subChoice) {
		this.subChoice = subChoice;
	}
	public int getSubPrice() {
		return subPrice;
	}
	public void setSubPrice(int subPrice) {
		this.subPrice = subPrice;
	}
	public String getSubStartDate() {
		return subStartDate;
	}
	public void setSubStartDate(String subStartDate) {
		this.subStartDate = subStartDate;
	}
	public String getSubEndDate() {
		return subEndDate;
	}
	public void setSubEndDate(String subEndDate) {
		this.subEndDate = subEndDate;
	}
	public List<SubIngVO> getSubIngList() {
		return subIngList;
	}
	public void setSubIngList(List<SubIngVO> subIngList) {
		this.subIngList = subIngList;
	}
	public List<SubPayVO> getSubPayList() {
		return subPayList;
	}
	public void setSubPayList(List<SubPayVO> subPayList) {
		this.subPayList = subPayList;
	}
	
	
	
}
