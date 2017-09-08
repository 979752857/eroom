package com.eroom.web.service;

import com.eroom.web.BaseTest;
import com.eroom.web.constants.RoomConstants;
import com.eroom.web.entity.bo.RoomRentBo;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.rent.RoomBookService;
import com.eroom.web.service.rent.RoomRentService;
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
public class RoomBookServiceTest extends BaseTest {

    @Resource
    private RoomBookService roomBookService;

    @Test
    public void getRoomRentTest() throws Exception {
        List<RoomBookVo> list = roomBookService.getRoomBookAll(1L);
        for(RoomBookVo vo : list){
            logger.info(vo.toString());
        }
    }
}
