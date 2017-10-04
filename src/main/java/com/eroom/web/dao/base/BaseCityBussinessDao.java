package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.bo.BussinessInfoBo;
import com.eroom.web.entity.po.BaseCityBussiness;
import com.eroom.web.entity.po.BaseCityDistrict;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseCityBussinessDao extends BaseDao {

    /**
     * 根据商圈id获取商圈信息
     */
    public BaseCityBussiness getBaseCityBussiness(Long id) throws Exception {
        String hql = " from BaseCityBussiness where id = :id ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<BaseCityBussiness> list = this.getList(hql, params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据商圈id获取商圈信息
     */
    public List<BussinessInfoBo> getBaseCityBussinessByCityId(Long cityId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append(" select new com.eroom.web.entity.bo.BussinessInfoBo(bcb.id, bcd.id, bcb.bussinessName, bcd.districtName) ");
        hql.append(" from BaseCityBussiness bcb, BaseCityDistrict bcd where ");
        hql.append(" bcb.districtId = bcd.id and bcd.cityId = :cityId order by bcd.id desc ");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cityId", cityId);

        List<BussinessInfoBo> list = this.getList(hql.toString(), params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }
}
