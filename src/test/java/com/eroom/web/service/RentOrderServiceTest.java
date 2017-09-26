package com.eroom.web.service;

import com.eroom.web.BaseTest;
import com.eroom.web.constants.PayConstants;
import com.eroom.web.entity.po.PayOrder;
import com.eroom.web.entity.po.RentOrder;
import com.eroom.web.service.pay.PayOrderService;
import com.eroom.web.service.pay.RentOrderService;
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
public class RentOrderServiceTest extends BaseTest {

    @Resource
    private PayOrderService payOrderService;

    @Resource
    private RentOrderService rentOrderService;

    @Test
    public void getRentOrderTest() throws Exception {
        RentOrder rentOrder = rentOrderService.saveRentOrder(1L, 8L, "01");
        //生成第一期订单       rentOrder没有获取到
        payOrderService.addPayRentOrder(rentOrder);
        logger.info("\n测试数据："+rentOrder.toString());
    }
}
