package com.eroom.web.service.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.pay.PayOrderDao;
import com.eroom.web.dao.pay.RentOrderDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.dao.rent.RoomRentSetDao;
import com.eroom.web.dao.room.BedroomInfoDao;
import com.eroom.web.dao.room.RoomInfoDao;
import com.eroom.web.entity.po.*;
import com.eroom.web.entity.vo.rent.RentOrderVo;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.CollectionUtil;
import com.eroom.web.utils.util.DateUtil;
import com.eroom.web.utils.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class RentOrderService extends BaseService {

    @Resource
    private RentOrderDao rentOrderDao;

    @Resource
    private RoomRentDao roomRentDao;

    @Resource
    private RoomInfoDao roomInfoDao;

    @Resource
    private BedroomInfoDao bedroomInfoDao;

    @Resource
    private RoomRentSetDao roomRentSetDao;

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

    /**
     * 获取租房订单列表
     *
     * @return
     * @throws Exception
     */
    public List<RentOrder> getPaidRentOrderList(Long custRenterId) throws Exception {
        List<RentOrder> list = rentOrderDao.getRentOrderList(custRenterId, PayConstants.RentOrder.RentOrderState.PAID, PayConstants.RentOrder.RENT_ORDER_LIMIT);
        return list;
    }

    /**
     * 获取最近到期租房订单
     *
     * @return
     * @throws Exception
     */
    public RentOrder getLastRentOrderList(Long custRenterId) throws Exception {
        RentOrder rentOrder = rentOrderDao.getLastRentOrderList(custRenterId);
        return rentOrder;
    }

    /**
     * 获取用户的租房合同订单信息
     * @param custRenterId
     * @param orderState
     * @return
     * @throws Exception
     */
    public List<RentOrderVo> getRentOrderVoList(Long custRenterId, String orderState) throws Exception {
        List<RentOrderVo> voList = new ArrayList<>();
        List<RentOrder> list = rentOrderDao.getRentOrderList(custRenterId, orderState, PayConstants.RentOrder.RENT_ORDER_LIMIT);
        if(!CollectionUtil.isEmpty(list)){
            for(RentOrder rentOrder : list){
                RentOrderVo vo = new RentOrderVo();
                vo.setRentOrderId(rentOrder.getRentOrderId());
                vo.setRentId(rentOrder.getRentId());
                vo.setRoomId(rentOrder.getRoomId());
                vo.setBedroomId(rentOrder.getBedroomId());
                vo.setCustRenterId(rentOrder.getCustRenterId());
                vo.setAmount(rentOrder.getAmount());
                vo.setCreateTime(rentOrder.getCreateTime());
                vo.setUpdateTime(rentOrder.getUpdateTime());
                vo.setRentOrderState(rentOrder.getRentOrderState());
                vo.setRemark(rentOrder.getRemark());
                vo.setStartTime(rentOrder.getStartTime());
                vo.setEndTime(rentOrder.getEndTime());
                vo.setLength(rentOrder.getLength());
                vo.setLateAmount(rentOrder.getLateAmount());
                vo.setRentAmount(rentOrder.getRentAmount());
                vo.setMortgageAmount(rentOrder.getMortgageAmount());
                vo.setPayType(rentOrder.getPayType());
                vo.setRentSetId(rentOrder.getRentSetId());
                vo.setRentType(rentOrder.getRentType());
                vo.setRentTimeType(rentOrder.getRentTimeType());
                vo.setType(rentOrder.getType());
                vo.setPayPhase(rentOrder.getPayPhase());
                vo.setTotlePhase(rentOrder.getTotlePhase());
                vo.setPaidAmount(rentOrder.getPaidAmount());
                vo.setPaidEndTime(rentOrder.getPaidEndTime());
                RoomInfo roomInfo = roomInfoDao.get(RoomInfo.class, vo.getRoomId());
                BedroomInfo bedroomInfo = bedroomInfoDao.get(BedroomInfo.class, vo.getBedroomId());
                if(roomInfo == null || bedroomInfo == null){
                    logger.error("RentOrderService.getRentOrderVoList没有获取到房屋基本信息  rentOrder:"+rentOrder.toString());
                    throw new BusinessException(SystemConstants.ExceptionMsg.SYS_ERROR_EXCEPTION_MSG);
                }
                vo.setBedroomImageUrl(bedroomInfo.getImageUrl());
                vo.setRoomImageUrl(roomInfo.getImageUrl());
                vo.setName(roomInfo.getName());
                vo.setRoomType(roomInfo.getRoomType());
                vo.setSpace(bedroomInfo.getSpace());
                vo.setDecorate(bedroomInfo.getDecorate());
                vo.setAddress(roomInfo.getAddress());
                voList.add(vo);
            }
        }
        return voList;
    }

    /**
     * 保存提交得租房订单
     * @return
     */
    public RentOrder saveRentOrder(Long custId, Long rentId, String rentTimeType) throws Exception {
        if(rentId == null || rentTimeType == null || custId == null){
            logger.error("RentOrderService.saveRentOrder  custId:"+custId+"  rentId:"+rentId+"   rentTimeType:"+rentTimeType);
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }
        //获取rentid
        RoomRent roomRent = roomRentDao.get(RoomRent.class, rentId);
        if(roomRent == null){
            logger.error("RentOrderService.saveRentOrder  roomRent为空 custId:"+custId+"  rentId:"+rentId+"   rentTimeType:"+rentTimeType);
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }

        //校验该用户是否存在此租房订单
        RentOrder rentOrder = getValidRentOrder(custId, rentId);
        if(null != rentOrder){      //该用户已经存在有效的租房订单
            logger.error("该用户已经存在有效的租房订单"+rentOrder.toString());
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }

        //生成租房订单的租住时间
        Date startTime = null;
        Date endTime = null;
        //通过租期类型设置付房租的次数（年租12次，月租1次、周租1次、三日租1次）
        int payNum = 1;
        String startTimestr = DateUtil.getDateString(DateUtil.getCurrentDate(), DateUtil.YYYY_MM_DD)+" 12:00:00";
        startTime = DateUtil.getDate(startTimestr, DateUtil.YYYY_MM_DD_HH_MM_SS);
        //判断租期类型设置到期时间
        if(RoomConstants.RoomRentSet.RentTimeType.RENT_YEAR.equals(rentTimeType)){
            endTime = DateUtil.getOffsetYearsTime(DateUtil.getTimestamp(startTime), 1);
            payNum = RoomConstants.RoomRentSet.RentTimeType.RENT_YEAR_PAY_NUM;
        }else if(RoomConstants.RoomRentSet.RentTimeType.RENT_MOUNTH.equals(rentTimeType)){
            endTime = DateUtil.getOffsetMonthsTime(DateUtil.getTimestamp(startTime), 1);
            payNum = RoomConstants.RoomRentSet.RentTimeType.RENT_MOUNTH_PAY_NUM;
        }else if(RoomConstants.RoomRentSet.RentTimeType.RENT_WEEK.equals(rentTimeType)){
            endTime = DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(startTime), 7);
            payNum = RoomConstants.RoomRentSet.RentTimeType.RENT_WEEK_PAY_NUM;
        }else if(RoomConstants.RoomRentSet.RentTimeType.RENT_THREEDAY.equals(rentTimeType)){
            endTime = DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(startTime), 3);
            payNum = RoomConstants.RoomRentSet.RentTimeType.RENT_THREEDAY_PAY_NUM;
        }else{
            //以上都没有则默认一年
            endTime = DateUtil.getOffsetYearsTime(DateUtil.getTimestamp(startTime), 1);
            payNum = RoomConstants.RoomRentSet.RentTimeType.RENT_YEAR_PAY_NUM;
        }

        //判断租住时间是在出租期
        if(endTime.after(roomRent.getEndTime())){
            logger.error("RentOrderService.saveRentOrder  订单结束时间大于出租的结束时间  endTime:"+endTime+"  roomRent.endTime:"+roomRent.getEndTime());
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }

        RoomRentSet roomRentSet = roomRentSetDao.getRoomRentSet(rentId, rentTimeType);
        rentOrder = new RentOrder();
        rentOrder.setCustRenterId(custId);
        rentOrder.setRoomId(roomRent.getRoomId());
        rentOrder.setRentId(rentId);
        rentOrder.setCreateTime(DateUtil.getCurrentDate());
        rentOrder.setStartTime(startTime);
        rentOrder.setEndTime(endTime);
        rentOrder.setRentSetId(roomRentSet.getRentSetId());
        rentOrder.setMortgageAmount(roomRentSet.getMortgageAmount());
        rentOrder.setLateAmount(roomRentSet.getLateAmount());
        rentOrder.setRentAmount(roomRentSet.getRentAmount());
        //设置总支付金额
        rentOrder.setAmount(roomRentSet.getRentAmount().multiply(BigDecimal.valueOf(payNum)));
        rentOrder.setPayPhase(0);
        rentOrder.setTotlePhase(payNum);
        rentOrder.setPaidAmount(BigDecimal.ZERO);
        rentOrder.setLength(DateUtil.getDaysBetween(DateUtil.getDateString(startTime, DateUtil.YYYYMMDD), DateUtil.getDateString(endTime, DateUtil.YYYYMMDD)));

        rentOrder.setBedroomId(roomRent.getBedroomId());
        rentOrder.setRentType(roomRent.getRentType());
        rentOrder.setPayType(roomRent.getPayType());
        rentOrder.setPaidEndTime(startTime);

        //设置订单状态为待支付状态
        rentOrder.setRentOrderState(PayConstants.RentOrder.RentOrderState.WAIT_PAY);
        rentOrder.setCreateTime(DateUtil.getCurrentDate());
        rentOrder.setRentTimeType(roomRentSet.getRentTimeType());

        logger.info("RentOrderService.saveRentOrder  rentOrder:"+rentOrder.toString());
        rentOrder = rentOrderDao.save(rentOrder);
        return rentOrder;
    }

    /**
     * 获取用户有效的租房订单
     * @param custId
     * @param rentId
     * @return
     * @throws Exception
     */
    public RentOrder getValidRentOrder(Long custId, Long rentId) throws Exception {
        RentOrder rentOrder = null;
        rentOrder = rentOrderDao.getValidRentOrder(custId, rentId);
        return rentOrder;
    }

}
