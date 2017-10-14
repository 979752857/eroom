package com.eroom.web.service;

import com.eroom.web.BaseTest;
import com.eroom.web.entity.vo.rentlife.TaskInfoVo;
import com.eroom.web.service.rentlife.TaskInfoService;
import com.eroom.web.utils.util.CollectionUtil;
import com.eroom.web.utils.util.DateUtil;
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
public class TaskInfoServiceTest extends BaseTest {

    @Resource
    private TaskInfoService taskInfoService;

    @Test
    public void getTaskInfoTest() throws Exception {
        List<TaskInfoVo> list = taskInfoService.getMonthTaskInfoVo(1L);
        if(!CollectionUtil.isEmpty(list)){
            logger.info("\n测试数据："+list.size());
            for(TaskInfoVo vo : list){
                logger.info("\n"+vo.getContent()+"  "+ DateUtil.getDateString(vo.getStartTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
            }
        }else{
            logger.info("\n获取空数据");
        }
    }


}
