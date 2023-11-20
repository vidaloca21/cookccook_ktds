package com.cookccook.app.article.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.service.ArticleService;
import com.cookccook.app.article.service.RecipeService;
import com.cookccook.app.article.vo.ArticleListVO;
import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.article.vo.InterestVO;
import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.article.vo.SearchArticleVO;
import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.recommend.service.RecommService;
import com.cookccook.app.recommend.vo.ScoreVO;
import com.cookccook.util.SessionUtil;
import com.cookccook.util.XssIgnoreUtil;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService; 
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private FileHandler fileHandler;
	@Autowired
	private RecommService recommService;
	
	@GetMapping("/recipe/recipelist")
	public ModelAndView viewAllRecipeArticle(@ModelAttribute SearchArticleVO searchArticleVO
											, Authentication memberVO
											, @RequestParam(required = false) String code) {
		ArticleListVO articleListVO = articleService.getAllRecipeArticle(searchArticleVO);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		searchArticleVO.setPageCount(articleListVO.getArticleCnt());
		modelAndView.setViewName("article/recipelist");
		modelAndView.addObject("articleList", articleListVO);
		modelAndView.addObject("member", member);
		modelAndView.addObject("searchArticleVO", searchArticleVO);
		modelAndView.addObject("code", code);
		return modelAndView;
	}
	
	@GetMapping("/recipe/{articleId}")
	public ModelAndView viewOneRecipeArticle(@PathVariable String articleId
										   , Authentication memberVO) {
		ArticleVO articleVO = articleService.getOneRecipeArticle(articleId, true);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		if (member != null) {
			ScoreVO scoreVO = new ScoreVO();
			scoreVO.setMemberId(member.getMemberId());
			scoreVO.setArticleId(articleId);
			scoreVO.setCode(1);
			scoreVO.setScore(1);
			boolean isAdded = recommService.addScore(scoreVO);
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/recipe");
		modelAndView.addObject("articleVO", articleVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@GetMapping("/recipe/recipesearch")
	public ModelAndView getRecipeArticleByArticleId(@RequestParam String articleId) {
		List<ArticleVO> ArticleListVO = articleService.getRecipeArticleByTitle(articleId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/recipelist");
		modelAndView.addObject("recipelist", ArticleListVO);
		return modelAndView;
	}
	
	@GetMapping("/recipe/recipewrite")
	public String viewRecipeWritePage() {
		return "article/recipecreate";
	}
	
	@PostMapping("/recipe/recipewrite")
	public ModelAndView doRecipeWrite(@ModelAttribute RecipeVO recipeVO
									 , BindingResult bindingResult
								     , @RequestParam MultipartFile attImgSmall
								     , @RequestParam MultipartFile manualImg01
								     , @RequestParam MultipartFile manualImg02
								     , @RequestParam MultipartFile manualImg03
								     , @RequestParam MultipartFile manualImg04
								     , @RequestParam MultipartFile manualImg05
								     , @RequestParam MultipartFile manualImg06
								     , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ArticleVO newArticle = new ArticleVO();
		newArticle.setTitle(recipeVO.getTitle());
		newArticle.setArticleType(1);
		newArticle.setMemberId(member.getMemberId());
		ArticleVO article = articleService.createNewRecipeArticle(newArticle);
		recipeVO.setArticleId(article.getArticleId());
		if(!attImgSmall.isEmpty()) {
        	FileHandler.StoredFile storedAttImgSmall = fileHandler.storeRcpTitleImgFile(attImgSmall);
        	if(!attImgSmall.isEmpty() && attImgSmall != null) {
        		recipeVO.setAttImgSmall(storedAttImgSmall.getRealFileName());
        	}
        	if(!manualImg01.isEmpty() && manualImg01 != null) {
        		FileHandler.StoredFile storedManualImg01 = fileHandler.storeFile(manualImg01);
        		recipeVO.setManualImg01(storedManualImg01.getRealFileName());
        	}
        	if(!manualImg02.isEmpty() && manualImg02 != null) {
        		FileHandler.StoredFile storedManualImg02 = fileHandler.storeFile(manualImg02);
        		recipeVO.setManualImg02(storedManualImg02.getRealFileName());
        	}
        	if(!manualImg03.isEmpty() && manualImg03 != null) {
        		FileHandler.StoredFile storedManualImg03 = fileHandler.storeFile(manualImg03);
        		recipeVO.setManualImg03(storedManualImg03.getRealFileName());
        	}
        	if(!manualImg04.isEmpty() && manualImg04 != null) {
        		FileHandler.StoredFile storedManualImg04 = fileHandler.storeFile(manualImg04);
        		recipeVO.setManualImg04(storedManualImg04.getRealFileName());
        	}
        	if(!manualImg05.isEmpty() && manualImg05 != null) {
        		FileHandler.StoredFile storedManualImg05 = fileHandler.storeFile(manualImg05);
        		recipeVO.setManualImg05(storedManualImg05.getRealFileName());
        	}
        	if(!manualImg06.isEmpty() && manualImg06 != null) {
        		FileHandler.StoredFile storedManualImg06 = fileHandler.storeFile(manualImg06);
        		recipeVO.setManualImg06(storedManualImg06.getRealFileName());
        	}
        }
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = recipeService.createNewRecipe(recipeVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/recipe/recipelist?code=new");
			return modelAndView;
		}
		else {
			modelAndView.setViewName("recipe/recipecreate");
			modelAndView.addObject("recipeVO", recipeVO);
			return modelAndView;
		}
	}
	
	@ResponseBody
	@GetMapping("/recipe/contentimg/{filename}")
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
	
	@GetMapping("/recipe/recipemodify/{articleId}")
	public ModelAndView viewRecipeModifyPage(@PathVariable String articleId) {
		ArticleVO articleVO = articleService.getOneRecipeArticle(articleId, false);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/recipemodify");
		modelAndView.addObject("articleVO", articleVO);
		return modelAndView;
	}
	
	@PostMapping("/recipe/recipemodify")
	public ModelAndView doRecipeUpdate(@ModelAttribute ArticleVO articleVO) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = articleService.updateOneRecipeArticle(articleVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/recipe/recipelist");
			return modelAndView;
		}
		else {
			modelAndView.setViewName("article/recipemodify");
			modelAndView.addObject("articleVO", articleVO);
			return modelAndView;
		}
	}
	
	@GetMapping("/recipe/recipedelete/{articleId}")
	public String viewRecipeDeletePage(@PathVariable String articleId) {
		
		boolean isSuccess = articleService.deleteOneRecipeArticle(articleId);
		if (isSuccess) {
			return "redirect:/recipe/recipelist";
		}
		else {
			return "/recipe/recipedelete/{articleId}";
		}
	}
//================================================================================================

	@GetMapping("/community/communitylist")
	public ModelAndView viewAllCommunityArticle(@ModelAttribute SearchArticleVO searchArticleVO,
												Authentication memberVO) {
		ArticleListVO articleListVO = articleService.getAllCommunityArticle(searchArticleVO);
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		searchArticleVO.setPageCount(articleListVO.getArticleCnt());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/communitylist");
		modelAndView.addObject("articleList", articleListVO);
		modelAndView.addObject("member", member);
		modelAndView.addObject("searchArticleVO", searchArticleVO);
		return modelAndView;
	}
	
	@GetMapping("/community/{articleId}")
	public ModelAndView viewOneCommunityArticle(@PathVariable String articleId
											  , Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ArticleVO articleVO = articleService.getOneCommunityArticle(articleId, true);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/community");
		modelAndView.addObject("articleVO", articleVO);
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@GetMapping("/community/communitysearch")
	public ModelAndView getCommunityArticleByArticleId(@RequestParam String articleId) {
		List<ArticleVO> ArticleListVO = articleService.getCommunityArticleByTitle(articleId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/communitylist");
		modelAndView.addObject("communitylist", ArticleListVO);
		return modelAndView;
	}
	
	@GetMapping("/community/communitywrite")
	public String viewCommunityWritePage() {
		return "article/communitywrite";
	}
	
	@PostMapping("/community/communitywrite")
	public ModelAndView doCommunityWrite(@ModelAttribute ArticleVO articleVO
										 , Authentication memberVO) {
		XssIgnoreUtil.ignore(articleVO);
		
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		articleVO.setMemberId(member.getMemberId());
		ModelAndView modelAndView = new ModelAndView();
		
		boolean isSuccess = articleService.createNewCommunityArticle(articleVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/community/communitylist");
			return modelAndView;
		}
		else {
			modelAndView.setViewName("article/communitywrite");
			modelAndView.addObject("articleVO", articleVO);
			return modelAndView;
		}
	}
	
	@GetMapping("/community/communitymodify/{articleId}")
	public ModelAndView viewCommunityModifyPage(@PathVariable String articleId) {
		ArticleVO articleVO = articleService.getOneCommunityArticle(articleId, false);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("article/communitymodify");
		modelAndView.addObject("articleVO", articleVO);
		return modelAndView;
	}
	
	@PostMapping("/community/communitymodify")
	public ModelAndView doCommunityUpdate(@ModelAttribute ArticleVO articleVO) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = articleService.updateOneCommunityArticle(articleVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/community/communitylist");
			return modelAndView;
		}
		else {
			modelAndView.setViewName("article/communitymodify");
			modelAndView.addObject("articleVO", articleVO);
			return modelAndView;
		}
	}
	
	@GetMapping("/community/communitydelete/{articleId}")
	public String viewCommunityDeletePage(@PathVariable String articleId) {
		
		boolean isSuccess = articleService.deleteOneCommunityArticle(articleId);
		if (isSuccess) {
			return "redirect:/community/communitylist";
		}
		else {
			return "/community/communitydelete/{articleId}";
		}
	}
//===============================================================================
	
	@GetMapping("/apitest/articleapi")
	public String viewApitest() {
		return "article/articleapi";
	}
	
	@ResponseBody
	@GetMapping("/recipe/all")
	public Map<String, Object> getAllIngredientDetails() {
		Map<String, Object> resultMap = new HashMap<>();
		List<RecipeVO> ingredientDetailList = recipeService.getAllIngredientDetails();
		resultMap.put("result", ingredientDetailList);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("/api/recipe/{articleId}")
	public Map<String, Object> getIngredientDetailByRecipeId(@PathVariable String articleId) {
		Map<String, Object> resultMap = new HashMap<>();
		String ingredientDetail = recipeService.getIngredientDetailByArticleId(articleId);
		resultMap.put("result", ingredientDetail);
		return resultMap;
	}
	
	@GetMapping("/article/like")
	public String increaseLikeCount(@ModelAttribute InterestVO interestVO) {
		articleService.increaseLikeCount(interestVO);
		return "redirect:/article/" + interestVO.getArticleId();
	}
	
	@GetMapping("/article/dislike")
	public String decreaseLikeCount(String articleId) {
		articleService.decreaseLikeCount(articleId);
		return "redirect:/article/" + articleId;
	}
	
	@ResponseBody
	@GetMapping("/article/interest/{articleId}")
	public Map<String, Object> getAllInterestByArticleId(@PathVariable String articleId) {
		Map<String, Object> resultMap = new HashMap<>();
		List<InterestVO> interestList =  articleService.getAllInterestByArticleId(articleId);
		resultMap.put("count", interestList.size());
		resultMap.put("result", interestList);
		return resultMap;
	}
	
	
	// ==========================================================================
	@GetMapping("/recipe/excel/download")
	public ResponseEntity<Resource> downloadRecipeExcelFile(@ModelAttribute SearchArticleVO searchArticleVO) {
		
		// 엑셀로 만들 모든 게시글을 조회.
		ArticleListVO articleListVO = articleService.getAllRecipeArticle(searchArticleVO);
		// xlsx 파일을 만든다.
		Workbook workbook = new SXSSFWorkbook(-1);
		// 엑셀 시트를 만든다.
		Sheet sheet = workbook.createSheet("레시피 목록");
		// 엑셀 시트에 행(row)을 만든다.
		Row row = sheet.createRow(0);
		// 행(row)에 열(column)을 추가해 타이틀을 만든다.
		Cell cell = row.createCell(0);
		cell.setCellValue("번호");
		
		cell = row.createCell(1);
		cell.setCellValue("제목");
		
		cell = row.createCell(2);
		cell.setCellValue("첨부파일명");
		
		cell = row.createCell(3);
		cell.setCellValue("작성자이메일");
		
		cell = row.createCell(4);
		cell.setCellValue("조회수");
		
		cell = row.createCell(5);
		cell.setCellValue("등록일");
		
		cell = row.createCell(6);
		cell.setCellValue("수정일");
		
		// 게시글의 수 만큼 행(row)을 만들고 열(column)을 만들어 데이터를 추가한다.
		List<ArticleVO> articleList = articleListVO.getArticleList();
		
		// 두 번째 줄부터 데이터를 만든다.
		int rowIndex = 1;
		for (ArticleVO articleVO : articleList) {
			row = sheet.createRow(rowIndex);
			
			cell = row.createCell(0);
			cell.setCellValue(articleVO.getArticleId() + "");
			
			cell = row.createCell(1);
			cell.setCellValue(articleVO.getTitle());
			
			cell = row.createCell(2);
			cell.setCellValue(articleVO.getMemEmail());
			
			cell = row.createCell(3);
			cell.setCellValue(articleVO.getPostDate());
			
			cell = row.createCell(4);
			cell.setCellValue(articleVO.getViewCnt() + "");
			
			cell = row.createCell(5);
			cell.setCellValue(articleVO.getBookmarkCnt());
			
			rowIndex += 1;
		}
		
		// 작성한 문서를 파일로 만든다.
		File excelFile = fileHandler.getStoredFile("레시피_목록.xlsx");
		// OutputStream: Java에서 다른 시스템으로 데이터를 내보내는 것.
		OutputStream os = null;
		try {
			// 파일 데이터를 다른 시스템으로 내보낸다.
			os = new FileOutputStream(excelFile);
			workbook.write(os);
		} catch (IOException e) {
			throw new IllegalAccessError("엑셀 파일을 만들 수 없습니다.");
		} finally {
			// 파일로 다 쓰고 나면
			// 엑셀 파일을 닫는다.
			try {
				workbook.close();
			} catch (IOException e) {}
			
			// OutputStream을 쓰고 닫는다.
			if (os != null) {
				// 메모리에 저장하고 있는 OutputStream을 외부로 내보낸다.
				try {
					os.flush();
				} catch (IOException e) {}
				
				// OutputStream을 다 내보내고 나면 닫는다.
				try {
					os.close();
				} catch (IOException e) {}
			}
		}
		// 엑셀 파일을 다운로드 한다.
		// 파일명 생성
		// 다운로드할 파일명이 한글일 때, URLEncoder라는 것을 사용한다.
		String downloadFileName = URLEncoder.encode("레시피.xlsx", 
				                       Charset.defaultCharset()); // UTF-8
		// 다운로드 시작.
		return fileHandler.getResponseEntity(excelFile, downloadFileName);
	}
	
	@GetMapping("/community/excel/download")
	public ResponseEntity<Resource> downloadCommuExcelFile(@ModelAttribute SearchArticleVO searchArticleVO) {
		
		// 엑셀로 만들 모든 게시글을 조회.
		ArticleListVO articleListVO = articleService.getAllCommunityArticle(searchArticleVO);
		// xlsx 파일을 만든다.
		Workbook workbook = new SXSSFWorkbook(-1);
		// 엑셀 시트를 만든다.
		Sheet sheet = workbook.createSheet("커뮤니티 목록");
		// 엑셀 시트에 행(row)을 만든다.
		Row row = sheet.createRow(0);
		// 행(row)에 열(column)을 추가해 타이틀을 만든다.
		Cell cell = row.createCell(0);
		cell.setCellValue("번호");
		
		cell = row.createCell(1);
		cell.setCellValue("제목");
		
		cell = row.createCell(2);
		cell.setCellValue("작성자");
		
		cell = row.createCell(3);
		cell.setCellValue("게시일");
		
		cell = row.createCell(4);
		cell.setCellValue("조회수");
		
		cell = row.createCell(5);
		cell.setCellValue("좋아요");
		
		// 게시글의 수 만큼 행(row)을 만들고 열(column)을 만들어 데이터를 추가한다.
		List<ArticleVO> communityList = articleListVO.getArticleList();
		
		// 두 번째 줄부터 데이터를 만든다.
		int rowIndex = 1;
		for (ArticleVO articleVO : communityList) {
			row = sheet.createRow(rowIndex);
			
			cell = row.createCell(0);
			cell.setCellValue(articleVO.getArticleId() + "");
			
			cell = row.createCell(1);
			cell.setCellValue(articleVO.getTitle());
			
			cell = row.createCell(2);
			cell.setCellValue(articleVO.getMemberVO().getMemNickname());
			
			cell = row.createCell(3);
			cell.setCellValue(articleVO.getPostDate());
			
			cell = row.createCell(4);
			cell.setCellValue(articleVO.getViewCnt() + "");
			
			cell = row.createCell(5);
			cell.setCellValue(articleVO.getLikeCnt());
			
			rowIndex += 1;
		}
		
		// 작성한 문서를 파일로 만든다.
		File excelFile = fileHandler.getStoredFile("커뮤니티_목록.xlsx");
		// OutputStream: Java에서 다른 시스템으로 데이터를 내보내는 것.
		OutputStream os = null;
		try {
			// 파일 데이터를 다른 시스템으로 내보낸다.
			os = new FileOutputStream(excelFile);
			workbook.write(os);
		} catch (IOException e) {
			throw new IllegalAccessError("엑셀 파일을 만들 수 없습니다.");
		} finally {
			// 파일로 다 쓰고 나면
			// 엑셀 파일을 닫는다.
			try {
				workbook.close();
			} catch (IOException e) {}
			
			// OutputStream을 쓰고 닫는다.
			if (os != null) {
				// 메모리에 저장하고 있는 OutputStream을 외부로 내보낸다.
				try {
					os.flush();
				} catch (IOException e) {}
				
				// OutputStream을 다 내보내고 나면 닫는다.
				try {
					os.close();
				} catch (IOException e) {}
			}
		}
		// 엑셀 파일을 다운로드 한다.
		// 파일명 생성
		// 다운로드할 파일명이 한글일 때, URLEncoder라는 것을 사용한다.
		String downloadFileName = URLEncoder.encode("커뮤니티.xlsx", 
				                       Charset.defaultCharset()); // UTF-8
		// 다운로드 시작.
		return fileHandler.getResponseEntity(excelFile, downloadFileName);
	}
	
	
	@GetMapping("/article/titleimg/{filename}")
	public ResponseEntity<byte[]> getRcpTitleImg(@PathVariable String filename) {
		File storedFile = fileHandler.getStoredRcpFile(filename);
        if (storedFile == null || !storedFile.exists() || !storedFile.isFile()) {
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
