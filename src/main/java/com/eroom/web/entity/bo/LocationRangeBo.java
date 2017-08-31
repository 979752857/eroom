package com.eroom.web.entity.bo;

import java.math.BigDecimal;

/**
 * Created by tendy on 2017/8/31.
 * Lon:116.300274     Lat:39.954895
 */
public class LocationRangeBo {
    private BigDecimal minLon;
    private BigDecimal minLat;
    private BigDecimal maxLon;
    private BigDecimal maxLat;

    public BigDecimal getMinLon() {
        return minLon;
    }

    public void setMinLon(BigDecimal minLon) {
        this.minLon = minLon;
    }

    public BigDecimal getMinLat() {
        return minLat;
    }

    public void setMinLat(BigDecimal minLat) {
        this.minLat = minLat;
    }

    public BigDecimal getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(BigDecimal maxLon) {
        this.maxLon = maxLon;
    }

    public BigDecimal getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(BigDecimal maxLat) {
        this.maxLat = maxLat;
    }

    @Override
    public String toString() {
        return "LocationRangeBo{" +
                "minLon=" + minLon +
                ", minLat=" + minLat +
                ", maxLon=" + maxLon +
                ", maxLat=" + maxLat +
                '}';
    }
}
