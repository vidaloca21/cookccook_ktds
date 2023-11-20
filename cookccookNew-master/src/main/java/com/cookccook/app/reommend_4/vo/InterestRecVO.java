package com.cookccook.app.reommend_4.vo;

import java.util.List;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.CuisineVO;

public class InterestRecVO {
	
	private String recipeName;
    private String attImgBig;
	private List<MemberVO> memberVO;
	private List<InterestVO> interestVO;
	private List<ArticleVO> articleVO;
	private List<CuisineVO> cuisineVO;
	private List<RecipeVO> recipeVO;
	
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getAttImgBig() {
		return attImgBig;
	}
	public void setAttImgBig(String attImgBig) {
		this.attImgBig = attImgBig;
	}
	
	public List<MemberVO> getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(List<MemberVO> memberVO) {
		this.memberVO = memberVO;
	}
	public List<InterestVO> getInterestVO() {
		return interestVO;
	}
	public void setInterestVO(List<InterestVO> interestVO) {
		this.interestVO = interestVO;
	}
	public List<ArticleVO> getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(List<ArticleVO> articleVO) {
		this.articleVO = articleVO;
	}
	public List<CuisineVO> getCuisineVO() {
		return cuisineVO;
	}
	public void setCuisineVO(List<CuisineVO> cuisineVO) {
		this.cuisineVO = cuisineVO;
	}
	public List<RecipeVO> getRecipeVO() {
		return recipeVO;
	}
	public void setRecipeVO(List<RecipeVO> recipeVO) {
		this.recipeVO = recipeVO;
	}
	
	

}
