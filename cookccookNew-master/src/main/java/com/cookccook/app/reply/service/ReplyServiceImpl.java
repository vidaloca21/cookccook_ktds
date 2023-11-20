package com.cookccook.app.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.reply.dao.ReplyDAO;
import com.cookccook.app.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public List<ReplyVO> getAllReplies(String articleId) {
		return replyDAO.getAllReplies(articleId);
	}

	@Override
	public ReplyVO getOneReply(String replyId) {
		return replyDAO.getOneReply(replyId);
	}

	@Override
	public boolean createNewReply(ReplyVO replyVO) {
		int insertCount = replyDAO.createNewReply(replyVO);
		return insertCount > 0;
	}

	@Override
	public boolean updateOnereply(ReplyVO replyVO) {
		ReplyVO originReplyVO = replyDAO.getOneReply(replyVO.getReplyId());
		if (!replyVO.getMemberId().equals(originReplyVO.getMemberId())) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return replyDAO.updateOneReply(replyVO) > 0;
	}

	@Override
	public boolean deleteOneReply(String replyId, String memberId) {
		ReplyVO replyVO = replyDAO.getOneReply(replyId);
		if (!memberId.equals(replyVO.getMemberId())) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return replyDAO.deleteOneReply(replyId) > 0;
	}

}
