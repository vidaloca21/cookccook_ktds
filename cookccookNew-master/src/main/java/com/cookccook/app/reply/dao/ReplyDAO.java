/**
 * 20231019
 * 김형관
 * 댓글 구현
 */
package com.cookccook.app.reply.dao;

import java.util.List;

import com.cookccook.app.reply.vo.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> getAllReplies(String ariticleId);
	
	public ReplyVO getOneReply(String replyId);
	
	public int createNewReply(ReplyVO replyVO);
	
	public int updateOneReply(ReplyVO replyVO);
	
	public int deleteOneReply(String replyId);
	
}
