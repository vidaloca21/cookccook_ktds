package com.cookccook.app.member.vo;

public class MemoVO {
	
	private String memoId;
	private String fridgeId;
	private String memoContent;
	private String memoPostDate;

	private FridgeVO fridgeVO;

	
	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}

	public String getFridgeId() {
		return fridgeId;
	}

	public void setFridgeId(String fridgeId) {
		this.fridgeId = fridgeId;
	}

	public String getMemoContent() {
		return memoContent;
	}

	public void setMemoContent(String memoContent) {
		this.memoContent = memoContent;
	}

	public String getMemoPostDate() {
		return memoPostDate;
	}

	public void setMemoPostDate(String memoPostDate) {
		this.memoPostDate = memoPostDate;
	}

	public FridgeVO getFridgeVO() {
		return fridgeVO;
	}

	public void setFridgeVO(FridgeVO fridgeVO) {
		this.fridgeVO = fridgeVO;
	}
	
}