package com.cookccook.app.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductVO;

@Repository
public class ProductDAOImpl extends SqlSessionDaoSupport implements ProductDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int getProductAllCount() {
		return getSqlSession().selectOne("getProductAllCount");
	}

	@Override
	public List<ProductVO> getAllProduct() {
		return getSqlSession().selectList("getAllProduct");
	}

	@Override
	public List<ProductVO> getProductListByPrdName(String prdName) {
		return getSqlSession().selectList("getProductListByPrdName", prdName);
	}

	@Override
	public ProductVO getOneProduct(String productId) {
		return getSqlSession().selectOne("getOneProduct", productId);
	}
	
	@Override
	public List<ChoiceVO> getProductChoice(String productId) {
		return getSqlSession().selectList("getProductChoice", productId);
	}
	
	@Override
	public String getOneProductByChoiceId(String choiceId) {
		return getSqlSession().selectOne("getOneProductByChoiceId", choiceId);
	}
}
