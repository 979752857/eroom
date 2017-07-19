package com.eroom.web.service.user;
//package com.minijy.trade.service.user;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.minijy.trade.constants.SystemConstants;
//import com.minijy.trade.dao.cust.CmCustWxDao;
//import com.minijy.trade.service.cust.CmCustService;
//import com.minijy.trade.service.system.SystemCfgService;
//
//@Service
//public class UserService {
//
//	@Resource
//	private CmCustWxDao custDao;
//
//	@Resource
//	private SystemCfgService sysCfgService;
//
//	@Resource
//	private CmCustService cmCustService;
//
//	// public String getSmsValidCode(String tenantNo, long custId, String phone)
//	// throws Exception {
//	// String reg = "^1[3|4|5|7|8]\\d{9}$";
//	// Pattern pattern = Pattern.compile(reg);
//	// Matcher matcher = pattern.matcher(phone);
//	// if (!matcher.matches()) {
//	// throw new BusinessException("请输入有效手机号！");
//	// }
//	// this.getValidCodeCntCheck(tenantNo, custId, phone);
//	// String randomCode = StringUtil.getRandomCode();
//	// String key = this.getValidCodeRedisKey(tenantNo, String.valueOf(custId),
//	// phone);
//	// redisDao.setex(key, 300, randomCode);
//	// String content = "验证码：" + randomCode + ",有效期为5分钟，请在页面中提交验证码完成验证。";
//	// String url = sysCfgService.getUIPUrlPreFix(tenantNo) + "/sms/smsSubmit";
//	// // String url = "http://101.201.75.9:12900/trade-uip/sms/smsSubmit";
//	// String params = "tenantNo=" + tenantNo;
//	// params += "&smsType=1";
//	// params += "&phones=" + phone;
//	// params += "&content=" + content;
//	// String response = HttpUtil.sendPost(url, params);
//	// return response;
//	// }
//
//	/**
//	 * 用户注册，更新用户的认证标志和绑定手机号
//	 * 
//	 * @param tenantNo
//	 * @param custId
//	 * @param phone
//	 * @param validCode
//	 * @throws Exception
//	 * @author caiyt
//	 */
//	public void updateCmCust(String tenantNo, long custId, String userName, String phone, String validCode)
//			throws Exception {
//		String key = this.getValidCodeRedisKey(tenantNo, String.valueOf(custId), phone);
//		String validCodeCache = redisDao.get(key);
//		if (StringUtil.isBlank(validCodeCache) || !validCodeCache.equals(validCode)) {
//			throw new BusinessException("该验证码已失效或为无效验证码，请重新获取并验证！");
//		}
//		CmCustWx cust = custDao.get(CmCustWx.class, custId);
//		if (cust == null) {
//			throw new BusinessException("该用户不存在！");
//		}
//		cust.setAuthFlag(CustConstants.CmCust.AuthFlag.AUTHED);
//		CmCustWxExt ext = custDao.get(CmCustWxExt.class, custId);
//		if (ext == null) {
//			ext = new CmCustWxExt();
//			ext.setTenantNo(cust.getTenantNo());
//			ext.setCreateTime(new Date());
//			ext.setCustId(cust.getCustId());
//			custDao.save(ext);
//		}
//		ext.setContactTel(phone);
//		ext.setCustName(userName);
//		custDao.update(ext);
//		redisDao.rem(key);
//
//		// 数据同步至客户表
//		cmCustService.updateCmCustFromWechat(tenantNo, cust.getOpenid(), userName, phone);
//	}
//
//	private String getValidCodeRedisKey(String tenantNo, String custId, String phone) {
//		return SystemConstants.RedisKey.CACHE_KEY_PREFIX + tenantNo + ".REGISTE." + custId + "." + phone;
//	}
//
//	/**
//	 * 校验获取验证码次数
//	 * 
//	 * @param tenantNo
//	 * @param custId
//	 * @param phone
//	 * @author caiyt
//	 */
//	 private void getValidCodeCntCheck(String tenantNo, long custId, String
//	 phone) {
//	 String cntKey = SystemConstants.RedisKey.CACHE_KEY_PREFIX + tenantNo +
//	 ".REGISTE_CNT."
//	 + custId + "." + phone;
//	 String getValidCodeCnt = redisDao.get(cntKey);
//	 int cnt = 0;
//	 if (!StringUtil.isBlank(getValidCodeCnt)) {
//	 cnt = Integer.parseInt(getValidCodeCnt);
//	 if (cnt >= 10) {
//	 throw new BusinessException("您当天获取短信次数已超过上限！");
//	 }
//	 }
//	 Calendar cal = Calendar.getInstance();
//	 cal.set(Calendar.HOUR_OF_DAY, 0);
//	 cal.set(Calendar.MINUTE, 0);
//	 cal.set(Calendar.SECOND, 0);
//	 cal.set(Calendar.MILLISECOND, 0);
//	 cal.add(Calendar.DAY_OF_MONTH, 1);
//	 cal.add(Calendar.MILLISECOND, -1);
//	 redisDao.setex(cntKey, (int) ((cal.getTimeInMillis() -
//	 System.currentTimeMillis()) / 1000),
//	 String.valueOf(++cnt));
//	 }
//}