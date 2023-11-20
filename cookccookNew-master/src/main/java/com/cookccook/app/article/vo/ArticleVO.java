package com.cookccook.app.article.vo;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.ArtIngVO;
import com.cookccook.app.recommend.vo.ArtTagVO;
import com.cookccook.app.recommend.vo.CuisineVO;
import com.cookccook.util.XssIgnore;

public class ArticleVO {

	private String articleId;
	private int articleType;
	private String memberId;
	private String cuisineId;
	private String upperArticleId;
	private String title;
	@XssIgnore
	private String content;
	private int viewCnt;
	private int likeCnt;
	private int bookmarkCnt;
	private String eventStatus;
	private String saveStatus;
	private String postDate;
	private String editDate;
	private String hiddenDate;
	private String memEmail;
	private String articleIndex;
	private String articleContent;
	private MemberVO memberVO;
	private CuisineVO cuisineVO;
	private ArticleVO articleVO;
	private RecipeVO recipeVO;
	private ArtIngVO artIngVO;
	private ArtTagVO artTagVO;
	@XssIgnore
	private List<ArtIngVO> artIngVOList;
	@XssIgnore
	private List<ArtTagVO> artTagVOList;
	
	private List<InterestVO> interests;

	public List<InterestVO> getInterests() {
		return interests;
	}
	public void setInterests(List<InterestVO> interests) {
		this.interests = interests;
	}
	public String getArticleIndex() {
		return articleIndex;
	}
	public void setArticleIndex(String articleIndex) {
		this.articleIndex = articleIndex;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getArticleType() {
		return articleType;
	}
	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCuisineId() {
		return cuisineId;
	}
	public void setCuisineId(String cuisineId) {
		this.cuisineId = cuisineId;
	}
	public String getUpperArticleId() {
		return upperArticleId;
	}
	public void setUpperArticleId(String upperArticleId) {
		this.upperArticleId = upperArticleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getBookmarkCnt() {
		return bookmarkCnt;
	}
	public void setBookmarkCnt(int bookmarkCnt) {
		this.bookmarkCnt = bookmarkCnt;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getSaveStatus() {
		return saveStatus;
	}
	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getHiddenDate() {
		return hiddenDate;
	}
	public void setHiddenDate(String hiddenDate) {
		this.hiddenDate = hiddenDate;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public CuisineVO getCuisineVO() {
		return cuisineVO;
	}
	public void setCuisineVO(CuisineVO cuisineVO) {
		this.cuisineVO = cuisineVO;
	}
	public ArticleVO getArticleVO() {
		return articleVO;
	}
	public void setArticleVO(ArticleVO articleVO) {
		this.articleVO = articleVO;
	}
	public RecipeVO getRecipeVO() {
		return recipeVO;
	}
	public void setRecipeVO(RecipeVO recipeVO) {
		this.recipeVO = recipeVO;
	}
	public ArtIngVO getArtIngVO() {
		return artIngVO;
	}
	public void setArtIngVO(ArtIngVO artIngVO) {
		this.artIngVO = artIngVO;
	}
	public ArtTagVO getArtTagVO() {
		return artTagVO;
	}
	public void setArtTagVO(ArtTagVO artTagVO) {
		this.artTagVO = artTagVO;
	}
	public List<ArtIngVO> getArtIngVOList() {
		return artIngVOList;
	}
	public void setArtIngVOList(List<ArtIngVO> artIngVOList) {
		this.artIngVOList = artIngVOList;
	}
	public List<ArtTagVO> getArtTagVOList() {
		return artTagVOList;
	}
	public void setArtTagVOList(List<ArtTagVO> artTagVOList) {
		this.artTagVOList = artTagVOList;
	}
	

	
}
