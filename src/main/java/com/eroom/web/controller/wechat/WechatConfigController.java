package com.eroom.web.controller.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.CmCustWx;
import com.eroom.web.entity.po.SystemBase;
import com.eroom.web.entity.po.SystemBaseExt;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.wechat.JsApiVo;
import com.eroom.web.service.cust.CustInfoWxService;
import com.eroom.web.service.system.SystemBaseService;
import com.eroom.web.service.wechat.WechatService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.StringUtil;
import com.eroom.web.utils.wechat.WetchatSign;

@Controller
@RequestMapping("/config")
public class WechatConfigController extends BaseController {

    @Resource
    private CustInfoWxService cmService;

    @Resource
    private SystemBaseService systemBaseService;

    @Resource
    private WechatService wechatService;

    @RequestMapping("/getWechatConfig")
    @ResponseBody
    public ResultVo getWechatConfig(Long userId, String url) throws Exception {
        ResultVo result = new ResultVo();
        if (userId == null) {
            throw new BusinessException("当前页面不能分享");
        }
        if (StringUtil.isBlank(url)) {
            throw new BusinessException("页面地址不存在不能分享");
        }

        if (userId != null) {
            CmCustWx cmCust = cmService.getCmCustByCustId(userId);
            if (ObjectUtils.isEmpty(cmCust)) {
                throw new BusinessException("当前用户暂不能分享");
            }
        }

        SystemBase systemBase = systemBaseService.getSystemBase();
        if (ObjectUtils.isEmpty(systemBase)) {
            throw new BusinessException("当前系统未生效");
        }

        String jsapi_ticket = wechatService.getApiTiket();
        if (StringUtil.isBlank(jsapi_ticket)) {
            throw new BusinessException("未获取到微信js安全密匙");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        // 生成js所需签名
        JsApiVo jsApi = WetchatSign.sign(jsapi_ticket, url);
        jsApi.setAppId(systemBase.getAppid());
        map.put("jsApi", jsApi);
        // 分享所需要的参数
         SystemBaseExt attr = systemBaseService.getSystemBaseExt();
         map.put("systemBaseExt", attr);
        result.setDatas(map);
        return result;
    }

}
