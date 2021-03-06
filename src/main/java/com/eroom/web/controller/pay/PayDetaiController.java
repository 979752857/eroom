package com.eroom.web.controller.pay;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroom.web.controller.BaseController;
import com.eroom.web.entity.po.PayDetail;
import com.eroom.web.entity.vo.base.ResultVo;
import com.eroom.web.entity.vo.base.SessionVo;
import com.eroom.web.service.pay.PayDetailService;

@Controller
@RequestMapping("/paydetail")
public class PayDetaiController extends BaseController {

	@Resource
	private PayDetailService payDetailService;

	/**
	 * 获取最新缴费信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPayDetail")
	@ResponseBody
	public ResultVo getPayDetail() throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		List<PayDetail> list = payDetailService.getLastPayDetail(sessionVo.getCustId());
		result.setDatas(list);
		return result;
	}

	/**
	 * 获取最多两年缴费记录
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPayDetailPage")
	@ResponseBody
	public ResultVo getPayDetailHalfYear(int page) throws Exception {
		ResultVo result = new ResultVo();
		SessionVo sessionVo = this.getCustSession();
		Map<String, Object> map = payDetailService.getPayDetailPage(sessionVo.getCustId(), page);
		result.setDatas(map);
		return result;
}

}
