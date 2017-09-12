package com.eroom.web.dao.cust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.CustInfo;

@Repository
public class TCustInfoDao extends BaseDao {

    /**
     * 根据openid查询客户信息
     */
    public CustInfo getTCustInfoByOpenid(String openid) throws Exception {
        String hql = "from CustInfo where openid = :openid";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", openid);

        List<CustInfo> l = this.getList(hql, params);

        if (l != null && l.size() > 0) {
            return l.get(0);
        }

        return null;
    }

    /**
     * 根据custid查询客户信息
     */
    public CustInfo getTCustInfoByCustId(Long custId) throws Exception {
        String hql = "from CustInfo where custId = :custId";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);

        List<CustInfo> l = this.getList(hql, params);

        if (l != null && l.size() > 0) {
            return l.get(0);
        }

        return null;
    }
}
