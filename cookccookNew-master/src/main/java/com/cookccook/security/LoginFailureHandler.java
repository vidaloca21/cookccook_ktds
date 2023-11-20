package com.cookccook.security;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.cookccook.app.member.service.MemberService;
import com.cookccook.app.member.vo.MemberVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	private MemberService memberService;
	
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
    	String memEmail = request.getParameter("memEmail");
        MemberVO memberVO = memberService.getOneAdminMember(memEmail);
        if (memberVO == null) {
            System.out.println("No member found with email: " + memEmail);
        } else {
            int loginCnt = memberVO.getLoginCnt();
            loginCnt++;
            memberVO.setLoginCnt(loginCnt);
            if (loginCnt >= 5) {
                memberVO.setBlockYn("Y");
            }
            //memberService.adminUpdateMember(memberVO);
//            Date currentDate = new Date();
//            
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String dateString = dateFormat.format(currentDate);
//            
//            memberVO.setLatestLoginFailDate(dateString);       
            
            memberService.adminMemberLoginCount(memberVO);
        }
    		
    	
    	RequestDispatcher rd = request.getRequestDispatcher(
                "/WEB-INF/views/member/memberlogin.jsp");
        request.setAttribute("message", exception.getMessage());
        rd.forward(request, response);
    }
}
