package com.eroom.web.service.rentlife;

import com.eroom.web.constants.RentLifeConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.rentlife.RoomMessageDao;
import com.eroom.web.entity.po.RoomMessage;
import com.eroom.web.entity.po.RoomRent;
import com.eroom.web.entity.vo.rentlife.RoomMessageVo;
import com.eroom.web.service.BaseService;
import com.eroom.web.service.rent.RoomRentService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomMessageService extends BaseService {

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
	    RoomRent roomRent = getRoomRent(custId);
	    List<RoomMessageVo> list = roomMessageDao.getLastRoomMessageVo(roomRent.getRoomId(), RentLifeConstants.LAST_ROOM_MESSAGE_LIMIT);
		return list;
	}
	
	/**
     * 获取所有留言信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomMessageVo> getAllRoomMessage(Long custId) throws Exception {
        RoomRent roomRent = getRoomRent(custId);
        List<RoomMessageVo> list = roomMessageDao.getMonthRoomMessageVo(roomRent.getRoomId(), DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), -1));
        return list;
    }

	/**
     * 获取所有留言信息
     *
     * @return
     * @throws Exception
     */
    public List<RoomMessageVo> getAllRoomMessage(Long custId, int curPage) throws Exception {
        RoomRent roomRent = getRoomRent(custId);
		Map<String, Object> map = new HashMap<>();
		Long page = 0L;
		Long totle = roomMessageDao.countRoomMessageVo(roomRent.getRoomId());
		if(totle != null){
			page = totle/ SystemConstants.LAST_DATA_LIMIT;
			if(totle%SystemConstants.LAST_DATA_LIMIT != 0){
				page += 1;
			}
		}
		List<RoomMessageVo> list = roomMessageDao.getRoomMessageVo(roomRent.getRoomId(), SystemConstants.LAST_DATA_LIMIT, curPage);
		map.put("list", list);
		map.put("totle", totle);
		map.put("page", page);
		map.put("curPage", curPage);
        return list;
    }

	/**
     * 添加留言信息
     *
     * @return
     * @throws Exception
     */
    public RoomMessage addRoomMessage(Long custId, String message) throws Exception {
		RoomRent roomRent = getRoomRent(custId);
		if(StringUtil.isBlank(message)){
			throw new BusinessException("留言不能为空哦！");
		}
		RoomMessage roomMessage = new RoomMessage();
		roomMessage.setCustId(custId);
		roomMessage.setRoomId(roomRent.getRoomId());
		roomMessage.setState(SystemConstants.State.ACTIVE);
		roomMessage.setContent(message);
		roomMessage.setCreateTime(DateUtil.getCurrentDate());
		roomMessage = roomMessageDao.save(roomMessage);
        return roomMessage;
    }

	/**
	 * 添加校验并获取用户所租住房屋
	 * @param custId
	 * @return
     */
	private RoomRent getRoomRent(Long custId) throws Exception {
		RoomRent roomRent = roomRentService.getTRoomRentByCustId(custId);
		if(roomRent == null){
			throw new BusinessException("您还没有租住中的房屋，快去租房吧");
		}
		Long roomId = roomRent.getRoomId();
		if(roomId == null || roomId == 0){
			logger.warn("roomId出错： custId--"+custId+"  roomid--"+roomId);
			throw new BusinessException("服务器错误，请重试");
		}
		return roomRent;
	}
}
