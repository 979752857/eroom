package com.eroom.web.controller.rent;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rent.RoomDetailVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.rent.RoomRentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/roomrent")
public class RoomRentController extends BaseController {

    @Resource
    private RoomRentService roomRentService;

    /**
     * 获取出租中的房源
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentingRentId")
    @ResponseBody
    public ResultVo getRentingRentId(Long rentId) throws Exception {
        ResultVo result = new ResultVo();
        RoomDetailVo vo = roomRentService.getRoomDetailVoByRentId(rentId);
        result.setDatas(vo);
        return result;
    }

    /**
     * 获取房东出租中的房源
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFdRenting")
    @ResponseBody
    public ResultVo getRenting() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomRentVo> list = roomRentService.getFdRoomRentVoRenting(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 获取房东已出租的房源
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFdRented")
    @ResponseBody
    public ResultVo getRented() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomRentVo> list = roomRentService.getFdRoomRentVoRented(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 获取房东所有的房源
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFdAll")
    @ResponseBody
    public ResultVo getAll() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomRentVo> list = roomRentService.getFdRoomRentVoAll(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

}
