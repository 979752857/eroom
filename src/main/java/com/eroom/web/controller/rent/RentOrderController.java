package com.eroom.web.controller.rent;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.pay.PayOrderService;
import com.eroom.web.service.pay.RentOrderService;
import com.eroom.web.service.rent.RoomRentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/rentorder")
public class RentOrderController extends BaseController {

    @Resource
    private RentOrderService rentOrderService;

    @Resource
    private PayOrderService payOrderService;

    @Resource
    private RoomRentService roomRentService;

    /**
     * 获取租房订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRentOrder")
    @ResponseBody
    public ResultVo getRentOrder() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<RentOrder> list = rentOrderService.getRentOrderList(sessionVo.getCustId());
        result.setDatas(list);
        return result;
    }

    /**
     * 获取有效租房订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getValidRentOrder")
    @ResponseBody
    public ResultVo getValidRentOrder(Long rentId) throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        RentOrder rentOrder = rentOrderService.getValidRentOrder(sessionVo.getCustId(), rentId);
        result.setDatas(rentOrder);
        return result;
    }

    /**
     * 提交租房订单
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveRentOrder")
    @ResponseBody
    public ResultVo saveRentOrder(Long rentId, String rentTimeType) throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        RentOrder rentOrder = rentOrderService.saveRentOrder(sessionVo.getCustId(), rentId, rentTimeType);
        //生成第一期订单       rentOrder没有获取到
        payOrderService.addPayRentOrder(rentOrder);
        result.setCode(SystemConstants.ExceptionMsg.SUCCESS_CODE);
        result.setDatas(rentOrder);
        return result;
    }
}