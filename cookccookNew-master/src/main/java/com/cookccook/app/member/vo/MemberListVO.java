package com.cookccook.app.member.vo;

import java.util.List;

import com.cookccook.util.Mask;

public class MemberListVO {
	private int memberCnt;
	
	@Mask
    private List<MemberVO> memberList;
	
    public int getMemberCnt() {
        return memberCnt;
    }

    public void setMemberCnt(int memberCnt) {
        this.memberCnt = memberCnt;
    }

    public List<MemberVO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberVO> memberList) {
        this.memberList = memberList;
    }
}
