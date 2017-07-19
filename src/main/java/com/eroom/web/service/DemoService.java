package com.eroom.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.exception.LoginTimeOutException;
import com.eroom.web.utils.exception.SystemException;

@Service
public class DemoService {
    private Log log = LogFactory.getLog(getClass());

    public String demo(int flag) throws Exception {
        if (flag == 1) {// 拦截器处理返回“该商品不存在！”
            log.error("根据ID[5]没有找到相应的商品ID！"); // 此处需要打印具体异常日志，给开发人员排查故障时查看
            throw new BusinessException("该商品不存在！");// 此处的内容会反馈到客户层面
        }
        if (flag == 2) {// 拦截器处理反馈到客户层面“系统异常...”
            // log可不打印，拦截器处会打印异常里的内容：从缓存获取的XXX参数为空！
            throw new SystemException("从缓存获取的XXX参数为空！");
        }
        if (flag == 3) {// 拦截器处理返回“登录超时，请退出重新进入！”
            // log可不打印，拦截器已处理
            throw new LoginTimeOutException();
        }
        if (flag == 4)// 拦截器处理返回“系统异常...”
            throw new Exception("系统bug");
        return "hello world";
    }
}