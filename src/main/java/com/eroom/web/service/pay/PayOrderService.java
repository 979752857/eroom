package com.eroom.web.service.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.dao.pay.PayOrderDao;
import com.eroom.web.entity.po.TPayOrder;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayOrderService extends BaseService {

    @Resource
    private PayOrderDao payOrderDao;

    /**
     * 获取最新留言信息
     *
     * @return
     * @throws Exception
     */
    public List<TPayOrder> getPayOrderList(Long custRenterId, String orderState) throws Exception {
        if(StringUtil.isBlank(orderState)){
            throw new BusinessException("订单状态为空");
        }
        List<TPayOrder> list = null;
        if(PayConstants.PayOrder.OrderState.WAITING.equals(orderState)){
            list = payOrderDao.getTPayOrderList(custRenterId, orderState, null);
        }else if(PayConstants.PayOrder.OrderState.FINISH.equals(orderState)){
            list = payOrderDao.getTPayOrderList(custRenterId, orderState, null);
        }else{
            list = payOrderDao.getTPayOrderList(custRenterId, orderState, PayConstants.PayOrder.ORDER_ALL_LIMIT);
        }
        return list;
    }

}
