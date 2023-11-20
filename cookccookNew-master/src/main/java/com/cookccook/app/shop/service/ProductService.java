package com.cookccook.app.shop.service;

import java.util.List;

import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductListVO;
import com.cookccook.app.shop.vo.ProductVO;

public interface ProductService {

	// 상품 목록 및 개수 모두 조회
	public List<ProductVO> getAllProduct();
	
	// 상품명으로 상품 목록 조회
	public List<ProductVO> getProductListByPrdName(String prdName);
	
	public ProductVO getOneProduct(String productId);
	
	public String getOneProductByChoiceId(String choiceId);
}
