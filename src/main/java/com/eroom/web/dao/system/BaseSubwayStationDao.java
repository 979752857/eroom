package com.eroom.web.dao.system;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseSubway;
import com.eroom.web.entity.po.BaseSubwayStation;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseSubwayStationDao extends BaseDao {

    /**
     * 获取地铁站信息表
     * @return BaseSubwayStation
     * @throws Exception
     * @author tendy
     */
    public List<BaseSubwayStation> getBaseSubwayStation() throws Exception {
        String hql = "from BaseSubwayStation ";
        List<BaseSubwayStation> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取地铁站信息
     * @return BaseSubwayStation
     * @throws Exception
     * @author tendy
     */
    public List<BaseSubwayStation> getBaseSubwayStation(Long id) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseSubwayStation where id = :id ";
        params.put("id", id);
        List<BaseSubwayStation> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
