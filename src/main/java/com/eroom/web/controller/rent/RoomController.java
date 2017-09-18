package com.eroom.web.controller.rent;

import java.util.List;

import javax.annotation.Resource;

import com.eroom.web.entity.bo.RoomRentBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.rent.RoomRentService;

@Controller
@RequestMapping("/room")
public class RoomController extends BaseController {

    @Resource
    private RoomRentService roomRentService;

    /**
     * 获取房源信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoom")
    @ResponseBody
    public ResultVo getRoom(RoomRentBo roomRentBo) throws Exception {
        ResultVo result = new ResultVo();
        List<RoomRentVo> list = roomRentService.getRoomRent(roomRentBo);
        result.setDatas(list);
        return result;
    }

    /**
     * 获取房源信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoomPage")
    @ResponseBody
    public ResultVo getRoom(int page) throws Exception {
        ResultVo result = new ResultVo();
        List<RoomRentVo> list = roomRentService.getRoomRent(null, page);
        result.setDatas(list);
        return result;
    }

}
