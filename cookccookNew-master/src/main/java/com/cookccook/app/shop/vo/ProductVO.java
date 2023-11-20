package com.cookccook.app.shop.vo;

import java.util.List;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.vo.IngredientVO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductVO {
   
	private String productId;
	private String memberId;
	private String ingredientId;
	@NotBlank(message = "상품명을 입력해 주세요.")
	private String prdName;
	@NotNull(message = "가격을 입력해 주세요.")
	private int prdPrice;
	@NotBlank(message = "원산지를 입력해 주세요.")
	private String prdOrigin;
	@NotBlank(message = "상세설명을 입력해 주세요.")
	private String prdContent;
	private int saleState;
	@NotNull(message = "재고를 입력해 주세요.")
	private int stock;
	private String prdRegistDate;
	private String prdHiddenDate;
	private String prdTitleImg;
	private String prdTitleImgOrigin;
	private String prdContentImg;
	private String prdContentImgOrigin;
	
   private MemberVO memberVO;
   private IngredientVO ingredientVO;
   private List<ChoiceVO> choiceList;
   
   
   public String getProductId() {
      return productId;
   }
   public void setProductId(String productId) {
      this.productId = productId;
   }
   public String getIngredientId() {
      return ingredientId;
   }
   public void setIngredientId(String ingredientId) {
      this.ingredientId = ingredientId;
   }
   public String getPrdName() {
      return prdName;
   }
   public void setPrdName(String prdName) {
      this.prdName = prdName;
   }
   public int getPrdPrice() {
      return prdPrice;
   }
   public void setPrdPrice(int prdPrice) {
      this.prdPrice = prdPrice;
   }
   public String getPrdOrigin() {
      return prdOrigin;
   }
   public void setPrdOrigin(String prdOrigin) {
      this.prdOrigin = prdOrigin;
   }
   public String getPrdContent() {
      return prdContent;
   }
   public void setPrdContent(String prdContent) {
      this.prdContent = prdContent;
   }
   public int getSaleState() {
      return saleState;
   }
   public void setSaleState(int saleState) {
      this.saleState = saleState;
   }
   public int getStock() {
      return stock;
   }
   public void setStock(int stock) {
      this.stock = stock;
   }
   public String getPrdRegistDate() {
      return prdRegistDate;
   }
   public void setPrdRegistDate(String prdRegistDate) {
      this.prdRegistDate = prdRegistDate;
   }
	public String getPrdHiddenDate() {
		return prdHiddenDate;
	}
	public void setPrdHiddenDate(String prdHiddenDate) {
		this.prdHiddenDate = prdHiddenDate;
	}
	public IngredientVO getIngredientVO() {
		return ingredientVO;
	}
	public void setIngredientVO(IngredientVO ingredientVO) {
		this.ingredientVO = ingredientVO;
	}
	public List<ChoiceVO> getChoiceList() {
		return choiceList;
	}
	public void setChoiceList(List<ChoiceVO> choiceList) {
		this.choiceList = choiceList;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public String getPrdTitleImg() {
		return prdTitleImg;
	}
	public void setPrdTitleImg(String prdTitleImg) {
		this.prdTitleImg = prdTitleImg;
	}
	public String getPrdContentImg() {
		return prdContentImg;
	}
	public void setPrdContentImg(String prdContentImg) {
		this.prdContentImg = prdContentImg;
	}
	public String getPrdTitleImgOrigin() {
		return prdTitleImgOrigin;
	}
	public void setPrdTitleImgOrigin(String prdTitleImgOrigin) {
		this.prdTitleImgOrigin = prdTitleImgOrigin;
	}
	public String getPrdContentImgOrigin() {
		return prdContentImgOrigin;
	}
	public void setPrdContentImgOrigin(String prdContentImgOrigin) {
		this.prdContentImgOrigin = prdContentImgOrigin;
	}
	
}
