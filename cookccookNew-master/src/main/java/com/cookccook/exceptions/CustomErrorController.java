//package com.cookccook.exceptions;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//
//public class CustomErrorController implements ErrorController{
//	
//	@RequestMapping("/error")
//    public String handleError(HttpServletRequest request) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        
//        if (status != null) {
//            int statusCode = Integer.parseInt(status.toString());
//            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error/404"; // 404 에러 페이지 경로
//            }
//        }
//        
//        return "error/generic"; // 다른 오류에 대한 사용자 정의 페이지 경로
//    }
//
//}
