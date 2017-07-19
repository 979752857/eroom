package com.eroom.web.service.rentlife;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.RentLifeConstants;
import com.eroom.web.dao.rentlife.RoomMessageDao;
import com.eroom.web.entity.po.TRoomRent;
import com.eroom.web.entity.vo.rentlife.RoomMessageVo;
import com.eroom.web.service.rent.RoomRentService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;

@Service
public class RoomMessageService {

	@Resource
	private RoomMessageDao roomMessageDao;
	@Resource
	private RoomRentService roomRentService;

	/**
	 * 获取最新留言信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RoomMessageVo> getLastRoomMessage(Long custId) throws Exception {
	    TRoomRent tRoomRent = roomRentService.getTRoomRentByCustId(custId);
	    if(tRoomRent == null){
	        throw new BusinessException("您还没有租住中的房屋，快去租房吧");
	    }
	    Long roomId = tRoomRent.getRoomId();
	    if(roomId == null || roomId == 0){
	        return null;
	    }
	    List<RoomMessageVo> list = roomMessageDao.getLastRoomMessageVo(roomId, RentLifeConstants.LAST_ROOM_MESSAGE_LIMIT);
		return list;
	}
	
	/**
     * 获取所有留言信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomMessageVo> getAllRoomMessage(Long custId) throws Exception {
        TRoomRent tRoomRent = roomRentService.getTRoomRentByCustId(custId);
        if(tRoomRent == null){
            throw new BusinessException("您还没有租住中的房屋，快去租房吧");
        }
        Long roomId = tRoomRent.getRoomId();
        if(roomId == null || roomId == 0){
            return null;
        }
        List<RoomMessageVo> list = roomMessageDao.getMonthRoomMessageVo(roomId, DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), -1));
        return list;
    }

}
