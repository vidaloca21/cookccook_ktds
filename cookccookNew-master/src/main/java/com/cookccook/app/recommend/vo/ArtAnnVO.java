package com.cookccook.app.recommend.vo;

import com.cookccook.app.article.vo.ArticleVO;

public class ArtAnnVO {
	
	private String artAnnId;
	private String articleId;
	private String anniversaryId;
	
	private ArticleVO articleVO;
	private AnniversaryVO anniversaryVO;
	
	public String getArtAnnId() {
		return artAnnId;
	}
	public void setArtAnnId(String artAnnId) {
		this.artAnnId = artAnnId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getAnniversaryId() {
		return anniversaryId;
	}
	public void setAnniversaryId(String anniversaryId) {
		this.anniversaryId = anniversaryId;
	}
	public ArticleVO getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(ArticleVO articleVO) {
		this.articleVO = articleVO;
	}
	public AnniversaryVO getAnniversaryVO() {
		return anniversaryVO;
	}
	public void setAnniversaryVO(AnniversaryVO anniversaryVO) {
		this.anniversaryVO = anniversaryVO;
	}
	
	
}
