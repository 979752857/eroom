package com.eroom.web.dao.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TPayDetail;

@Repository
public class PayDetailDao extends BaseDao {

    /**
     * 获取最新缴费信息
     * 
     * @return TRoomRent
     * @throws Exception
     * @author tendy
     */
    public List<TPayDetail> getLastTPayDetail(Long custId, int limit) throws Exception {
        String hql = "from TPayDetail where custId = :custId order by createTime desc";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        
        List<TPayDetail> list = this.getPageList(hql, params, 0, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
	/**
	 * 获取缴费信息
	 * 
	 * @return TRoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public List<TPayDetail> getTPayDetail(Long custId) throws Exception {
		String hql = "from TPayDetail where custId order by createTime desc";
		List<TPayDetail> list = this.getList(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}
	
}
