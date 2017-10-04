package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseSubwayStationRel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseSubwayStationRelDao extends BaseDao {

    /**
     * 获取地铁站信息
     */
    public List<BaseSubwayStationRel> getBaseSubwayStationRel(Long subwayId) throws Exception {
        String hql = " from BaseSubwayStationRel where subwayId = :subwayId ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("subwayId", subwayId);

        List<BaseSubwayStationRel> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

}
