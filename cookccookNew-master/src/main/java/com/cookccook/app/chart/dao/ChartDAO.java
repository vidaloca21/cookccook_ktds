package com.cookccook.app.chart.dao;

import java.util.List;

import com.cookccook.app.chart.vo.ChartSearchVO;
import com.cookccook.app.chart.vo.ChartVO;

public interface ChartDAO {
	
	/**
	 * 전체 상품(월별) 상품 매출 순위.
	 * @param chartVO
	 */
	public List<ChartVO> allMonthlyProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 상품(연별) 상품 매출 순위.
	 * @param chartVO
	 */
	public List<ChartVO> allYearProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 상품(월별) 상품 판매량 순위
	 * @param chartVO
	 * @returnList<ChartVO>
	 */
	public List<ChartVO> allMonthlyProductSalesRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 상품(연별) 상품 판매량 순위
	 * @param chartVO
	 * @return
	 */
	public List<ChartVO> allYearProductSalesRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 상품 매출 순위
	 * @param chartVO
	 */
	public List<ChartVO> allProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 상품 판매량 순위
	 * @param chartVO
	 */
	public List<ChartVO> allProductSalesRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 판매자(월별) 매출 순위
	 */
	public List<ChartVO> allMonthlySellerRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 판매자(연별) 매출 순위
	 * @param chartVO
	 */
	public List<ChartVO> allYearSellerRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 판매자 매출 순위
	 * @param chartVO
	 */
	public List<ChartVO> allSellerRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 전체 상품 탑 3밑으로 다 기타로 나오게.
	 * @param chartVO
	 */
	public List<ChartVO> Top3ProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 일자별 전체 매출
	 * @param totalPrice
	 * @return
	 */
	public List<ChartVO> DateByProduct(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 전체 상품(월별) 매출 순위.
	 * @param memberId 
	 * @param chartVO
	 * @return
	 */
	public List<ChartVO> SellerByAllMonthlyProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 전체 상품(연별) 매출 순위.
	 * @param chartVO
	 */
	public List<ChartVO> SellerByAllYearProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 *  판매자의 전체 상품(월별) 판매량 순위
	 * @param chartVO
	 */
	public List<ChartVO> SellerByAllMonthlyProductSalesRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 전체 상품(연별) 판매량 순위
	 * @param chartVO
	 */
	public List<ChartVO> SellerByAllYearProductSalesRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 모든 상품 매출 순위
	 * @param chartVO
	 */
	public List<ChartVO> SellerByAllProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 전체 상품 판매량 순위
	 * @param chartVO
	 */
	public List<ChartVO> SellerByAllProductSalesRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 상품한개의 (월별) 매출
	 * @param chartVO
	 */
	public List<ChartVO> SellerByMonthlyOneProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 상품한개의 (연별) 매출
	 * @param chartVO
	 */
	public List<ChartVO> SellerByYearOneProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 상품한개의 전체 매출
	 * @param chartVO
	 */
	public List<ChartVO> SellerByOneProductRank(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자의 상품한개의 전체 판매량 
	 * @param chartVO
	 */
	public List<ChartVO> SellerByOneProductSales(ChartSearchVO chartSearchVO);
	
	/**
	 * 판매자 상품의 매출 랭크 탑 ? 밑으로는 다 기타로 나오게
	 * @param chartVO
	 */
	public List<ChartVO> TopRankOneProduct(ChartSearchVO chartSearchVO);
	
}
