package com.eroom.web.controller.access;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.eroom.web.entity.po.CustInfo;
import com.eroom.web.utils.wechat.WechatLogin;
import com.eroom.web.utils.wechat.WetchatSign;
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

	@RequestMapping("/wechat")
	public void wechat(HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		if (StringUtil.isBlank(code)) {
			throw new SystemException("======[code不能为空]");
		}
		String state = request.getParameter("state");
		if (StringUtil.isBlank(state)) {
			throw new SystemException("======[state不能为空]");
		}

		loginfo("LoginController.wechat  state:{}   code:{}  ", state, code);

		// 获取openid
		CustInfo custInfo = null;
		try{
			if(WechatLogin.checkLogin(code)){
				custInfo = cmCustWxService.addByWechat(code);
			}else{
				return ;
			}
		}catch (Exception e){
			throw e;
		}finally {
			WechatLogin.delete(code);
		}

		String url = null;
		if (state.equals(CustConstants.Login.SERVICE_INDEX)) {// 跳转至服务首页
			url = "/eroom/html/index.html?openid=" + custInfo.getOpenid();
		} else if (state.equals(CustConstants.Login.TASK_CENTER)) {// 跳转至任务中心
			url = "/eroom/html/rent-life-task.html?openid=" + custInfo.getOpenid();
		} else if (state.equals(CustConstants.Login.MESSAGE_CENTER)) {// 跳转至留言中心
			url = "/eroom/html/rent-life-message.html?openid=" + custInfo.getOpenid();
		} else if (state.equals(CustConstants.Login.CONTACT_US)) {// 跳转至联系客服
			url = "/eroom/html/index.html?openid=" + custInfo.getOpenid();
		} else if (state.equals(CustConstants.Login.SPREAD_ONLINE)) {// 跳转至在线推广
			url = "/eroom/html/index.html?openid=" + custInfo.getOpenid();
		} else if (state.equals(CustConstants.Login.ABOUT_US)) {// 跳转至关于我们
			url = "/eroom/html/about.html?openid=" + custInfo.getOpenid();
		} else {// 默认跳转至服务首页
			url = "/eroom/html/index.html?openid=" + custInfo.getOpenid();
		}

		loginfo("LoginController.wechat  custInfo:"+custInfo.toString()+"   url:"+url);

		response.sendRedirect(url);
	}
}
