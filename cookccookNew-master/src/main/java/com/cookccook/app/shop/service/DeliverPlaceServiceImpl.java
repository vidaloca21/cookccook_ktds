package com.cookccook.app.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cookccook.app.shop.dao.DeliverPlaceDAO;
import com.cookccook.app.shop.vo.DeliverPlaceVO;

@Service
public class DeliverPlaceServiceImpl implements DeliverPlaceService {

	@Autowired
	private DeliverPlaceDAO deliverPlaceDAO;
	
	@Override
	public List<DeliverPlaceVO> getAlldeliverPlaceByMemberId(String memberId) {
		return deliverPlaceDAO.getAlldeliverPlaceByMemberId(memberId);
	}

	@Override
	public boolean addNewDeliverPlace(DeliverPlaceVO deliverPlaceVO) {
		return deliverPlaceDAO.addNewDeliverPlace(deliverPlaceVO) >0;
	}

	@Override
	public boolean updateOneDeliverPlace(DeliverPlaceVO deliverPlaceVO) {
		return deliverPlaceDAO.updateOneDeliverPlace(deliverPlaceVO) >0;
	}

	@Override
	public boolean deleteOneDeliverPlace(String deliverPlaceId) {
		return deliverPlaceDAO.deleteOneDeliverPlace(deliverPlaceId) >0;
	}

}
