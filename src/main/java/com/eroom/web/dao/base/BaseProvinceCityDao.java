package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseCityBussiness;
import com.eroom.web.entity.po.BaseProvinceCity;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseProvinceCityDao extends BaseDao {

    /**
     * 获取省份城市信息
     * @return BaseProvinceCity
     * @throws Exception
     * @author tendy
     */
    public List<BaseProvinceCity> getBaseProvinceCity() throws Exception {
        String hql = "from BaseProvinceCity ";
        List<BaseProvinceCity> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取行政区的城市商圈信息
     * @return BaseProvinceCity
     * @throws Exception
     * @author tendy
     */
    public List<BaseProvinceCity> getBaseProvinceCity(Long id) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseProvinceCity where id = :id ";
        params.put("id", id);
        List<BaseProvinceCity> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
