package com.eroom.web.dao.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.WechatNotice;

@Repository
public class WechatNoticeDao extends BaseDao {

    public List<WechatNotice> getWechatNoticeList(Integer state) throws Exception {
        String hql = "from WechatNotice where state = :state and sendTime <= now()";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", state);

        return this.getList(hql, params);
    }

}
