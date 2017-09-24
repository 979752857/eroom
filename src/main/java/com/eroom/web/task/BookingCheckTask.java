package com.eroom.web.task;

import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.utils.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class BookingCheckTask {

	protected final Log logger = LogFactory.getLog(getClass());

	@Resource
	private RoomBookDao roomBookDao;


	/**
	 * 每天晚上12点检查用户预约是否到期
	 */
	@Scheduled(cron = "59 59 23 ? * *")
	public void checkBooking() {

		Date nowTime = DateUtil.getCurrentDate();
		try {
			roomBookDao.checkBooking(nowTime);
		} catch (Exception e) {
			logger.warn("定时检查用户预订失败："+e.toString());
		}

	}

	/**
	 * 每5分钟定时扫描数据库数据进行预约订单状态修改
	 */

	/**
	 * 每5分钟定时扫描数据库数据进行支付订单状态修改
	 */

	/**
	 * 定时扫描数据库数据进行租房订单状态修改
	 */

}
