package com.cookccook.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityAuthenticationInterceptor implements HandlerInterceptor {

    /**
     * 컨트롤러가 실행된 이후에 처리한다.
     *
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

        //인증정보를 가져온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            //인증정보가 존재할 경우에만 실행
            // UsernamePasswordAuthenticationToken에 지정한 UserDetails 객체를 가져온다.
            Object principle = authentication.getPrincipal();
            SecurityUser user = null;
            if (principle instanceof UserDetails) {
                user = (SecurityUser) principle;
            }
            // View에 사용자의 이름을 전달한다.
            if (user != null && modelAndView != null) {
                // RestController 혹은 ResponseBody는 ModelAndView가 없다.
                modelAndView.addObject("memberName", user.getName());
                modelAndView.addObject("memberEmail", user.getUsername());
            }

        }
    }
}
