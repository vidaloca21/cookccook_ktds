package com.cookccook.security;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.security.oauth2.user.OAuth2UserInfo;

public class PrincipalDetails extends SecurityUser implements OAuth2User {

    private static final long serialVersionUID = -1221322999918964762L;
    private OAuth2UserInfo oAuth2UserInfo;

    public PrincipalDetails(MemberVO user, OAuth2UserInfo oAuth2UserInfo) {
        super(user);
        this.oAuth2UserInfo = oAuth2UserInfo;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }
}
