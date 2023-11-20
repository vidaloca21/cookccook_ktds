package com.cookccook.app.review.vo;

public class RatingStateVO {
	private double ratingAvg;
	private int allCnt;
	private int oneCnt;
	private int twoCnt;
	private int threeCnt;
	private int fourCnt;
	private int fiveCnt;
	
	public int getOneCnt() {
		return oneCnt;
	}
	public void setOneCnt(int oneCnt) {
		this.oneCnt = oneCnt;
	}
	public int getTwoCnt() {
		return twoCnt;
	}
	public void setTwoCnt(int twoCnt) {
		this.twoCnt = twoCnt;
	}
	public int getThreeCnt() {
		return threeCnt;
	}
	public void setThreeCnt(int threeCnt) {
		this.threeCnt = threeCnt;
	}
	public int getFourCnt() {
		return fourCnt;
	}
	public void setFourCnt(int fourCnt) {
		this.fourCnt = fourCnt;
	}
	public int getFiveCnt() {
		return fiveCnt;
	}
	public void setFiveCnt(int fiveCnt) {
		this.fiveCnt = fiveCnt;
	}
	public double getRatingAvg() {
		return ratingAvg;
	}
	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}
	public int getAllCnt() {
		return allCnt;
	}
	public void setAllCnt(int allCnt) {
		this.allCnt = allCnt;
	}
}
