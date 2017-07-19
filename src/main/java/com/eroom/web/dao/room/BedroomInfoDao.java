package com.eroom.web.dao.room;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TBedroomInfo;

@Repository
public class BedroomInfoDao extends BaseDao {

	/**
	 * 获取卧室信息表
	 * 
	 * @return TBedroomInfo
	 * @throws Exception
	 * @author tendy
	 */
	public List<TBedroomInfo> getTBedroomInfo() throws Exception {
		String hql = "from TBedroomInfo order by sortId desc";
		List<TBedroomInfo> list = this.getList(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

}
