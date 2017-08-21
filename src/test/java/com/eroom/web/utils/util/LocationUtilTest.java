package com.eroom.web.utils.util;

import com.eroom.web.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tendy on 2017/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class LocationUtilTest extends BaseTest {

    @Test
    public void getDistanceTest() throws Exception {
        double lat1 = 37.480563;
        double lng1 = 121.467113;
        double lat2 = 37.480591;
        double lng2 = 121.467926;

        double distance = LocationUtil.getDistance(lat1, lng1, lat2, lng2);        //准确
        logger.info("\n测试距离为："+distance+"米");

        double num = 12;
        lat1 += num;
        lat2 += num;
        distance = LocationUtil.getDistance(lat1, lng1, lat2, lng2);        //准确
        logger.info("\n测试距离为："+distance+"米");
        lat1 = 37.480563;
        lng1 = 121.467113;
        double[] arr = LocationUtil.getAround(lat1, lng1, 2000);
        for(double item : arr){
            logger.info("\n范围1："+item);
        }

        distance = LocationUtil.getDistance(lat1, lng1, arr[0], arr[1]);        //准确
        logger.info("\n测试距离为："+distance+"米");

        arr = LocationUtil.getAroundPrecision(lat1, lng1, 2000);
        for(double item : arr){
            logger.info("\n范围2："+item);
        }

        distance = LocationUtil.getDistance(lat1, lng1, arr[0], arr[1]);        //准确
        logger.info("\n测试距离为："+distance+"米");
    }
}