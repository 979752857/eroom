package com.eroom.web.dao.system;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseCityBussiness;
import com.eroom.web.entity.po.SystemBase;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseCityBussinessDao extends BaseDao {

    /**
     * 获取城市商圈信息
     * @return BaseCityBussiness
     * @throws Exception
     * @author tendy
     */
    public List<BaseCityBussiness> getBaseCityBussiness() throws Exception {
        String hql = "from BaseCityBussiness ";
        List<BaseCityBussiness> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取行政区的城市商圈信息
     * @return BaseCityBussiness
     * @throws Exception
     * @author tendy
     */
    public List<BaseCityBussiness> getBaseCityBussiness(Long districtId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseCityBussiness where districtId = :districtId ";
        params.put("districtId", districtId);
        List<BaseCityBussiness> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
