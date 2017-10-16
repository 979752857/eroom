package com.eroom.web.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eroom.web.entity.po.SystemCfg;
import com.eroom.web.utils.util.CollectionUtil;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;

@Repository
public class SystemCfgDao extends BaseDao {

    /**
     * 根据租户编码/配置分类/配置编码获取配置取值
     * 
     * @param cfgType
     * @param cfgCode
     * @return
     * @throws Exception
     * @author tendy
     */
    public String getCfgValue(String cfgType, String cfgCode) throws Exception {
        String hql = "select cfgValue from SystemCfg where cfgType = :cfgType and cfgCode =:cfgCode";
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("cfgType", cfgType);
        values.put("cfgCode", cfgCode);
        List<Object> list = this.getList(hql, values);
        if (list != null && list.size() > 0) {
            for (Object value : list) {
                return value.toString();
            }
        }
        return null;
    }

    /**
     * 根据状态获取配置信息
     * @param state
     * @return
     * @throws Exception
     * @author tendy
     */
    public List<SystemCfg> getAllCfgValue(String state) throws Exception {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> values = new HashMap<String, Object>();
        hql.append(" from SystemCfg where 1=1 ");
        if(!StringUtil.isBlank(state)){
            hql.append(" and state = :state ");
            values.put("state", state);
        }
        List<SystemCfg> list = this.getList(hql.toString(), values);
        if (!CollectionUtil.isEmpty(list)) {
            return list;
        }
        return null;
    }

}
