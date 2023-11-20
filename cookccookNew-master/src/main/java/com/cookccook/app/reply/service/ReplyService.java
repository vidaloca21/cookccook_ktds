package com.cookccook.app.reply.service;

import java.util.List;

import com.cookccook.app.reply.vo.ReplyVO;

public interface ReplyService {

	public List<ReplyVO> getAllReplies(String articleId);
	
	public ReplyVO getOneReply(String replyId);
	
	public boolean createNewReply (ReplyVO replyVO);
	
	public boolean updateOnereply (ReplyVO replyVO);
	
	public boolean deleteOneReply (String replyId, String memberId);
}
