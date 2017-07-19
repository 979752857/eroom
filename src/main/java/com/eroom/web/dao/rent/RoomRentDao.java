package com.eroom.web.dao.rent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TRoomRent;
import com.eroom.web.entity.vo.rent.RoomDetailVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.utils.util.StringUtil;

@Repository
public class RoomRentDao extends BaseDao {

	/**
	 * 获取租房信息表
	 * 
	 * @return TRoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public List<TRoomRent> getTRoomRent() throws Exception {
		String hql = "from TRoomRent order by sortId desc";
		List<TRoomRent> list = this.getList(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}
	
	/**
	 * 通过custId获取TRoomRent
	 * 
	 * @return custId
	 * @throws Exception
	 * @author tendy
	 */
	public TRoomRent getTRoomRent(Long custId, String rentState) throws Exception {
	    String hql = "from TRoomRent where custRenterId = :custId and rentState = :rentState order by sortId desc";
	    
	    Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        params.put("rentState", rentState);
        
	    List<TRoomRent> list = this.getList(hql, params);
	    if (!CollectionUtils.isEmpty(list)) {
	        return list.get(0);
	    }
	    return null;
	}
	
	/**
     * 获取租房信息表
     * 
     * @return TRoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getTRoomRentVo() throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate) from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri "
                + "where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId order by trr.sortId desc";
        List<RoomRentVo> list = this.getList(hql);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
    /**
     * 获取租房租期信息表
     * 
     * @return TRoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getTRoomRentVo(Long custRenterId) throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate, trr.endTime ) from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri "
                + "where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId and trr.custRenterId = :custRenterId and trr.rentState = :rentState order by trr.endTime desc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custRenterId", custRenterId);
        params.put("rentState", RoomConstants.RoomRent.RentState.RENTED);
        List<RoomRentVo> list = this.getList(hql, params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
    /**
     * 获取租房租期信息表
     * 
     * @return TRoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getFdTRoomRentVo(Long custOwnerId, String rentState) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate, trr.endTime, trr.createTime, trr.rentState ) from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri "
                + "where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId and trr.custOwnerId = :custOwnerId and trr.state = :state ";
        if(!StringUtil.isBlank(rentState)){
            hql += "and trr.rentState = :rentState ";
            params.put("rentState", rentState);
        }
        hql += "order by trr.endTime desc";
        params.put("custOwnerId", custOwnerId);
        params.put("state", SystemConstants.State.ACTIVE);
        List<RoomRentVo> list = this.getList(hql, params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
    /**
     * 获取最先到期租房信息表
     * 
     * @return TRoomRent
     * @throws Exception
     * @author tendy
     */
    public RoomRentVo getLastTRoomRentVo(Long custRenterId) throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate, trr.endTime ) from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri "
                + "where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId and trr.custRenterId = :custRenterId and trr.rentState = :rentState order by trr.endTime desc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custRenterId", custRenterId);
        params.put("rentState", RoomConstants.RoomRent.RentState.RENTED);
        List<RoomRentVo> list = this.getPageList(hql, params, 0, 1);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

	/**
	 * 获取热门租房信息表
	 * 
	 * @return TRoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public List<RoomRentVo> getTRoomRentVoHot(int limit) throws Exception {
		String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
				+ "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
				+ "tri.roomType, tbi.space, tbi.decorate) from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri "
				+ "where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId order by trr.sortId desc";
		List<RoomRentVo> list = this.getPageList(hql, null, 0, limit);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

	/**
	 * 获取卧室详细信息表
	 * 
	 * @return TRoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public RoomDetailVo getRoomDetailVo(Long rentId) throws Exception {
		String hql = " select new com.eroom.web.entity.vo.rent.RoomDetailVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
				+ " trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
				+ " tri.roomType, tbi.space, tbi.decorate, tbi.direction, tri.environment, tri.year, tri.buildType, "
				+ " tri.heating, tri.greening, tri.config, trr.state, trr.updateTime, tri.floor, tri.aroundInfo, tri.traffice) "
				+ " from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri "
				+ " where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId and trr.rentId = :rentId order by trr.sortId desc ";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rentId", rentId);

		List<RoomDetailVo> l = this.getList(hql, params);
		if (CollectionUtils.isEmpty(l)) {
			return null;
		}
		return l.get(0);
	}
}
