package com.eroom.web.dao.rent;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.RoomRentSet;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRentSetDao extends BaseDao {
	
	/**
	 * 获取租房价格配置信息表
	 * 
	 * @return rentId
	 * @throws Exception
	 * @author tendy
	 */
	public RoomRentSet getRoomRentSet(Long rentId, String rentTimeType) throws Exception {
	    String hql = "from RoomRentSet where rentId = :rentId and rentTimeType = :rentTimeType and state = :state order by createTime desc";
	    
	    Map<String, Object> params = new HashMap<String, Object>();
        params.put("rentId", rentId);
        params.put("rentTimeType", rentTimeType);
        params.put("state", SystemConstants.State.ACTIVE);

	    List<RoomRentSet> list = this.getList(hql, params);
	    if (!CollectionUtils.isEmpty(list)) {
	        return list.get(0);
	    }
	    return null;
	}

	/**
	 * 获取租房价格配置信息表
	 *
	 * @return rentId
	 * @throws Exception
	 * @author tendy
	 */
	public List<RoomRentSet> getRoomRentSet(Long rentId) throws Exception {
	    String hql = "from RoomRentSet where rentId = :rentId and state = :state order by createTime desc";

	    Map<String, Object> params = new HashMap<String, Object>();
        params.put("rentId", rentId);
        params.put("state", SystemConstants.State.ACTIVE);

	    List<RoomRentSet> list = this.getList(hql, params);
	    if (!CollectionUtils.isEmpty(list)) {
	        return list;
	    }
	    return null;
	}
}
