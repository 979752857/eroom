package com.eroom.web.service.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.dao.system.SystemCfgDao;

@Service
public class SystemCfgService {

    @Resource
    private SystemCfgDao sysCfgDao;

    /**
     * 根据租户/表名/字段名获取参数配置
     * 
     * @param tenantNo
     * @param paramType
     * @param paramSubType
     * @return
     * @throws Exception
     * @author tendy
     */
    public String getCfgValue(String cfgType, String cfgCode) throws Exception {
        return sysCfgDao.getCfgValue(cfgType, cfgCode);
    }

}
