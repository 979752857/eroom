package com.eroom.web.entity.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/8/7.
 */
@Entity
@javax.persistence.Table(name = "t_pay_order", schema = "eroom", catalog = "")
public class PayOrder {
    private long payOrderId;

    @Id
    @javax.persistence.Column(name = "pay_order_id")
    public long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(long payOrderId) {
        this.payOrderId = payOrderId;
    }

    private long rentOrderId;

    @Basic
    @javax.persistence.Column(name = "rent_order_id")
    public long getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(long rentOrderId) {
        this.rentOrderId = rentOrderId;
    }

    private long custRenterId;

    @Basic
    @javax.persistence.Column(name = "cust_renter_id")
    public long getCustRenterId() {
        return custRenterId;
    }

    public void setCustRenterId(long custRenterId) {
        this.custRenterId = custRenterId;
    }

    private BigDecimal amount;

    @Basic
    @javax.persistence.Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    private Date updateTime;

    @Basic
    @javax.persistence.Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private String payOrderState;

    @Basic
    @javax.persistence.Column(name = "pay_order_state")
    public String getPayOrderState() {
        return payOrderState;
    }

    public void setPayOrderState(String payOrderState) {
        this.payOrderState = payOrderState;
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

    private int length;

    @Basic
    @javax.persistence.Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private Date startTime;

    @Basic
    @javax.persistence.Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    private long roomId;

    @Basic
    @javax.persistence.Column(name = "room_id")
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    private long bedroomId;

    @Basic
    @javax.persistence.Column(name = "bedroom_id")
    public long getBedroomId() {
        return bedroomId;
    }

    public void setBedroomId(long bedroomId) {
        this.bedroomId = bedroomId;
    }

    private Date endTime;

    @Basic
    @javax.persistence.Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private BigDecimal lateAmount;

    @Basic
    @javax.persistence.Column(name = "late_amount")
    public BigDecimal getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(BigDecimal lateAmount) {
        this.lateAmount = lateAmount;
    }

    private BigDecimal mortgageAmount;

    @Basic
    @javax.persistence.Column(name = "mortgage_amount")
    public BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    private BigDecimal rentAmount;

    @Basic
    @javax.persistence.Column(name = "rent_amount")
    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    private String type;

    @Basic
    @javax.persistence.Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String orderState;

    @Basic
    @javax.persistence.Column(name = "order_state")
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    private Long rentId;

    @Basic
    @javax.persistence.Column(name = "rent_id")
    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayOrder that = (PayOrder) o;

        if (payOrderId != that.payOrderId) return false;
        if (rentOrderId != that.rentOrderId) return false;
        if (custRenterId != that.custRenterId) return false;
        if (length != that.length) return false;
        if (roomId != that.roomId) return false;
        if (bedroomId != that.bedroomId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (payOrderState != null ? !payOrderState.equals(that.payOrderState) : that.payOrderState != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (lateAmount != null ? !lateAmount.equals(that.lateAmount) : that.lateAmount != null) return false;
        if (mortgageAmount != null ? !mortgageAmount.equals(that.mortgageAmount) : that.mortgageAmount != null)
            return false;
        if (rentAmount != null ? !rentAmount.equals(that.rentAmount) : that.rentAmount != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (orderState != null ? !orderState.equals(that.orderState) : that.orderState != null) return false;
        if (rentId != null ? !rentId.equals(that.rentId) : that.rentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (payOrderId ^ (payOrderId >>> 32));
        result = 31 * result + (int) (rentOrderId ^ (rentOrderId >>> 32));
        result = 31 * result + (int) (custRenterId ^ (custRenterId >>> 32));
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (payOrderState != null ? payOrderState.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (int) (bedroomId ^ (bedroomId >>> 32));
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (lateAmount != null ? lateAmount.hashCode() : 0);
        result = 31 * result + (mortgageAmount != null ? mortgageAmount.hashCode() : 0);
        result = 31 * result + (rentAmount != null ? rentAmount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (rentId != null ? rentId.hashCode() : 0);
        return result;
    }
}
