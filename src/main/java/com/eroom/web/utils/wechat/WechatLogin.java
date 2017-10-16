package com.eroom.web.utils.wechat;

import com.eroom.web.utils.util.DateUtil;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class WechatLogin {

    public static ConcurrentHashMap<String, Date> loginMap = new ConcurrentHashMap<>();

    public static boolean checkLogin(String code){
        boolean flag = true;

        if(loginMap.get(code) == null){
            loginMap.put(code, new Date());
        }else{
            Date time = loginMap.get(code);
            Date now = new Date();
            if(now.before(DateUtil.getOffsetSecondsDate(time, 180))){
                flag = false;
            }else{
                loginMap.put(code, now);
            }
        }

        return flag;
    }

    public static void delete(String code){
        loginMap.remove(code);
    }
}
