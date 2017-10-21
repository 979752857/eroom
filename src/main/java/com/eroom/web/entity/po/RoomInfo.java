package com.eroom.web.entity.po;
// Generated 2017-5-24 16:38:21 by Hibernate Tools 5.2.1.Final

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * RoomInfo generated by hbm2java
 */
@Entity
@javax.persistence.Table(name = "room_info", schema = "eroom", catalog = "")
public class RoomInfo {
    private long roomId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "room_id")
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String roomType;

    @Basic
    @javax.persistence.Column(name = "room_type")
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    private String floor;

    @Basic
    @javax.persistence.Column(name = "floor")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    private String imageUrl;

    @Basic
    @javax.persistence.Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String environment;

    @Basic
    @javax.persistence.Column(name = "environment")
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    private String year;

    @Basic
    @javax.persistence.Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String buildType;

    @Basic
    @javax.persistence.Column(name = "build_type")
    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    private String heating;

    @Basic
    @javax.persistence.Column(name = "heating")
    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    private String greening;

    @Basic
    @javax.persistence.Column(name = "greening")
    public String getGreening() {
        return greening;
    }

    public void setGreening(String greening) {
        this.greening = greening;
    }

    private String config;

    @Basic
    @javax.persistence.Column(name = "config")
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    private String state;

    @Basic
    @javax.persistence.Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private Date updateTime;

    @Basic
    @javax.persistence.Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Date createTime;

    @Basic
    @javax.persistence.Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String aroundInfo;

    @Basic
    @javax.persistence.Column(name = "around_info")
    public String getAroundInfo() {
        return aroundInfo;
    }

    public void setAroundInfo(String aroundInfo) {
        this.aroundInfo = aroundInfo;
    }

    private String traffice;

    @Basic
    @javax.persistence.Column(name = "traffice")
    public String getTraffice() {
        return traffice;
    }

    public void setTraffice(String traffice) {
        this.traffice = traffice;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String address;

    @Basic
    @javax.persistence.Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private BigDecimal lon;

    @Basic
    @javax.persistence.Column(name = "lon")
    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    private BigDecimal lat;

    @Basic
    @javax.persistence.Column(name = "lat")
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    private Long cityId;

    @Basic
    @javax.persistence.Column(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    private Long districtId;

    @Basic
    @javax.persistence.Column(name = "district_id")
    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    private Long bussinessId;

    @Basic
    @javax.persistence.Column(name = "bussiness_id")
    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomInfo that = (RoomInfo) o;

        if (roomId != that.roomId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;
        if (environment != null ? !environment.equals(that.environment) : that.environment != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (buildType != null ? !buildType.equals(that.buildType) : that.buildType != null) return false;
        if (heating != null ? !heating.equals(that.heating) : that.heating != null) return false;
        if (greening != null ? !greening.equals(that.greening) : that.greening != null) return false;
        if (config != null ? !config.equals(that.config) : that.config != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (aroundInfo != null ? !aroundInfo.equals(that.aroundInfo) : that.aroundInfo != null) return false;
        if (traffice != null ? !traffice.equals(that.traffice) : that.traffice != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (lon != null ? !lon.equals(that.lon) : that.lon != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (cityId != null ? !cityId.equals(that.cityId) : that.cityId != null) return false;
        if (districtId != null ? !districtId.equals(that.districtId) : that.districtId != null) return false;
        if (bussinessId != null ? !bussinessId.equals(that.bussinessId) : that.bussinessId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (environment != null ? environment.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (buildType != null ? buildType.hashCode() : 0);
        result = 31 * result + (heating != null ? heating.hashCode() : 0);
        result = 31 * result + (greening != null ? greening.hashCode() : 0);
        result = 31 * result + (config != null ? config.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (aroundInfo != null ? aroundInfo.hashCode() : 0);
        result = 31 * result + (traffice != null ? traffice.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (districtId != null ? districtId.hashCode() : 0);
        result = 31 * result + (bussinessId != null ? bussinessId.hashCode() : 0);
        return result;
    }
}
