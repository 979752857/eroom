package com.eroom.web.service.rentlife;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.RentLifeConstants;
import com.eroom.web.dao.rentlife.RoomTaskDao;
import com.eroom.web.entity.vo.rentlife.TaskInfoVo;
import com.eroom.web.utils.util.DateUtil;

@Service
public class TaskInfoService {

	@Resource
	private RoomTaskDao taskInfoDao;

	/**
	 * 获取最新任务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TaskInfoVo> getLastTaskInfoVo(Long custId) throws Exception {
	    List<TaskInfoVo> list = taskInfoDao.getLastTaskInfoVo(custId, RentLifeConstants.LAST_TASK_MESSAGE_LIMIT);
		return list;
	}
	
	/**
	 * 获取近一个月任务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TaskInfoVo> getMonthTaskInfoVo(Long custId) throws Exception {
	    List<TaskInfoVo> list = taskInfoDao.getMonthTaskInfoVo(custId, DateUtil.getOffsetMonthsTime(DateUtil.getSysDate(), -1));
	    return list;
	}

}
