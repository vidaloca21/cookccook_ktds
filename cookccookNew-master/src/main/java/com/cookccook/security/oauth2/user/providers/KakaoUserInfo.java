package com.cookccook.security.oauth2.user.providers;

import com.cookccook.security.oauth2.user.OAuth2UserInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KakaoUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    private Map<String, Object> kakaoAccountAttributes;
    private Map<String, Object> profileAttributes;

    public KakaoUserInfo(Map<String, Object> attributes){
        this.attributes  =  attributes;
        this.kakaoAccountAttributes = (Map<String, Object>) attributes.get("kakao_account");
        this.profileAttributes = (Map<String, Object>) attributes.get("profile");

    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getEmail() {
        Object emailValue = kakaoAccountAttributes.get("email");
        if (emailValue != null) {
            return emailValue.toString();
        } else {
            // "email" 값이 null인 경우에 대한 처리
            return "Unknown Email";
        }
    }

    @Override
    public String getName() {
        Object nicknameValue = kakaoAccountAttributes.get("nickname");
        if (nicknameValue != null) {
            return nicknameValue.toString();
        } else {
            // "email" 값이 null인 경우에 대한 처리
            return "Unknown nickname";
        }
    }

}
