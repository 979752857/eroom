package com.eroom.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.eroom.web.constants.CustConstants;
import com.eroom.web.constants.ResponseConstants;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.exception.LoginTimeOutException;
import com.eroom.web.utils.exception.SystemException;

@Controller
public class BaseController {
	protected final Log logger = LogFactory.getLog(getClass());

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpSession session;

    /**
     * 异常统一封装处理
     * 
     * @author tendy
     */
    @ExceptionHandler
    @ResponseBody
    public ResultVo handleException(HttpServletRequest request, HttpServletResponse response,
            Exception ex) {

        ResultVo vo = new ResultVo();
        vo.setSuccess(false);

        if (ex instanceof BusinessException) {
            BusinessException s = (BusinessException) ex;
            vo.setMessage(s.getMessage());
            vo.setCode(s.getCode());
        } else if (ex instanceof LoginTimeOutException) {
            vo.setMessage("登录超时，请退出重新进入！");
            vo.setCode(ResponseConstants.LOGIN_TIME_OUT);
        } else if (ex instanceof SystemException) {
            SystemException b = (SystemException) ex;
            vo.setMessage("系统内部异常，请联系客服！");
            vo.setCode(b.getCode());
            logger.error(b.getMessage());
        } else {
            ex.printStackTrace();
            vo.setMessage("系统内部异常，请联系客服！");
            vo.setCode(ResponseConstants.SYSTEM_ERROR);
        }
        logger.error("响应内容：" + JSONObject.toJSONString(vo));

        return vo;
    }

    /**
     * 遇到异常向页面返回异常消息
     * 
     * @param response
     * @param errorMsg
     * @param errorCode
     * @author tendy
     */
    protected void outErrorMsg(HttpServletResponse response, String errorMsg, String errorCode) {
        try {
            if (errorMsg == null) {
                errorMsg = "";
            }
            byte[] bytes;
            if (errorCode != null) {
                JSONObject errorObj = new JSONObject();
                errorObj.put("message", errorMsg);
                errorObj.put("responseCode", errorCode);
                bytes = errorObj.toString().getBytes("UTF-8");
            } else {
                bytes = errorMsg.getBytes("UTF-8");
            }
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取工程的根路径
     * 
     * @param request
     * @return
     * @author tendy
     */
    public String getProjectRoot(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + path;
    }

    /**
     * 获取sessioni
     * 
     * @return
     * @throws Exception
     * @author zhangym
     */
    public SessionVo getCustSession() throws Exception {
        SessionVo vo = (SessionVo) session.getAttribute(CustConstants.CUST_SESSION);
        if (ObjectUtils.isEmpty(vo)) {
            throw new LoginTimeOutException();
        }
        return vo;
    }

    /**
     * 解析接口响应串为本地响应数据
     * 
     * @param response
     * @return
     * @author tendy
     */
    protected ResultVo parseResStr(String response) {
        JSONObject resObj = JSONObject.parseObject(response);
        ResultVo vo = new ResultVo();
        if (resObj.containsKey("retcode") && resObj.getString("retcode").equals("0000")) {
            vo.setSuccess(true);
        }
        vo.setMessage(resObj.getString("message"));
        return vo;
    }
}