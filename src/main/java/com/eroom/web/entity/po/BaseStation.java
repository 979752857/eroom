package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/8/31.
 */
@Entity
@Table(name = "base_station", schema = "eroom", catalog = "")
public class BaseStation {
    private int id;
    private int cityId;
    private String stationName;
    private double lon;
    private double lat;
    private String sysRemark;
    private byte sysStatus;
    private Date sysCdate;
    private int sysCuser;
    private Date sysMdate;
    private int sysMuser;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
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
    @Column(name = "lon")
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Basic
    @Column(name = "lat")
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
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
    public int getSysCuser() {
        return sysCuser;
    }

    public void setSysCuser(int sysCuser) {
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
    public int getSysMuser() {
        return sysMuser;
    }

    public void setSysMuser(int sysMuser) {
        this.sysMuser = sysMuser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseStation that = (BaseStation) o;

        if (id != that.id) return false;
        if (cityId != that.cityId) return false;
        if (Double.compare(that.lon, lon) != 0) return false;
        if (Double.compare(that.lat, lat) != 0) return false;
        if (sysStatus != that.sysStatus) return false;
        if (sysCuser != that.sysCuser) return false;
        if (sysMuser != that.sysMuser) return false;
        if (stationName != null ? !stationName.equals(that.stationName) : that.stationName != null) return false;
        if (sysRemark != null ? !sysRemark.equals(that.sysRemark) : that.sysRemark != null) return false;
        if (sysCdate != null ? !sysCdate.equals(that.sysCdate) : that.sysCdate != null) return false;
        if (sysMdate != null ? !sysMdate.equals(that.sysMdate) : that.sysMdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + cityId;
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sysRemark != null ? sysRemark.hashCode() : 0);
        result = 31 * result + (int) sysStatus;
        result = 31 * result + (sysCdate != null ? sysCdate.hashCode() : 0);
        result = 31 * result + sysCuser;
        result = 31 * result + (sysMdate != null ? sysMdate.hashCode() : 0);
        result = 31 * result + sysMuser;
        return result;
    }
}

