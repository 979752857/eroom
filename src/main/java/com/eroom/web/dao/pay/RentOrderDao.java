package com.eroom.web.dao.pay;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.utils.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RentOrderDao extends BaseDao {

    /**
     * 获取租房订单列表
     * 
     * @return RentOrder
     * @throws Exception
     * @author tendy
     */
    public List<RentOrder> getRentOrderList(Long custRenterId, String orderState, Integer limit) throws Exception {
        return getRentOrderList(custRenterId, orderState, limit, 0);
    }
    public List<RentOrder> getRentOrderList(Long custRenterId, String[] orderState, Integer limit) throws Exception {
        return getRentOrderList(custRenterId, orderState, limit, 0);
    }

    public List<RentOrder> getRentOrderList(Long custRenterId, String orderState, Integer limit, int page) throws Exception {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append("from RentOrder where custRenterId = :custRenterId ");
        if(!StringUtil.isBlank(orderState)){
            hql.append(" and rentOrderState = :orderState ");
            params.put("orderState", orderState);
        }
        hql.append(" order by createTime desc");

        params.put("custRenterId", custRenterId);

        List<RentOrder> list = this.getPageList(hql.toString(), params, page, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

    public List<RentOrder> getRentOrderList(Long custRenterId, String[] orderState, Integer limit, int page) throws Exception {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append("from RentOrder where rentOrderState in (");
        for(int i = 0; i<orderState.length; i++){
            hql.append(":orderState"+i);
            params.put("orderState"+i, orderState[i]);
            if(i<orderState.length-1){
                hql.append(",");
            }
        }
        hql.append(") and custRenterId = :custRenterId order by createTime desc");
        params.put("custRenterId", custRenterId);

        List<RentOrder> list = this.getPageList(hql.toString(), params, page, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

    /**
     * 获取用户的有效的租房订单
     * @param custId
     * @param rentId
     * @return
     * @throws Exception
     */
    public RentOrder getValidRentOrder(Long custId, Long rentId) throws Exception {
        StringBuilder hql = new StringBuilder();
        String[] orderState = {PayConstants.RentOrder.RentOrderState.WAIT_PAY, PayConstants.RentOrder.RentOrderState.PAID};
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append("from RentOrder where rentId = :rentId and custRenterId = :custRenterId and rentOrderState in (");
        for(int i = 0; i<orderState.length; i++){
            hql.append(":orderState"+i);
            params.put("orderState"+i, orderState[i]);
            if(i<orderState.length-1){
                hql.append(",");
            }
        }
        hql.append(") order by createTime desc");
        params.put("custRenterId", custId);
        params.put("rentId", rentId);

        List<RentOrder> list = this.getList(hql.toString(), params);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取最近到期的租房订单
     * @param custRenterId
     * @return
     * @throws Exception
     */
    public RentOrder getLastRentOrderList(Long custRenterId) throws Exception {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        hql.append("from RentOrder where custRenterId = :custRenterId ");
        hql.append(" and rentOrderState = :orderState ");
        hql.append(" order by paidEndTime asc");

        params.put("orderState", PayConstants.RentOrder.RentOrderState.PAID);
        params.put("custRenterId", custRenterId);

        List<RentOrder> list = this.getList(hql.toString(), params);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
	
}
