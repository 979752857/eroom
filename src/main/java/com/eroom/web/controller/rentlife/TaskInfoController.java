package com.eroom.web.controller.rentlife;

import java.util.List;

import javax.annotation.Resource;

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
	 * 获取近一个月任务信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAllTaskInfo")
	@ResponseBody
	public ResultVo getAllTaskInfo() throws Exception {
	    ResultVo result = new ResultVo();
	    SessionVo sessionVo = this.getCustSession();   
	    List<TaskInfoVo> list = taskInfoService.getMonthTaskInfoVo(sessionVo.getCustId());
	    result.setDatas(list);
	    return result;
	}

}
