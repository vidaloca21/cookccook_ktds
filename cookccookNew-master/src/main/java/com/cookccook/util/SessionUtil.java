package com.cookccook.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.cookccook.app.member.dao.MemberDAO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.security.SecurityUser;

import jakarta.servlet.http.HttpServletRequest;

public class SessionUtil {

	public static MemberVO getLoginMember(Authentication authentication) {
		if (authentication != null) {
			try {
				SecurityUser user = (SecurityUser) authentication.getPrincipal();
				return user.getMemberVO();
			}
			catch(ClassCastException exception) {
				return null;
			}
		}
		return null;
	}
	
	public static String getMemberId(Authentication authentications) {
		
		MemberVO memberVO = getLoginMember(authentications);
		if (memberVO == null) {
			return null;
		}
		return memberVO.getMemberId();
		
	}
	
	public static String getIpAddress(Authentication authentication) {
		if (authentication != null) {
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            if (details != null) {
                return details.getRemoteAddress();
            }
        }
        return null;
	}
	
//	public static void saveIpAddress(Authentication authentication, MemberDAO memberDAO) {
//	    String ipAddress = getIpAddress(authentication);
//	    if (ipAddress != null) {
//	        String memEmail = getMemberId(authentication);
//	        if (memEmail != null) {
//	            memberDAO.updateIpAddress(memEmail, ipAddress);
//	        }
//	    }
//	}
	
}
