package com.cookccook.app.seller.service;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.seller.vo.PurStateVO;
import com.cookccook.app.seller.vo.PurchaseSearchVO;
import com.cookccook.app.seller.vo.SaleStateVO;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

public interface SellerService {
	/**
	 * 파라미터로 전달받은 판매자id로 상품 목록을 조회
	 * @param memberId 판매자id 
	 * @return 판매자id가 등록한 상품 목록 정보
	 */
	public List<ProductVO> getAllProductBySeller(String memberId);
	
	/**
	 * 파라미터로 전달받은 상품id의 상품 정보 조회
	 * @param productId 상품id
	 * @return 상품id의 상품 정보
	 */
	public ProductVO getOneProductBySeller(String productId);
	
	//로그인 구현 시 삭제
	/**
	 * 파라미터로 전달받은 판매자id의 판매자 정보 조회
	 * @param memberId 판매자id
	 * @return 판매자id의 판매자 정보
	 */
	public MemberVO getOneSeller(String memberId);

	/**
	 * DB에 새로운 상품 등록 처리
	 * @param productVO 사용자가 입력한 상품 정보
	 * @return 정상적으로 등록 되었는지 여부
	 */
	public boolean createNewProduct(ProductVO productVO);
	
	/**
	 * 상품 정보 수정 처리
	 * @param productVO 사용자가 입력한 상품 정보
	 * @return 정상적으로 수정 되었는지 여부
	 */
	public boolean updateOneProduct(ProductVO productVO);
	
	/**
	 * 상품 삭제날짜(PRD_HIDDEN_DATE) 수정 처리
	 * @param productId 삭제할 상품id
	 * @return 정상적으로 수정 되었는지 여부
	 */
	public boolean deleteOneProduct(String productId);
	
	/**
	 * 재료 목록 조회
	 * @return 재료 목록 정보
	 */
	public List<IngredientVO> getAllIngredient();
	
	/**
	 * 검색 조건으로 상품 목록 조회
	 * @param productSearchVO 검색조건
	 * @return 검색된 상품 목록 정보
	 */
	public List<ProductVO> getProductsBySearch(ProductSearchVO productSearchVO);
	
	public String getLatestProductId(String memberId);

	public SaleStateVO getSaleStateCntBySeller(String memberId);

	public List<PurchaseProductVO> getPurchaseProductsBySeller(String memberId);

	public PurStateVO getPurStateCntBySeller(String memberId);

	public List<PurchaseProductVO> getPurchaseProductsBySearch(@Valid PurchaseSearchVO purchaseSearchVO);

	public PurchaseProductVO getPurchaseProductByPurchaseProductId(String purchaseProductId);

	public boolean updateOnePurchaseProduct(PurchaseProductVO purchaseProductVO);
	
	public double calculateAverageRating(String memberId);
	public void updateSellerRating(String memberId);

	public boolean createNewChoice(List<ChoiceVO> choiceList);

	public ProductVO getProductByPrdName(String prdName);

	public List<ChoiceVO> getChoiceByProductId(String productId);

	public List<PurchaseProductVO> getCancleBySeller(String memberId);

	public CancelVO getOneCancleByPurchaseId(String purchaseId);

	public List<PurchaseProductVO> getCancleBySearch(@Valid PurchaseSearchVO purchaseSearchVO);
	
}
