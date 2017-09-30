package com.eroom.web.dao.system;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseCityBussiness;
import com.eroom.web.entity.po.BaseCityDistrict;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseCityDistrictDao extends BaseDao {

    /**
     * 获取城市行政区
     * @return BaseCityDistrict
     * @throws Exception
     * @author tendy
     */
    public List<BaseCityDistrict> getBaseCityDistrict() throws Exception {
        String hql = "from BaseCityDistrict ";
        List<BaseCityDistrict> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取城市的行政区
     * @return BaseCityDistrict
     * @throws Exception
     * @author tendy
     */
    public List<BaseCityDistrict> getBaseCityDistrict(Long cityId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseCityDistrict where cityId = :cityId ";
        params.put("cityId", cityId);
        List<BaseCityDistrict> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
