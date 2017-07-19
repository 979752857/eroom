package com.eroom.web.dao.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.WechatNoticeTemplet;

@Repository
public class WechatNoticeTempletDao extends BaseDao {

    public WechatNoticeTemplet getWechatNoticeTemplet(String tenantNo, String templetType)
            throws Exception {
        String hql = "from WechatNoticeTemplet where tenantNo = :tenantNo and templetType = :templetType";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tenantNo", tenantNo);
        params.put("templetType", templetType);

        List<WechatNoticeTemplet> l = this.getList(hql, params);

        if (l != null && l.size() > 0) {
            return l.get(0);
        }

        return null;
    }

}
