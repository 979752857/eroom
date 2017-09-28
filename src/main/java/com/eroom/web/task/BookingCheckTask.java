package com.eroom.web.task;

import com.eroom.web.constants.TaskRunningConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.task.TaskRunningDao;
import com.eroom.web.entity.po.TaskRunning;
import com.eroom.web.utils.util.CollectionUtil;
import com.eroom.web.utils.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class BookingCheckTask {

	protected final Log logger = LogFactory.getLog(getClass());

	@Resource
	private RoomBookDao roomBookDao;

	@Resource
	private TaskRunningDao taskRunningDao;


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
	 * 每5分钟定时扫描任务表查找需要更新的数据
	 */
	@Scheduled(cron = "59 59 23 ? * *")
	public void checkTaskRunnning(){
		List<TaskRunning> list = null;
		try {
			list = taskRunningDao.getTaskRunningList();
		} catch (Exception e) {
			logger.error("BookingCheckTask.checkBookingState  定时任务执行异常 error:"+e.toString());
		}
		if(CollectionUtil.isEmpty(list)){
			return;
		}
		for(TaskRunning item : list){
			try {
				int num = taskRunningDao.updateDataBySql(item);
				if(num > 0){
					item.setState(TaskRunningConstants.State.FINISH);
				}else{
					item.setState(TaskRunningConstants.State.THROW);
				}
				taskRunningDao.update(item);
				logger.info("BookingCheckTask.checkBookingState  单个任务执行成功  taskRunning:"+item.toString());
			} catch (Exception e) {
				item.setState(TaskRunningConstants.State.FAIL);
				try {
					taskRunningDao.update(item);
				} catch (Exception e1) {
					e1.printStackTrace();
					logger.error("BookingCheckTask.checkBookingState  更新定时任务异常  taskRunning:"+item.toString()+"   error:"+e.toString());
				}
				e.printStackTrace();
				logger.error("BookingCheckTask.checkBookingState  单个任务执行异常  taskRunning:"+item.toString()+"   error:"+e.toString());
			}
		}
	}

	/**
	 * 每5分钟定时扫描数据库数据进行支付订单状态修改
	 */

	/**
	 * 定时扫描数据库数据进行租房订单状态修改
	 */

	/**
	 * 每5分钟定时扫描数据库数据进行预约订单状态修改
	 */

}
