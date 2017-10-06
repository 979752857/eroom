package com.eroom.web.dao.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.vo.rent.PayOrderVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PayOrderDao extends BaseDao {

    /**
     * 获取支付订单
     * 
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public List<PayOrder> getPayOrderList(Long rentOrderId, String orderState) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from PayOrder where payOrderState = :orderState and rentOrderId = :rentOrderId order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", orderState);
        params.put("rentOrderId", rentOrderId);

        List<PayOrder> list = this.getList(hql.toString(), params);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取用户支付订单
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public PayOrderVo getWaitPayOrder(Long custId, Long rentOrderId) throws Exception {
        StringBuilder hql = new StringBuilder();
//        hql.append("from PayOrder where payOrderState = :orderState and custRenterId = :custId and rentOrderId = :rentOrderId order by createTime desc");
        hql.append(" select new com.eroom.web.entity.vo.rent.PayOrderVo(po.payOrderId, po.rentOrderId, ");
        hql.append(" po.custRenterId, po.roomId, po.bedroomId, po.rentId, po.amount, ");
        hql.append(" po.lateAmount, po.mortgageAmount, po.rentAmount, po.startTime, ");
        hql.append(" po.endTime, po.length, po.type, po.payOrderState, tbi.imageUrl, tri.imageUrl, ");
        hql.append(" tri.name, tri.roomType, tbi.space, tbi.decorate, ro.payPhase, tri.address) ");
        hql.append(" from PayOrder po, BedroomInfo tbi, RoomInfo tri, RentOrder ro ");
        hql.append(" where po.custRenterId = :custRenterId ");
        hql.append(" and po.payOrderState = :orderState ");
        hql.append(" and po.rentOrderId = :rentOrderId ");
        hql.append(" and po.roomId = tri.roomId and po.bedroomId = tbi.bedroomId and po.rentOrderId = ro.rentOrderId ");
        hql.append(" order by po.createTime desc  ");


        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", PayConstants.PayOrder.OrderState.WAITING);
        params.put("custRenterId", custId);
        params.put("rentOrderId", rentOrderId);

        List<PayOrderVo> list = this.getList(hql.toString(), params);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        if(list.size() > 1){
            logger.warn("\n支付订单待支付数据大于2条："+list.toString());
        }
        return list.get(0);
    }

    /**
     * 获取用户支付订单
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public List<PayOrder> getWaitPayOrder(Long custId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from PayOrder where payOrderState = :orderState and custRenterId = :custId order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", PayConstants.PayOrder.OrderState.WAITING);
        params.put("custId", custId);

        List<PayOrder> list = this.getList(hql.toString(), params);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取用户支付订单
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public List<PayOrder> getWaitPayOrderByRentOrderId(Long rentOrderId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from PayOrder where payOrderState = :orderState and rentOrderId = :rentOrderId order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", PayConstants.PayOrder.OrderState.WAITING);
        params.put("rentOrderId", rentOrderId);

        List<PayOrder> list = this.getList(hql.toString(), params);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取支付订单列表
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public List<PayOrder> getPayOrderList(Long custRenterId, String orderState, Integer limit) throws Exception {
        return getPayOrderList(custRenterId, orderState, limit, 0);
    }
    public List<PayOrder> getPayOrderList(Long custRenterId, String orderState, Integer limit, int page) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from PayOrder where payOrderState = :orderState and custRenterId = :custRenterId order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", orderState);
        params.put("custRenterId", custRenterId);

        List<PayOrder> list = this.getPageList(hql.toString(), params, page, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

    /**
     * 获取支付订单列表
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public List<PayOrderVo> getPayOrderVoList(Long custRenterId, String orderState, Integer limit) throws Exception {
        return getPayOrderVoList(custRenterId, orderState, limit, 0);
    }
    public List<PayOrderVo> getPayOrderVoList(Long custRenterId, String orderState, Integer limit, int page) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append(" select new com.eroom.web.entity.vo.rent.PayOrderVo(po.payOrderId, po.rentOrderId, ");
        hql.append(" po.custRenterId, po.roomId, po.bedroomId, po.rentId, po.amount, ");
        hql.append(" po.lateAmount, po.mortgageAmount, po.rentAmount, po.startTime, ");
        hql.append(" po.endTime, po.length, po.type, po.payOrderState, tbi.imageUrl, tri.imageUrl, ");
        hql.append(" tri.name, tri.roomType, tbi.space, tbi.decorate) ");
        hql.append(" from PayOrder po, BedroomInfo tbi, RoomInfo tri ");
        hql.append(" where po.custRenterId = :custRenterId ");
        if(StringUtils.isNotBlank(orderState)){
            hql.append(" and po.payOrderState = :orderState ");
        }
        hql.append(" and po.roomId = tri.roomId and po.bedroomId = tbi.bedroomId ");
        hql.append(" order by po.createTime desc  ");

        Map<String, Object> params = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(orderState)){
            params.put("orderState", orderState);
        }
        params.put("custRenterId", custRenterId);

        List<PayOrderVo> list = this.getPageList(hql.toString(), params, page, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
	
}
