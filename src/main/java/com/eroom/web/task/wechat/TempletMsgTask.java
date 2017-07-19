package com.eroom.web.task.wechat;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eroom.web.constants.WechatConstants;
import com.eroom.web.entity.po.WechatNotice;
import com.eroom.web.service.system.SystemBaseService;
import com.eroom.web.service.wechat.WechatNoticeService;
import com.eroom.web.service.wechat.WechatService;
import com.eroom.web.service.wechat.WechatTempletMsgService;
import com.eroom.web.utils.util.CollectionUtil;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;

@Component
public class TempletMsgTask {

	@Resource
	private WechatNoticeService wechatNoticeService;

	@Resource
	private WechatService WechatService;

	@Resource
	private SystemBaseService systemBaseService;

	@Resource
	private WechatTempletMsgService templetMsgService;

	/**
	 * 调用微信公众平台发送消息
	 */
	@Scheduled(cron = "0/15 * * * * ?")
	public void sendWechatMessage() {
		try {
			List<WechatNotice> l = wechatNoticeService.getNotSendNoticeList();

			if (!CollectionUtil.isEmpty(l)) {
				Date sysdate = DateUtil.getCurrentDate();

				for (WechatNotice notice : l) {
					try {
						WechatService.sendTemplate(notice.getNoticeContent());
						notice.setState(WechatConstants.WechatNotice.State.SEND_SUCCES);
					} catch (Exception e) {
						notice.setState(WechatConstants.WechatNotice.State.SEND_FAIL);
						String msg = e.getMessage();
						if (StringUtil.isBlank(msg)) {
							msg = "微信发送消息失败，消息ID为" + notice.getNoticeId();
						} else {
							if (msg.length() > 200) {
								msg.substring(0, 200);
							}
						}
						notice.setRemark(msg);
					}
					notice.setUpdateTime(sysdate);
					wechatNoticeService.updateWechatNotice(notice);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
