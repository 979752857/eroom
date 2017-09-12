package com.eroom.web.controller.rentlife;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.RoomMessage;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rentlife.RoomMessageVo;
import com.eroom.web.service.rentlife.RoomMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Resource
	private RoomMessageService roomMessageService;

	/**
	 * 获取最新留言信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMessage")
	@ResponseBody
	public ResultVo getRoomDetail() throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		List<RoomMessageVo> list = roomMessageService.getLastRoomMessage(sessionVo.getCustId());
		result.setDatas(list);
		return result;
	}

	/**
     * 获取所有留言信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAllMessage")
    @ResponseBody
    public ResultVo getAllMessage() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomMessageVo> list = roomMessageService.getAllRoomMessage(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

	/**
     * 添加留言信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/addMessage")
    @ResponseBody
    public ResultVo addMessage(String message) throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        RoomMessage tRoomMessage = roomMessageService.addRoomMessage(sessionVo.getCustId(), message);
        result.setDatas(tRoomMessage);
        return result;
    }
}
