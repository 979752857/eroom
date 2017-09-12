package com.eroom.web.controller.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.pay.PayOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/payorder")
public class PayOrderController extends BaseController {

    @Resource
    private PayOrderService payOrderService;

    /**
     * 获取待支付订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPayOrderWait")
    @ResponseBody
    public ResultVo getPayOrderWait() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<PayOrder> list = payOrderService.getPayOrderList(sessionVo .getCustId(), PayConstants.PayOrder.OrderState.WAITING);
        result.setDatas(list);
        return result;
    }

    /**
     * 获取已支付订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPayOrderFinish")
    @ResponseBody
    public ResultVo getPayOrderFinish() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<PayOrder> list = payOrderService.getPayOrderList(sessionVo .getCustId(), PayConstants.PayOrder.OrderState.FINISH);
        result.setDatas(list);
        return result;
    }

    /**
     * 获取全部支付订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getPayOrderAll")
    @ResponseBody
    public ResultVo getPayOrderAll() throws Exception {
        ResultVo result = new ResultVo();
        SessionVo sessionVo = this.getCustSession();
        List<PayOrder> list = payOrderService.getPayOrderList(sessionVo .getCustId(), null);
        result.setDatas(list);
        return result;
    }
}