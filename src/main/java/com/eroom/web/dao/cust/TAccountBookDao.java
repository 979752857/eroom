package com.eroom.web.dao.cust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TAccountBook;

@Repository
public class TAccountBookDao extends BaseDao {

    public TAccountBook getAcccoutBookByCustId(Long custId, String bookItemId)
            throws Exception {

        String hql = "from TAccountBook where custId = :custId and bookItemId = :bookItemId and state = :state";
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("custId", custId);
        values.put("state", SystemConstants.State.ACTIVE);
        values.put("bookItemId", bookItemId);
        List<TAccountBook> list = this.getList(hql, values);
        if (CollectionUtils.isEmpty(list)) {
            return null;            
        }
        return list.get(0);
    }

}
