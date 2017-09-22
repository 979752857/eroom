package com.eroom.web.dao.room;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.vo.rentlife.RoomAssessVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomAssessDao extends BaseDao {

    public List<RoomAssessVo> getRoomAssesses(Long targetId) throws Exception {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        hql.append(" select new com.eroom.web.entity.vo.rentlife.RoomAssessVo(assess.targetId, assess.level, assess.content, ");
        hql.append(" assess.imgUrls, assess.createTime, assess.custId, cust.nickName) ");
        hql.append(" from RoomAssess assess, TCustInfo cust where assess.targetId = cust.custId and assess.targetId = :targetId ");
        params.put("targetId", targetId);

        List<RoomAssessVo> list = this.getList(hql.toString(), params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
}
