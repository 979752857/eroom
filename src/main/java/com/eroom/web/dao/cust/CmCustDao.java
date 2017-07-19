package com.eroom.web.dao.cust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.CmCust;

@Repository
public class CmCustDao extends BaseDao {

    /**
     * 根据openid查询客户信息
     */
    public CmCust getCmCustByOpenid(String tenantNo, String openid) throws Exception {
        String hql = "from CmCust where tenantNo = :tenantNo and wxOpenid = :wxOpenid";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tenantNo", tenantNo);
        params.put("wxOpenid", openid);

        List<CmCust> l = this.getList(hql, params);

        if (l != null && l.size() > 0) {
            return l.get(0);
        }

        return null;
    }

    /**
     * 查询客户信息
     */
    public CmCust getCmCust(String tenantNo, String openid, String phone) throws Exception {
        // 根据openid查询客户信息
        String hql = "from CmCust where tenantNo = :tenantNo and wxOpenid = :wxOpenid";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tenantNo", tenantNo);
        params.put("wxOpenid", openid);

        List<CmCust> l = this.getList(hql, params);

        // 根据手机号码查询客户信息
        if (l == null || l.isEmpty()) {
            hql = "from CmCust where tenantNo = :tenantNo and (contactTel = :contactTel or contactTel2 = :contactTel2)";

            params.clear();
            params.put("tenantNo", tenantNo);
            params.put("contactTel", phone);
            params.put("contactTel2", phone);

            l = this.getList(hql, params);
        }

        // 如果记录存在则返回否则为空
        if (l != null && l.size() > 0) {
            return l.get(0);
        }

        return null;
    }

}
