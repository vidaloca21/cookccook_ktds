package com.cookccook.app.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.WishlistVO;

@Repository
public class WishlistDAOImpl extends SqlSessionDaoSupport implements WishlistDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<String> getWishlistProductByMember(String memberId) {
		return getSqlSession().selectList("getWishlistProductByMember", memberId);
	}

	@Override
	public int addNewWishToWishList(WishlistVO wishlistVO) {
		return getSqlSession().insert("addNewWishToWishList", wishlistVO);
	}

	@Override
	public int deleteExistWishFromWishList(WishlistVO wishlistVO) {
		return getSqlSession().delete("deleteExistWishFromWishList" , wishlistVO);
	}
	
	@Override
	public WishlistVO getOneWishlistVOByProductId(WishlistVO wishlistVO) {
		return getSqlSession().selectOne("getOneWishlistVOByProductId", wishlistVO);
	}

}
