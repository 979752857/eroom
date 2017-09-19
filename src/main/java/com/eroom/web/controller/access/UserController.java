package com.eroom.web.controller.access;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	// @Resource
	// private UserService userService;

	/**
	 * 获取手机验证码
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 * @author tendy
	 */
	@RequestMapping("/getSmsValidCode")
	@ResponseBody
	public ResultVo getSmsValidCode(String phone) throws Exception {
		// SessionVo cust = this.getCustSession();
		String response = null; // userService.getSmsValidCode(cust.getTenantNo(),
								// cust.getCustId(), phone);
		ResultVo vo = this.parseResStr(response);
		if (vo.getSuccess()) {
			vo.setMessage("验证码已发送至您的手机，请注意查收！");
		}
		return vo;
	}

	/**
	 * 验证完成注册
	 * 
	 * @param userName
	 * @param phone
	 * @param validCode
	 * @return
	 * @throws Exception
	 * @author tendy
	 */
	@RequestMapping("/bindphone")
	@ResponseBody
	public ResultVo bindphone(String userName, String phone, String validCode) throws Exception {
		SessionVo cust = this.getCustSession();
		ResultVo vo = new ResultVo();
		// userService.updateCmCust(cust.getTenantNo(), cust.getCustId(),
		// userName, phone, validCode);
		vo.setSuccess(true);
		vo.setMessage("注册成功！");
		vo.setDatas(cust.getTenantNo());
		return vo;
	}

}