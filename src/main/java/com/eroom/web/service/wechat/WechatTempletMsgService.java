package com.eroom.web.service.wechat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.service.cust.CustInfoWxService;

@Service
public class WechatTempletMsgService {

    @Resource
    private CustInfoWxService cmCustWxService;

    @Resource
    private WechatNoticeService wechatNoticeService;

    @Resource
    private WechatNoticeTempletService wechatNoticeTempletService;


}
