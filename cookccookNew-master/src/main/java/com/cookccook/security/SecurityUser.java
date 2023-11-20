package com.cookccook.security;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cookccook.app.member.vo.MemberVO;

public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = -432432523523523432L;

    private MemberVO memberVO;

    public SecurityUser(MemberVO memberVO){
        this.memberVO = memberVO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.memberVO.getRole()));
    }

    @Override
    public String getUsername() {
        return this.memberVO.getMemEmail();
    }

    @Override
    public String getPassword() {
        return this.memberVO.getMemPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getSalt() {
        return memberVO.getSalt();
    }

    public String getName() {
        return memberVO.getMemName();
    }
    
    public MemberVO getMemberVO() {
    	return memberVO;
    }


}
