package com.eroom.web.controller.cust;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.TAccountBook;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.cust.AccountBookService;

@Controller
@RequestMapping("/cust")
public class CustController extends BaseController {

	@Resource
	private AccountBookService accountBookService;

	/**
	 * 获取用户账户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCustAccount")
	@ResponseBody
	public ResultVo getCustAccount() throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		TAccountBook custAccount = accountBookService.getCustAccount(sessionVo.getCustId());
		result.setDatas(custAccount);
		return result;
	}

}
