package com.eroom.web.dao.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eroom.web.utils.util.StringUtil;
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
    public List<TPayDetail> getTPayDetail(Long custId, int limit) throws Exception {
        return getTPayDetail(custId, limit, 0);
    }

	/**
	 * 统计缴费历史情况
	 * @param custId
	 * @return
	 * @throws Exception
     */
	public Long countTPayDetail(Long custId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("select count(1) ");
		hql.append(" from TPayDetail where custId = :custId ");
		hql.append(" order by createTime desc ");
		params.put("custId", custId);
		Long count = this.getCount(hql.toString(), params);
		return count;
	}

	/**
	 * 获取缴费信息
	 *
	 * @return TRoomRent
	 * @throws Exception
	 * @author tendy
	 */
    public List<TPayDetail> getTPayDetail(Long custId, int limit, int page) throws Exception {
        StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from TPayDetail where custId = :custId ");
		hql.append(" order by createTime desc ");

        params.put("custId", custId);

        List<TPayDetail> list = this.getPageList(hql.toString(), params, page, limit);
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
