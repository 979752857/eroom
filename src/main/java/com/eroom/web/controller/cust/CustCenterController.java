package com.eroom.web.controller.cust;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.CustRoomCollect;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.cust.CustCenterService;
import com.eroom.web.service.rent.RoomRentService;

@Controller
@RequestMapping("/custcenter")
public class CustCenterController extends BaseController {

    @Resource
    private RoomRentService roomRentService;

    @Resource
    private CustCenterService custCenterService;

    /**
     * 获取用户租期信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getCustRent")
    @ResponseBody
    public ResultVo getCustRent() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomRentVo> list = roomRentService.getRoomRentVoByCustId(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 获取用户最先到期租期信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getLastCustRent")
    @ResponseBody
    public ResultVo getLastCustRent() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        RoomRentVo roomRentVo = roomRentService.getLastRoomRentVoByCustId(sessionVo.getCustId());
        result.setDatas(roomRentVo);
        return result;
    }

    /**
     * 获取用户是否收藏房屋信息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getCollectByRentid")
    @ResponseBody
    public ResultVo getCollectByRentid(Long rentId) throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        CustRoomCollect collect = custCenterService.getCustRoomCollect(sessionVo.getCustId(),
                rentId);
        result.setDatas(collect);
        return result;
    }

    /**
     * 获取用户收藏房屋信息列表
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getCollect")
    @ResponseBody
    public ResultVo getCollect() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RoomRentVo> collect = custCenterService.getCustRoomCollect(sessionVo.getCustId());
        result.setDatas(collect);
        return result;
    }

    /**
     * 删除用户收藏
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteCollect")
    @ResponseBody
    public ResultVo deleteCollect(Long collectId) throws Exception {
        ResultVo result = new ResultVo();
        CustRoomCollect t = custCenterService.deleteCustRoomCollect(collectId);
        result.setDatas(t);
        return result;
    }

    /**
     * 添加用户收藏
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/addCollect")
    @ResponseBody
    public ResultVo addCollect(Long rentId) throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        CustRoomCollect collect = custCenterService.addCustRoomCollect(sessionVo.getCustId(),
                rentId);
        result.setDatas(collect);
        return result;
    }

}
