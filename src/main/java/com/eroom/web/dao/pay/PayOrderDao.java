package com.eroom.web.dao.pay;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TPayDetail;
import com.eroom.web.entity.po.TPayOrder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PayOrderDao extends BaseDao {

    /**
     * 获取支付订单列表
     * 
     * @return TPayOrder
     * @throws Exception
     * @author tendy
     */
    public List<TPayOrder> getTPayOrderList(Long custRenterId, String orderState, Integer limit) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from TPayOrder where orderState = :orderState and custRenterId = :custRenterId order by createTime desc");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", orderState);
        params.put("custRenterId", custRenterId);

        List<TPayOrder> list = this.getPageList(hql.toString(), params, 0, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
	
}
