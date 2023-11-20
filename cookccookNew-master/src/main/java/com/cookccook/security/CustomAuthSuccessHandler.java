package com.cookccook.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.cookccook.app.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	private MemberService memberService;
	
	private final HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
		/*
		 * String ipAddress = request.getRemoteAddr(); String memberEmail =
		 * authentication.getName(); memberService.updateIpAddress(memberEmail,
		 * ipAddress);
		 * 
		 * 
		 * //
		 * 
		 * String prevPage = (String) request.getSession().getAttribute("prevPage"); if
		 * (prevPage != null) { request.getSession().removeAttribute("prevPage");
		 * response.sendRedirect(prevPage); } else { response.sendRedirect("/main"); }
		 */
		
		/*
		 * String ipAddress = request.getRemoteAddr(); String memberEmail =
		 * authentication.getName(); memberService.updateIpAddress(memberEmail,
		 * ipAddress);
		 * 
		 * String prevPage = (String) request.getSession().getAttribute("prevPage");
		 * 
		 * 
		 * if (authentication.getAuthorities().stream() .anyMatch(r ->
		 * r.getAuthority().equals("ROLE_SELLER"))) {
		 * response.sendRedirect("/seller/orderlist"); } else if (prevPage != null) {
		 * request.getSession().removeAttribute("prevPage");
		 * response.sendRedirect(prevPage); } else
		 * if(authentication.getAuthorities().stream() .anyMatch(rol ->
		 * rol.getAuthority().equals("ROLE_ADMIN"))) {
		 * response.sendRedirect("/admin/adminchartview");
		 * 
		 * } else { response.sendRedirect("/main"); }
		 */
		
		
		String ipAddress = request.getRemoteAddr();
	    String memberEmail = authentication.getName();
	    memberService.updateIpAddress(memberEmail, ipAddress);

	    String prevPage = (String) request.getSession().getAttribute("prevPage");
	    
	    // 여기서 관리
	    if (authentication.getAuthorities().stream()
	            .anyMatch(r -> r.getAuthority().equals("ROLE_SELLER"))) {
	        response.sendRedirect("/seller/orderlist");
	    } else if (authentication.getAuthorities().stream()
	            .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
	        response.sendRedirect("/admin/adminchartview");
	    } else if (authentication.getAuthorities().stream()
	    		.anyMatch(r -> r.getAuthority().equals("ROLE_PRESELLER"))) {
	    	response.sendRedirect("/seller/stanby");
	    } else if (prevPage != null) {
	        request.getSession().removeAttribute("prevPage");
	        response.sendRedirect(prevPage);
	    } else {
	        response.sendRedirect("/main");
	    }
    }

}
