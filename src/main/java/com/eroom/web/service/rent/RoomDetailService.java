package com.eroom.web.service.rent;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.entity.vo.rent.RoomDetailVo;

@Service
public class RoomDetailService {

	@Resource
	private RoomRentDao roomRentDao;

	/**
	 * 获取房源详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public RoomDetailVo getRoomDetail(Long rentId) throws Exception {
		return roomRentDao.getRoomDetailVo(rentId);
	}

}
