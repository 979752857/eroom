package com.eroom.web.entity.bo;

import java.math.BigDecimal;

/**
 * 根据条件查询筛选数据
 * Created by tendy on 2017/8/3.
 */
public class RoomRentBo {

    /**
     * 最高价
     */
    private BigDecimal priceMax;

    /**
     * 最低价
     */
    private BigDecimal priceMin;

    /**
     * 租住类型
     */
    private String rentType;

    /**
     * 地铁站
     */
    private Long stationId;

    /**
     * 地铁线
     */

    private Long subwayId;

    /**
     * 区域id
     */
    private Long districtId;

    /**
     * 商圈id
     */
    private Long bussinessId;

    /**
     * 地铁站不为空则传入地址范围
     */
    private LocationRangeBo locationRange;

    public LocationRangeBo getLocationRange() {
        return locationRange;
    }

    public void setLocationRange(LocationRangeBo locationRange) {
        this.locationRange = locationRange;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }

    public Long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Long subwayId) {
        this.subwayId = subwayId;
    }
}
