package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseSubway;
import com.eroom.web.entity.po.BaseSubwayStation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseSubwayDao extends BaseDao {

    /**
     * 获取地铁站信息
     */
    public BaseSubway getBaseSubway(Long id) throws Exception {
        String hql = " from BaseSubway where id = :id ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<BaseSubway> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 获取城市的地铁站信息
     */
    public List<BaseSubway> getBaseSubwayByCityId(Long cityId) throws Exception {
        String hql = " from BaseSubway where cityId = :cityId ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cityId", cityId);

        List<BaseSubway> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

}
