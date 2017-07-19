package com.eroom.web.service.wechat;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.eroom.web.entity.vo.wechat.MenuClickVo;
import com.eroom.web.service.system.SystemBaseService;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;
import com.eroom.web.utils.weixin.MessageUtil;
import com.eroom.web.utils.weixin.message.resp.BaseMessage;
import com.eroom.web.utils.weixin.message.resp.ImageMessage;
import com.eroom.web.utils.weixin.message.resp.Media;
import com.eroom.web.utils.weixin.message.resp.TextMessage;

@Service
public class WechatPlatformService {
    private static final Log log = LogFactory.getLog(WechatPlatformService.class);

    @Resource
    private SystemBaseService systemBaseService;

    // 关注页
    public String echoHelloPage(String fromUserName, String toUserName) throws Exception {
        String respMessage = null;
        // 默认返回的文本消息内容
        StringBuffer respContent = new StringBuffer();
        TextMessage textMessage = new TextMessage(fromUserName, toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
//        SysTenantAttr tenantAttr = tenantAttrService
//                .getSysTenantAttrByTenantNo(systemBase.getTenantNo());
//        String hint = tenantAttr.getWelcomeWord();
//        respContent.append(hint);
        textMessage.setContent(respContent.toString());
        respMessage = MessageUtil.textMessageToXml(textMessage);
        return respMessage;
    }

    // 关键字回复
    public String keywordReply(String fromUserName, String toUserName, String textContent) {
        return "";
    }

    // 转发多客服
    public String forwardCustomerService(String fromUserName, String toUserName) {
        String respMessage = "";
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setToUserName(fromUserName);
        baseMessage.setFromUserName(toUserName);
        baseMessage.setCreateTime(new Date().getTime());
        baseMessage.setMsgType(MessageUtil.ResponseMsgType.RESP_MESSAGE_TYPE_CUSTOMSERVICE);
        respMessage = MessageUtil.baseMessageToXml(baseMessage);
        return respMessage;
    }

    // 点击菜单请求分发
    public String sortingClickEventByKey(MenuClickVo request) throws Exception {
        String respMessage = "";
        String fromUserName = request.getFromUserName();
        String toUserName = request.getToUserName();
        String eventKey = request.getEventKey();
        log.info("!!===点击类事件请求,参数为：fromUserName:" + fromUserName + " | toUserName：" + toUserName
                + " | eventkey:" + eventKey);

        // 联系我们点击按钮(发送客服的图片)
        if (eventKey.startsWith(MessageUtil.BUTTON_CLICK_CONTACT_US)) {
            respMessage = this.echoContactUs(toUserName, fromUserName);
        }
        log.debug("查询结束，返回的结果是：" + respMessage);
        return respMessage;
    }

    // 点击联系我们菜单
    public String echoContactUs(String fromUserName, String toUserName) throws Exception {
        ImageMessage imgMsg = new ImageMessage();
        imgMsg.setFromUserName(fromUserName);
        imgMsg.setToUserName(toUserName);
        imgMsg.setCreateTime(DateUtil.getCurrentDate().getTime());
        imgMsg.setMsgType(MessageUtil.ResponseMsgType.RESP_MESSAGE_TYPE_IMAGE);

//        SysTenantAttr tenantAttr = tenantAttrService
//                .getSysTenantAttrByTenantNo(tenant.getTenantNo());
        String contactUs = ""; //tenantAttr.getContactUs();

        String mediaId = "";
        if (!StringUtil.isBlank(contactUs) && contactUs.contains(",")) {
            // 当前的客服图片序号
            int index = 1;
//            String key = SystemConstants.RedisKey.CONTACT_US_IMG_IDX;
//            String field = tenant.getTenantNo();

//            String indexStr = redisDao.hget(key, field);
//            if (!StringUtil.isBlank(indexStr)) {
//                index = Integer.valueOf(indexStr);
//            }

            String[] imgIds = contactUs.split(",");
            if (index >= imgIds.length) {
                index = 1;
                mediaId = imgIds[0];
            } else {
                mediaId = imgIds[index];
                index++;
            }

//            redisDao.hset(key, field, String.valueOf(index));
        } else {
            mediaId = contactUs;
        }

        // 暂无素材，后续有切换成多个图片轮流发送给客户
        Media media = new Media();
        media.setMediaId(mediaId);
        imgMsg.setImage(media);

        return MessageUtil.imageMessageToXml(imgMsg);
    }
}
