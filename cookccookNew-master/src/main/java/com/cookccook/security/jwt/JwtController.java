package com.cookccook.security.jwt;


import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookccook.app.member.dao.MemberDAO;
import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.security.SHA;

@RestController
public class JwtController {
    @Autowired
    private JsonWebTokenProvider jsonWebTokenProvider;
    @Autowired
    private MemberDAO memberDAO;
    @PostMapping("/auth/token")
    public ResponseEntity<Map<String, Object>> createNewAccessToken(
            @RequestBody MemberVO memberVO) {
        MemberVO member = memberDAO.getMemberByEmail(memberVO.getMemEmail());
        if (member == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "아이디 또는 비밀번호가 일치하지 않습니다."));
        }
        SHA sha = new SHA();
        String encodePassword = sha.getEncrypt(memberVO.getMemPassword(),
                member.getSalt());
        if (member.getMemPassword().equals(encodePassword)) {
            String jwt = jsonWebTokenProvider.generateToken(
                    Duration.ofHours(12), member);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("token", jwt));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "아이디 또는 비밀번호가 일치하지 않습니다."));
    }
}