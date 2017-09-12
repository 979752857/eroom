package com.eroom.web.dao.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.PayDetail;

@Repository
public class PayDetailDao extends BaseDao {

    /**
     * 获取最新缴费信息
     * 
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public List<PayDetail> getLastTPayDetail(Long custId, int limit) throws Exception {
        String hql = "from PayDetail where custId = :custId order by createTime desc";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custId", custId);
        
        List<PayDetail> list = this.getPageList(hql, params, 0, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

	public Long countTPayDetail(Long custId, Date startTime) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("select count(1) ");
		hql.append(" from PayDetail where custId = :custId ");
		if(startTime != null){
			hql.append(" and createTime >= :startTime ");
			params.put("startTime", startTime);
		}
		hql.append(" order by createTime desc ");

		params.put("custId", custId);
		Long count = this.getCount(hql.toString(), params);
		return count;
	}

    /**
     * 获取缴费信息
     *
     * @return RoomRent
     * @throws Exception
     * @author tendy
     */
    public List<PayDetail> getTPayDetail(Long custId, Date startTime, int limit) throws Exception {
        return getTPayDetail(custId, startTime, limit, 0);
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
		hql.append(" from PayDetail where custId = :custId ");
		hql.append(" order by createTime desc ");
		params.put("custId", custId);
		Long count = this.getCount(hql.toString(), params);
		return count;
	}

	/**
	 * 获取缴费信息
	 *
	 * @return RoomRent
	 * @throws Exception
	 * @author tendy
	 */
    public List<PayDetail> getTPayDetail(Long custId, Date startTime, int limit, int page) throws Exception {
        StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from PayDetail where custId = :custId ");
		if(startTime != null){
			hql.append(" and createTime >= :startTime ");
			params.put("startTime", startTime);
		}
		hql.append(" order by createTime desc ");

        params.put("custId", custId);

        List<PayDetail> list = this.getPageList(hql.toString(), params, page, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
    
	/**
	 * 获取缴费信息
	 * 
	 * @return RoomRent
	 * @throws Exception
	 * @author tendy
	 */
	public List<PayDetail> getTPayDetail(Long custId) throws Exception {
		String hql = "from PayDetail where custId order by createTime desc";
		List<PayDetail> list = this.getList(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}
	
}
