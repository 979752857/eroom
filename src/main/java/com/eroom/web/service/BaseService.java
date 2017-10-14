package com.eroom.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected final Log logger = LogFactory.getLog(getClass());

    protected void loginfo(String info, String... param){
        logger.info(strChange(info, param));
    }

    protected void logwarn(String info, String... param){
        logger.info(strChange(info, param));
    }

    protected void logerror(String info, String... param){
        logger.info(strChange(info, param));
    }

    protected String strChange(String info, String... param){
        if(param != null){
            for(String item : param){
                info = info.replaceFirst("\\{\\}", item);
            }
        }
        return info;
    }
}