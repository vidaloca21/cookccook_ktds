package com.cookccook.security.jwt;

import java.io.IOException;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//import com.cookccook.app.member.vo.MemberVO;
//import com.cookccook.security.SecurityUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private JsonWebTokenProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
//        String servletPath = request.getServletPath();
//        if (servletPath.startsWith("/api/")) {
//            String jwt = request.getHeader("Authorization");
//            MemberVO member = jwtProvider.getUserFromToken(jwt);
//            SecurityUser user = new SecurityUser(member);
//            Authentication auth = new UsernamePasswordAuthenticationToken(user,
//                    null, user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
        filterChain.doFilter(request, response);
    }
}
