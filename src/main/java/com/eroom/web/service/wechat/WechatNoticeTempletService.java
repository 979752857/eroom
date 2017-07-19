package com.eroom.web.service.wechat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.dao.wechat.WechatNoticeTempletDao;
import com.eroom.web.entity.po.WechatNoticeTemplet;
import com.eroom.web.utils.exception.SystemException;
import com.eroom.web.utils.util.StringUtil;

@Service
public class WechatNoticeTempletService {

    @Resource
    private WechatNoticeTempletDao wechatNoticeTempletDao;

    public WechatNoticeTemplet getWechatNoticeTemplet(String tenantNo, String templetType)
            throws Exception {
        if (StringUtil.isBlank(tenantNo)) {
            throw new SystemException("租户编码不能为空");
        }

        if (StringUtil.isBlank(templetType)) {
            throw new SystemException("模板类型不能为空");
        }

        return wechatNoticeTempletDao.getWechatNoticeTemplet(tenantNo, templetType);
    }

}
