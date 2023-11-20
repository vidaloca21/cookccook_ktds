package com.cookccook.app.chart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.chart.dao.ChartDAO;
import com.cookccook.app.chart.vo.ChartSearchVO;
import com.cookccook.app.chart.vo.ChartVO;

@Service
public class ChartServiceImpl implements ChartSerivce{
	
	@Autowired
	private ChartDAO chartDAO;

	@Override
	public List<ChartVO> allMonthlyProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allMonthlyProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allYearProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allYearProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allMonthlyProductSalesRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allMonthlyProductSalesRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allYearProductSalesRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allYearProductSalesRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allProductSalesRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allProductSalesRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allMonthlySellerRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allMonthlySellerRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allYearSellerRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allYearSellerRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> allSellerRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.allSellerRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> Top3ProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.Top3ProductRank(chartSearchVO);
		return chartListVO;
	}
	
	@Override
	public List<ChartVO> DateByProduct(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.DateByProduct(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByAllMonthlyProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByAllMonthlyProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByAllYearProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByAllYearProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByAllMonthlyProductSalesRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByAllMonthlyProductSalesRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByAllYearProductSalesRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByAllYearProductSalesRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByAllProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByAllProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByAllProductSalesRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByAllProductSalesRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByMonthlyOneProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByMonthlyOneProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByYearOneProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByYearOneProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByOneProductRank(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByOneProductRank(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> SellerByOneProductSales(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.SellerByOneProductSales(chartSearchVO);
		return chartListVO;
	}

	@Override
	public List<ChartVO> TopRankOneProduct(ChartSearchVO chartSearchVO) {
		List<ChartVO> chartListVO = chartDAO.TopRankOneProduct(chartSearchVO);
		return chartListVO;
	}
	

}
