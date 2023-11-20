package com.cookccook.app.article.vo;

import java.util.List;

import com.cookccook.app.pagination.vo.AbstractSearchVO;
import com.cookccook.util.XssIgnore;

public class SearchArticleVO extends AbstractSearchVO{
	
	/**
	 * 제목검색 혹은 내용검색을 하기위한 구분자.
	 * 제목검색: subject
	 * 내용검색: content
	 * 재료검색: ingredient
	 * 자연어검색: nlpSearch
	 */
	@XssIgnore
	private String searchType;
	
	/**
	 * 검색어
	 */
	@XssIgnore
	private String searchKeyword;
	private List<String> keywordList;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public List<String> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<String> keywordList) {
		this.keywordList = keywordList;
	}
	

}
