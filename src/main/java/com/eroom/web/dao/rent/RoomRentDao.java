package com.eroom.web.dao.rent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eroom.web.entity.bo.LocationRangeBo;
import com.eroom.web.entity.bo.RoomRentBo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.RoomRent;
import com.eroom.web.entity.vo.rent.RoomDetailVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.utils.util.StringUtil;

@Repository
public class RoomRentDao extends BaseDao {

	/**
	 * 获取租房信息表
	 * 
	 * @return RoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public List<RoomRent> getTRoomRent() throws Exception {
		String hql = "from RoomRent order by sortId desc";
		List<RoomRent> list = this.getList(hql);
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
	public RoomRent getTRoomRent(Long custId, String rentState) throws Exception {
	    String hql = "from RoomRent where custRenterId = :custId and rentState = :rentState order by sortId desc";
	    
	    Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        params.put("rentState", rentState);
        
	    List<RoomRent> list = this.getList(hql, params);
	    if (!CollectionUtils.isEmpty(list)) {
	        return list.get(0);
	    }
	    return null;
	}
	
	/**
     * 获取租房信息表
     * 
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getTRoomRentVo(RoomRentBo roomRentBo) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, ");
		hql.append(" trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, ");
		hql.append(" tri.roomType, tbi.space, tbi.decorate) from RoomRent trr, BedroomInfo tbi, RoomInfo tri ");
		hql.append(" where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId ");
		//价格最小值
		if(roomRentBo.getPriceMin() != null){
			hql.append(" and trr.price >= :priceMin ");
			params.put("priceMin", roomRentBo.getPriceMin());
		}
		//价格最大值
		if(roomRentBo.getPriceMax() != null){
			hql.append(" and trr.price <= :priceMax ");
			params.put("priceMax", roomRentBo.getPriceMax());
		}
		//租住类型
		if(roomRentBo.getRentType() != null){
			hql.append(" and trr.rentType = :rentType ");
			params.put("rentType", roomRentBo.getRentType());
		}
		//地铁站
		if(roomRentBo.getStationId() != null && roomRentBo.getLocationRange() != null){
			LocationRangeBo range = roomRentBo.getLocationRange();
			if(range.getMinLat() != null){
				hql.append(" and tri.lat >= :minLat ");
				params.put("minLat", range.getMinLat());
			}
			if(range.getMaxLat() != null){
				hql.append(" and tri.lat <= :maxLat ");
				params.put("maxLat", range.getMaxLat());
			}
			if(range.getMinLon() != null){
				hql.append(" and tri.lon >= :minLon ");
				params.put("minLon", range.getMinLon());
			}
			if(range.getMaxLon() != null){
				hql.append(" and tri.lon <= :maxLon ");
				params.put("maxLon", range.getMaxLon());
			}
		}
		//行政区
		if(roomRentBo.getDistrictId() != null){
			hql.append(" and tri.districtId = :districId ");
			params.put("districId", roomRentBo.getDistrictId());
		}
		//商圈
		if(roomRentBo.getBussinessId() != null){
			hql.append(" and tri.bussinessId = :bussinessId ");
			params.put("bussinessId", roomRentBo.getBussinessId());
		}
		//出租状态
		hql.append(" and trr.rentState = :rentState ");
		params.put("rentState", RoomConstants.RoomRent.RentState.RENTING);
		hql.append(" order by trr.sortId desc ");
        List<RoomRentVo> list = this.getList(hql.toString(), params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

	/**
     * 获取租房信息表
     *
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getTRoomRentVo(RoomRentBo roomRentBo, int limit, int page) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, ");
		hql.append(" trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, ");
		hql.append(" tri.roomType, tbi.space, tbi.decorate) from RoomRent trr, BedroomInfo tbi, RoomInfo tri ");
		hql.append(" where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId ");
		//价格最小值
		if(roomRentBo.getPriceMin() != null){
			hql.append(" and trr.price >= :priceMin ");
			params.put("priceMin", roomRentBo.getPriceMin());
		}
		//价格最大值
		if(roomRentBo.getPriceMax() != null){
			hql.append(" and trr.price <= :priceMax ");
			params.put("priceMax", roomRentBo.getPriceMax());
		}
		//租住类型
		if(roomRentBo.getRentType() != null){
			hql.append(" and trr.rentType = :rentType ");
			params.put("rentType", roomRentBo.getRentType());
		}
		//地铁站
		if(roomRentBo.getStationId() != null && roomRentBo.getLocationRange() != null){
			LocationRangeBo range = roomRentBo.getLocationRange();
			if(range.getMinLat() != null){
				hql.append(" and tri.lat >= :minLat ");
				params.put("minLat", range.getMinLat());
			}
			if(range.getMaxLat() != null){
				hql.append(" and tri.lat <= :maxLat ");
				params.put("maxLat", range.getMaxLat());
			}
			if(range.getMinLon() != null){
				hql.append(" and tri.lon >= :minLon ");
				params.put("minLon", range.getMinLon());
			}
			if(range.getMaxLon() != null){
				hql.append(" and tri.lon <= :maxLon ");
				params.put("maxLon", range.getMaxLon());
			}
		}
		//行政区
		if(roomRentBo.getDistrictId() != null){
			hql.append(" and tri.districtId = :districId ");
			params.put("districId", roomRentBo.getDistrictId());
		}
		//商圈
		if(roomRentBo.getBussinessId() != null){
			hql.append(" and tri.bussinessId = :bussinessId ");
			params.put("bussinessId", roomRentBo.getBussinessId());
		}
		//出租状态
		hql.append(" and trr.rentState = :rentState ");
		params.put("rentState", RoomConstants.RoomRent.RentState.RENTING);
		hql.append(" order by trr.sortId desc ");
        List<RoomRentVo> list = this.getPageList(hql.toString(), params, page, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

	/**
     * 统计租房信息表
     *
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public Long countRoomRentVo(RoomRentBo roomRentBo) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" select count(1) from RoomRent trr, BedroomInfo tbi, RoomInfo tri ");
		hql.append(" where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId ");
		//价格最小值
		if(roomRentBo.getPriceMin() != null){
			hql.append(" and trr.price >= :priceMin ");
			params.put("priceMin", roomRentBo.getPriceMin());
		}
		//价格最大值
		if(roomRentBo.getPriceMax() != null){
			hql.append(" and trr.price <= :priceMax ");
			params.put("priceMax", roomRentBo.getPriceMax());
		}
		//租住类型
		if(roomRentBo.getRentType() != null){
			hql.append(" and trr.rentType = :rentType ");
			params.put("rentType", roomRentBo.getRentType());
		}
		//出租状态
		hql.append(" and trr.rentState = :rentState ");
		params.put("rentState", RoomConstants.RoomRent.RentState.RENTING);
		hql.append(" order by trr.sortId desc ");
		Long count = this.getCount(hql.toString(), params);
		return count;
    }
    
    /**
     * 获取租房租期信息表
     * 
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getTRoomRentVo(Long custRenterId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, ");
        hql.append(" trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, ");
        hql.append(" tri.roomType, tbi.space, tbi.decorate, trr.endTime ) from RoomRent trr, BedroomInfo tbi, RoomInfo tri ");
        hql.append(" where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId and trr.custRenterId = :custRenterId and trr.rentState = :rentState ");
        hql.append(" order by trr.endTime desc ");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custRenterId", custRenterId);
        params.put("rentState", RoomConstants.RoomRent.RentState.RENTED);
        List<RoomRentVo> list = this.getList(hql.toString(), params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
    /**
     * 获取租房租期信息表
     * 
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getFdTRoomRentVo(Long custOwnerId, String rentState) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate, trr.endTime, trr.createTime, trr.rentState ) from RoomRent trr, BedroomInfo tbi, RoomInfo tri "
                + "where trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId and trr.custOwnerId = :custOwnerId ";
        if(!StringUtil.isBlank(rentState)){
            hql += "and trr.rentState = :rentState ";
            params.put("rentState", rentState);
        }
        hql += "order by trr.endTime desc";
        params.put("custOwnerId", custOwnerId);
        List<RoomRentVo> list = this.getList(hql, params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
    /**
     * 获取最先到期租房信息表
     * 
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public RoomRentVo getLastTRoomRentVo(Long custRenterId) throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate, trr.endTime ) from RoomRent trr, BedroomInfo tbi, RoomInfo tri "
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
	 * @return RoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public List<RoomRentVo> getTRoomRentVoHot(int limit) throws Exception {
		String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
				+ "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
				+ "tri.roomType, tbi.space, tbi.decorate) from RoomRent trr, BedroomInfo tbi, RoomInfo tri "
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
	 * @return RoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public RoomDetailVo getRoomDetailVo(Long rentId) throws Exception {
		String hql = " select new com.eroom.web.entity.vo.rent.RoomDetailVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
				+ " trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
				+ " tri.roomType, tbi.space, tbi.decorate, tbi.direction, tri.environment, tri.year, tri.buildType, "
				+ " tri.heating, tri.greening, tri.config, trr.rentState, trr.updateTime, tri.floor, tri.aroundInfo, tri.traffice) "
				+ " from RoomRent trr, BedroomInfo tbi, RoomInfo tri "
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
