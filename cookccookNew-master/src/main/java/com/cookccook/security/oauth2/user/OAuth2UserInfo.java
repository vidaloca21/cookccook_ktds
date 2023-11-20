package com.cookccook.security.oauth2.user;

import java.util.Map;

public interface OAuth2UserInfo {


    public Map<String, Object> getAttributes();
    public String getEmail();
    public String getName();



}
