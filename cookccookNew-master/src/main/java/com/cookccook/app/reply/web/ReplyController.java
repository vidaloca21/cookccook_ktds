package com.cookccook.app.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.reply.service.ReplyService;
import com.cookccook.app.reply.vo.ReplyVO;
import com.cookccook.util.SessionUtil;

@RestController
public class ReplyController {

	@Autowired
	public ReplyService replyService;
	
	@GetMapping("/reply/{articleId}")
	public Map<String, Object> getAllReplies(@PathVariable String articleId
											, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<ReplyVO> replyList = replyService.getAllReplies(articleId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reply", replyList);
		resultMap.put("memberId", member.getMemberId());
		resultMap.put("memNickname", member.getMemNickname());
		return resultMap;
	}
	
	@PostMapping("/reply/{articleId}")
	public Map<String, Object> doCreateNewReply(@PathVariable String articleId,
												@ModelAttribute ReplyVO replyVO,
												Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		replyVO.setArticleId(articleId);
		replyVO.setMemberId(member.getMemberId());
		boolean isSuccess = replyService.createNewReply(replyVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@PostMapping("/reply/replymodify/{replyId}")
	public Map<String, Object> doUpdateReply (@PathVariable String replyId,
											  @ModelAttribute ReplyVO replyVO,
											  Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		replyVO.setArticleId(replyId);
		replyVO.setMemberId(member.getMemberId());
		boolean isSuccess = replyService.updateOnereply(replyVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@GetMapping("/reply/replydelete/{replyId}")
	public Map<String, Object> doDeleteReply(@PathVariable String replyId,
											 Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		boolean isSuccess = replyService.deleteOneReply(replyId, member.getMemberId());
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		return resultMap;
	}
}
