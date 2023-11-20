package com.cookccook.app.article.vo;

import java.util.List;


public class ArticleListVO {
	
	private int articleCnt;
	
    private List<ArticleVO> articleList;
    
    
	public int getArticleCnt() {
		return articleCnt;
	}
	public void setArticleCnt(int articleCnt) {
		this.articleCnt = articleCnt;
	}
	public List<ArticleVO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleVO> articleList) {
		this.articleList = articleList;
	}
    
}
