package com.cookccook.app.seller.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.question.service.QuestionService;
import com.cookccook.app.question.vo.QuestionVO;
import com.cookccook.app.question.vo.ReQueStateVO;
import com.cookccook.app.recommend.vo.IngredientVO;
import com.cookccook.app.review.service.ReviewService;
import com.cookccook.app.review.vo.RatingStateVO;
import com.cookccook.app.review.vo.ReviewVO;
import com.cookccook.app.seller.service.SellerService;
import com.cookccook.app.seller.vo.PurStateVO;
import com.cookccook.app.seller.vo.PurchaseSearchVO;
import com.cookccook.app.seller.vo.SaleStateVO;
import com.cookccook.app.shop.vo.CancelVO;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductSearchVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.shop.vo.PurchaseProductVO;
import com.cookccook.util.SessionUtil;

import jakarta.validation.Valid;

@Controller
public class SellerController {
	@Autowired
	private SellerService sellerService;
	@Autowired
    private QuestionService questionService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileHandler filehandler;
	/**
	 * 판매자id가 등록한 상품 목록 페이지
	 * @return "http://localhost:8080/seller/productlist"
	 */
	@GetMapping("/seller/productlist")
	public ModelAndView viewProductList(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		List<ProductVO> productList = sellerService.getAllProductBySeller(memberId);
		SaleStateVO saleStateVO = sellerService.getSaleStateCntBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/productlist");
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("productCnt", productList.size());
		modelAndView.addObject("saleStateVO", saleStateVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@PostMapping("/seller/productlist")
	public ModelAndView doProductsSearch(Authentication memberVO
									, @Valid @ModelAttribute ProductSearchVO productSearchVO
									, BindingResult bindingResult) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		productSearchVO.setMemberId(memberId);
		List<ProductVO> productList = sellerService.getProductsBySearch(productSearchVO);
		SaleStateVO saleStateVO = sellerService.getSaleStateCntBySeller(memberId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/productlist");
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("productCnt", productList.size());
		modelAndView.addObject("saleStateVO", saleStateVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	/**
	 * 상품 등록 페이지
	 * @return "http://localhost:8080/seller/productcreate"
	 */
	@GetMapping("/seller/productcreate")
	public ModelAndView viewProductCreate(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		List<IngredientVO> ingredientList = sellerService.getAllIngredient();

		if(member == null) {
			return null; //로그인 페이지로 이동
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/productcreate");
		modelAndView.addObject("ingredientList", ingredientList);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	/**
	 * 등록한 상품 정보 저장
	 * @return 상품이 정상적으로 등록되었으면, "http://localhost:8080/seller/productlist"
	 * 		   상품이 정상적으로 등록되지 않으면, "http://localhost:8080/seller/productcreate"
	 */
	@ResponseBody
	@PostMapping("/seller/productcreate")
	public Map<String, Object> doProductCreate(@Valid @ModelAttribute ProductVO productVO
								   , BindingResult bindingResult
								   , @RequestParam MultipartFile prdTitleImgfile
								   , @RequestParam MultipartFile prdContentImgfile
								   , Authentication memberVO) {
		 //파일 업로드 처리
        if(!prdTitleImgfile.isEmpty() && !prdContentImgfile.isEmpty()) {
        	FileHandler.StoredFile storedPrdTitleImg = filehandler.storeFile(prdTitleImgfile);
        	FileHandler.StoredFile storedPrdContentImg = filehandler.storeFile(prdContentImgfile);
        	if(prdTitleImgfile != null) {
        		productVO.setPrdTitleImg(storedPrdTitleImg.getFileName());
        		productVO.setPrdTitleImgOrigin(storedPrdTitleImg.getRealFileName());
        	}
        	if(prdContentImgfile != null) {
        		productVO.setPrdContentImg(storedPrdContentImg.getFileName());
        		productVO.setPrdContentImgOrigin(storedPrdContentImg.getRealFileName());
        	}
        }
        Map<String, Object> resultMap = new HashMap<>();
        
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		productVO.setMemberId(member.getMemberId());

		boolean isSuccess = sellerService.createNewProduct(productVO);
		if(bindingResult.hasErrors()) {
			isSuccess = false;
		}
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	/**
	 * productId의 상품 수정 페이지
	 * @return "http://localhost:8080/seller/productmodify/{productId}"
	 */
	@GetMapping("/seller/productmodify/{productId}")
	public ModelAndView viewProductModify(@PathVariable String productId
										, Model model
										, Authentication memberVO) {
		ProductVO productVO = sellerService.getOneProductBySeller(productId);
		List<IngredientVO> ingredientList = sellerService.getAllIngredient();
		List<ChoiceVO> choiceList = sellerService.getChoiceByProductId(productId);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if(!productVO.getMemberId().equals(member.getMemberId())) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/productmodify");
		modelAndView.addObject("productVO", productVO);
		modelAndView.addObject("ingredientList", ingredientList);
		modelAndView.addObject("choiceList", choiceList);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	/**
	 * 수정한 상품 정보 저장
	 * @return 상품이 정상적으로 수정되었다면, "http://localhost:8080/seller/productlist"
	 * 		   상품이 정상적으로 수정되지 않았다면, "http://localhost:8080/seller/productmodify/{productId}"
	 */
	@ResponseBody
	@PostMapping("/seller/productmodify")
	public Map<String, Object> doBoardUpdate(@Valid @ModelAttribute ProductVO productVO
								   , BindingResult bindingResult
								   , @RequestParam MultipartFile prdTitleImgfile
								   , @RequestParam MultipartFile prdContentImgfile
								   , Authentication memberVO) {
		//파일 업로드 처리
        if(!prdTitleImgfile.isEmpty() && !prdContentImgfile.isEmpty()) {
        	FileHandler.StoredFile storedPrdTitleImg = filehandler.storeFile(prdTitleImgfile);
        	FileHandler.StoredFile storedPrdContentImg = filehandler.storeFile(prdContentImgfile);
        	if(prdTitleImgfile != null) {
        		productVO.setPrdTitleImg(storedPrdTitleImg.getFileName());
        		productVO.setPrdTitleImgOrigin(storedPrdTitleImg.getRealFileName());
        	}
        	if(prdContentImgfile != null) {
        		productVO.setPrdContentImg(storedPrdContentImg.getFileName());
        		productVO.setPrdContentImgOrigin(storedPrdContentImg.getRealFileName());
        	}
        }
        
		MemberVO member = SessionUtil.getLoginMember(memberVO);
        Map<String, Object> resultMap = new HashMap<>();
		productVO.setMemberId(member.getMemberId());

		boolean isSuccess = sellerService.updateOneProduct(productVO);
		if(bindingResult.hasErrors()) {
			isSuccess = false;
		}
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	/**
	 * 상품 삭제
	 * @return 상품이 정상적으로 삭제되었다면, "http://localhost:8080/seller/productlist"
	 * 		   상품이 정상적으로 삭제되지 않았다면, "http://localhost:8080/seller/productlist"
	 */
	@GetMapping("/seller/productdelete/{productId}")
	public ModelAndView doProductDelete(@PathVariable String productId
							  , Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		ProductVO productVO = sellerService.getOneProductBySeller(productId);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if(!productVO.getMemberId().equals(member.getMemberId())) {
			throw new IllegalArgumentException("잘못된 접근입니다.");
		}
		
		boolean isSuccess = sellerService.deleteOneProduct(productId);
		if(isSuccess) {
			modelAndView.setViewName("redirect:/seller/productlist");
			return modelAndView;
		} else {
			modelAndView.setViewName("redirect:/seller/productlist"); //추후 에러페이지로 변경
			return modelAndView;
		}
	}
	
	@GetMapping("/seller/orderlist")
	public ModelAndView viewOrderList(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		List<PurchaseProductVO> purchaseProductList = sellerService.getPurchaseProductsBySeller(memberId);
		PurStateVO purStateVO = sellerService.getPurStateCntBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/orderlist");
		modelAndView.addObject("purchaseProductList", purchaseProductList);
		modelAndView.addObject("purchaseProductCnt", purchaseProductList.size());
		modelAndView.addObject("purStateVO", purStateVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@PostMapping("/seller/orderlist")
	public ModelAndView doOrderSearch(Authentication memberVO
									, @Valid @ModelAttribute PurchaseSearchVO purchaseSearchVO
									, BindingResult bindingResult) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		purchaseSearchVO.setMemberId(memberId);
		List<PurchaseProductVO> purchaseProductList = sellerService.getPurchaseProductsBySearch(purchaseSearchVO);
		PurStateVO purStateVO = sellerService.getPurStateCntBySeller(memberId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/orderlist");
		modelAndView.addObject("purchaseProductList", purchaseProductList);
		modelAndView.addObject("purchaseProductCnt", purchaseProductList.size());
		modelAndView.addObject("purStateVO", purStateVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@GetMapping("/seller/orderdetail/{purchaseProductId}")
	public ModelAndView viewOrderModify(@PathVariable String purchaseProductId
										, Model model
										, Authentication memberVO) {
		PurchaseProductVO purchaseProductVO = sellerService.getPurchaseProductByPurchaseProductId(purchaseProductId);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/orderdetail");
		modelAndView.addObject("purchaseProductVO", purchaseProductVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@ResponseBody
	@PostMapping("/seller/orderdetail")
	public Map<String, Object> doOrderUpdate(@ModelAttribute PurchaseProductVO purchaseProductVO
									, Authentication memberVO) {
		
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		Map<String, Object> resultMap = new HashMap<>();
		PurchaseProductVO originPurchaseProductVO = sellerService.getPurchaseProductByPurchaseProductId(purchaseProductVO.getPurchaseProductId());
		
		boolean isSuccess = sellerService.updateOnePurchaseProduct(purchaseProductVO);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@GetMapping("/seller/review")
	public ModelAndView getReviewByProduct(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		List<ReviewVO> reviewList = reviewService.getReviewByMemberId(memberId);
		RatingStateVO ratingStateVO = reviewService.getRatingStateBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("seller/reviewlist");
		modelAndView.addObject("member", member);
		modelAndView.addObject("reviewCnt", reviewList.size());
		modelAndView.addObject("reviewList", reviewList);
		modelAndView.addObject("ratingStateVO", ratingStateVO);
		return modelAndView;
	}
	
	@PostMapping("/seller/review")
	public ModelAndView doReviewSearch(Authentication memberVO
									, @Valid @ModelAttribute ProductSearchVO productSearchVO
									, BindingResult bindingResult) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		productSearchVO.setMemberId(memberId);
		List<ReviewVO> reviewList = reviewService.getReviewBySearch(productSearchVO);
		RatingStateVO ratingStateVO = reviewService.getRatingStateBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("seller/reviewlist");
		modelAndView.addObject("member", member);
		modelAndView.addObject("reviewCnt", reviewList.size());
		modelAndView.addObject("reviewList", reviewList);
		modelAndView.addObject("ratingStateVO", ratingStateVO);
		return modelAndView;
	}
	
	@GetMapping("/seller/question")
	public ModelAndView getQuestionByProduct(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		List<QuestionVO> questionList = questionService.getQuestionByMemberId(memberId);
		ReQueStateVO queStateVO = questionService.getReQueStateBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("seller/questionlist");
		modelAndView.addObject("member", member);
		modelAndView.addObject("questionCnt", questionList.size());
		modelAndView.addObject("questionList", questionList);
		modelAndView.addObject("queStateVO", queStateVO);
		return modelAndView;
	}
	
	@PostMapping("/seller/question")
	public ModelAndView doQuestionSearch(Authentication memberVO
									, @Valid @ModelAttribute ProductSearchVO productSearchVO
									, BindingResult bindingResult) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		productSearchVO.setMemberId(memberId);
		List<QuestionVO> questionList = questionService.getQuestionBySearch(productSearchVO);
		ReQueStateVO queStateVO = questionService.getReQueStateBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("seller/questionlist");
		modelAndView.addObject("member", member);
		modelAndView.addObject("questionCnt", questionList.size());
		modelAndView.addObject("questionList", questionList);
		modelAndView.addObject("queStateVO", queStateVO);
		return modelAndView;
	}
	
	@PostMapping("/seller/question/write")
    public ModelAndView doQuestionWrite(@Valid @ModelAttribute QuestionVO questionVO
    									, BindingResult bindingResult
    									, Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		
		// Validation 체크한 것 중 실패한 것이 있다면.
		if (bindingResult.hasErrors()) {
			// 화면을 보여준다.
			// 게시글 등록은 하지 않는다.
			modelAndView.setViewName("question/questionwrite");
			modelAndView.addObject("questionwrite", questionVO);
			return modelAndView;
		}
		System.out.println("getProductId: " + questionVO.getProductId());
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if (member == null) {
			modelAndView.setViewName("redirect:/member/memberlogin");
			return modelAndView;
		}
		boolean isSucces = questionService.createNewQuestion(questionVO);
		if(isSucces) {
			modelAndView.setViewName("redirect:/seller/question");
			modelAndView.addObject("member", member);
		}
		return modelAndView;
    }
	
	@GetMapping("/seller/question/modify")
	public ModelAndView viewQuestionModifyPage(@RequestParam String questionId
												, Authentication memberVO) {
		QuestionVO questionVO = questionService.getOneQuestionVO(questionId, false);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = questionVO.getMemberId();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("questionVO", questionVO);
		modelAndView.setViewName("seller/questionmodify");
		modelAndView.addObject("memberId", memberId);
		return modelAndView;
	}
	
	@PostMapping("/seller/question/modify")
	public ModelAndView doQuestionUpdate(@ModelAttribute QuestionVO questionVO
											, Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = questionService.updateQuestion(questionVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/seller/question");
			return modelAndView;
		}
		else {
			modelAndView.setViewName("seller/questionmodify");
			modelAndView.addObject("questionVO", questionVO);
			return modelAndView;
		}
	}
	
	@GetMapping("/seller/canclelist")
	public ModelAndView getCancleBySeller(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId();
		List<PurchaseProductVO> cancelList = sellerService.getCancleBySeller(memberId);
		PurStateVO purStateVO = sellerService.getPurStateCntBySeller(memberId);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("seller/canclelist");
		modelAndView.addObject("member", member);
		modelAndView.addObject("cancelCnt", cancelList.size());
		modelAndView.addObject("cancelList", cancelList);
		modelAndView.addObject("purStateVO", purStateVO);
		return modelAndView;
	}
	
	@PostMapping("/seller/canclelist")
	public ModelAndView doCancleSearch(Authentication memberVO
									, @Valid @ModelAttribute PurchaseSearchVO purchaseSearchVO
									, BindingResult bindingResult) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		String memberId = member.getMemberId(); 
		purchaseSearchVO.setMemberId(memberId);
		List<PurchaseProductVO> cancelList = sellerService.getCancleBySearch(purchaseSearchVO);

		PurStateVO purStateVO = sellerService.getPurStateCntBySeller(memberId);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("seller/canclelist");
		modelAndView.addObject("member", member);
		modelAndView.addObject("cancelCnt", cancelList.size());
		modelAndView.addObject("cancelList", cancelList);
		modelAndView.addObject("purStateVO", purStateVO);
		return modelAndView;
	}
	
	@GetMapping("/seller/cancledetail/{purchaseId}")
	public ModelAndView viewCancleDetail(@PathVariable String purchaseId
										, Model model
										, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		CancelVO cancelVO = sellerService.getOneCancleByPurchaseId(purchaseId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/cancledetail");
		modelAndView.addObject("cancelVO", cancelVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
}
