package com.eroom.web.controller.cust;

import javax.annotation.Resource;

import com.eroom.web.entity.po.CustInfo;
import com.eroom.web.service.cust.CmCustService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.AccountBook;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.cust.AccountBookService;

@Controller
@RequestMapping("/cust")
public class CustController extends BaseController {

	@Resource
	private AccountBookService accountBookService;

	@Resource
	private CmCustService cmCustService;

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
		AccountBook custAccount = accountBookService.getCustAccount(sessionVo.getCustId());
		result.setDatas(custAccount);
		return result;
	}

	/**
	 * 获取用户基本信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCustInfo")
	@ResponseBody
	public ResultVo getCustInfo() throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		CustInfo custInfo = cmCustService.getTCustInfoByCustId(sessionVo.getCustId());
		result.setDatas(custInfo);
		return result;
	}

	/**
	 * 修改用户基本信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateCustInfo")
	@ResponseBody
	public ResultVo updateCustInfo(CustInfo custInfo) throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		custInfo.setCustId(sessionVo.getCustId());
		custInfo = cmCustService.updateCustInfo(custInfo);
		result.setDatas(custInfo);
		return result;
	}

}
