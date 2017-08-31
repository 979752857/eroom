package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/8/31.
 */
@Entity
@Table(name = "base_subway_station_rel", schema = "eroom", catalog = "")
public class BaseSubwayStationRel {
    private int id;
    private int subwayId;
    private int stationId;
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
    @Column(name = "subway_id")
    public int getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(int subwayId) {
        this.subwayId = subwayId;
    }

    @Basic
    @Column(name = "station_id")
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
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

        BaseSubwayStationRel that = (BaseSubwayStationRel) o;

        if (id != that.id) return false;
        if (subwayId != that.subwayId) return false;
        if (stationId != that.stationId) return false;
        if (sysStatus != that.sysStatus) return false;
        if (sysCuser != that.sysCuser) return false;
        if (sysMuser != that.sysMuser) return false;
        if (sysRemark != null ? !sysRemark.equals(that.sysRemark) : that.sysRemark != null) return false;
        if (sysCdate != null ? !sysCdate.equals(that.sysCdate) : that.sysCdate != null) return false;
        if (sysMdate != null ? !sysMdate.equals(that.sysMdate) : that.sysMdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subwayId;
        result = 31 * result + stationId;
        result = 31 * result + (sysRemark != null ? sysRemark.hashCode() : 0);
        result = 31 * result + (int) sysStatus;
        result = 31 * result + (sysCdate != null ? sysCdate.hashCode() : 0);
        result = 31 * result + sysCuser;
        result = 31 * result + (sysMdate != null ? sysMdate.hashCode() : 0);
        result = 31 * result + sysMuser;
        return result;
    }
}

