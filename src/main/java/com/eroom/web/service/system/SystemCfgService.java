package com.eroom.web.service.system;

import javax.annotation.Resource;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.entity.po.SystemCfg;
import com.eroom.web.entity.po.SystemParam;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Service;

import com.eroom.web.dao.system.SystemCfgDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemCfgService {

    @Resource
    private SystemCfgDao sysCfgDao;

    /**
     * 根据租户/表名/字段名获取参数配置
     * 
     * @param cfgType
     * @param cfgCode
     * @return
     * @throws Exception
     * @author tendy
     */
    public String getCfgValue(String cfgType, String cfgCode) throws Exception {
        return sysCfgDao.getCfgValue(cfgType, cfgCode);
    }

    /**
     * 获取js可缓存的配置信息
     */
    public Map<String, String> getBufferAllCfgValue() throws Exception {
        Map<String, String> map = new HashMap<>();
        List<SystemCfg> list = sysCfgDao.getAllCfgValue(SystemConstants.SystemCfg.state.CAN_BUFFER);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }

        for (SystemCfg param : list) {
            String field = param.getCfgType() + '.' + param.getCfgCode();
            map.put(field, param.getCfgValue());
        }
        return map;
    }

}
