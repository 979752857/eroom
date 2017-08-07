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
     * 获取最新缴费信息
     * 
     * @return TRoomRent
     * @throws Exception
     * @author tendy
     */
    public List<TPayOrder> getLastTPayOrder(String orderState, int limit) throws Exception {
        String hql = "from TPayOrder where orderState = :orderState order by createTime desc";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderState", orderState);
        
        List<TPayOrder> list = this.getPageList(hql, params, 0, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
	
}
