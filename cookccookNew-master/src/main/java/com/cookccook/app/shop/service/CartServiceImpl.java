package com.cookccook.app.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.shop.dao.CartDAO;
import com.cookccook.app.shop.vo.CartVO;
import com.cookccook.app.shop.vo.CartlistVO;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAO cartDAO;

	@Override
	public List<CartVO> getAllCartProduct(String memberId) {
		return cartDAO.getAllCartProduct(memberId);
	}

	@Override
	public boolean addNewProductToCart(CartVO cartVO) {
		int insertCount = cartDAO.addNewProductToCart(cartVO);
		return insertCount > 0;
	}

	@Override
	public boolean deleteExistProductFromCart(CartVO cartVO) {
		int deleteCount = cartDAO.deleteExistProductFromCart(cartVO);
		return deleteCount > 0;
	}

	@Override
	public boolean editProductCountFromCart(CartVO cartVO) {
		int updateCount = cartDAO.editProductCountFromCart(cartVO);
		return updateCount > 0;
	}
	
	@Override
	public CartVO getOneCartByCartId(String cartId) {
		return cartDAO.getOneCartByCartId(cartId);
	}

	@Override
	public List<String> getCartChoiceList(String memberId) {
		return cartDAO.getCartChoiceList(memberId);
	}
	
	@Transactional
	@Override
	public boolean deleteCartList(List<CartVO> cartListVO) {
		int deleteCount = 0;
		for (CartVO cartVO: cartListVO) {
			deleteCount += cartDAO.deleteExistProductFromCart(cartVO);
		}
		return deleteCount == cartListVO.size();
	}
}
