package com.eroom.web.service;

import com.eroom.web.BaseTest;
import com.eroom.web.service.wechat.WechatPlatformService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by tendy on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WechatPlatformServiceTest extends BaseTest {

    @Resource
    private WechatPlatformService wechatPlatformService;

    @Test
    public void echoHelloPageTest() throws Exception {
        String content = wechatPlatformService.addCustAndEchoHello("123123123", "321321321");
        logger.info("\n测试数据："+content);
    }
}
