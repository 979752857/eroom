package com.eroom.web.service.pay;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.PaymentConstants;
import com.eroom.web.dao.pay.PayDetailDao;
import com.eroom.web.entity.po.TPayDetail;
import com.eroom.web.utils.exception.BusinessException;

@Service
public class PayDetailService {

	@Resource
	private PayDetailDao payDetailDao;

	/**
	 * 获取最新留言信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TPayDetail> getLastPayDetail(Long custId) throws Exception {
	    if(custId == null || custId == 0){
	        throw new BusinessException("未获取到租客编号");
	    }
	    List<TPayDetail> list = payDetailDao.getLastTPayDetail(custId, PaymentConstants.LIMIT);
		return list;
	}

}
