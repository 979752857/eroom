package com.eroom.web.controller.rent;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.rent.RoomDetailVo;
import com.eroom.web.service.rent.RoomDetailService;

@Controller
@RequestMapping("/roomdetail")
public class RoomDetailController extends BaseController {

	@Resource
	private RoomDetailService roomDetailService;

	/**
	 * 获取房源详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoomDetail")
	@ResponseBody
	public ResultVo getRoomDetail(Long rentId) throws Exception {
		ResultVo result = new ResultVo();
		RoomDetailVo vo = roomDetailService.getRoomDetail(rentId);
		result.setDatas(vo);
		return result;
	}

}
