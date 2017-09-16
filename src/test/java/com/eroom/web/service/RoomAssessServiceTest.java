package com.eroom.web.service;

import com.eroom.web.entity.vo.rentlife.RoomAssessVo;
import com.eroom.web.service.rentlife.RoomAssessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class RoomAssessServiceTest {

    @Resource
    private RoomAssessService roomAssessService;

    @Test
    public void test1() throws Exception {
        roomAssessService.addAssess(1L,1L,1D,"test","1",null);
    }

    @Test
    public void test2() throws Exception {
        List<RoomAssessVo> list = roomAssessService.getRoomAssess(1L);
        for (RoomAssessVo roomAssessVo : list) {
            System.out.println(roomAssessVo.getContent());
        }
    }
}
