package com.cookccook.app.article.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookccook.app.article.service.InterestService;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.service.RecommService;
import com.cookccook.app.recommend.vo.ScoreVO;
import com.cookccook.util.SessionUtil;

@RestController
public class InterestController {

	@Autowired
	private InterestService interestService;
	@Autowired
	private RecommService recommService;
	
	@GetMapping("/api/interest")
	public Map<String, Object> getInterestIdList(Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<String> interestList = interestService.getAllInterestIdList();
		resultMap.put("interestList", interestList);
		if (member == null) {
			resultMap.put("interestMember", null);
			resultMap.put("memberId", "");
		}
		else {
			String memberId = member.getMemberId();
			List<String> interestMember = interestService.getInterestIdList(memberId);
			resultMap.put("interestMember", interestMember);
			resultMap.put("memberId", memberId);
		}
		return resultMap;
	}
	
	@PostMapping("/api/interest/add")
	public Map<String, Object> editInterest(@RequestBody String articleId
										   , Authentication memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		InterestVO interestVO = new InterestVO();
		interestVO.setArticleId(articleId);
		interestVO.setMemberId(memberId);
		ScoreVO scoreVO = new ScoreVO();
		scoreVO.setMemberId(memberId);
		scoreVO.setArticleId(articleId);
		List<String> interestList = interestService.getInterestIdList(memberId);
		if (interestList.contains(articleId)) {
			scoreVO.setCode(3);
			scoreVO.setScore(-2);
			boolean isAdded = recommService.addScore(scoreVO);
			boolean isSuccess = interestService.deleteOneInterest(interestVO);
			resultMap.put("action", "delete");
			resultMap.put("result", isSuccess);
			resultMap.put("score", isAdded);
		}
		else {
			scoreVO.setCode(2);
			scoreVO.setScore(2);
			boolean isAdded = recommService.addScore(scoreVO);
			boolean isSuccess = interestService.addNewInterest(interestVO);
			resultMap.put("action", "add");
			resultMap.put("result", isSuccess);
			resultMap.put("score", isAdded);
		}
		return resultMap;
	}
}
