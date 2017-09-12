package com.eroom.web.dao.room;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.BedroomInfo;

@Repository
public class BedroomInfoDao extends BaseDao {

	/**
	 * 获取卧室信息表
	 * 
	 * @return BedroomInfo
	 * @throws Exception
	 * @author tendy
	 */
	public List<BedroomInfo> getTBedroomInfo() throws Exception {
		String hql = "from BedroomInfo order by sortId desc";
		List<BedroomInfo> list = this.getList(hql);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

}
