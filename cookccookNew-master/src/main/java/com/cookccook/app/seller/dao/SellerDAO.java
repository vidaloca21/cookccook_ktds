package com.cookccook.app.seller.dao;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.seller.vo.PurchaseSearchVO;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

public interface SellerDAO {

	/**
	 * 판매자가 등록한 상품 갯수 조회
	 * @param memberId 조회할 판매자 아이디
	 * @return 판매자가 등록한 상품 갯수
	 */
	public int getProductAllCount(String memberId);
	public int getOnSaleCntBySeller(String memberId);
	public int getSoldoutCntBySeller(String memberId);
	public int getEndSaleCntBySeller(String memberId);
	
	/**
	 * 판매자가 등록한 상품 정보 모두 조회
	 * @param memberId 조회할 판매자 아이디
	 * @return 판매자가 등록한 상품 정보 리스트
	 */
	public List<ProductVO> getAllProductBySeller(String memberId);
	
	/**
	 * 상품 등록
	 * @param productVO 등록할 상품 정보
	 * @return DB에 insert한 갯수
	 */
	public int createNewProduct(ProductVO productVO);
	
	public int createNewChoice(List<ChoiceVO> choiceList);
	
	/**
	 * 상품id로 상품 정보 조회
	 * @param productId 조회할 상품id
	 * @return 조회한 상품 정보
	 */
	public ProductVO getOneProductBySeller(String productId);
	
	//로그인 구현 시 삭제
	/**
	 * 판매자 정보 조회
	 * @param memberId 조회할 판매자id
	 * @return 조회한 판매자 정보
	 */
	public MemberVO getOneSeller(String memberId);
	
	/**
	 * 상품 정보 수정
	 * @param productVO 수정할 상품 정보
	 * @return DB에 update한 갯수
	 */
	public int updateOneProduct(ProductVO productVO);
	
	public int deleteChoiceByProduct(String productId);
	
	/**
	 * 상품 정보 삭제(삭제 날짜 업데이트)
	 * @param productId 삭제할 상품 id
	 * @return DB에 update한 갯수
	 */
	public int deleteOneProduct(String productId);
	
	/**
	 * 재료 정보 모두 조회
	 * @return 재료 정보 리스트
	 */
	public List<IngredientVO> getAllIngredient();
	
	/**
	 * 검색한 등록한 상품 갯수 조회
	 * @param productSerchVO 검색조건VO
	 * @return 검색한 등록한 상품 갯수
	 */
	public int getProductsBySearchCount(ProductSearchVO productSearchVO);
	
	/**
	 * 상품 검색
	 * @param productSerchVO 검색조건VO
	 * @return 검색조건에 해당하는 상품 목록
	 */
	public List<ProductVO> getProductsBySearch(ProductSearchVO productSearchVO);

	public ProductVO getProductByPrdName(String prdName);
	
	public String getLatestProductId(String memberId);
	
	public List<PurchaseProductVO> getPurchaseProductsBySeller(String memberId);
	
	public int getAllPurCntBySeller(String memberId);
	public int getOrderCntBySeller(String memberId);
	public int getPreparationCntBySeller(String memberId);
	public int getDeliveringCntBySeller(String memberId);
	public int getCompleteDeliverCntBySeller(String memberId);
	public int getCancleCntBySeller(String memberId);
	
	public List<PurchaseProductVO> getPurchaseProductsBySearch(PurchaseSearchVO purchaseSearchVO);
	
	public PurchaseProductVO getPurchaseProductByPurchaseProductId(String purchaseProductId);
	
	public int updateOnePurchaseProduct(PurchaseProductVO purchaseProductVO);

	public double calculateAverageRating(String memberId);
    public int updateSellerRating(String memberId);
	public List<ChoiceVO> getChoiceByProductId(String productId);
	public List<PurchaseProductVO> getCancleBySeller(String memberId);
	public CancelVO getOneCancleByPurchaseId(String purchaseId);
	public List<PurchaseProductVO> getCancleBySearch(@Valid PurchaseSearchVO purchaseSearchVO);
}