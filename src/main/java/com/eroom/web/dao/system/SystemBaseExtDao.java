package com.eroom.web.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.SystemBaseExt;

@Repository
public class SystemBaseExtDao extends BaseDao {

    /**
     * 获取系统基本信息扩展
     * @return SystemBase
     * @throws Exception
     * @author tendy
     */
    public SystemBaseExt getSystemBaseExt() throws Exception {
        String hql = "from SystemBaseExt ";
        List<SystemBaseExt> list = this.getList(hql);
        if (list != null && list.size() > 0) {
        	return list.get(0);
        }
        return null;
    }

}
