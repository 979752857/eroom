package com.eroom.web.controller.rent;

import java.util.List;

import javax.annotation.Resource;

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
     * 获取热门房源信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRoom")
    @ResponseBody
    public ResultVo getRoom() throws Exception {
        ResultVo result = new ResultVo();
        List<RoomRentVo> list = roomRentService.getRoomRent();
        result.setDatas(list);
        return result;
    }

}
