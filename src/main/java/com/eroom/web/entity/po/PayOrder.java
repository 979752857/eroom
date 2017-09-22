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
    private long rentOrderId;
    private long custRenterId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private String payOrderState;
    private String remark;
    private int length;
    private Date startTime;
    private long roomId;
    private long bedroomId;
    private Date endTime;
    private BigDecimal lateAmount;
    private BigDecimal mortgageAmount;
    private BigDecimal rentAmount;
    private String type;
    private long rentId;

    @Id
    @Column(name = "pay_order_id")
    public long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(long payOrderId) {
        this.payOrderId = payOrderId;
    }

    @Basic
    @Column(name = "rent_order_id")
    public long getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(long rentOrderId) {
        this.rentOrderId = rentOrderId;
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
    @Column(name = "pay_order_state")
    public String getPayOrderState() {
        return payOrderState;
    }

    public void setPayOrderState(String payOrderState) {
        this.payOrderState = payOrderState;
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
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "room_id")
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "bedroom_id")
    public long getBedroomId() {
        return bedroomId;
    }

    public void setBedroomId(long bedroomId) {
        this.bedroomId = bedroomId;
    }

    @Basic
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "late_amount")
    public BigDecimal getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(BigDecimal lateAmount) {
        this.lateAmount = lateAmount;
    }

    @Basic
    @Column(name = "mortgage_amount")
    public BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    @Basic
    @Column(name = "rent_amount")
    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "rent_id")
    public long getRentId() {
        return rentId;
    }

    public void setRentId(long rentId) {
        this.rentId = rentId;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "payOrderId=" + payOrderId +
                ", rentOrderId=" + rentOrderId +
                ", custRenterId=" + custRenterId +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", payOrderState='" + payOrderState + '\'' +
                ", remark='" + remark + '\'' +
                ", length=" + length +
                ", startTime=" + startTime +
                ", roomId=" + roomId +
                ", bedroomId=" + bedroomId +
                ", endTime=" + endTime +
                ", lateAmount=" + lateAmount +
                ", mortgageAmount=" + mortgageAmount +
                ", rentAmount=" + rentAmount +
                ", type='" + type + '\'' +
                ", rentId=" + rentId +
                '}';
    }
}
