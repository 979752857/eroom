package com.eroom.web.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.dao.system.SystemParamDao;
import com.eroom.web.entity.po.SystemParam;

@Service
public class SystemParamService {
	@Resource
	private SystemParamDao systemParamDao;

	/**
	 * 查询所有参数配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getSystemParam() throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		List<SystemParam> l = systemParamDao.getSystemParamList();
		if (l == null || l.size() == 0) {
			return null;
		}

		for (SystemParam param : l) {
			String field = param.getParamType() + '.' + param.getParamSubType() + '.' + param.getParamCode();
			map.put(field, param.getParamTitle());
		}

		return map;
	}

}
