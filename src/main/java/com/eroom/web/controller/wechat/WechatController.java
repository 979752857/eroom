package com.eroom.web.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.wechat.MenuClickVo;
import com.eroom.web.service.cust.CustInfoWxService;
import com.eroom.web.service.wechat.WechatPlatformService;
import com.eroom.web.utils.util.StringUtil;
import com.eroom.web.utils.weixin.MessageUtil;
import com.eroom.web.utils.weixin.SignUtil;

@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseController {
    private final Log log = LogFactory.getLog(WechatController.class);

    @Autowired
    private WechatPlatformService wechatPlatformService;

    @Autowired
    private CustInfoWxService cmCustService;

    /**
     * 微信接入
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/entrance")
    public void customComplain(HttpServletRequest request, HttpServletResponse response) {
        try {
            String method = request.getMethod();
            if (method.endsWith("GET")) {
                this.doGet(request, response);
            } else if (method.endsWith("POST")) {
                this.doPost(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     * 
     * @throws Exception
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
        String respMessage = "";
        MenuClickVo requestObject = MessageUtil.parseObject(request);
        if (requestObject == null) {
            log.error("请求异常,未能得到请求参数.");
            return;
        }
        JSONObject json = (JSONObject) JSONObject.toJSON(requestObject);
        log.info("收到的请求消息：" + json);
        // 事件类消息
        if (MessageUtil.RequestMsgType.REQ_MESSAGE_TYPE_EVENT.equals(requestObject.getMsgType())) {
            // 订阅事件
            if (MessageUtil.EventType.EVENT_TYPE_SUBSCRIBE.equals(requestObject.getEvent())) {
                // TODO 订阅之后推送欢迎页.
                respMessage = wechatPlatformService.addCustAndEchoHello(requestObject.getFromUserName(),
                        requestObject.getToUserName());
                // 通过邀请人注册成为系统会员
                if (!StringUtil.isBlank(requestObject.getEventKey())
                        && !StringUtil.isBlank(requestObject.getTicket())) {
                    String tenantNo = StringUtil.getCenterStr(requestObject.getEventKey(), "_",
                            ",");
                    String openid = requestObject.getFromUserName();
                    String qrCode = requestObject.getTicket();
                    // 通过邀请人创建默认用户
                }
            }
            // 退订事件
            if (MessageUtil.EventType.EVENT_TYPE_UNSUBSCRIBE.equals(requestObject.getEvent())) {
                // 退订解绑关系
            }
            // 点击类事件
            if (MessageUtil.EventType.EVENT_TYPE_CLICK.equals(requestObject.getEvent())) {
                // 根据事件KEY来处理点击事物.;
                respMessage = wechatPlatformService.sortingClickEventByKey(requestObject);
            }
            // 跳转类事件
            if (MessageUtil.EventType.EVENT_TYPE_VIEW.equals(requestObject.getEvent())) {

            }
        } else {// 其他类型消息统一从这里回复 有业务之后再拆分
            if (MessageUtil.RequestMsgType.REQ_MESSAGE_TYPE_TEXT
                    .equals(requestObject.getMsgType())) {
                respMessage = wechatPlatformService.keywordReply(requestObject.getFromUserName(),
                        requestObject.getToUserName(), requestObject.getContent());
            }
            if (StringUtil.isBlank(respMessage)) {
                respMessage = wechatPlatformService.forwardCustomerService(
                        requestObject.getFromUserName(), requestObject.getToUserName());
            }
        }
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
