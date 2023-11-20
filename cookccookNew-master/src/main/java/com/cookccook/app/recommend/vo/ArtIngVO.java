package com.cookccook.app.recommend.vo;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.shop.vo.ProductVO;

public class ArtIngVO {

	private String artIngId;
	private String articleId;
	private String ingredientId;
	
	private ArticleVO articleVO;
	private IngredientVO ingredientVO;
	
	private List<ArticleVO> articleList;
	private List<ProductVO> productList;
	
	public String getArtIngId() {
		return artIngId;
	}
	public void setArtIngId(String artIngId) {
		this.artIngId = artIngId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(String ingredientId) {
		this.ingredientId = ingredientId;
	}
	public ArticleVO getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(ArticleVO articleVO) {
		this.articleVO = articleVO;
	}
	public IngredientVO getIngredientVO() {
		return ingredientVO;
	}
	public void setIngredientVO(IngredientVO ingredientVO) {
		this.ingredientVO = ingredientVO;
	}
	public List<ArticleVO> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<ArticleVO> articleList) {
		this.articleList = articleList;
	}
	public List<ProductVO> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductVO> productList) {
		this.productList = productList;
	}
	
	
}
