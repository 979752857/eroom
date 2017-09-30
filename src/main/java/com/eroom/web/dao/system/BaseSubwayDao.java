package com.eroom.web.dao.system;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BaseProvinceCity;
import com.eroom.web.entity.po.BaseSubway;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BaseSubwayDao extends BaseDao {

    /**
     * 获取地铁信息表
     * @return BaseSubway
     * @throws Exception
     * @author tendy
     */
    public List<BaseSubway> getBaseSubway() throws Exception {
        String hql = "from BaseSubway ";
        List<BaseSubway> list = this.getList(hql);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取行政区的城市商圈信息
     * @return BaseSubway
     * @throws Exception
     * @author tendy
     */
    public List<BaseSubway> getBaseSubway(Long id) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "from BaseSubway where id = :id ";
        params.put("id", id);
        List<BaseSubway> list = this.getList(hql, params);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

}
