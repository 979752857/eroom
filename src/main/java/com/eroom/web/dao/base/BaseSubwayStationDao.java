package com.eroom.web.dao.base;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.bo.StationInfoBo;
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

    /**
     * 获取地铁站信息
     */
    public List<StationInfoBo> getBaseSubwayStationByCityId(Long cityId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append(" select new com.eroom.web.entity.bo.StationInfoBo(bss.id, bs.id, bss.stationName, bs.subwayName) ");
        hql.append(" from BaseSubwayStation bss, BaseSubwayStationRel bssr, BaseSubway bs where bss.cityId = :cityId ");
        hql.append(" and bss.id = bssr.stationId and bssr.subwayId = bs.id order by bs.id desc ");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cityId", cityId);

        List<StationInfoBo> list = this.getList(hql.toString(), params);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

}
