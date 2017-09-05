package com.eroom.web.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
