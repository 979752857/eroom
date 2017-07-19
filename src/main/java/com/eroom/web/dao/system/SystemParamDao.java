package com.eroom.web.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.SystemParam;

@Repository
public class SystemParamDao extends BaseDao {

    /**
     * 查询所有参数配置
     * @return
     * @throws Exception
     */
    public List<SystemParam> getSystemParamList() throws Exception {
        String hql = "from SystemParam where state = :state";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", SystemConstants.State.ACTIVE);

        return this.getList(hql, map);
    }

}
