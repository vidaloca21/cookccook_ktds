package com.cookccook.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cookccook.app.member.dao.MemberDAO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.security.PrincipalDetails;
import com.cookccook.security.oauth2.user.OAuth2UserInfo;
import com.cookccook.security.oauth2.user.providers.GoogleUserInfo;
import com.cookccook.security.oauth2.user.providers.KakaoUserInfo;

@Service
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration()
                .getRegistrationId();
        if (provider.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if (provider.equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        //Role generate
        //List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");


        //TODO 일단 이메일이랑 이름만 가져왔는데 확정되면 추가적인 정보를 더 가져올 것
        MemberVO memberVO = new MemberVO();
        memberVO.setMemEmail(oAuth2UserInfo.getEmail());
        memberVO.setMemName(oAuth2UserInfo.getName());
        memberVO.setProvider(provider);
        memberVO.setRole("USER"); //유저라고 표기 >> 어짜피 provider로 로그인 하는 놈들 전부 유저일거라 상관없을 거 같아
        // DB에 정보 등록
        memberDAO.createOrUpdate(memberVO);
        return new PrincipalDetails(memberVO, oAuth2UserInfo);
    }

}
