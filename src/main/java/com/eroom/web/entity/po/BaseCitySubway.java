package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/9/5.
 */
@Entity
@Table(name = "base_city_subway", schema = "eroom", catalog = "")
public class BaseCitySubway {
    private long subwayId;
    private long cityId;
    private String subwayName;
    private String state;
    private Date updateTime;
    private Date createTime;
    private String remark;
    private Integer sortId;

    @Id
    @Column(name = "subway_id")
    public long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(long subwayId) {
        this.subwayId = subwayId;
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
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "sort_id")
    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseCitySubway that = (BaseCitySubway) o;

        if (subwayId != that.subwayId) return false;
        if (cityId != that.cityId) return false;
        if (subwayName != null ? !subwayName.equals(that.subwayName) : that.subwayName != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (sortId != null ? !sortId.equals(that.sortId) : that.sortId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (subwayId ^ (subwayId >>> 32));
        result = 31 * result + (int) (cityId ^ (cityId >>> 32));
        result = 31 * result + (subwayName != null ? subwayName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (sortId != null ? sortId.hashCode() : 0);
        return result;
    }
}
