package com.cookccook.exceptions;

import com.cookccook.app.member.vo.MemberVO;

public class AlreadyUseException extends RuntimeException{
    private static final long serialVersionUID = 6062148073962465674L;

    /**
     * 가입정보
     */
    private MemberVO memberVO;

    public AlreadyUseException(MemberVO memberVO, String message) {
        super(message);
        this.memberVO = memberVO;
    }
}
