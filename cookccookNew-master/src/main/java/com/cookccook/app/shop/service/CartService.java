package com.cookccook.app.shop.service;

import java.util.List;

import com.cookccook.app.shop.vo.CartVO;

public interface CartService {

	public List<CartVO> getAllCartProduct(String memberId);
	
	public boolean addNewProductToCart(CartVO cartVO);
	
	public boolean deleteExistProductFromCart(CartVO cartVO);
	
	public boolean editProductCountFromCart(CartVO cartVO);
	
	public CartVO getOneCartByCartId(String cartId);
	
	public List<String> getCartChoiceList(String memberId);
	public boolean deleteCartList(List<CartVO> cartListVO);
	
}
