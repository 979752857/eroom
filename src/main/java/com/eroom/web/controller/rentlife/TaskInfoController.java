package com.eroom.web.controller.rentlife;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.eroom.web.constants.RentLifeConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rentlife.TaskInfoVo;
import com.eroom.web.service.rentlife.TaskInfoService;

@Controller
@RequestMapping("/taskinfo")
public class TaskInfoController extends BaseController {

	@Resource
	private TaskInfoService taskInfoService;

	/**
	 * 获取最新任务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskInfo")
	@ResponseBody
	public ResultVo getTaskInfo() throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		List<TaskInfoVo> list = taskInfoService.getLastTaskInfoVo(sessionVo.getCustId());
		result.setDatas(list);
		return result;
	}

	/**
	 * 任务完成
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/taskFinish")
	@ResponseBody
	public ResultVo taskFinish(Long taskId) throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		result = taskInfoService.updateTaskInfoFinish(sessionVo.getCustId(), taskId);
		return result;
	}
	
	/**
	 * 获取近一个月任务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAllTaskInfoMonth")
	@ResponseBody
	public ResultVo getAllTaskInfoMonth() throws Exception {
	    ResultVo result = new ResultVo();
	    SessionVo sessionVo = this.getCustSession();   
	    List<TaskInfoVo> list = taskInfoService.getMonthTaskInfoVo(sessionVo.getCustId());
	    result.setDatas(list);
	    return result;
	}

	/**
	 * 获取全部任务信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAllTaskInfo")
	@ResponseBody
	public ResultVo getAllTaskInfo(int page) throws Exception {
	    ResultVo result = new ResultVo();
	    SessionVo sessionVo = this.getCustSession();
		Map<String, Object> map = taskInfoService.getTaskInfoByState(sessionVo.getCustId(), null, page);
	    result.setDatas(map);
	    return result;
	}

	/**
	 * 获取待完成任务
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskInfoWait")
	@ResponseBody
	public ResultVo getTaskInfoWait(int page) throws Exception {
	    ResultVo result = new ResultVo();
	    SessionVo sessionVo = this.getCustSession();
		Map<String, Object> map = taskInfoService.getTaskInfoByState(sessionVo.getCustId(), RentLifeConstants.TaskInfo.TaskState.WAITTING, page);
	    result.setDatas(map);
	    return result;
	}

	/**
	 * 获取待完成任务
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskInfoFinish")
	@ResponseBody
	public ResultVo getTaskInfoFinish(int page) throws Exception {
	    ResultVo result = new ResultVo();
	    SessionVo sessionVo = this.getCustSession();
		Map<String, Object> map = taskInfoService.getTaskInfoByState(sessionVo.getCustId(), RentLifeConstants.TaskInfo.TaskState.FINISH, page);
	    result.setDatas(map);
	    return result;
	}

	/**
	 * 任务评价
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskInfoAssess")
	@ResponseBody
	public ResultVo getTaskInfoAssess(Long taskId, String message, int level) throws Exception {
	    ResultVo result = new ResultVo();
	    SessionVo sessionVo = this.getCustSession();
	    List<TaskInfoVo> list = null; //taskInfoService.getMonthTaskInfoVo(sessionVo.getCustId());
	    result.setDatas(list);
	    return result;
	}

}
