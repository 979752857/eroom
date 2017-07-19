package com.eroom.web.dao.rentlife;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.vo.rentlife.RoomMessageVo;

@Repository
public class RoomMessageDao extends BaseDao {

	/**
	 * 获取最新留言信息
	 * 
	 * @return TRoomMessage
	 * @throws Exception
	 * @author tendy
	 */
	public List<RoomMessageVo> getLastRoomMessageVo(Long roomId, int limit) throws Exception {
		String hql = "select new com.eroom.web.entity.vo.rentlife.RoomMessageVo( "
                + "trm.roomId, trm.custId, tci.name, tci.image, trm.content, trm.createTime, trm.state "
                + ") from TCustInfo tci, TRoomMessage trm "
                + "where trm.roomId = :roomId and trm.custId = tci.id order by trm.createTime desc ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roomId", roomId);
        
		List<RoomMessageVo> list = this.getPageList(hql, params, 0, limit);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}
	
	/**
	 * 获取近一个月留言信息
	 * 
	 * @return TRoomMessage
	 * @throws Exception
	 * @author tendy
	 */
	public List<RoomMessageVo> getMonthRoomMessageVo(Long roomId, Date time) throws Exception {
	    String hql = "select new com.eroom.web.entity.vo.rentlife.RoomMessageVo( "
	            + "trm.roomId, trm.custId, tci.name, tci.image, trm.content, trm.createTime, trm.state "
	            + ") from TCustInfo tci, TRoomMessage trm "
	            + "where trm.roomId = :roomId and trm.createTime > :time and trm.custId = tci.id order by trm.createTime desc ";
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("roomId", roomId);
	    params.put("time", time);
	    
	    List<RoomMessageVo> list = this.getList(hql, params);
	    if (!CollectionUtils.isEmpty(list)) {
	        return list;
	    }
	    return null;
	}

}
