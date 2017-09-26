package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/9/5.
 */
@Entity
@Table(name = "base_subway", schema = "eroom", catalog = "")
public class BaseSubway {
    private long id;
    private long cityId;
    private String subwayName;
    private String sysRemark;
    private byte sysStatus;
    private Date sysCdate;
    private int sysCuser;
    private Date sysMdate;
    private int sysMuser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id")
    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "subway_name")
    public String getSubwayName() {
        return subwayName;
    }

    public void setSubwayName(String subwayName) {
        this.subwayName = subwayName;
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

        BaseSubway that = (BaseSubway) o;

        if (id != that.id) return false;
        if (cityId != that.cityId) return false;
        if (sysStatus != that.sysStatus) return false;
        if (sysCuser != that.sysCuser) return false;
        if (sysMuser != that.sysMuser) return false;
        if (subwayName != null ? !subwayName.equals(that.subwayName) : that.subwayName != null) return false;
        if (sysRemark != null ? !sysRemark.equals(that.sysRemark) : that.sysRemark != null) return false;
        if (sysCdate != null ? !sysCdate.equals(that.sysCdate) : that.sysCdate != null) return false;
        if (sysMdate != null ? !sysMdate.equals(that.sysMdate) : that.sysMdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (cityId ^ (cityId >>> 32));
        result = 31 * result + (subwayName != null ? subwayName.hashCode() : 0);
        result = 31 * result + (sysRemark != null ? sysRemark.hashCode() : 0);
        result = 31 * result + (int) sysStatus;
        result = 31 * result + (sysCdate != null ? sysCdate.hashCode() : 0);
        result = 31 * result + sysCuser;
        result = 31 * result + (sysMdate != null ? sysMdate.hashCode() : 0);
        result = 31 * result + sysMuser;
        return result;
    }
}
