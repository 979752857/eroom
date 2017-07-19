package com.eroom.web.service.cust;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.CustConstants;
import com.eroom.web.dao.cust.TAccountBookDao;
import com.eroom.web.entity.po.TAccountBook;
import com.eroom.web.utils.exception.BusinessException;

@Service
public class AccountBookService {

	@Resource
	private TAccountBookDao tAccountBookDao;

	/**
	 * 获取用户账户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public TAccountBook getCustAccount(Long custId) throws Exception {
	    if(custId == null || custId == 0){
	        throw new BusinessException("未获取到租客编号");
	    }
	    TAccountBook account = tAccountBookDao.getAcccoutBookByCustId(custId, CustConstants.TAccountBook.BookItemId.CUSTMONEY);
		return account;
	}

}
