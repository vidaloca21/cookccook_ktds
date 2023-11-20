package com.cookccook.app.recommend.vo;

import java.util.List;

public class AnniversaryVO {

	private String anniversaryId;
	private String anniversaryName;
	private String anniversaryDate;
	
	private List<ArtAnnVO> artAnnVOList;
	
	public String getAnniversaryId() {
		return anniversaryId;
	}
	public void setAnniversaryId(String anniversaryId) {
		this.anniversaryId = anniversaryId;
	}
	public String getAnniversaryName() {
		return anniversaryName;
	}
	public void setAnniversaryName(String anniversaryName) {
		this.anniversaryName = anniversaryName;
	}
	public String getAnniversaryDate() {
		return anniversaryDate;
	}
	public void setAnniversaryDate(String anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}
	public List<ArtAnnVO> getArtAnnVOList() {
		return artAnnVOList;
	}
	public void setArtAnnVOList(List<ArtAnnVO> artAnnVOList) {
		this.artAnnVOList = artAnnVOList;
	}
	
	
	
}
