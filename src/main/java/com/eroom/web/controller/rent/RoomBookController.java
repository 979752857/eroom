package com.eroom.web.controller.rent;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.service.rent.RoomBookService;

@Controller
@RequestMapping("/roombook")
public class RoomBookController extends BaseController {

    @Resource
    private RoomBookService roomBookService;

    /**
     * 获取预约信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentBookApply")
    @ResponseBody
    public ResultVo getRentBookApply() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookService.getRoomBookApply(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 房东获取预约信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFdRentBookApply")
    @ResponseBody
    public ResultVo getFdRentBookApply() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookService.getFdRoomBookApply(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 获取已完成预约信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentBookFinish")
    @ResponseBody
    public ResultVo getRentBookFinish() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookService.getRoomBookFinish(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 房东获取已完成预约信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFdRentBookFinish")
    @ResponseBody
    public ResultVo getFdRentBookFinish() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookService.getFdRoomBookFinish(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 获取所有预约信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentBookAll")
    @ResponseBody
    public ResultVo getRentBookAll() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookService.getRoomBookAll(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 房东获取所有预约信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getFdRentBookAll")
    @ResponseBody
    public ResultVo getFdRentBookAll() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomBookVo> list = roomBookService.getFdRoomBookAll(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 申请预约看房(有效期一个小时)
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/applyBook")
    @ResponseBody
    public ResultVo applyBook(Long rentId, Date time) throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        roomBookService.addApplyBook(sessionVo.getCustId(), rentId, time);
        result.setDatas(null);
        result.setSuccess(true);
        return result;
    }

    /**
     * 取消预约看房
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/cancelBook")
    @ResponseBody
    public ResultVo cancelBook(Long bookId) throws Exception {
        ResultVo result = new ResultVo();
        roomBookService.deleteRoomBook(bookId);
        result.setDatas(null);
        result.setSuccess(true);
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
        roomBookService.updateRefuseRoomBook(bookId);
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
        roomBookService.updateAgreeRoomBook(bookId);
        result.setDatas(null);
        result.setSuccess(true);
        return result;
    }

}
