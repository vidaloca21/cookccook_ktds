package com.cookccook.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

@SuppressWarnings("deprecation")
public class CustomVoter  implements AccessDecisionVoter<Object>{
	
	@Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if(authentication == null) {
            return ACCESS_DENIED;
        }
        
        for(GrantedAuthority authority : authentication.getAuthorities()) {
            if ("ROLE_SELLER".equals(authority.getAuthority()) &&
                ((FilterInvocation) object).getRequestUrl().startsWith("/MAIN")) {
                return ACCESS_DENIED;
            }
        }
        return ACCESS_GRANTED;
    }

}
