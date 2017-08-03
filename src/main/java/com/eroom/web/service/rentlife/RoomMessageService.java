package com.eroom.web.service.rentlife;

import java.util.List;

import javax.annotation.Resource;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.entity.po.TRoomMessage;
import com.eroom.web.utils.util.StringUtil;
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
	    TRoomRent tRoomRent = getRoomRent(custId);
	    List<RoomMessageVo> list = roomMessageDao.getLastRoomMessageVo(tRoomRent.getRoomId(), RentLifeConstants.LAST_ROOM_MESSAGE_LIMIT);
		return list;
	}
	
	/**
     * 获取所有留言信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomMessageVo> getAllRoomMessage(Long custId) throws Exception {
        TRoomRent tRoomRent = getRoomRent(custId);
        List<RoomMessageVo> list = roomMessageDao.getMonthRoomMessageVo(tRoomRent.getRoomId(), DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), -1));
        return list;
    }

	/**
     * 添加留言信息
     *
     * @return
     * @throws Exception
     */
    public TRoomMessage addRoomMessage(Long custId, String message) throws Exception {
		TRoomRent tRoomRent = getRoomRent(custId);
		if(StringUtil.isBlank(message)){
			throw new BusinessException("留言不能为空哦！");
		}
		TRoomMessage tRoomMessage = new TRoomMessage();
		tRoomMessage.setCustId(custId);
		tRoomMessage.setRoomId(tRoomRent.getRoomId());
		tRoomMessage.setState(SystemConstants.State.ACTIVE);
		tRoomMessage.setContent(message);
		tRoomMessage.setCreateTime(DateUtil.getCurrentDate());
		tRoomMessage = roomMessageDao.save(tRoomMessage);
        return tRoomMessage;
    }

	/**
	 * 添加校验并获取用户所租住房屋
	 * @param custId
	 * @return
     */
	private TRoomRent getRoomRent(Long custId) throws Exception {
		TRoomRent tRoomRent = roomRentService.getTRoomRentByCustId(custId);
		if(tRoomRent == null){
			throw new BusinessException("您还没有租住中的房屋，快去租房吧");
		}
		Long roomId = tRoomRent.getRoomId();
		if(roomId == null || roomId == 0){
			throw new BusinessException("roomId出错： custId--"+custId+"  roomid--"+roomId);
		}
		return  tRoomRent;
	}
}
