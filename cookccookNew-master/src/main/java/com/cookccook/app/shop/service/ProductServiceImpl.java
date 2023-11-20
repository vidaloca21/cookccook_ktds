package com.cookccook.app.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.shop.dao.ProductDAO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductListVO;
import com.cookccook.app.shop.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public List<ProductVO>getAllProduct() {
		return productDAO.getAllProduct();
	}

	@Override
	public List<ProductVO> getProductListByPrdName(String prdName) {
		return productDAO.getProductListByPrdName(prdName);
	}
	
	@Override
	public ProductVO getOneProduct(String productId) {
		ProductVO productVO = productDAO.getOneProduct(productId);
		if (productVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다");
		}
		List<ChoiceVO> choiceList = productDAO.getProductChoice(productId);
		productVO.setChoiceList(choiceList);
		return productVO;
	}
	
	@Override
	public String getOneProductByChoiceId(String choiceId) {
		return productDAO.getOneProductByChoiceId(choiceId);
	}
	
}
