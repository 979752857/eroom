package com.eroom.web.service.rent;

import javax.annotation.Resource;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.utils.exception.BusinessException;
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
		if(rentId == null){
			throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
		}
		return roomRentDao.getRoomDetailVo(rentId);
	}

}
