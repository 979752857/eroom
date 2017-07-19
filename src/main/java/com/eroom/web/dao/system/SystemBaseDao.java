package com.eroom.web.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.SystemBase;

@Repository
public class SystemBaseDao extends BaseDao {

    /**
     * 获取系统基本信息
     * @return SystemBase
     * @throws Exception
     * @author tendy
     */
    public SystemBase getSystemBase() throws Exception {
        String hql = "from SystemBase ";
        List<SystemBase> list = this.getList(hql);
        if (list != null && list.size() > 0) {
        	return list.get(0);
        }
        return null;
    }

}
