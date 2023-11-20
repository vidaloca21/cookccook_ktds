package com.cookccook.app.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HeaderInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		String requestURI = request.getRequestURI();
		
		if (modelAndView == null) {
			return;
		}
		
		if (requestURI.startsWith("/member")) {
			modelAndView.addObject("header_type", "header_mem");
		} 
		else if (requestURI.startsWith("/admin")) {
			modelAndView.addObject("header_type", "header_admin");
		} 
		else if (requestURI.startsWith("/seller")) {
			modelAndView.addObject("header_type", "header_seller");
			modelAndView.addObject("footer_type", "copyright");
		} 
		else {
			modelAndView.addObject("header_type", "header2");
		}
	}


}