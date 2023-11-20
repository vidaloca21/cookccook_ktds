package com.cookccook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Spring Security 인증을 수행.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(email);
        String salt = ((SecurityUser) user).getSalt();
        System.out.println(salt);
        ((SecuritySHA) passwordEncoder).setSalt(salt);
        boolean isMatchPassword = passwordEncoder.matches(password,
                user.getPassword());
        if (isMatchPassword) {
            //인터셉터를 이용해 서버에서 사용자 정보 보내주기
            // token에 email대신 UserDetails를 전달한다.
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        }
        else {
            //이번에는 여기서 걸림
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
    /**
     * 인증 요청을 처리할 인증 필터타입 지정.
     */
    @Override
    public boolean supports(Class<?> authentication) {
// UsernamePasswordAuthenticationToken: ID/Password로 인증을 요청함.
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}