package com.eroom.web.entity.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/9/12.
 */
@Entity
@Table(name = "t_rent_order", schema = "eroom", catalog = "")
public class RentOrder {
    private long rentOrderId;

    @Id
    @javax.persistence.Column(name = "rent_order_id")
    public long getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(long rentOrderId) {
        this.rentOrderId = rentOrderId;
    }

    private long rentId;

    @Basic
    @javax.persistence.Column(name = "rent_id")
    public long getRentId() {
        return rentId;
    }

    public void setRentId(long rentId) {
        this.rentId = rentId;
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

    private String rentOrderState;

    @Basic
    @javax.persistence.Column(name = "rent_order_state")
    public String getRentOrderState() {
        return rentOrderState;
    }

    public void setRentOrderState(String rentOrderState) {
        this.rentOrderState = rentOrderState;
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

    private Date startTime;

    @Basic
    @javax.persistence.Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    private int length;

    @Basic
    @javax.persistence.Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    private BigDecimal rentAmount;

    @Basic
    @javax.persistence.Column(name = "rent_amount")
    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
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

    private String payType;

    @Basic
    @javax.persistence.Column(name = "pay_type")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    private long rentSetId;

    @Basic
    @javax.persistence.Column(name = "rent_set_id")
    public long getRentSetId() {
        return rentSetId;
    }

    public void setRentSetId(long rentSetId) {
        this.rentSetId = rentSetId;
    }

    private String rentType;

    @Basic
    @javax.persistence.Column(name = "rent_type")
    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    private String rentTimeType;

    @Basic
    @javax.persistence.Column(name = "rent_time_type")
    public String getRentTimeType() {
        return rentTimeType;
    }

    public void setRentTimeType(String rentTimeType) {
        this.rentTimeType = rentTimeType;
    }

    @Override
    public String toString() {
        return "RentOrder{" +
                "rentOrderId=" + rentOrderId +
                ", rentId=" + rentId +
                ", roomId=" + roomId +
                ", bedroomId=" + bedroomId +
                ", custRenterId=" + custRenterId +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", rentOrderState='" + rentOrderState + '\'' +
                ", remark='" + remark + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", length=" + length +
                ", lateAmount=" + lateAmount +
                ", rentAmount=" + rentAmount +
                ", mortgageAmount=" + mortgageAmount +
                ", payType='" + payType + '\'' +
                ", rentSetId=" + rentSetId +
                ", rentType='" + rentType + '\'' +
                ", rentTimeType='" + rentTimeType + '\'' +
                '}';
    }
}
