package com.eroom.web.service.base;

import com.eroom.web.BaseTest;
import com.eroom.web.entity.bo.LocationRangeBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseSubwayStationServiceTest extends BaseTest {

    @Resource
    private BaseSubwayStationService baseSubwayStationService;

    @Test
    public void getStationRange() throws Exception {

        LocationRangeBo range = baseSubwayStationService.getLocationRange(268L);
        logger.info("\n测试数据：" + range.toString());

    }
}
