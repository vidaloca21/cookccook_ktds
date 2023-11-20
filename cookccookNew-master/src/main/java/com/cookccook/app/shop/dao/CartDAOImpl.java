/**
 * 김형관
 * 20231006
 * 장바구니CRUD
 */
package com.cookccook.app.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.CartVO;

@Repository
public class CartDAOImpl extends SqlSessionDaoSupport implements CartDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getCartProductAllCount() {
		return getSqlSession().selectOne("getCartProductAllCount");
	}
	
	@Override
	public List<CartVO> getAllCartProduct(String memberId) {
		return getSqlSession().selectList("getAllCartProduct", memberId);
	}

	@Override
	public int addNewProductToCart(CartVO cartVO) {
		return getSqlSession().insert("addNewProductToCart", cartVO);
	}

	@Override
	public int deleteExistProductFromCart(CartVO cartVO) {
		return getSqlSession().update("deleteExistProductFromCart", cartVO);
	}

	@Override
	public int editProductCountFromCart(CartVO cartVO) {
		return getSqlSession().update("editProductCountFromCart", cartVO);
	}

	@Override
	public CartVO getOneCartByCartId(String cartId) {
		return getSqlSession().selectOne("getOneCartByCartId", cartId);
	}
	
	@Override
	public List<String> getCartChoiceList(String memberId) {
		return getSqlSession().selectList("getCartChoiceList", memberId);
	}
	
	@Override
	public int deleteOneCart(String cartId) {
		return getSqlSession().update("deleteOneCart", cartId);
	}
}
