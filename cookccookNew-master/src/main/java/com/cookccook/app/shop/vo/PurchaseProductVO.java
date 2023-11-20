package com.cookccook.app.shop.vo;

import java.util.List;

import com.cookccook.app.subscribe.vo.PaymentsVO;

public class PurchaseProductVO {

	private String purchaseProductId;
	private String purchaseId;
	private String choiceId;
	private int quantity;
	private int purPrdPrice;
	private int deliverFee;
	private int purState;
	private String purCancelStatus;
	private int waybill;
	private String deliverDate;
	private String cartId;
	
	private String state;
	private String deliverState;
	private String prdHiddenDate;

	private PurchaseVO purchaseVO;
	private ChoiceVO choiceVO;
	private ProductVO productVO;
	private PaymentsVO paymentsVO;
	private CancelVO cancelVO;
	private List<PurchaseProductVO> purchaseProductList;
	
	
	public String getPurchaseProductId() {
		return purchaseProductId;
	}

	public void setPurchaseProductId(String purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}

	/*
	 * public List<PurchaseProductVO> getPurchaseProductList() { return
	 * purchaseProductList; } public void
	 * setPurchaseProductList(List<PurchaseProductVO> purchaseProductList) {
	 * this.purchaseProductList = purchaseProductList; }
	 */
	public String getPrdHiddenDate() {
		return prdHiddenDate;
	}

	public void setPrdHiddenDate(String prdHiddenDate) {
		this.prdHiddenDate = prdHiddenDate;
	}

	
	
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public ChoiceVO getChoiceVO() {
		return choiceVO;
	}

	public void setChoiceVO(ChoiceVO choiceVO) {
		this.choiceVO = choiceVO;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPurPrdPrice() {
		return purPrdPrice;
	}

	public void setPurPrdPrice(int purPrdPrice) {
		this.purPrdPrice = purPrdPrice;
	}

	public int getDeliverFee() {
		return deliverFee;
	}

	public void setDeliverFee(int deliverFee) {
		this.deliverFee = deliverFee;
	}

	public int getPurState() {
		return purState;
	}

	public void setPurState(int purState) {
		this.purState = purState;
	}

	public String getPurCancelStatus() {
		return purCancelStatus;
	}

	public void setPurCancelStatus(String purCancelStatus) {
		this.purCancelStatus = purCancelStatus;
	}

	public int getWaybill() {
		return waybill;
	}

	public void setWaybill(int waybill) {
		this.waybill = waybill;
	}

	public String getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDeliverState() {
		return deliverState;
	}

	public void setDeliverState(String deliverState) {
		this.deliverState = deliverState;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

	public PurchaseVO getPurchaseVO() {
		return purchaseVO;
	}

	public void setPurchaseVO(PurchaseVO purchaseVO) {
		this.purchaseVO = purchaseVO;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}

	public PaymentsVO getPaymentsVO() {
		return paymentsVO;
	}

	public void setPaymentsVO(PaymentsVO paymentsVO) {
		this.paymentsVO = paymentsVO;
	}

	public List<PurchaseProductVO> getPurchaseProductList() {
		return purchaseProductList;
	}

	public void setPurchaseProductList(List<PurchaseProductVO> purchaseProductList) {
		this.purchaseProductList = purchaseProductList;
	}

	public CancelVO getCancelVO() {
		return cancelVO;
	}

	public void setCancelVO(CancelVO cancelVO) {
		this.cancelVO = cancelVO;
	}

}
