package com.eroom.web.utils.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONObject;
import com.eroom.web.utils.exception.BusinessException;
import com.zaxxer.hikari.HikariConfig;

public final class ConfigTool {

    private ConfigTool() {

    }

    public static HikariConfig getDBConf(String dataSourceName) {
        String data="";
        try {
            String encoding = "UTF-8";
            String jdbcName = "jdbc.json";
            String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            /*---------------------------------------*/
            //判断是否是idea单元测试
            if(filePath.indexOf("classes/test/") > 0){
                filePath = filePath.substring(0, filePath.indexOf("classes/test/"));
                filePath += "resources/main/";
            }
            /*---------------------------------------*/
            filePath += jdbcName;
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                // 读取文件内容
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式

                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    lineTxt = lineTxt.trim();
                    if (!lineTxt.equals("\r") && !lineTxt.equals("\n") && !lineTxt.equals("\t")
                            && !lineTxt.equals(" ")) {
                        data += lineTxt;
                    }

                }
                read.close();
            } else {
                System.out.println("找不到数据源配置文件");
            }
        } catch (Exception e) {
            System.out.println("数据源配置文件内容有错");
            e.printStackTrace();
        }
        if (StringUtil.isBlank(data)) {
            throw new BusinessException("cann't get database conf from jdbc.json");
        }
        JSONObject dbConfJson = JSONObject.parseObject(data);
        JSONObject confObject = (JSONObject) dbConfJson.get(dataSourceName);
        if (confObject == null) {
            throw new BusinessException("cann't get database config info of dataSourceName["
                    + dataSourceName + "]");
        }
        HikariConfig dbconf = JSONObject.toJavaObject(confObject, HikariConfig.class);
        return dbconf;
    }

}
