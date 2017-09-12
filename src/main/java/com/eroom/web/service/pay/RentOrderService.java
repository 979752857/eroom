package com.eroom.web.service.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.dao.pay.RentOrderDao;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

}
