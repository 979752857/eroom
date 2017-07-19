package com.eroom.web.service.system;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.system.SystemBaseDao;
import com.eroom.web.dao.system.SystemBaseExtDao;
import com.eroom.web.entity.po.SystemBase;
import com.eroom.web.entity.po.SystemBaseExt;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.HttpUtil;
import com.eroom.web.utils.util.StringUtil;

@Service
public class SystemBaseService {

    @Resource
    private SystemBaseDao systemBaseDao;
    
    @Resource
    private SystemBaseExtDao systemBaseExtDao;

    /**
     * 获取微信的apiTiket
     * @return
     * @throws Exception
     */
    public String getApiTiket() throws Exception{
    	SystemBase systemBase = systemBaseDao.getSystemBase();
    	if(systemBase != null){
    		String apiTicket = systemBase.getAccessToken();
    		if(StringUtil.isBlank(apiTicket) || DateUtil.getMinuteDif(DateUtil.getSysDate(), (Timestamp)systemBase.getAccessTokenTime()) > SystemConstants.Wechat.AccessToken.TIME_OUT){
    			String access_token = this.getBasisAccessToken();
                String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                        + access_token + "&&type=jsapi";
                String json = HttpUtil.httpReq(url, "", "GET");// 获取授权信息
                JSONObject js = JSONObject.parseObject(json);
                apiTicket = js.getString("ticket");
	            systemBase.setApiTicket(apiTicket);
	            systemBase.setApiTicketTime(DateUtil.getCurrentDate());
	            systemBaseDao.save(systemBase);
    		}
    		return systemBase.getApiTicket();
    	}else{
    		throw new BusinessException("没有获取到系统基本信息");
    	}
    }

    /**
     * 获取微信的AccessToken
     * @return
     * @throws Exception
     */
    public String getBasisAccessToken() throws Exception{
    	SystemBase systemBase = systemBaseDao.getSystemBase();
    	if(systemBase != null){
    		String accessToken = systemBase.getAccessToken();
    		if(StringUtil.isBlank(accessToken) || DateUtil.getMinuteDif(DateUtil.getSysDate(), (Timestamp)systemBase.getAccessTokenTime()) > SystemConstants.Wechat.AccessToken.TIME_OUT){
	            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
	                    + systemBase.getAppid() + "&secret=" + systemBase.getAppscret();
	            String json = HttpUtil.httpReq(url, "", "GET");// 获取授权信息
	            JSONObject js = JSONObject.parseObject(json);
	            accessToken = js.getString("access_token");
	            systemBase.setAccessToken(accessToken);
	            systemBase.setAccessTokenTime(DateUtil.getCurrentDate());
	            systemBaseDao.save(systemBase);
    		}
    		return systemBase.getAccessToken();
    	}else{
    		throw new BusinessException("没有获取到系统基本信息");
    	}
    }
    
    /**
     * 获取系统基础信息
     * @return
     * @throws Exception
     * @author tendy
     */
    public SystemBase getSystemBase() throws Exception{
        return systemBaseDao.getSystemBase();
    }
    
    /**
     * 获取系统基础信息扩展
     * @return
     * @throws Exception
     * @author tendy
     */
    public SystemBaseExt getSystemBaseExt() throws Exception{
        return systemBaseExtDao.getSystemBaseExt();
    }
}
