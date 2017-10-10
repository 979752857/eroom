package com.eroom.web.service;

import com.eroom.web.BaseTest;
import com.eroom.web.constants.PayConstants;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.vo.rent.PayOrderVo;
import com.eroom.web.service.pay.PayOrderService;
import com.eroom.web.utils.util.CollectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tendy on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PayOrderServiceTest extends BaseTest {

    @Resource
    private PayOrderService payOrderService;

    @Test
    public void getPayOrderTest() throws Exception {
        List<PayOrderVo> list = payOrderService.getPayOrderList(1L, PayConstants.PayOrder.OrderState.FINISH);
        if(!CollectionUtil.isEmpty(list)){
            logger.info("\n测试数据："+list.size());
        }else{
            logger.info("\n获取空数据");
        }
    }

    @Test
    public void payOrderTest() throws Exception {
        payOrderService.addPayRentOrder(1L);
    }
}
