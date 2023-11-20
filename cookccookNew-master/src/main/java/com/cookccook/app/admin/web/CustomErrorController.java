package com.cookccook.app.admin.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{
	
	
	@GetMapping("/error/error403")
	public String viewError403() {
		return "error/error403";
	}
	
//	@GetMapping("/error/NotFound")
//	public String viewNotFound() {
//		return "error/errorNotFound";
//	}
	
//	 @RequestMapping("/error")
//	    public String handleError(HttpServletRequest request) {
//	        // 에러 종류를 확인하고 원하는 에러 페이지로 리다이렉트합니다.
//	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//	        if (status != null) {
//	            int statusCode = Integer.parseInt(status.toString());
//	            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//	                return "errorNotFound"; // 404 에러 페이지로 리다이렉트
//	            }
//	        }
//	        return "error"; // 기본 에러 페이지로 리다이렉트
//	    }
	 
	
}
