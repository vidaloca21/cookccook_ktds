package com.cookccook.app.member.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.member.service.MemberService;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.member.vo.validategroup.MemberRegistGroup;
import com.cookccook.app.remember.dao.PersistentLoginDAO;
import com.cookccook.util.SessionUtil;
import jakarta.validation.Valid;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private PersistentLoginDAO persistentLoginDAO;

	@GetMapping("/")
    public ModelAndView viewIntroPage(Authentication memberVO) {
       ModelAndView modelAndView = new ModelAndView();
       MemberVO member = SessionUtil.getLoginMember(memberVO);
       modelAndView.setViewName("article/recomm");
       modelAndView.addObject("member", member);
       return modelAndView;
    }
	

	@GetMapping("/admin/home")
	public String helloAdmin() {
		return "admin/home";
	}

	@GetMapping("/seller/home")
	public String helloSeller() {
		return "redirect:/shop/product";
	}

	@GetMapping("/member/memberregist")
	public String viewRegistMemberPage() {
		return "member/memberregist";
	}

	// 이게 맞는지 다시 확인해야한다.
	@ResponseBody // 응답데이터를 JSON으로 변환하여 브라우저에게 전송한다.
	@GetMapping("/member/regist/available")
	public Map<String, Object> checkAvailableEmail(@RequestParam String email) {

		boolean isAvailableEmail = memberService.checkAvailableEmail(email);

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("email", email);
		responseMap.put("available", isAvailableEmail);

		// Map을 반환하면 @ResponesBody에 의해 JSON으로 변환되어 응답된다.
		return responseMap;

	}

	@PostMapping("/member/memberregist")
	public ModelAndView doRegistMember(@Valid @Validated(MemberRegistGroup.class) @ModelAttribute MemberVO memberVO,
			BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("member/memberregist");
			modelAndView.addObject("memberVO", memberVO);
			
			/*
			 * bindingResult.getAllErrors().forEach(error -> {
			 * modelAndView.addObject(error.getObjectName(), error.getDefaultMessage()); });
			 */
			
			return modelAndView;
		}

		boolean isSuccess = memberService.createNewMember(memberVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/main");
			return modelAndView;
		}

		modelAndView.setViewName("member/memberregist");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}

//	    @GetMapping("/member/memberlogin")
//	    public String viewMemberLogin(){
//	        return "member/memberlogin";
//	    }

	@GetMapping("/member/memberlogin")
	public String viewMemberLogin(HttpServletRequest request) {
		String referrer = request.getHeader("Referer");
		if (referrer != null && !referrer.contains("/member/memberlogin")) {
			request.getSession().setAttribute("prevPage", referrer);
		}
		return "member/memberlogin";
	}

	@GetMapping("/member/memberlist")
	public String viewMemberList() {
		return "member/memberlist";
	}

	@GetMapping("/member/memberlogout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response, Authentication memberVO) {
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, memberVO);
		return "redirect:/member/memberlogin";
	}

	@GetMapping("/member/delete-me")
	public String doDeleteMe(HttpServletRequest request, HttpServletResponse response, Authentication memberVO) {
		boolean isSuccess = memberService.deleteMe(memberVO.getName());
		if (!isSuccess) {
			return "redirect:/member/fail-delete-me";
		}
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, memberVO);
		return "redirect:/member/success-delete-me";
	}

	@GetMapping("/member/deletemember")
	public String doDeleteMember(HttpServletRequest request, HttpServletResponse response, Authentication memberVO) {
		boolean isDeleteSuccess = memberService.deleteMember(memberVO.getName());
		if (!isDeleteSuccess) {
			return "redirect:/member/success-deletemember";
		}
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, memberVO);
		return "redirect:/member/fail-deletemember";
	}

	@GetMapping({"/member/mypage", "/seller/mypage"})
	public ModelAndView viewMemberMypage(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("member/membermypage");
		modelAndView.addObject("member", member);
		return modelAndView;
	}

	@GetMapping({"/member/myinfoedit", "/seller/myinfoedit"})
	public ModelAndView viewMemberEdit(Authentication authentication) {
		String memberEmail = authentication.getName(); // 현재 로그인한 사용자의 아이디를 가져옵니다.

		MemberVO memberVO = memberService.getOneMember(memberEmail);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("member/myinfoedit");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}

	@PostMapping({"/member/myinfoedit", "/seller/myinfoedit"})
	public ModelAndView doMemberEdit(@ModelAttribute MemberVO memberVO, @RequestParam MultipartFile file,
			@RequestParam String currentPassword, @RequestParam String newPassword,
			@RequestParam String confirmPassword, HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String memberEmail = authentication.getName(); // 현재 로그인한 사용자의 아이디를 가져옵니다.
		ModelAndView modelAndView = new ModelAndView();

		// Member가 비밀번호 수정을 안하는 경우
		if (currentPassword != null && !currentPassword.isEmpty()) {
			// 현재 비밀번호 검증
			boolean isCurrentPasswordValid = memberService.verifyPassword(memberEmail, currentPassword);
			if (!isCurrentPasswordValid) {
				// 현재 비번이 일치하지 않을 경우
				// 오류 메시지 처리 해야됨
				modelAndView.setViewName("member/myinfoedit");
				modelAndView.addObject("memberVO", memberVO);
				modelAndView.addObject("error", "현재 비밀번호가 일치하지 않습니다.");
				return modelAndView;
			}
		}
		
		boolean isInputNewPassword = !newPassword.isEmpty() || !confirmPassword.isEmpty();
		boolean isCorrectPassword =  newPassword.equals(confirmPassword);
		if (isInputNewPassword && !isCorrectPassword) {
			modelAndView.setViewName("member/myinfoedit");
			modelAndView.addObject("memberVO", memberVO);
			modelAndView.addObject("error", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
			return modelAndView;
		}

		memberVO.setMemEmail(memberEmail); // memberVO에 사용자 아이디 설정
		if (isInputNewPassword && isCorrectPassword) {
			memberVO.setMemPassword(newPassword);
		}
		
		boolean isSuccess = memberService.memberUpdateInfo(memberVO, file);

		//TODO 최종 버전
	        if (isSuccess) {
	            if (request.getCookies() != null) {
	                for (Cookie cookie : request.getCookies()) {
	                    if (cookie.getName().equals("remember-me")) {
	                        // remember-me 쿠키를 지우기
	                        cookie.setMaxAge(0);
	                        response.addCookie(cookie);
	                        persistentLoginDAO.removeUserTokens(authentication.getName());
	                    }
	                }
	            }
			
		    // 로그아웃 처리
		    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		    logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

		    modelAndView.setViewName("redirect:/member/memberlogin");
		    return modelAndView;
		}
	        
		
		modelAndView.setViewName("member/myinfoedit");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}

	@GetMapping("/member/profile-image")
	public ResponseEntity<byte[]> getProfileImage(Authentication authentication) {
		String memberEmail = authentication.getName(); // 현재 로그인한 사용자의 이메일을 가져옵니다.

		MemberVO memberVO = memberService.getOneMember(memberEmail);

		if (memberVO == null || memberVO.getOriginFileName() == null) {
			throw new IllegalArgumentException("프로필 사진이 없습니다.");
		}

		File storedFile = fileHandler.getStoredFile(memberVO.getFileName());

		if (storedFile == null) {
			throw new IllegalArgumentException("파일이 존재하지 않습니다.");
		}

		try {
			byte[] imageBytes = Files.readAllBytes(storedFile.toPath());
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + memberVO.getOriginFileName());

			return new ResponseEntity<>(imageBytes, header, HttpStatus.OK);
		} catch (IOException e) {
			throw new IllegalArgumentException("이미지를 불러올 수 없습니다.");
		}
	}

	@GetMapping("/member/myarticle")
	public String viewMemberArticles(Model model, Authentication authentication) {
		String memberId = SessionUtil.getMemberId(authentication);
		List<ArticleVO> memberArticles = memberService.getMemberArticles(memberId);
		model.addAttribute("memberArticle", memberArticles);
		return "member/myarticle";
	}

	@GetMapping("/member/address")
	public String viewAddressEdit() {
		return "tools/jusoPopup";
	}

	@PostMapping("/member/address")
	public String doAddressEdit() {
		return "tools/jusoPopup";
	}
	
	@GetMapping("/member/memberDelete")
	public String viewMemberDeletePage() {
		return "member/memberDelete";
	}
	
	@PostMapping("/member/memberDelete")
	public String doMemberDelete(@RequestParam("password") String password, Authentication authentication) {
	    String memberEmail = authentication.getName();

	    // 현재 비밀번호를 확인합니다.
	    if (memberService.verifyPassword(memberEmail, password)) {
	        boolean isSuccess = memberService.deleteMember(memberEmail);

	        if (isSuccess) {
	            return "redirect:/member/successdeleteme"; // 성공 페이지로 리다이렉트
	        }
	    }

	    return "redirect:/member/faildeleteme"; // 실패 페이지로 리다이렉트
	}
	
	@GetMapping("/member/successdeleteme")
	public String viewMembersuccessDeleteMe() {
		
		return "member/successdeleteme";
	}
	
	@GetMapping("/member/faildeleteme")
	public String viewMemberfailDeleteMe() {
		
		return "member/faildeleteme";
	}

	@GetMapping("/seller/sellerregist")
	public String viewRegistSellerPage() {
		return "member/sellerregist";
	}


	@PostMapping("/seller/sellerregist")
	public ModelAndView doRegistSeller(@Valid @Validated(MemberRegistGroup.class) @ModelAttribute MemberVO memberVO,
			BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("member/sellerregist");
			modelAndView.addObject("memberVO", memberVO);
			return modelAndView;
		}

		boolean isSuccess = memberService.createNewSeller(memberVO);
		if (isSuccess) {
			modelAndView.setViewName("redirect:/main");
			return modelAndView;
		}

		modelAndView.setViewName("member/sellerregist");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}
	
	@GetMapping("/seller/stanby")
	public ModelAndView viewPreSellerPage(Authentication memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		modelAndView.setViewName("member/preseller");
		modelAndView.addObject("member", member);
		return modelAndView;
	}

}
