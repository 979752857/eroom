package com.eroom.web.dao.cust;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.CmCustWx;
import com.eroom.web.utils.util.CollectionUtil;

@Repository
public class CmCustWxDao extends BaseDao {

    @SuppressWarnings("unchecked")
    public CmCustWx getCmCustByOpenId(String openid) throws Exception {
        String hql = "from CmCustWx where openid = :openid";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("openid", openid);
        List<CmCustWx> userList = (List<CmCustWx>) query.list();
        if (CollectionUtil.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public CmCustWx getCmCustByQrCode(String qrCode) throws Exception {
        String hql = "from CmCustWx where qrCode = :qrCode";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("qrCode", qrCode);
        List<CmCustWx> userList = (List<CmCustWx>) query.list();
        if (CollectionUtil.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    public CmCustWx getCmCustByCustId(Long custId) throws Exception {
        String hql = "from CmCustWx where custId = :custId";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("custId", custId);
        List<CmCustWx> userList = (List<CmCustWx>) query.list();
        if (CollectionUtil.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }
}
