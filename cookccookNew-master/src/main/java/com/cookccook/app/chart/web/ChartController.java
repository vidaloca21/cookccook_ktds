package com.cookccook.app.chart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.chart.service.ChartSerivce;
import com.cookccook.app.chart.vo.ChartSearchVO;
import com.cookccook.app.chart.vo.ChartVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.seller.service.SellerService;
import com.cookccook.app.shop.service.ProductService;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.util.SessionUtil;

import retrofit2.http.POST;

@RestController
public class ChartController {
	
	@Autowired
	private ChartSerivce chartSerivce;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/chart/chart1")
	public ModelAndView ViewAllMonthlyProductRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		// System.out.println(chartSearchVO.getPeriod() + chartSearchVO.getSeperator() + "=============================");
		List<ChartVO> chartList = chartSerivce.allMonthlyProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart1");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
		
	}
	
	@GetMapping("/chart/chart2")
	public ModelAndView ViewALlYearProductRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allYearProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart2");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	@GetMapping("/chart/chart3")
	public ModelAndView ViewALlMonthlyProductSalesRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allMonthlyProductSalesRank(chartSearchVO);
		System.out.println("========================================================="+chartSearchVO.getStartDate());
		System.out.println("========================================================="+chartSearchVO.getEndDate());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart3");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart4")
	public ModelAndView ViewALlYearProductSalesRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allYearProductSalesRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart4");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart5")
	public ModelAndView ViewALlProductRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart5");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart6")
	public ModelAndView ViewALlProductSalesRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allProductSalesRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart6");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart7")
	public ModelAndView ViewAllMonthlySellerRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allMonthlySellerRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart7");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart8")
	public ModelAndView ViewAllYearSellerRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allYearSellerRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart8");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart9")
	public ModelAndView ViewAllSellerRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.allSellerRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart9");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart10")
	public ModelAndView ViewTop3ProductRank(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.Top3ProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart10");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/chart/chart11")
	public ModelAndView ViewDateByProduct(@ModelAttribute ChartSearchVO chartSearchVO) {
		List<ChartVO> chartList = chartSerivce.DateByProduct(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart11");
		modelAndView.addObject("chartList", chartList);
		return modelAndView;
	}
	
	@GetMapping("/admin/adminChart")
	public ModelAndView adminChart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminChart");
		return modelAndView;
	}
	
	@GetMapping("/admin/adminchartview2")
	public ModelAndView adminchartview2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminchartview2");
		return modelAndView;
	}
	
	@GetMapping("/admin/adminchartview3")
	public ModelAndView adminchartview3() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminchartview3");
		return modelAndView;
	}
	
	@GetMapping("/chart/chart13")
	public ModelAndView timeChart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chart/chart13");
		return modelAndView;
		
	}
	@GetMapping("/seller/sellerChart")
	public ModelAndView sellerChart(@ModelAttribute ChartSearchVO chartSearchVO
									, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/sellerChart");
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	//=========================================================================================================
	
	@GetMapping("/sellerChart/sellerChart1")
	public ModelAndView SellerByAllMonthlyProductRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByAllMonthlyProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart1");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart2")
	public ModelAndView SellerByAllYearProductRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByAllYearProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart2");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart3")
	public ModelAndView SellerByAllMonthlyProductSalesRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByAllMonthlyProductSalesRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart3");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart4")
	public ModelAndView SellerByAllYearProductSalesRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByAllYearProductSalesRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart4");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart5")
	public ModelAndView SellerByAllProductRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByAllProductRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart5");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart6")
	public ModelAndView SellerByAllProductSalesRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByAllProductSalesRank(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart6");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart7")
	public ModelAndView viewSellerByMonthlyOneProductRank(@ModelAttribute ChartSearchVO chartSearchVO
														 , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart7");
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	@PostMapping("/sellerChart/sellerChart7")
	public ModelAndView SellerByMonthlyOneProductRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByMonthlyOneProductRank(chartSearchVO);
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart7");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	
	@GetMapping("/sellerChart/sellerChart8")
	public ModelAndView viewSellerByYearOneProductRank(@ModelAttribute ChartSearchVO chartSearchVO
														 , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart8");
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	@PostMapping("/sellerChart/sellerChart8")
	public ModelAndView SellerByYearOneProductRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByYearOneProductRank(chartSearchVO);
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart8");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart9")
	public ModelAndView viewSellerByOneProductRank(@ModelAttribute ChartSearchVO chartSearchVO
														 , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart9");
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	@PostMapping("/sellerChart/sellerChart9")
	public ModelAndView SellerByOneProductRank(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByOneProductRank(chartSearchVO);
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart9");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	@GetMapping("/sellerChart/sellerChart10")
	public ModelAndView viewSellerByOneProductSales(@ModelAttribute ChartSearchVO chartSearchVO
														 , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart10");
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	@PostMapping("/sellerChart/sellerChart10")
	public ModelAndView SellerByOneProductSales(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.SellerByOneProductSales(chartSearchVO);
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart10");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		modelAndView.addObject("productList", productList);
		return modelAndView;
	}
	
	
	
	@GetMapping("/sellerChart/sellerChart11")
	public ModelAndView TopRankOneProduct(@ModelAttribute ChartSearchVO chartSearchVO
													  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		chartSearchVO.setMemberId(memberId);
		List<ChartVO> chartList = chartSerivce.TopRankOneProduct(chartSearchVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sellerChart/sellerChart11");
		modelAndView.addObject("chartList", chartList);
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	
}
