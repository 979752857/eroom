package com.eroom.web.controller.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.pay.PayOrderService;
import com.eroom.web.service.pay.RentOrderService;
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
}