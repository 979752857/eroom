package com.eroom.web.dao.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.PayOrder;
import org.apache.commons.collections.CollectionUtils;
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
    public List<PayOrder> getWaitPayOrder(Long custId, Long rentOrderId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from PayOrder where payOrderState = :orderState and custRentId = :custId and rentOrderId = :rentOrderId order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", PayConstants.PayOrder.OrderState.WAITING);
        params.put("custId", custId);
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
    public List<PayOrder> getWaitPayOrder(Long custId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from PayOrder where payOrderState = :orderState and custRentId = :custId order by createTime desc");

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
     * 获取支付订单列表
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public List<PayOrder> getTPayOrderList(Long custRenterId, String orderState, Integer limit) throws Exception {
        return getTPayOrderList(custRenterId, orderState, limit, 0);
    }
    public List<PayOrder> getTPayOrderList(Long custRenterId, String orderState, Integer limit, int page) throws Exception {
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
	
}
