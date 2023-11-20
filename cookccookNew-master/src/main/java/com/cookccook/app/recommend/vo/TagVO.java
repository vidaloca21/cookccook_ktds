package com.cookccook.app.recommend.vo;

import java.util.List;

public class TagVO {
	
	private String tagId;
	private String tagTitle;
	
	private List<ArtTagVO> artTagList;
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getTagTitle() {
		return tagTitle;
	}
	public void setTagTitle(String tagTitle) {
		this.tagTitle = tagTitle;
	}
	public List<ArtTagVO> getArtTagList() {
		return artTagList;
	}
	public void setArtTagList(List<ArtTagVO> artTagList) {
		this.artTagList = artTagList;
	}
	

}
