package com.eroom.web.dao.cust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TCustRoomCollect;
import com.eroom.web.entity.vo.rent.RoomRentVo;

@Repository
public class TCustRoomCollectDao extends BaseDao {

    /**
     * 根据租客ID查询收藏信息
     */
    public TCustRoomCollect getTCustRoomCollect(Long custId, Long rentId) throws Exception {
        String hql = "from TCustRoomCollect where custId = :custId and rentId = :rentId and state = :state ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        params.put("rentId", rentId);
        params.put("state", SystemConstants.State.ACTIVE);

        List<TCustRoomCollect> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 查询收藏信息
     */
    public List<TCustRoomCollect> getTCustRoomCollect(Long custId) throws Exception {
        String hql = "from TCustRoomCollect where custId = :custId and state = :state ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        params.put("state", SystemConstants.State.ACTIVE);

        List<TCustRoomCollect> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    /**
     * 获取收藏房源信息
     * 
     * @return custId
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getCollectTRoomRentVo(Long custId) throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rent.RoomRentVo(trr.rentId, trr.roomId, trr.custOwnerId, trr.bedroomId, "
                + "trr.custRenterId, tbi.imageUrl, tri.imageUrl, tri.name, trr.price, "
                + "tri.roomType, tbi.space, tbi.decorate) from TRoomRent trr, TBedroomInfo tbi, TRoomInfo tri, TCustRoomCollect tcrc "
                + "where tcrc.custId = :custId and tcrc.state = :state and tcrc.rentId = trr.rentId and trr.roomId = tri.roomId and trr.bedroomId = tbi.bedroomId "
                + "order by trr.sortId desc";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        params.put("state", SystemConstants.State.ACTIVE);
        List<RoomRentVo> list = this.getList(hql, params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
}
