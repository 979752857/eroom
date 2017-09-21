package com.eroom.web.service.cust;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.SystemException;

import org.springframework.stereotype.Service;

import com.eroom.web.dao.cust.CmCustWxExtDao;
import com.eroom.web.entity.po.CmCustWxExt;

@Service
public class CmCustWxExtService {

	@Resource
	private CmCustWxExtDao cmCustWxExtDao;

	@Resource
	private CmCustService cmCustService;

	/**
	 * 根据id获取客户扩展信息
	 * 
	 * @param cmCustWxExt
	 * @throws Exception
	 * @author tendy
	 */
	public CmCustWxExt getCmCustWxExtByCustId(Long custId) throws Exception {
		return cmCustWxExtDao.get(CmCustWxExt.class, custId);
	}

	/**
	 * 保存客户扩展信息
	 * 
	 * @param cmCustWxExt
	 * @throws Exception
	 * @author tendy
	 */
	public void saveCmCustWxExt(CmCustWxExt cmCustWxExt) throws Exception {
		if (cmCustWxExt == null) {
			throw new SystemException("保存的客户扩展对象不存在");
		}
		cmCustWxExtDao.save(cmCustWxExt);
	}

	/**
	 * 更新扩展信息
	 * 
	 * @param cmCustWxExt
	 * @throws Exception
	 * @author tendy
	 */
	public void updateCmCustWxExt(CmCustWxExt cmCustWxExt) throws Exception {
		if (cmCustWxExt == null) {
			throw new SystemException("更新的客户扩展对象不存在");
		}
		cmCustWxExtDao.update(cmCustWxExt);
	}

	/**
	 * 保存或更新
	 * 
	 * @param cmCustWxExt
	 * @throws Exception
	 * @author jingbao
	 */
	public void saveUpdateCmCustWxExt(CmCustWxExt cmCustWxExt, Long custId, String tenantNo, String openid)
			throws Exception {
		CmCustWxExt oldCmCustWxExt = cmCustWxExtDao.get(CmCustWxExt.class, custId);
		if (oldCmCustWxExt == null) { // 扩展表中的用户资料为空，新增操作
			cmCustWxExt.setCustId(custId);
			cmCustWxExt.setTenantNo(tenantNo);
			cmCustWxExt.setCreateTime(new Date());
			cmCustWxExtDao.save(cmCustWxExt);
		} else { // 扩展表中的用户资料不为空，修改操作
			oldCmCustWxExt.setCustName(cmCustWxExt.getCustName());
			oldCmCustWxExt.setSex(cmCustWxExt.getSex());
			oldCmCustWxExt.setContactTel(cmCustWxExt.getContactTel());
			oldCmCustWxExt.setContactTel2(cmCustWxExt.getContactTel2());
			oldCmCustWxExt.setContactTel3(cmCustWxExt.getContactTel3());
			oldCmCustWxExt.setContactQq(cmCustWxExt.getContactQq());
			oldCmCustWxExt.setContactWechat(cmCustWxExt.getContactWechat());
			oldCmCustWxExt.setInvestAge(cmCustWxExt.getInvestAge());
			oldCmCustWxExt.setCityCode(cmCustWxExt.getCityCode());
			cmCustWxExtDao.update(oldCmCustWxExt);
		}

		// 数据同步至客户表
		cmCustService.updateCmCustFromWechat(tenantNo, openid, cmCustWxExt);
	}
}
