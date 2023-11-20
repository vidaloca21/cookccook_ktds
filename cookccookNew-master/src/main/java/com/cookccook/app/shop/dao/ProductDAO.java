package com.cookccook.app.shop.dao;

import java.util.List;

import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductVO;

public interface ProductDAO {

	public int getProductAllCount();
	public List<ProductVO> getAllProduct();
	public List<ProductVO> getProductListByPrdName(String prdName);
	public ProductVO getOneProduct(String productId);
	public List<ChoiceVO> getProductChoice(String productId);	
	public String getOneProductByChoiceId(String choiceId);
}
