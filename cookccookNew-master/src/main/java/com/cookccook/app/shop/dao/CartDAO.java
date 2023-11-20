/**
 * 김형관
 * 20231010
 * 장바구니CRUD
 */
package com.cookccook.app.shop.dao;

import java.util.List;

import com.cookccook.app.shop.vo.CartVO;

public interface CartDAO {

	/**
	 * 장바구니에 담긴 상품 개수를 확인한다.
	 */
	public int getCartProductAllCount();
	
	/**
	 * 장바구니의 모든 상품을 조회한다.
	 */
	public List<CartVO> getAllCartProduct (String memberId);
	
	/**
	 * 장바구니에 새로운 상품을 추가한다
	 */
	public int addNewProductToCart (CartVO cartVO);
	
	/**
	 * 장바구니에서 기존의 상품을 제거한다
	 */
	public int deleteExistProductFromCart (CartVO cartVO);
	
	/**
	 * 장바구니 상품의 수량을 수정한다
	 */
	public int editProductCountFromCart (CartVO cartVO);
	
	public CartVO getOneCartByCartId(String cartId);
	
	public List<String> getCartChoiceList(String memberId);
	public int deleteOneCart(String cartId);
}
