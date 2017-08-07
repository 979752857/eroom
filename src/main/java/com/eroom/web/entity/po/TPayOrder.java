package com.eroom.web.entity.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/8/7.
 */
@Entity
@Table(name = "t_pay_order", schema = "eroom", catalog = "")
public class TPayOrder {
    private long payOrderId;
    private long rentId;
    private long custRenterId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private String orderState;
    private String remark;
    private int length;
    private java.sql.Date startTime;
    private java.sql.Date endTime;

    @Id
    @Column(name = "pay_order_id")
    public long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(long payOrderId) {
        this.payOrderId = payOrderId;
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
    @Column(name = "cust_renter_id")
    public long getCustRenterId() {
        return custRenterId;
    }

    public void setCustRenterId(long custRenterId) {
        this.custRenterId = custRenterId;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    @Basic
    @Column(name = "order_state")
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "start_time")
    public java.sql.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public java.sql.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPayOrder that = (TPayOrder) o;

        if (payOrderId != that.payOrderId) return false;
        if (rentId != that.rentId) return false;
        if (custRenterId != that.custRenterId) return false;
        if (length != that.length) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (orderState != null ? !orderState.equals(that.orderState) : that.orderState != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (payOrderId ^ (payOrderId >>> 32));
        result = 31 * result + (int) (rentId ^ (rentId >>> 32));
        result = 31 * result + (int) (custRenterId ^ (custRenterId >>> 32));
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
