package com.eroom.web.dao.room;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TRoomInfo;

@Repository
public class RoomInfoDao extends BaseDao {

	/**
	 * 获取房屋信息表
	 * 
	 * @return TRoomInfo
	 * @throws Exception
	 * @author tendy
	 */
	public List<TRoomInfo> getTRoomInfo() throws Exception {
		String hql = "from TRoomInfo order by sortId desc";
		List<TRoomInfo> list = this.getList(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

}
