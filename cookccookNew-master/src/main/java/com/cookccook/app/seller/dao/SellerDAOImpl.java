package com.cookccook.app.seller.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.seller.vo.PurchaseSearchVO;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;

import jakarta.validation.Valid;

@Repository
public class SellerDAOImpl extends SqlSessionDaoSupport implements SellerDAO {
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int getProductAllCount(String memberId) {
		return getSqlSession().selectOne("getProductAllCount", memberId);
	}
	
	@Override
	public int getOnSaleCntBySeller(String memberId) {
		return getSqlSession().selectOne("getOnSaleCntBySeller", memberId);
	}

	@Override
	public int getSoldoutCntBySeller(String memberId) {
		return getSqlSession().selectOne("getSoldoutCntBySeller", memberId);
	}

	@Override
	public int getEndSaleCntBySeller(String memberId) {
		return getSqlSession().selectOne("getEndSaleCntBySeller", memberId);
	}

	@Override
	public List<ProductVO> getAllProductBySeller(String memberId) {
		return getSqlSession().selectList("getAllProductBySeller", memberId);
	}

	@Override
	public int createNewProduct(ProductVO productVO) {
		return getSqlSession().insert("createNewProduct", productVO);
	}
	
	@Override
	public ProductVO getProductByPrdName(String prdName) {
		return getSqlSession().selectOne("getProductByPrdName", prdName);
	}
	
	@Override
	public int createNewChoice(List<ChoiceVO> choiceList) {
		int cnt = 0;
		for(int i = 0; i < choiceList.size(); i++) {
			if(getSqlSession().insert("createNewChoice", choiceList.get(i)) > 0)
				cnt++;
		}
		return cnt;
	}

	@Override
	public ProductVO getOneProductBySeller(String productId) {
		return getSqlSession().selectOne("getOneProductBySeller", productId);
	}
	
	//로그인 구현 시 삭제
	@Override
	public MemberVO getOneSeller(String memberId) {
		return getSqlSession().selectOne("getOneSeller", memberId);
	}

	@Override
	public int updateOneProduct(ProductVO productVO) {
		return getSqlSession().update("updateOneProduct", productVO);
	}

	@Override
	public int deleteOneProduct(String productId) {
		return getSqlSession().update("deleteOneProduct", productId);
	}

	@Override
	public List<IngredientVO> getAllIngredient() {
		return getSqlSession().selectList("getAllIngredient");
	}

	@Override
	public int getProductsBySearchCount(ProductSearchVO productSearchVO) {
		return getSqlSession().selectOne("getProductsBySearchCount", productSearchVO);
	}
	
	@Override
	public List<ProductVO> getProductsBySearch(ProductSearchVO productSearchVO) {
		return getSqlSession().selectList("getProductsBySearch", productSearchVO);
	}
	
	@Override
	public String getLatestProductId(String memberId) {
		return getSqlSession().selectOne(memberId);
	}

	@Override
	public List<PurchaseProductVO> getPurchaseProductsBySeller(String memberId) {
		return getSqlSession().selectList("getPurchaseProductsBySeller", memberId);
	}

	@Override
	public int getAllPurCntBySeller(String memberId) {
		return getSqlSession().selectOne("getAllPurCntBySeller", memberId);
	}

	@Override
	public int getOrderCntBySeller(String memberId) {
		return getSqlSession().selectOne("getOrderCntBySeller", memberId);
	}

	@Override
	public int getPreparationCntBySeller(String memberId) {
		return getSqlSession().selectOne("getPreparationCntBySeller", memberId);
	}

	@Override
	public int getDeliveringCntBySeller(String memberId) {
		return getSqlSession().selectOne("getDeliveringCntBySeller", memberId);
	}

	@Override
	public int getCompleteDeliverCntBySeller(String memberId) {
		return getSqlSession().selectOne("getCompleteDeliverCntBySeller", memberId);
	}
	
	@Override
	public int getCancleCntBySeller(String memberId) {
		return getSqlSession().selectOne("getCancleCntBySeller", memberId);
	}

	@Override
	public List<PurchaseProductVO> getPurchaseProductsBySearch(PurchaseSearchVO purchaseSearchVO) {
		return getSqlSession().selectList("getPurchaseProductsBySearch", purchaseSearchVO);
	}

	@Override
	public PurchaseProductVO getPurchaseProductByPurchaseProductId(String purchaseProductId) {
		return getSqlSession().selectOne("getPurchaseProductByPurchaseProductId", purchaseProductId);
	}

	@Override
	public int updateOnePurchaseProduct(PurchaseProductVO purchaseProductVO) {
		return getSqlSession().update("updateOnePurchaseProduct", purchaseProductVO);
	}

	
	@Override
    public int updateSellerRating(String memberId) {
        return getSqlSession().update("updateSellerRating", memberId);
    }

    @Override
    public double calculateAverageRating(String memberId) {
        Double averageRating = getSqlSession().selectOne("calculateAverageRating", memberId);
        return averageRating != null ? averageRating : 0.0;
    }

	@Override
	public int deleteChoiceByProduct(String productId) {
		return getSqlSession().delete("deleteChoiceByProduct", productId);
	}

	@Override
	public List<ChoiceVO> getChoiceByProductId(String productId) {
		return getSqlSession().selectList("getChoiceByProductId", productId);
	}

	@Override
	public List<PurchaseProductVO> getCancleBySeller(String memberId) {
		return getSqlSession().selectList("getCancleBySeller", memberId);
	}

	@Override
	public CancelVO getOneCancleByPurchaseId(String purchaseId) {
		return getSqlSession().selectOne("getOneCancleByPurchaseId", purchaseId);
	}

	@Override
	public List<PurchaseProductVO> getCancleBySearch(@Valid PurchaseSearchVO purchaseSearchVO) {
		return getSqlSession().selectList("getCancleBySearch", purchaseSearchVO);
	}

}