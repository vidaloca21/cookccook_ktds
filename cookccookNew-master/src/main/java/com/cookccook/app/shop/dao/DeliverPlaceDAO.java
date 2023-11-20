package com.cookccook.app.shop.dao;

import java.util.List;

import com.cookccook.app.shop.vo.DeliverPlaceVO;

public interface DeliverPlaceDAO {
	
	public List<DeliverPlaceVO> getAlldeliverPlaceByMemberId(String memberId);
	public int addNewDeliverPlace(DeliverPlaceVO deliverPlaceVO);
	public int updateOneDeliverPlace(DeliverPlaceVO deliverPlaceVO);
	public int deleteOneDeliverPlace(String deliverPlaceId);

}
