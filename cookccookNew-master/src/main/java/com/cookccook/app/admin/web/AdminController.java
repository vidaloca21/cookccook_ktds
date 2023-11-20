package com.cookccook.app.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cookccook.app.article.vo.ArticleVO;
import com.cookccook.app.member.service.MemberService;
import com.cookccook.app.member.vo.MemberListVO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.member.vo.MemberVOList;
import com.cookccook.app.shop.vo.ProductVO;
import com.cookccook.app.subscribe.vo.SubPayVO;
import com.cookccook.util.MaskUtil;
import com.cookccook.util.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/admin/memberAllList")
	public ModelAndView MemberAllList() {
		 
		MemberListVO memberListVO = memberService.getAllMember();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("admin/memberAllList");
		modelAndView.addObject("memberListVO", memberListVO);
//		MaskUtil.masking(memberListVO);
		return modelAndView;
	}
	
	@GetMapping("/admin/{memEmail}")
	public ModelAndView viewAdminMemberpage(@PathVariable String memEmail) {
		
		MemberVO memberVO = memberService.getOneAdminMember(memEmail);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminmemberinfodetail");
		modelAndView.addObject("memberVO",memberVO);
		MaskUtil.masking(memberVO);
		return modelAndView;
	}
	
	/*
	 * @GetMapping("/admin/{memberId}/adminupdatemember") public ModelAndView
	 * viewAdminUpdateMember(@PathVariable String memberId) {
	 * 
	 * MemberVO memberVO = memberService.getOneMember(memberId);
	 * 
	 * ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("admin/adminupdatemember");
	 * modelAndView.addObject("memberVO",memberVO); return modelAndView; }
	 */
	
	@GetMapping("/admin/{memEmail}/adminupdatemember")
	public ModelAndView viewAdminUpdateMember(@PathVariable String memEmail) {
		
		MemberVO member = memberService.getOneAdminMember(memEmail);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("admin/adminupdatemember");
		modelAndView.addObject("memberVO",member);
		return modelAndView;
	}
	
	@PostMapping("/admin/{memEmail}/adminupdatemember")
	public ModelAndView doAdminUpdateMember(@PathVariable String memEmail, @ModelAttribute MemberVO memberVO) {
		
		
		MemberVO member = memberService.getOneAdminMember(memEmail);
		memberVO.setMemEmail(member.getMemEmail());
		ModelAndView modelAndView = new ModelAndView();
		boolean isSuccess = memberService.adminUpdateMember(memberVO);
		if(isSuccess) {
			modelAndView.setViewName("redirect:/admin/adminmemberalllist");
			return modelAndView;
		}
		modelAndView.setViewName("admin/adminupdatemember");
		modelAndView.addObject("memberVO", member);
		
		return modelAndView;
	}
	
	
	
	
	@GetMapping("/admin/adminmemberrole/{memEmail}")
	public ModelAndView adminmemberrole(@PathVariable String memEmail) {
		ModelAndView modelAndView = new ModelAndView();
		MemberVO memberVO = memberService.getOneMember(memEmail);
		modelAndView.setViewName("admin/adminmemberrole");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}
	
	@ResponseBody
	@PostMapping("/admin/adminmemberrole")
	public Map<String, Object> doAdminMemberRole(@ModelAttribute MemberVO memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = memberService.adminUpdateMember(memberVO);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@GetMapping("/admin/adminarticle")
	public String viewAdminArticles(Model model, Authentication authentication) {
		String memberId = SessionUtil.getMemberId(authentication);
		List<ArticleVO> memberArticles = memberService.getMemberArticles(memberId);
		model.addAttribute("memberArticle", memberArticles);
		return "admin/adminarticle";
	}
	
	@GetMapping("/admin/adminhome")
	public ModelAndView viewadminhome(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminhome");
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@GetMapping("/admin/adminlogout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response, Authentication memberVO) {
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, response, memberVO);
		return "redirect:/main";	
	}
	
	@GetMapping("/admin/adminmypage")
	public ModelAndView viewadminmypage(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminmypage");
		modelAndView.addObject("member", member);
		MaskUtil.masking(member);
		return modelAndView;
	}
	
	@GetMapping("/admin/adminchartview")
	public ModelAndView viewadminchartview(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminchartview");
		modelAndView.addObject("member", member);
		return modelAndView;
	}
	
	@GetMapping("/admin/adminmemberalllist")
	public ModelAndView AdminMemberAlllist() {
		 
		MemberListVO memberListVO = memberService.getAllMember();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("admin/adminmemberalllist");
		modelAndView.addObject("memberListVO", memberListVO);
		return modelAndView;
	}
	
	
	//TODO 이 밑으로 솔직히 불필요한 부분들
	@GetMapping("/admin/adminupdateinfo")
	public ModelAndView viewMemberEdit(Authentication authentication) {
		String memberEmail = authentication.getName(); // 현재 로그인한 사용자의 아이디를 가져옵니다.

		MemberVO memberVO = memberService.getOneMember(memberEmail);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/adminupdateinfo");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}
	
	@PostMapping("/admin/adminupdateinfo")
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
				modelAndView.setViewName("admin/adminupdateinfo");
				modelAndView.addObject("memberVO", memberVO);
				modelAndView.addObject("error", "현재 비밀번호가 일치하지 않습니다.");
				return modelAndView;
			}
		}
		
		boolean isInputNewPassword = !newPassword.isEmpty() || !confirmPassword.isEmpty();
		boolean isCorrectPassword =  newPassword.equals(confirmPassword);
		if (isInputNewPassword && !isCorrectPassword) {
			modelAndView.setViewName("admin/adminupdateinfo");
			modelAndView.addObject("memberVO", memberVO);
			modelAndView.addObject("error", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
			return modelAndView;
		}

		memberVO.setMemEmail(memberEmail); // memberVO에 사용자 아이디 설정
		if (isInputNewPassword && isCorrectPassword) {
			memberVO.setMemPassword(newPassword);
		}
		
		boolean isSuccess = memberService.memberUpdateInfo(memberVO, file);

		if (isSuccess) {
		    // 로그아웃 처리
		    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		    logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

		    modelAndView.setViewName("redirect:/member/memberlogin");
		    return modelAndView;
		}
		
		modelAndView.setViewName("admin/adminupdateinfo");
		modelAndView.addObject("memberVO", memberVO);
		return modelAndView;
	}
	
	@GetMapping("/admin/influencerinfo")
	public ModelAndView getInfluencerMember(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		List<MemberVO> influencerList = memberService.getAllInfluencer(); 
		List<MemberVO> inflReadyList = memberService.getInflReadyMember();
		modelAndView.setViewName("admin/admininfuencer");
		modelAndView.addObject("influencerList", influencerList);
		modelAndView.addObject("inflReadyList", inflReadyList);
		return modelAndView;
	}
	

	
	
	//인플루언서
	@GetMapping("/admin/influencerready")
	public ModelAndView getInflReadyMember(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/admininflready");
		return modelAndView;
	}
	
	@ResponseBody
	@PostMapping("/admin/influencerupgrade")
	public Map<String, Object> updateInfluencer(@ModelAttribute MemberVO memberVO) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = memberService.updateInfluencer(memberVO);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	
	@GetMapping("/admin/adminsellerList")
    public String viewSellerMembers(Model model) {
        List<MemberVO> memberList = memberService.getAllSellerMembers();
        model.addAttribute("memberList", memberList);
        return "admin/adminsellerList";
    }
	
	@GetMapping("/admin/adminproductalllist")
	public String viewProductAllList(Model model) {
        List<ProductVO> product = memberService.getAllProductList();
        model.addAttribute("product", product);
        return "admin/adminproductalllist";
    }
	@PostMapping("/admin/adminproductalllist")
	public String viewProductAllListSort(String sortColumn, Model model) {
		List<ProductVO> product = memberService.getProductAllListSort(sortColumn);
		model.addAttribute("product", product);
		return "admin/adminproductalllist";
	}
	@ResponseBody
	@GetMapping("/admin/subMemberList")
	public ModelAndView viewAllSubMemberList() {
		ModelAndView modelAndView = new ModelAndView();
		List<SubPayVO> subMemberlist = memberService.getSubPayMemberList();
		modelAndView.addObject("subMemberlist", subMemberlist);
		modelAndView.setViewName("admin/adminsubmemberlist");
		return modelAndView;
	}
	
	@GetMapping("/admin/adminsellerconfirm")
	public ModelAndView getAllPreSellers() {
		ModelAndView modelAndView = new ModelAndView();
		List<MemberVO> presellerList = memberService.getAllPreSellers();
		modelAndView.setViewName("admin/adminsellerconfirm");
		modelAndView.addObject("presellerList", presellerList);
		return modelAndView;
	}
	
	@ResponseBody
	@PostMapping("/admin/sellerconfirm")
	public Map<String, Object> confirmSeller(@RequestBody List<MemberVO> memberVOList) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = memberService.updatePresellerToSeller(memberVOList);
		resultMap.put("result", isSuccess);
		return resultMap;
	}

	@ResponseBody
	@PostMapping("/admin/sellerdeny")
	public Map<String, Object> denySeller(@RequestBody List<MemberVO> memberVOList) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = memberService.denySeller(memberVOList);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
}
