package com.cookccook.app.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cookccook.app.article.dao.ArticleDAO;
import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.SearchArticleVO;
import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.pagination.vo.AbstractSearchVO;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDAO articleDAO;
	
	@Autowired
	private FileHandler fileHandler; 
	
	@Override
	public ArticleListVO getAllRecipeArticle(SearchArticleVO searchArticleVO) {
		ArticleListVO articleListVO = new ArticleListVO();
		articleListVO.setArticleCnt(articleDAO.getArticleAllCount(searchArticleVO));
		
		if (searchArticleVO == null) {
			articleListVO.setArticleList(articleDAO.getAllRecipeArticle());
		}
		else {
			articleListVO.setArticleList(articleDAO.searchAllRecipeArticle(searchArticleVO));
		}
		return articleListVO;
	}
	
	@Override
	public List<ArticleVO> getRecipeArticleByTitle(String title) {
		return articleDAO.getRecipeArticleByTitle(title);
	}
	
	@Override
	public ArticleVO getOneRecipeArticle(String articleId, boolean isIncrease) {
		if (isIncrease) {
			int updateCount = articleDAO.increaseViewCount(articleId);
			if (updateCount == 0) {
				throw new IllegalArgumentException("잘못된 접근입니다.");
			}
		}
		
		ArticleVO articleVO = articleDAO.getOneRecipeArticle(articleId);
		if (articleVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다");
		}
		return articleVO;
	}
	
	@Override
	public ArticleVO createNewRecipeArticle(ArticleVO articleVO) {
		ArticleVO article = articleDAO.createNewRecipeArticle(articleVO);
		return article;
	}
	
	@Transactional
	@Override
	public boolean updateOneRecipeArticle(ArticleVO articleVO) {
		ArticleVO article = articleDAO.createNewRecipeArticle(articleVO);
		return article ==null;
	}
	
	@Transactional
	@Override
	public boolean deleteOneRecipeArticle(String articleId) {
		int articleDeleteCount = articleDAO.deleteOneRecipeArticle(articleId);
		return articleDeleteCount > 0;
	}
	
	@Override
	public ArticleListVO getAllCommunityArticle(SearchArticleVO searchArticleVO) {
		ArticleListVO articleListVO = new ArticleListVO();
		articleListVO.setArticleCnt(articleDAO.getCommuArticleAllCount(searchArticleVO));
		articleListVO.setArticleList(articleDAO.getAllCommunityArticle(searchArticleVO));
//		if (searchArticleVO == null) {
//		}
//		else {
////			articleListVO.setArticleList(articleDAO.searchAllCommunityArticle(searchArticleVO));
//			articleListVO.setArticleList(articleDAO.getAllCommunityArticle(searchArticleVO));
//		}
		return articleListVO;
	}

	@Override
	public List<ArticleVO> getCommunityArticleByTitle(String title) {
		return articleDAO.getCommunityArticleByTitle(title);
	}

	@Override
	public ArticleVO getOneCommunityArticle(String articleId, boolean isIncrease) {
		
		if (isIncrease) {
			int updateCount = articleDAO.increaseViewCount(articleId);
			if (updateCount == 0) {
				throw new IllegalArgumentException("잘못된 접근입니다.");
			}
		}
		
		ArticleVO articleVO = articleDAO.getOneCommunityArticle(articleId);
		if (articleVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다");
		}
		return articleVO;
	}
	
	@Override
	public boolean createNewCommunityArticle(ArticleVO articleVO) {
		int articleInsertCount = articleDAO.createNewCommunityArticle(articleVO);
		return articleInsertCount > 0;
	}

	@Transactional
	@Override
	public boolean updateOneCommunityArticle(ArticleVO articleVO) {
		int articleInsertCount = articleDAO.updateOneCommunityArticle(articleVO);
		return articleInsertCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneCommunityArticle(String articleId) {
		int articleDeleteCount = articleDAO.deleteOneCommunityArticle(articleId);
		return articleDeleteCount > 0;
	}
	
	@Transactional
	@Override
	public boolean increaseLikeCount(InterestVO interestVO) {
		int increaseCount = articleDAO.increaseLikeCount(interestVO);
		return increaseCount >0;
	}

	@Transactional
	@Override
	public boolean decreaseLikeCount(String interestId) {
		int decreaseCount = articleDAO.decreaseLikeCount(interestId);
		return decreaseCount >0;
	}
	
	@Override
	public List<InterestVO> getAllInterestByArticleId(String articleId) {
		return articleDAO.getAllInterestByArticleId(articleId);
	}


}