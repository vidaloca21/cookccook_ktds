package com.cookccook.app.shop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cookccook.app.member.vo.MemberVO;
import com.cookccook.app.shop.service.DeliverPlaceService;
import com.cookccook.app.shop.vo.DeliverPlaceVO;
import com.cookccook.util.SessionUtil;

@RestController
public class DeliverPlaceController {

	@Autowired
	private DeliverPlaceService deliverPlaceService;
	
	@GetMapping("/deliverPlace/list")
	public Map<String, Object> getAlldeliverPlaceByMemberId(Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		Map<String, Object> resultMap = new HashMap<>();
		List<DeliverPlaceVO> deliverPlaceList = deliverPlaceService.getAlldeliverPlaceByMemberId(member.getMemberId());
		resultMap.put("result", deliverPlaceList.size());
		resultMap.put("deliverPlaceList", deliverPlaceList);
		return resultMap;
	}
	
	@PostMapping("/deliverPlace/new")
	public Map<String, Object> addNewDeliverPlace(DeliverPlaceVO deliverPlaceVO
													, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		Map<String, Object> resultMap = new HashMap<>();
		deliverPlaceVO.setMemberId(member.getMemberId());
		boolean isSuccess = deliverPlaceService.addNewDeliverPlace(deliverPlaceVO);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@PostMapping("/deliverPlace/modify")
	public Map<String, Object> updateOneDeliverPlace(DeliverPlaceVO deliverPlaceVO
													, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		Map<String, Object> resultMap = new HashMap<>();
		deliverPlaceVO.setMemberId(member.getMemberId());
		boolean isSuccess = deliverPlaceService.updateOneDeliverPlace(deliverPlaceVO);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	@PostMapping("/deliverPlace/deleteOne")
	public Map<String, Object> deleteOneDeliverPlace(String deliverPlaceId
													, Authentication memberVO) {
		MemberVO member = SessionUtil.getLoginMember(memberVO);
		Map<String, Object> resultMap = new HashMap<>();
		boolean isSuccess = deliverPlaceService.deleteOneDeliverPlace(deliverPlaceId);
		resultMap.put("result", isSuccess);
		return resultMap;
	}
	
	
}
