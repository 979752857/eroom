package com.eroom.web.controller.pay;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.service.pay.PayDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/payorder")
public class PayOrderController extends BaseController {

    @Resource
    private PayDetailService payDetailService;

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

        return result;
    }

    /**
     * 支付订单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/payOrder")
    @ResponseBody
    public ResultVo payOrder() throws Exception {
        ResultVo result = new ResultVo();

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

        return result;
    }
}