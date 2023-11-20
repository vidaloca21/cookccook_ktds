package com.cookccook.app.reply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.reply.vo.ReplyVO;

@Repository
public class ReplyDAOImpl extends SqlSessionDaoSupport implements ReplyDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<ReplyVO> getAllReplies(String ariticleId) {
		return getSqlSession().selectList("getAllReplies", ariticleId);
	}

	@Override
	public ReplyVO getOneReply(String replyId) {
		return getSqlSession().selectOne("getOneReply", replyId);
	}

	@Override
	public int createNewReply(ReplyVO replyVO) {
		return getSqlSession().insert("createNewReply", replyVO);
	}

	@Override
	public int updateOneReply(ReplyVO replyVO) {
		return getSqlSession().update("updateOneReply", replyVO);
	}

	@Override
	public int deleteOneReply(String replyId) {
		return getSqlSession().update("deleteOneReply", replyId);
	}

}
