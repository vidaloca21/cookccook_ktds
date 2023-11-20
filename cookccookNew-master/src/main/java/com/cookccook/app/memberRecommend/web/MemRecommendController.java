package com.cookccook.app.memberRecommend.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cookccook.app.article.service.ArticleService;
import com.cookccook.app.article.service.InterestService;
import com.cookccook.app.article.service.RecipeService;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.member.service.MemberService;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.memberRecommend.service.MemRecommendService;
import com.cookccook.util.SessionUtil;

@Controller
public class MemRecommendController {
    
    @Autowired
    public MemRecommendService memRecommendService;
    
    @Autowired
    public InterestService interestService;
    
    @Autowired
    public MemberService memberService;
    
    @Autowired
    public ArticleService articleService;
    
    @Autowired
    public RecipeService recipeService;
    
    @ResponseBody
    @GetMapping("/api/sellerChart/memRecommend")
    public Map<String, Object> getMemInterestIdList(Authentication memberVO) { 
    	Map<String, Object> resultMap = new HashMap<>();
        MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		
        List<String> myLikedRecipes = interestService.getInterestIdList(memberId);
        Set<String> myLikedSet = new HashSet<>(myLikedRecipes);
        List<MemberVO> allMember = memberService.getAllMember().getMemberList();
        List<String> recipeIdList = interestService.getAllRecipeInterestList();
        
        double maxCommonSimilarity = 0.0;
        List<RecipeVO> articleList = new ArrayList<>();
        String bestMatchedUser = null;
        for (MemberVO targetMemberVO : allMember) {
            String targetMemberId = targetMemberVO.getMemberId();
            if (myLikedSet.size() > 0 && !targetMemberId.equals(memberId)) {
                List<String> interestMember = interestService.getInterestIdList(targetMemberId);
                Set<String> interestSet = new HashSet<>(interestMember);
                // 교집합 
                Set<String> commonInterests = new HashSet<>(myLikedSet);
                commonInterests.retainAll(interestSet);
                
                // 차집합 
                Set<String> difference = new HashSet<>(interestSet);
                difference.removeAll(myLikedSet);
                List<String> articleIdList = new ArrayList<>(difference);
                
                double commonSimilarity = commonInterests.size()*1.0 / myLikedSet.size() ;
                if (commonSimilarity > maxCommonSimilarity) {
                	maxCommonSimilarity = commonInterests.size()*1.0 / myLikedSet.size() ;
                	bestMatchedUser = targetMemberId;
                	for (String articleId: articleIdList) {
                		if(recipeIdList.contains(articleId) && articleIdList.size() >0) {
                			RecipeVO recipeVO = recipeService.getOneRecipe(articleId);
                			articleList.add(recipeVO);
                		}
                	}
                }
            }
        }
        resultMap.put("targetMemberId", bestMatchedUser);
        resultMap.put("commonSimilarity", maxCommonSimilarity);
        resultMap.put("difference", articleList);
        return resultMap;
    }
}
