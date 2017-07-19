package com.eroom.web.controller.rent;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.rent.RoomRentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Resource
	private RoomRentService roomRentService;

	/**
	 * 获取热门房源信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getHot")
	@ResponseBody
	public ResultVo getHot(Long limit) throws Exception {
		ResultVo result = new ResultVo();
		if(limit == null || limit <= 0){
			limit = Long.valueOf(5);
		}
		List<RoomRentVo> list = roomRentService.getRoomRentHot(Integer.parseInt(limit.toString()));
		result.setDatas(list);
		return result;
	}

}
