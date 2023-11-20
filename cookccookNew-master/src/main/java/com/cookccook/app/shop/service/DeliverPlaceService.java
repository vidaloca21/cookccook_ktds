package com.cookccook.app.shop.service;

import java.util.List;

import com.cookccook.app.shop.vo.DeliverPlaceVO;

public interface DeliverPlaceService {

	public List<DeliverPlaceVO> getAlldeliverPlaceByMemberId(String memberId);
	public boolean addNewDeliverPlace(DeliverPlaceVO deliverPlaceVO);
	public boolean updateOneDeliverPlace(DeliverPlaceVO deliverPlaceVO);
	public boolean deleteOneDeliverPlace(String deliverPlaceId);
	
}
