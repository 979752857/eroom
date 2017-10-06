package com.eroom.web.controller.cust;

import javax.annotation.Resource;

import com.eroom.web.constants.PayConstants;
import com.eroom.web.entity.po.CustInfo;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.entity.vo.rent.PayOrderVo;
import com.eroom.web.service.cust.CmCustService;
import com.eroom.web.service.pay.PayOrderService;
import com.eroom.web.service.pay.RentOrderService;
import com.eroom.web.utils.util.CollectionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.AccountBook;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.cust.AccountBookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustController extends BaseController {

	@Resource
	private AccountBookService accountBookService;

	@Resource
	private RentOrderService rentOrderService;

	@Resource
	private PayOrderService payOrderService;

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

	/**
	 * 获取用户账单数量余额等信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getOrderInfo")
	@ResponseBody
	public ResultVo getOrderInfo() throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		Map<String, Object> map = new HashMap<>();
		List<RentOrder> list = rentOrderService.getPaidRentOrderList(sessionVo.getCustId());
		if(CollectionUtil.isEmpty(list)){
			map.put("rentOrderSize", 0);
		}else{
			map.put("rentOrderSize", list.size());
		}
		List<PayOrderVo> waitingList = payOrderService.getPayOrderList(sessionVo.getCustId(), PayConstants.PayOrder.OrderState.WAITING);
		List<PayOrderVo> finishList = payOrderService.getPayOrderList(sessionVo.getCustId(), PayConstants.PayOrder.OrderState.FINISH);
		if(CollectionUtil.isEmpty(waitingList)){
			map.put("payWaitSize", 0);
		}else{
			map.put("payWaitSize", waitingList.size());
		}
		if(CollectionUtil.isEmpty(finishList)){
			map.put("payFinishSize", 0);
		}else{
			map.put("payFinishSize", finishList.size());
		}
		result.setDatas(map);
		return result;
	}

}
