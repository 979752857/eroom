package com.eroom.web.service.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.dao.pay.PayOrderDao;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.service.BaseService;
import com.eroom.web.service.rent.RoomRentService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PayOrderService extends BaseService {

    @Resource
    private PayOrderDao payOrderDao;

    @Resource
    private RoomRentService roomRentService;

    /**
     * 支付租住订单、订单生成定时任务定时执行
     * @param custRenterId
     * @param payOrderId
     * @throws Exception
     */
    public void payRentOrder(Long custRenterId, Long payOrderId) throws Exception {
        PayOrder payOrder = payOrderDao.get(PayOrder.class, payOrderId);
        //支付修改数据

        //下架房源信息
        roomRentService.updateRoomRentPaid(payOrder.getRentId());
    }

    /**
     * 获取最新留言信息
     *
     * @return
     * @throws Exception
     */
    public List<PayOrder> getPayOrderList(Long custRenterId, String orderState) throws Exception {
        if(StringUtil.isBlank(orderState)){
            throw new BusinessException("订单状态为空");
        }
        List<PayOrder> list = null;
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
