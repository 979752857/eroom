package com.eroom.web.entity.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/9/12.
 */
@Entity
@Table(name = "t_room_rent_set", schema = "eroom", catalog = "")
public class RoomRentSet {
    private long rentSetId;
    private long rentId;
    private String rentType;
    private BigDecimal price;
    private String remark;
    private Date createTime;
    private Date updateTime;

    @Id
    @Column(name = "rent_set_id")
    public long getRentSetId() {
        return rentSetId;
    }

    public void setRentSetId(long rentSetId) {
        this.rentSetId = rentSetId;
    }

    @Basic
    @Column(name = "rent_id")
    public long getRentId() {
        return rentId;
    }

    public void setRentId(long rentId) {
        this.rentId = rentId;
    }

    @Basic
    @Column(name = "rent_type")
    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomRentSet that = (RoomRentSet) o;

        if (rentSetId != that.rentSetId) return false;
        if (rentId != that.rentId) return false;
        if (rentType != null ? !rentType.equals(that.rentType) : that.rentType != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rentSetId ^ (rentSetId >>> 32));
        result = 31 * result + (int) (rentId ^ (rentId >>> 32));
        result = 31 * result + (rentType != null ? rentType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
