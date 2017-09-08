package com.eroom.web.controller.rent;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.service.rent.RoomBookOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/roombook/owner")
public class RoomBookOwnerController extends BaseController {

    @Resource
    private RoomBookOwnerService roomBookOwnerService;

    /**
     * 房东获取所有预约信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentBookAll")
    @ResponseBody
    public ResultVo getFdRentBookAll() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookOwnerService.getFdRoomBookAll(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 房东获取预约信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentBookApply")
    @ResponseBody
    public ResultVo getFdRentBookApply() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookOwnerService.getFdRoomBookApply(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 房东获取已完成预约信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentBookFinish")
    @ResponseBody
    public ResultVo getFdRentBookFinish() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookOwnerService.getFdRoomBookFinish(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 拒绝预约看房
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/refuseBook")
    @ResponseBody
    public ResultVo refuseBook(Long bookId) throws Exception {
        ResultVo result = new ResultVo();
        roomBookOwnerService.updateRefuseRoomBook(bookId);
        result.setDatas(null);
        result.setSuccess(true);
        return result;
    }

    /**
     * 授权预约看房
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/agreeBook")
    @ResponseBody
    public ResultVo agreeBook(Long bookId) throws Exception {
        ResultVo result = new ResultVo();
        roomBookOwnerService.updateAgreeRoomBook(bookId);
        result.setDatas(null);
        result.setSuccess(true);
        return result;
    }

}
