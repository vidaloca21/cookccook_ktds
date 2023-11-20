package com.cookccook.app.seller.service;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.seller.dao.SellerDAO;
import com.cookccook.app.seller.vo.PurStateVO;
import com.cookccook.app.seller.vo.PurchaseSearchVO;
import com.cookccook.app.seller.vo.SaleStateVO;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerDAO sellerDAO;

	@Override
	public List<ProductVO> getAllProductBySeller(String memberId) {
		List<ProductVO> productListVO = sellerDAO.getAllProductBySeller(memberId);
		return productListVO;
	}
	
	@Override
	public ProductVO getOneProductBySeller(String productId) {
		ProductVO productVO = sellerDAO.getOneProductBySeller(productId);
		if(productVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return productVO;
	}
	
	//로그인 구현 시 삭제
	@Override
	public MemberVO getOneSeller(String memberId) {
		MemberVO memberVO = sellerDAO.getOneSeller(memberId);
		if(memberVO == null) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		return memberVO;
	}
	
	@Transactional
	@Override
	public boolean createNewProduct(ProductVO productVO) {
		int insertCount = sellerDAO.createNewProduct(productVO);
		if(insertCount > 0) {
			if(productVO.getChoiceList() == null) {
				List<ChoiceVO> choiceList = new ArrayList<>();
				ChoiceVO choiceVO = new ChoiceVO();
				choiceVO.setChoName("선택없음");
				choiceVO.setChoPrice(0);
				choiceList.add(choiceVO);
				productVO.setChoiceList(choiceList);
			}
			for(int i = 0; i < productVO.getChoiceList().size(); i++) {
				productVO.getChoiceList().get(i).setProductId(productVO.getProductId());
			}
			createNewChoice(productVO.getChoiceList());
		}
		return insertCount >0;
	}
	
	@Override
	public ProductVO getProductByPrdName(String prdName) {
		ProductVO productVO = sellerDAO.getProductByPrdName(prdName);
		return productVO;
	}
	
	@Transactional
	@Override
	public boolean createNewChoice(List<ChoiceVO> choiceList) {
		int insertCount = sellerDAO.createNewChoice(choiceList);
		return insertCount >= choiceList.size();
	}

	@Transactional
	@Override
	public boolean updateOneProduct(ProductVO productVO) {
		int updateCount = sellerDAO.updateOneProduct(productVO);
		int deleteCount = sellerDAO.deleteChoiceByProduct(productVO.getProductId());
		if(updateCount > 0 && deleteCount > 0) {
			if(productVO.getChoiceList() == null) {
				List<ChoiceVO> choiceList = new ArrayList<>();
				ChoiceVO choiceVO = new ChoiceVO();
				choiceVO.setChoName("선택없음");
				choiceVO.setChoPrice(0);
				choiceList.add(choiceVO);
				productVO.setChoiceList(choiceList);
			}
			for(int i = 0; i < productVO.getChoiceList().size(); i++) {
				productVO.getChoiceList().get(i).setProductId(productVO.getProductId());
			}
			createNewChoice(productVO.getChoiceList());
		}
		return updateCount > 0 && deleteCount > 0;
	}

	@Transactional
	@Override
	public boolean deleteOneProduct(String productId) {
		int deleteCount = sellerDAO.deleteOneProduct(productId);
		return deleteCount > 0;
	}

	@Override
	public List<IngredientVO> getAllIngredient() {
		List<IngredientVO> ingredientListVO = sellerDAO.getAllIngredient();
		return ingredientListVO;
	}
	
	@Override
	public List<ProductVO> getProductsBySearch(ProductSearchVO productSearchVO) {
		List<ProductVO> productListVO = sellerDAO.getProductsBySearch(productSearchVO);
		return productListVO;
	}
	
	@Override
	public String getLatestProductId(String memberId) {
		return sellerDAO.getLatestProductId(memberId);
	}

	@Override
	public SaleStateVO getSaleStateCntBySeller(String memberId) {
		SaleStateVO saleStateVO = new SaleStateVO();
		saleStateVO.setAllCnt(sellerDAO.getProductAllCount(memberId));
		saleStateVO.setOnSaleCnt(sellerDAO.getOnSaleCntBySeller(memberId));
		saleStateVO.setSoldoutCnt(sellerDAO.getSoldoutCntBySeller(memberId));
		saleStateVO.setEndSaleCnt(sellerDAO.getEndSaleCntBySeller(memberId));
		return saleStateVO;
	}

	@Override
	public List<PurchaseProductVO> getPurchaseProductsBySeller(String memberId) {
		List<PurchaseProductVO> PurchaseProductList = sellerDAO.getPurchaseProductsBySeller(memberId);
		return PurchaseProductList;
	}

	@Override
	public PurStateVO getPurStateCntBySeller(String memberId) {
		PurStateVO purStateVO = new PurStateVO();
		purStateVO.setAllCnt(sellerDAO.getAllPurCntBySeller(memberId));
		purStateVO.setOrderCnt(sellerDAO.getOrderCntBySeller(memberId));
		purStateVO.setPreparationCnt(sellerDAO.getPreparationCntBySeller(memberId));
		purStateVO.setDeliveringCnt(sellerDAO.getDeliveringCntBySeller(memberId));
		purStateVO.setCompleteCnt(sellerDAO.getCompleteDeliverCntBySeller(memberId));
		purStateVO.setCancleCnt(sellerDAO.getCancleCntBySeller(memberId));
		return purStateVO;
	}

	@Override
	public List<PurchaseProductVO> getPurchaseProductsBySearch(PurchaseSearchVO purchaseSearchVO) {
		List<PurchaseProductVO> PurchaseProductList = sellerDAO.getPurchaseProductsBySearch(purchaseSearchVO);
		return PurchaseProductList;
	}

	@Override
	public PurchaseProductVO getPurchaseProductByPurchaseProductId(String purchaseProductId) {
		PurchaseProductVO purchaseProductVO = sellerDAO.getPurchaseProductByPurchaseProductId(purchaseProductId);
		return purchaseProductVO;
	}

	@Override
	public boolean updateOnePurchaseProduct(PurchaseProductVO purchaseProductVO) {
		int updateCount = sellerDAO.updateOnePurchaseProduct(purchaseProductVO);
		return updateCount > 0;
	}

	@Override
	public double calculateAverageRating(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateSellerRating(String memberId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ChoiceVO> getChoiceByProductId(String productId) {
		return sellerDAO.getChoiceByProductId(productId);
	}

	@Override
	public List<PurchaseProductVO> getCancleBySeller(String memberId) {
		return sellerDAO.getCancleBySeller(memberId);
	}

	@Override
	public CancelVO getOneCancleByPurchaseId(String purchaseId) {
		return sellerDAO.getOneCancleByPurchaseId(purchaseId);
	}

	@Override
	public List<PurchaseProductVO> getCancleBySearch(@Valid PurchaseSearchVO purchaseSearchVO) {
		return sellerDAO.getCancleBySearch(purchaseSearchVO);
	}
}
