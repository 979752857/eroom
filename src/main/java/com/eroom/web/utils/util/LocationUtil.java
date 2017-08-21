package com.eroom.web.utils.util;

import java.math.BigDecimal;

/**
 * Created by tendy on 2017/8/21.
 */
public class LocationUtil {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static BigDecimal getDistancePrecision(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        BigDecimal distance = new BigDecimal(s);
        return distance;
    }

    /**
     * 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
     * @param lat 纬度
     * @param lon 经度
     * @param raidus 半径(米)
     * @return
     */
    public static double[] getAround(double lat, double lon, int raidus)
    {
        //先计算查询点的经纬度范围
        double r = EARTH_RADIUS;//地球半径千米
        double dis = raidus/1000;
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(rad(lat)));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minLat =lat-dlat;
        double maxLat = lat+dlat;
        double minLng = lon -dlng;
        double maxLng = lon + dlng;

        return new double[] { minLat, minLng, maxLat, maxLng };
    }

    /**
     * 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
     * @param lat 纬度
     * @param lon 经度
     * @param raidus 半径(米)
     * @return
     */
    public static double[] getAroundPrecision(double lat, double lon, int raidus)
    {
        //先计算查询点的经纬度范围
        double r = EARTH_RADIUS;//地球半径千米
        double dis = raidus/1000;
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(rad(lat)));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minLat =lat-dlat;
        double maxLat = lat+dlat;
        double minLng = lon -dlng;
        double maxLng = lon + dlng;

        return new double[] { minLat, minLng, maxLat, maxLng };
    }

    private int precise2Double(BigDecimal num){
        int n = num.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return n;
    }

    private double precise2Double2(BigDecimal num){
        double n = num.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return n;
    }

    private double precise2Double6(BigDecimal num){
        double n = num.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return n;
    }

    private BigDecimal precise2BigDecimal6(BigDecimal num){
        BigDecimal n = num.setScale(6);
        return n;
    }
}
