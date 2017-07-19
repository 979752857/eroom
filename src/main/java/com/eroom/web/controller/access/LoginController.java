package com.eroom.web.controller.access;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eroom.web.constants.CustConstants;
import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.CmCustWx;
import com.eroom.web.service.cust.CustInfoWxService;
import com.eroom.web.service.system.SystemCfgService;
import com.eroom.web.utils.exception.SystemException;
import com.eroom.web.utils.util.StringUtil;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource
	private CustInfoWxService cmCustWxService;

	@Resource
	private SystemCfgService systemCfgService;

	@RequestMapping("/wechat")
	public void wechat(HttpServletResponse response) throws Exception {
		String tenantNo = request.getParameter("tenantNo");
		if (StringUtil.isBlank(tenantNo)) {
			throw new SystemException("======[请传入机构编码]");
		}
		String code = request.getParameter("code");
		if (StringUtil.isBlank(code)) {
			throw new SystemException("======[code不能为空]");
		}
		String state = request.getParameter("state");
		if (StringUtil.isBlank(state)) {
			throw new SystemException("======[state不能为空]");
		}

		// 获取openid
		CmCustWx cmCust = cmCustWxService.addByWechat(tenantNo, code);

		String url = null;
		if (state.equals(CustConstants.Login.SERVICE_INDEX)) {// 跳转至服务首页
			url = "/security/html/serve.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else if (state.equals(CustConstants.Login.PERSONAL_CENTER)) {// 跳转至个人中心
			url = "/security/html/personalcenter.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else if (state.equals(CustConstants.Login.LIVE_ZONE)) {// 跳转至直播空间
			url = "/security/html/livespace.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else if (state.equals(CustConstants.Login.DAY_SIGN_IN)) {// 跳转至每日签到
			url = "/security/html/everydaysign.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else if (state.equals(CustConstants.Login.STOCK_INFO)) {// 跳转至名家看点
			url = "/security/html/famouswatch.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else if (state.equals(CustConstants.Login.DIAGNOSE_STK)) {// 跳转至名师诊股
			url = "/security/html/diagnose.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else if (state.equals(CustConstants.Login.INVEST_CALENDAR)) {// 跳转至投资日历
			url = "/security/html/investcalendar.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		} else {// 默认跳转至行情首页
			url = "/security/html/stockhome.html?openid=" + cmCust.getOpenid() + "&tenantNo=" + tenantNo;
		}

		response.sendRedirect(url);
	}
}
