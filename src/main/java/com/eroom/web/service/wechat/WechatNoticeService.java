package com.eroom.web.service.wechat;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.WechatConstants;
import com.eroom.web.dao.wechat.WechatNoticeDao;
import com.eroom.web.entity.po.WechatNotice;
import com.eroom.web.utils.exception.SystemException;

@Service
public class WechatNoticeService {

    @Resource
    private WechatNoticeDao wechatNoticeDao;

    /**
     * 保存微信消息
     * 
     * @param notice
     * @throws Exception
     * @author yicj
     */
    public void saveWechatNotice(WechatNotice notice) throws Exception {
        if (notice == null) {
            throw new SystemException("微信消息对象不存在");
        }

        wechatNoticeDao.save(notice);
    }

    /**
     * 保存微信消息
     * 
     * @param notice
     * @throws Exception
     * @author yicj
     */
    public void updateWechatNotice(WechatNotice notice) throws Exception {
        if (notice == null) {
            throw new SystemException("微信消息对象不存在");
        }

        wechatNoticeDao.update(notice);
    }

    /**
     * 获取未发送的消息列表
     * 
     * @return
     * @throws Exception
     * @author yicj
     */
    public List<WechatNotice> getNotSendNoticeList() throws Exception {
        int state = WechatConstants.WechatNotice.State.NOT_SEND;

        return wechatNoticeDao.getWechatNoticeList(state);
    }

}
