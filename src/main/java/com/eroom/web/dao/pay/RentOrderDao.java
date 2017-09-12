package com.eroom.web.dao.pay;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.po.RentOrder;
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
        hql.append("from RentOrder where rentOrderState = :orderState and custRenterId = :custRenterId order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", orderState);
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
	
}
