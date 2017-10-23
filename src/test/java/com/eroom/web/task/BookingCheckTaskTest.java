package com.eroom.web.task;

import com.eroom.web.BaseTest;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.utils.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by tendy on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BookingCheckTaskTest extends BaseTest {

    @Resource
    private RoomBookDao roomBookDao;

    @Resource
    private BookingCheckTask bookingCheckTask;

    @Test
    @Transactional
    public void bookingCheckTest() throws Exception {
        Date nowTime = DateUtil.getCurrentDate();
        try {
            int result = roomBookDao.checkBooking(nowTime);
            if(result > 0){
                logger.info("\n用户预约过期校验成功："+result);
            }else{
                logger.info("\n用户预约过期校验失败："+result);
            }
        } catch (Exception e) {
            logger.warn("\n定时检查用户预订失败："+e.toString());
        }
    }

    @Test
    @Transactional
    public void checkTaskRunnningTest() throws Exception {
        bookingCheckTask.batchTaskRunnning();
    }
}
