package com.cookccook.app.recommend.vo;

import com.cookccook.app.article.vo.ArticleVO;

public class ArtTagVO {

	private String artTagId;
	private String articleId;
	private String tagId;
	private String tempRange;
	
	private ArticleVO articleVO;
	private TagVO tagVO;
	
	
	public String getArtTagId() {
		return artTagId;
	}
	public void setArtTagId(String artTagId) {
		this.artTagId = artTagId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public ArticleVO getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(ArticleVO articleVO) {
		this.articleVO = articleVO;
	}
	public TagVO getTagVO() {
		return tagVO;
	}
	public void setTagVO(TagVO tagVO) {
		this.tagVO = tagVO;
	}
	public String getTempRange() {
		return tempRange;
	}
	public void setTempRange(String tempRange) {
		this.tempRange = tempRange;
	}
	
	
}