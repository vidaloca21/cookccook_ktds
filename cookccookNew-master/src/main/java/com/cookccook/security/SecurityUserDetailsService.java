package com.cookccook.security;


import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cookccook.app.member.dao.MemberDAO;
import com.cookccook.app.member.vo.MemberVO;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private MemberDAO memberDAO;
    
    

    public SecurityUserDetailsService(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }


    // loadUserByUsername: 로그인한 정보의 사용자 아이디를줄태니까 니가 알아서 db에서 찾아서 유저 디테일스로 반환을 해달라.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberDAO.getMemberByEmail(username);
        if (memberVO == null) {
            throw new UsernameNotFoundException(
                    username + "아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        //차단 및 회원 탈퇴 구분
        // R : 탈퇴 회원 , Y : 차단 회원 , N : 일반 회원
        String blockYn = memberVO.getBlockYn();
        if("Y".equals(blockYn)) {
        	throw new DisabledException("계정이 비활성화 되어있습니다.");        
        }
        if("R".equals(blockYn)) {
        	throw new DisabledException("탈퇴한 회원입니다.");        
        }

        memberDAO.updateLatestLoginSuccessDate(username);
        
        return new SecurityUser(memberVO);
    }
}
