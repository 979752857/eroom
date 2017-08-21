package com.eroom.web.service.rentlife;

import com.eroom.web.constants.RentLifeConstants;
import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.rentlife.RoomMessageDao;
import com.eroom.web.entity.po.TRoomMessage;
import com.eroom.web.entity.po.TRoomRent;
import com.eroom.web.entity.vo.rent.RoomRentVo;
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
     * 获取所有留言信息
     *
     * @return
     * @throws Exception
     */
    public List<RoomMessageVo> getAllRoomMessage(Long custId, int curPage) throws Exception {
        TRoomRent tRoomRent = getRoomRent(custId);
		Map<String, Object> map = new HashMap<>();
		Long page = 0L;
		Long totle = roomMessageDao.countRoomMessageVo(tRoomRent.getRoomId());
		if(totle != null){
			page = totle/ SystemConstants.LAST_DATA_LIMIT;
			if(totle%SystemConstants.LAST_DATA_LIMIT != 0){
				page += 1;
			}
		}
		List<RoomMessageVo> list = roomMessageDao.getRoomMessageVo(tRoomRent.getRoomId(), SystemConstants.LAST_DATA_LIMIT, curPage);
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
			logger.warn("roomId出错： custId--"+custId+"  roomid--"+roomId);
			throw new BusinessException("服务器错误，请重试");
		}
		return  tRoomRent;
	}
}
