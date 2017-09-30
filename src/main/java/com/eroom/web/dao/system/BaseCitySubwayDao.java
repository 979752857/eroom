package com.eroom.web.dao.system;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseCityBussiness;
import com.eroom.web.entity.po.BaseCitySubway;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseCitySubwayDao extends BaseDao {

    /**
     * 获取城市地铁信息
     * @return BaseCitySubway
     * @throws Exception
     * @author tendy
     */
    public List<BaseCitySubway> getBaseCitySubway() throws Exception {
        String hql = "from BaseCitySubway ";
        List<BaseCitySubway> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取城市的地铁信息
     * @return BaseCitySubway
     * @throws Exception
     * @author tendy
     */
    public List<BaseCitySubway> getBaseCitySubway(Long cityId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseCitySubway where cityId = :cityId ";
        params.put("cityId", cityId);
        List<BaseCitySubway> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
