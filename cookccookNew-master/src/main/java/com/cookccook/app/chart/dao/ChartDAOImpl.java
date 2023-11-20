package com.cookccook.app.chart.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.chart.vo.ChartSearchVO;
import com.cookccook.app.chart.vo.ChartVO;

@Repository
public class ChartDAOImpl extends SqlSessionDaoSupport implements ChartDAO {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<ChartVO> allMonthlyProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allMonthlyProductRank", chartSearchVO);
	}
	

	@Override
	public List<ChartVO> allYearProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allYearProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allMonthlyProductSalesRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allMonthlyProductSalesRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allYearProductSalesRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allYearProductSalesRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allProductSalesRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allProductSalesRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allMonthlySellerRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allMonthlySellerRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allYearSellerRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allYearSellerRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> allSellerRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("allSellerRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> Top3ProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("Top3ProductRank", chartSearchVO);
	}
	
	@Override
	public List<ChartVO> DateByProduct(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("DateByProduct", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByAllMonthlyProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByAllMonthlyProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByAllYearProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByAllYearProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByAllMonthlyProductSalesRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByAllMonthlyProductSalesRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByAllYearProductSalesRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByAllYearProductSalesRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByAllProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByAllProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByAllProductSalesRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByAllProductSalesRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByMonthlyOneProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByMonthlyOneProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByYearOneProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByYearOneProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByOneProductRank(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByOneProductRank", chartSearchVO);
	}

	@Override
	public List<ChartVO> SellerByOneProductSales(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("SellerByOneProductSales", chartSearchVO);
	}

	@Override
	public List<ChartVO> TopRankOneProduct(ChartSearchVO chartSearchVO) {
		return getSqlSession().selectList("TopRankOneProduct",chartSearchVO );
	}



}
