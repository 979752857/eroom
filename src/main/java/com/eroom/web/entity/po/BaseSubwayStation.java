package com.eroom.web.entity.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/8/31.
 */
@Entity
@Table(name = "base_subway_station", schema = "eroom", catalog = "")
public class BaseSubwayStation {
    private Long id;
    private Long cityId;
    private String stationName;
    private BigDecimal lon;
    private BigDecimal lat;
    private String sysRemark;
    private byte sysStatus;
    private Date sysCdate;
    private Long sysCuser;
    private Date sysMdate;
    private Long sysMuser;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "station_name")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Basic
    @Column(name = "lon", precision = 6)
    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    @Basic
    @Column(name = "lat", precision = 6)
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "sys_remark")
    public String getSysRemark() {
        return sysRemark;
    }

    public void setSysRemark(String sysRemark) {
        this.sysRemark = sysRemark;
    }

    @Basic
    @Column(name = "sys_status")
    public byte getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(byte sysStatus) {
        this.sysStatus = sysStatus;
    }

    @Basic
    @Column(name = "sys_cdate")
    public Date getSysCdate() {
        return sysCdate;
    }

    public void setSysCdate(Date sysCdate) {
        this.sysCdate = sysCdate;
    }

    @Basic
    @Column(name = "sys_cuser")
    public Long getSysCuser() {
        return sysCuser;
    }

    public void setSysCuser(Long sysCuser) {
        this.sysCuser = sysCuser;
    }

    @Basic
    @Column(name = "sys_mdate")
    public Date getSysMdate() {
        return sysMdate;
    }

    public void setSysMdate(Date sysMdate) {
        this.sysMdate = sysMdate;
    }

    @Basic
    @Column(name = "sys_muser")
    public Long getSysMuser() {
        return sysMuser;
    }

    public void setSysMuser(Long sysMuser) {
        this.sysMuser = sysMuser;
    }

}

