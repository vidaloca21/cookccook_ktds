package com.cookccook.app.shop.web;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.beans.FileHandler.StoredFile;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.ProductService;
import com.cookccook.app.shop.vo.ChoiceVO;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.util.GetImgSrc;
import com.cookccook.util.SessionUtil;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private FileHandler fileHandler;
	@Autowired
	private GetImgSrc getImgSrc;
	
	@GetMapping("/shop/product")
	public ModelAndView viewProductList(Authentication memberVO) {
		List<ProductVO> productList = productService.getAllProduct();
		ModelAndView modelAndView = new ModelAndView();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if (member == null) {
			modelAndView.addObject("memberId", "");
		}
		else {
			modelAndView.addObject("memberId", member.getMemberId());
		}
		modelAndView.addObject("productList", productList);
		modelAndView.setViewName("shop/productList");
		return modelAndView;
	}
	
	@GetMapping("/shop/product/{productId}")
	public ModelAndView viewOneProduct(@PathVariable String productId
									  , Authentication memberVO) {
		ProductVO productVO = productService.getOneProduct(productId);
		ModelAndView modelAndView = new ModelAndView();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		modelAndView.addObject("member", member);
		modelAndView.addObject("productVO", productVO);
		modelAndView.addObject("selNickname", productVO.getMemberVO().getMemNickname());
		modelAndView.setViewName("shop/product");
		return modelAndView;
	}
	
	@GetMapping("/shop/product/search")
	public ModelAndView getProductListByPrdName(@RequestParam String prdName) {
		List<ProductVO> productListVO = productService.getProductListByPrdName(prdName);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop/productList");
		modelAndView.addObject("productList", productListVO);
		return modelAndView;
	}
	
	@ResponseBody
	@GetMapping("/product/choice/{productId}")
	public List<Map<String, Object>> getProductChoice(@PathVariable String productId) {
		List<ChoiceVO> choiceList = productService.getOneProduct(productId).getChoiceList();
		List<Map<String, Object>> choiceMapList = new ArrayList<>();
		for (ChoiceVO choiceVO: choiceList) {
			Map<String, Object> choiceMapper = new HashMap<>();
			choiceMapper.put("choiceId", choiceVO.getChoiceId());
			choiceMapper.put("productId", choiceVO.getProductId());
			choiceMapper.put("choName", choiceVO.getChoName());
			choiceMapper.put("choPrice", choiceVO.getChoPrice());
			choiceMapList.add(choiceMapper);
		}
		return choiceMapList;
	}
	
	@GetMapping("/shop/product/titleimg/{filename}")
	public ResponseEntity<byte[]> getPrdTitleImg(@PathVariable String filename) {
		File storedFile = fileHandler.getStoredFile(filename);
        if (storedFile == null) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        try {
            byte[] imageBytes = Files.readAllBytes(storedFile.toPath());
            HttpHeaders header = new HttpHeaders();
	        header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename);
	        
            return new ResponseEntity<>(imageBytes, header, HttpStatus.OK);
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지를 불러올 수 없습니다.");
        }
	}
	
	@ResponseBody
	@GetMapping("/shop/product/contentimg/{filename}")
	public ResponseEntity<byte[]> getPrdContentImg(@PathVariable String filename) {
		File storedFile = fileHandler.getStoredFile(filename);
        if (storedFile == null) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        try {
            byte[] imageBytes = Files.readAllBytes(storedFile.toPath());
            HttpHeaders header = new HttpHeaders();
	        header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename);
	        
            return new ResponseEntity<>(imageBytes, header, HttpStatus.OK);
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지를 불러올 수 없습니다.");
        }
	}
}
