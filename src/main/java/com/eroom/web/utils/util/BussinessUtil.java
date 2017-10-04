package com.eroom.web.utils.util;

public class BussinessUtil {

    /**
     * 校验入参是否为空
     * @param param
     * @return
     */
    public static boolean checkParam(String... param){
        boolean flag = true;
        for(String item : param){
            if(StringUtil.isBlank(item)){
                flag = false;
                break;
            }
        }
        return flag;
    }

}
