package com.eroom.web.service.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.pay.RentOrderDao;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentOrderService extends BaseService {

    @Resource
    private RentOrderDao rentOrderDao;

    /**
     * 获取租房订单列表
     *
     * @return
     * @throws Exception
     */
    public List<RentOrder> getRentOrderList(Long custRenterId) throws Exception {
        List<RentOrder> list = rentOrderDao.getRentOrderList(custRenterId, new String[]{PayConstants.RentOrder.RentOrderState.WAIT_PAY, PayConstants.RentOrder.RentOrderState.PAID}, PayConstants.RentOrder.RENT_ORDER_LIMIT);

        return list;
    }

    public List<RentOrder> getRentOrderList(Long custRenterId, String orderState) throws Exception {
        if (StringUtil.isBlank(orderState)) {
            throw new BusinessException("订单状态为空");
        }
        List<RentOrder> list = rentOrderDao.getRentOrderList(custRenterId, orderState, PayConstants.RentOrder.RENT_ORDER_LIMIT);

        return list;
    }

    /**
     * 保存提交得租房订单
     * @param rentOrder
     * @return
     */
    public RentOrder saveRentOrder(RentOrder rentOrder) throws Exception {
        //获取rentid

        if(!checkRentOrder(rentOrder)){
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }
        //设置订单状态为待支付状态
        rentOrder.setRentOrderState(PayConstants.RentOrder.RentOrderState.WAIT_PAY);
        rentOrder.setCreateTime(DateUtil.getCurrentDate());

        rentOrder = rentOrderDao.save(rentOrder);
        return rentOrder;
    }

    /**
     * 校验订单信息
     * @param rentOrder
     * @return
     */
    private boolean checkRentOrder(RentOrder rentOrder){
        boolean flag = true;
        if(rentOrder.getCustRenterId() == 0){
            flag = false;
        }
        if(rentOrder.getBedroomId() == 0){
            flag = false;
        }
        if(rentOrder.getLength() == 0){
            flag = false;
        }
        if(rentOrder.getRentId() == 0){
            flag = false;
        }
        if(rentOrder.getRentOrderId() == 0){
            flag = false;
        }
        if(rentOrder.getAmount() == null){
            flag = false;
        }
        if(rentOrder.getStartTime() == null){
            flag = false;
        }
        if(rentOrder.getEndTime() == null){
            flag = false;
        }
        if(rentOrder.getLateAmount() == null){
            flag = false;
        }
        if(rentOrder.getMortgageAmount() == null){
            flag = false;
        }
        if(rentOrder.getRentAmount() == null){
            flag = false;
        }
        if(rentOrder.getType() == null){
            flag = false;
        }
        return flag;
    }
}
