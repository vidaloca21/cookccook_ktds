package com.cookccook.app.subscribe.vo;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;

public class SubPayVO {

	private String subPayId;
	private String memberId;
	private String subscribeId;
	private String payMethod;
	private String payDate;
	private String cancelDate;
	private String deliverPlaceId;
	private String impUid;
	
	private MemberVO memberVO;
	private SubscribeVO subscribeVO;
	private DeliverPlaceVO deliverPlaceVO;
	private PaymentsVO paymentsVO;
	
	/*
	 * 이전 배송 내역을 리스트로 조회하기 위함
	 */
	private List<SubPayVO> subPayList;

	
	private String subsCategory;
	private String subsCycle;
	private String nextDeliveryDate;
	private String deliveredDate;

	
	public String getSubPayId() {
		return subPayId;
	}
	public void setSubPayId(String subPayId) {
		this.subPayId = subPayId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getDeliverPlaceId() {
		return deliverPlaceId;
	}
	public void setDeliverPlaceId(String deliverPlaceId) {
		this.deliverPlaceId = deliverPlaceId;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public SubscribeVO getSubscribeVO() {
		return subscribeVO;
	}
	public void setSubscribeVO(SubscribeVO subscribeVO) {
		this.subscribeVO = subscribeVO;
	}
	public DeliverPlaceVO getDeliverPlaceVO() {
		return deliverPlaceVO;
	}
	public void setDeliverPlaceVO(DeliverPlaceVO deliverPlaceVO) {
		this.deliverPlaceVO = deliverPlaceVO;
	}
	
	public List<SubPayVO> getSubPayList() {
		return subPayList;
	}
	public void setSubPayList(List<SubPayVO> subPayList) {
		this.subPayList = subPayList;
	}
	public String getSubsCategory() {
		return subsCategory;
	}
	public void setSubsCategory(String subsCategory) {
		this.subsCategory = subsCategory;
	}
	public String getSubsCycle() {
		return subsCycle;
	}
	public void setSubsCycle(String subsCycle) {
		this.subsCycle = subsCycle;
	}
	
	public String getNextDeliveryDate() {
		return nextDeliveryDate;
	}
	public void setNextDeliveryDate(String nextDeliveryDate) {
		this.nextDeliveryDate = nextDeliveryDate;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public PaymentsVO getPaymentsVO() {
		return paymentsVO;
	}
	public void setPaymentsVO(PaymentsVO paymentsVO) {
		this.paymentsVO = paymentsVO;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	
	
	
}
