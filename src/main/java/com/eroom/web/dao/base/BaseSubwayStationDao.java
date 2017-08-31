package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseCityDistrict;
import com.eroom.web.entity.po.BaseSubwayStation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseSubwayStationDao extends BaseDao {

    /**
     * 获取地铁站信息
     */
    public BaseSubwayStation getBaseSubwayStation(Long id) throws Exception {
        String hql = " from BaseSubwayStation where id = :id ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<BaseSubwayStation> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

}
