package com.eroom.web.dao.system;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseSubwayStation;
import com.eroom.web.entity.po.BaseSubwayStationRel;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseSubwayStationRelDao extends BaseDao {

    /**
     * 获取地铁站关系信息表
     * @return BaseSubwayStationRel
     * @throws Exception
     * @author tendy
     */
    public List<BaseSubwayStationRel> getBaseSubwayStationRel() throws Exception {
        String hql = "from BaseSubwayStationRel ";
        List<BaseSubwayStationRel> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取地铁站关系信息表
     * @return BaseSubwayStationRel
     * @throws Exception
     * @author tendy
     */
    public List<BaseSubwayStationRel> getBaseSubwayStationRel(Long id) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseSubwayStationRel where id = :id ";
        params.put("id", id);
        List<BaseSubwayStationRel> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
