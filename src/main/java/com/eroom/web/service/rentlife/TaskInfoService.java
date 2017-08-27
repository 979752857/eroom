package com.eroom.web.service.rentlife;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.entity.po.TRoomTask;
import com.eroom.web.entity.vo.base.ResultVo;
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

	/**
	 * 获取任务信息
	 *
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTaskInfoByState(Long custId, String state, int curPage) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Long page = 0L;
		Long totle = taskInfoDao.countTaskInfoVoByState(custId, state);
		if(totle != null){
			page = totle/RentLifeConstants.LAST_TASK_DEFAULT_LIMIT;
			if(totle%RentLifeConstants.LAST_TASK_DEFAULT_LIMIT == 0){
				page -= 1;
			}
		}
	    List<TaskInfoVo> list = taskInfoDao.getTaskInfoVoByState(custId, state, RentLifeConstants.LAST_TASK_DEFAULT_LIMIT, curPage);
	    map.put("list", list);
	    map.put("totle", totle);
	    map.put("page", page);
	    map.put("curPage", curPage);
	    return map;
	}

	/**
	 * 任务完成
	 *
	 * @return
	 * @throws Exception
	 */
	public ResultVo updateTaskInfoFinish(Long custId, Long taskId) throws Exception {
		ResultVo result = new ResultVo();
		TRoomTask task = taskInfoDao.get(TRoomTask.class, taskId);
		if(task != null){
			task.setTaskState(RentLifeConstants.TaskInfo.TaskState.FINISH);
			taskInfoDao.update(task);
		}
	    return result;
	}

}
