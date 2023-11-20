package com.cookccook.app.recommend.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.service.ArticleService;
import com.cookccook.app.article.service.InterestService;
import com.cookccook.app.article.service.RecipeService;
import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.article.vo.SearchArticleVO;
import com.cookccook.app.main.service.Main6Service;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.service.IngredientService;
import com.cookccook.app.recommend.service.RecommService;
import com.cookccook.app.recommend.vo.DailyRankVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.service.MySubsService;
import com.cookccook.util.IngredientKeywordMaker;
import com.cookccook.util.NLPSearchUtil;
import com.cookccook.util.SessionUtil;
import com.cookccook.util.XssIgnoreUtil;

@Controller
public class RecommController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private RecommService recommService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private Main6Service artAnnTagService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private MySubsService mySubsService;
	
	
	 @GetMapping("/main")
	 public ModelAndView viewIntroPages(Authentication memberVO) {
	    ModelAndView modelAndView = new ModelAndView();
	    MemberVO member = SessionUtil.getLoginMember(memberVO);
	    modelAndView.setViewName("article/recomm");
	    modelAndView.addObject("member", member);
	    return modelAndView;
	 }
	
	@PostMapping("/recipe/search")
	public ModelAndView viewRecommRecipes(@ModelAttribute SearchArticleVO searchArticleVO
										, Authentication memberVO) {
		XssIgnoreUtil.ignore(searchArticleVO);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		if (searchArticleVO.getSearchType().equals("ingredient")) {
			ArticleListVO articleListVO = recommService.searchRecommArticleByIngredient(searchArticleVO);
			searchArticleVO.setPageCount(articleListVO.getArticleCnt());
			modelAndView.addObject("articleList", articleListVO);
		}
		else if (searchArticleVO.getSearchType().equals("nlpSearch")) {
			ArticleListVO articleListVO = recommService.searchRecommArticleBySentence(searchArticleVO);
			searchArticleVO.setPageCount(articleListVO.getArticleCnt());
			modelAndView.addObject("articleList", articleListVO);
			modelAndView.addObject("articleList", articleListVO);
		}else {
			searchArticleVO.setSearchKeyword("삼겹살");
			searchArticleVO.setSearchType("ingredient");
			ArticleListVO articleListVO = recommService.searchRecommArticleByIngredient(searchArticleVO);
			modelAndView.addObject("articleList", articleListVO);
		}
		modelAndView.addObject("member", member);
		modelAndView.addObject("searchArticleVO", searchArticleVO);
		modelAndView.setViewName("article/recipelist");
		return modelAndView;
	}
		
	
	@ResponseBody
	@GetMapping("/api/related/{articleId}")
	public Map<String, Object> getRelatedArtId(@PathVariable String articleId) {
		Map<String, Object> resultMap = new HashMap<>();
		List<RecipeVO> relatedArticle = artAnnTagService.getTagRecom(articleId);
		resultMap.put("result", relatedArticle);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("/api/recomm/ingredient")
	public Map<String, Object> getArticleByIngName() {
		Map<String, Object> resultMap = new HashMap<>();
		List<String> ingName = ingredientService.getAllIngredientName();
		Random random = new Random();
		boolean isContinue = true;
//		while (isContinue) {
//			int idx = random.nextInt(ingName.size());
//			String keyword = ingName.get(idx);
//			if (keyword.length() > 3 && keyword.length() < 12) {
//				List<ArticleVO>	articleList = recommService.getArticleByIngName(keyword);
//				if(articleList.size() < 5) {
//					continue;
//				}
//				else {
//					resultMap.put("ingName", keyword);
//					resultMap.put("articleList", articleList);
//					isContinue = false;
//					break;
//				}
//			}
//			else {
//				continue;
//			}
//		}
		List<String> keywordList = new ArrayList<>();
		keywordList.add("호두");
		keywordList.add("호박");
		keywordList.add("토마토");
		keywordList.add("치즈");
		keywordList.add("닭가슴살");
		keywordList.add("삼겹살");
		int idx = random.nextInt(keywordList.size());
		String keyword = keywordList.get(idx);
		List<ArticleVO> tempList = recommService.getArticleByIngName(keyword);
		List<ArticleVO>	articleList = tempList.subList(0, 5);
		resultMap.put("ingName", keyword);
		resultMap.put("articleList", articleList);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("/api/recomm/tagname")
	public Map<String, Object> getArticleByTagName() {
		Map<String, Object> resultMap = new HashMap<>();
		List<String> tagName = recommService.getAllTagTitle();
		Random random = new Random();
		boolean isContinue = true;
		while (isContinue) {
			int idx = random.nextInt(tagName.size());
			String keyword = tagName.get(idx);
			List<ArticleVO>	articleList = recommService.getArticleByTagName(keyword);
			if(articleList.size() < 5) {
				continue;
			}
			else {
				resultMap.put("tagName", keyword);
				resultMap.put("articleList", articleList);
				isContinue = false;
				break;
			}
		}
		return resultMap;
	}
	
	
	@ResponseBody
	@GetMapping("/api/relatedRecipe/{productId}")
	public Map<String, Object> recommendRecipeOnProduct(@PathVariable String productId) {
		Map<String, Object> resultMap = new HashMap<>();
		List<RecipeVO> recipeList = recommService.recommendRecipeOnProduct(productId);
		resultMap.put("result", recipeList);
		return resultMap;
	}

	@ResponseBody
	@GetMapping("/api/relatedProduct/{articleId}")
	public Map<String, Object> recommendProductOnRecipe(@PathVariable String articleId) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ProductVO> productList = recommService.recommendProductOnRecipe(articleId);
		resultMap.put("result", productList);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("/api/dailyrank")
	public Map<String, Object> getDailyRank() {
		Map<String, Object> resultMap = new HashMap<>();
		List<DailyRankVO> dailyRank = recommService.getDailyRank();
		resultMap.put("result", dailyRank);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("/api/mysubsrecomm")
	public Map<String, Object> getMySubsRecomm(Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<ArticleVO> recipeList = mySubsService.getMySubRecom(member.getMemberId());
		resultMap.put("recipeList", recipeList);
		return resultMap;
	}
	
	
}
