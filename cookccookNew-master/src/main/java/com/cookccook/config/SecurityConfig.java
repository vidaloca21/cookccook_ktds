package com.cookccook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.cookccook.security.CustomAccessDeniedHandler;
import com.cookccook.security.CustomAuthSuccessHandler;
import com.cookccook.security.LoginFailureHandler;
import com.cookccook.security.SecurityUserDetailsService;
import com.cookccook.security.jwt.JwtAuthenticationFilter;
import com.cookccook.security.oauth2.OAuthService;
import com.cookccook.app.remember.dao.PersistentLoginDAO;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    private OAuthService oauthService;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    
    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;
    
    @Autowired
    private PersistentLoginDAO persistentLoginDAO;
    
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }
    
    @Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices service = new PersistentTokenBasedRememberMeServices("rememberKey", securityUserDetailsService, persistentLoginDAO);
        service.setAlwaysRemember(false);
        return service;
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                //.requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/views/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("main"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/error/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/favicon.ico"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/css/**"))
                .requestMatchers(AntPathRequestMatcher.antMatcher("/img/**"))
        		.requestMatchers(AntPathRequestMatcher.antMatcher("/js/**"));
        
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http.oauth2Login(auth -> auth.defaultSuccessUrl("/main",true)
                        .userInfoEndpoint(user->user.userService(oauthService))
                        .loginPage("/member/memberlogin")
        );

        http.exceptionHandling().accessDeniedPage("/error/error403");
        						//.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.NOT_FOUND));

        // Access Denied 처리
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
        
        http.formLogin(formLogin->formLogin.failureHandler(loginFailureHandler)
        		//defaultSuccessUrl("/main",true)
        		.loginPage("/member/memberlogin")
                .loginProcessingUrl("/member/login-proc")
                .usernameParameter("memEmail")
                .passwordParameter("memPassword")
                .successHandler(authenticationSuccessHandler())
        );
        
        http.logout(logout -> logout.logoutUrl("/member/memberlogout")); //로그아웃을 구현해야 리멤버미의 무한 리디렉션을 방지할 수 있습니다.
           
        http.rememberMe(rm-> rm.rememberMeParameter("remember") // default: remember-me
    	        .tokenValiditySeconds(3600) // 쿠키의 만료시간 초 default: 14일
    	        .alwaysRemember(false)
    	        .userDetailsService(securityUserDetailsService)// 기능을 사용할 때 사용자 정보가 필요함. 반드시 이 설정 필요함.
        		.rememberMeServices(persistentTokenBasedRememberMeServices())
        		); 
        

//        http.rememberMe(rm->rm.rememberMeParameter("remember") // 사용자가 기억하기를 선택할 떄 웹폼에 해당 옵션을 표시
//                //.alwaysRemember(true)
//                .rememberMeServices(rememberMeServices)
//                        .tokenValiditySeconds(3600)
//                        .key("rememberKey"));//서버가 토큰을 식별하는데 사용되는 고유한 값
//
        
        http.authorizeHttpRequests(authorize -> authorize
        	    .requestMatchers(new AntPathRequestMatcher("/main")).access((authentication, request) -> {
        	        Authentication auth = authentication.get();
        	        boolean isSeller = auth.getAuthorities().stream()
        	            .anyMatch(grantedAuthority -> 
        	                grantedAuthority.getAuthority().equals("ROLE_SELLER")|| grantedAuthority.getAuthority().equals("ROLE_PRESELLER") );
        	        return new AuthorizationDecision(!isSeller);
        	    })
        	    // ... 기타 경로 설정 ...
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/memberlogin")).anonymous()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/memberlogout")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/memberregist")).anonymous()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/regist/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/address/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/seller/sellerregist/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/seller/stanby/**")).hasRole("PRESELLER")
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/mypage")).hasAnyRole("USER", "SELLER")
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/myinfoeidt")).hasAnyRole("USER","SELLER")
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/member/**")).hasAnyRole("USER", "ADMIN")
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/seller/**")).hasAnyRole("SELLER", "ADMIN")
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN")
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/shop/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/auth/token")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/question/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/review/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/subscribe/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/recipe/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/community/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/chart/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/reply/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/deliverPlace/**")).permitAll()
        	    .requestMatchers(AntPathRequestMatcher.antMatcher("/mypage")).authenticated()
        	    .anyRequest().permitAll()
        	);
        
        
        http.headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));
        http.csrf(csrf -> csrf.disable());
        http.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        
        return http.build();
    }
}
