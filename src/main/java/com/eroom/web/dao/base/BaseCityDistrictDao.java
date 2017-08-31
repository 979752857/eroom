package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseCityDistrict;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseCityDistrictDao extends BaseDao {

    /**
     * 根据行政区id获取行政区城市id和行政区名
     */
    public BaseCityDistrict getBaseCityDistrict(Long id) throws Exception {
        String hql = " from BaseCityDistrict where id = :id ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<BaseCityDistrict> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

}
