package com.eroom.web.service.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.pay.PayOrderDao;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.service.BaseService;
import com.eroom.web.service.rent.RoomRentService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
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
     * 获取用户支付订单
     * @param custId
     * @param rentOrderId
     * @throws Exception
     */
    public PayOrder getPayRentOrder(Long custId, Long rentOrderId) throws Exception {

        PayOrder payOrder = payOrderDao.getWaitPayOrder(custId, rentOrderId);

        return payOrder;
    }

    public PayOrder addPayRentOrder(RentOrder rentOrder) throws Exception {
        if(rentOrder == null || rentOrder.getRentOrderId() == 0){
            logger.info("rentOrder为空或者rentOrderId为0:"+rentOrder.toString());
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }
        //生成第一期支付订单
        PayOrder payOrder = new PayOrder();
        payOrder.setRentOrderId(rentOrder.getRentOrderId());
        payOrder.setStartTime(rentOrder.getStartTime());
        payOrder.setEndTime(getNextEndTime(rentOrder.getStartTime(), rentOrder.getRentTimeType()));
        payOrder.setLength(DateUtil.getDaysBetween(DateUtil.getDateString(payOrder.getStartTime(), DateUtil.YYYYMMDD), DateUtil.getDateString(payOrder.getEndTime(), DateUtil.YYYYMMDD)));
        payOrder.setLateAmount(rentOrder.getLateAmount());
        payOrder.setMortgageAmount(rentOrder.getMortgageAmount());
        payOrder.setRentAmount(rentOrder.getRentAmount());
        BigDecimal totleAmount = rentOrder.getRentAmount().add(rentOrder.getMortgageAmount()).add(rentOrder.getLateAmount());
        payOrder.setAmount(totleAmount);
        payOrder.setBedroomId(rentOrder.getBedroomId());
        payOrder.setCustRenterId(rentOrder.getCustRenterId());
        payOrder.setRentId(rentOrder.getRentId());
        payOrder.setRoomId(rentOrder.getRoomId());
        payOrder.setCreateTime(DateUtil.getCurrentDate());
        payOrder.setPayOrderState(PayConstants.PayOrder.OrderState.WAITING);
        logger.info("RentOrderService.saveRentOrder  payOrder:"+payOrder.toString());
        payOrder = payOrderDao.save(payOrder);
        return payOrder;
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

    private Date getNextEndTime(Date startTime, String rentTimeType){
        Date endTime = null;

        //判断租期类型设置到期时间
        if(RoomConstants.RoomRentSet.RentTimeType.RENT_YEAR.equals(rentTimeType)){
            endTime = DateUtil.getOffsetMonthsTime(DateUtil.getTimestamp(startTime), 1);
        }else if(RoomConstants.RoomRentSet.RentTimeType.RENT_MOUNTH.equals(rentTimeType)){
            endTime = DateUtil.getOffsetMonthsTime(DateUtil.getTimestamp(startTime), 1);
        }else if(RoomConstants.RoomRentSet.RentTimeType.RENT_WEEK.equals(rentTimeType)){
            endTime = DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(startTime), 7);
        }else if(RoomConstants.RoomRentSet.RentTimeType.RENT_THREEDAY.equals(rentTimeType)){
            endTime = DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(startTime), 3);
        }else{
            throw new BusinessException(SystemConstants.ExceptionMsg.SYS_ERROR_EXCEPTION_MSG);
        }

        return endTime;
    }
}
