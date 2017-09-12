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
    private long rentId;
    private long roomId;
    private long bedroomId;
    private long custRenterId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private String rentOrderState;
    private String remark;
    private Date startTime;
    private Date endTime;
    private int length;
    private BigDecimal lateAmount;
    private BigDecimal rentAmount;
    private BigDecimal mortgageAmount;
    private String type;

    @Id
    @Column(name = "rent_order_id")
    public long getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(long rentOrderId) {
        this.rentOrderId = rentOrderId;
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
    @Column(name = "cust_renter_id")
    public long getCustRenterId() {
        return custRenterId;
    }

    public void setCustRenterId(long custRenterId) {
        this.custRenterId = custRenterId;
    }

    @Basic
    @Column(name = " amount")
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
    @Column(name = "rent_order_state")
    public String getRentOrderState() {
        return rentOrderState;
    }

    public void setRentOrderState(String rentOrderState) {
        this.rentOrderState = rentOrderState;
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
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
    @Column(name = "rent_amount")
    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentOrder that = (RentOrder) o;

        if (rentOrderId != that.rentOrderId) return false;
        if (rentId != that.rentId) return false;
        if (roomId != that.roomId) return false;
        if (bedroomId != that.bedroomId) return false;
        if (custRenterId != that.custRenterId) return false;
        if (length != that.length) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (rentOrderState != null ? !rentOrderState.equals(that.rentOrderState) : that.rentOrderState != null)
            return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (lateAmount != null ? !lateAmount.equals(that.lateAmount) : that.lateAmount != null) return false;
        if (rentAmount != null ? !rentAmount.equals(that.rentAmount) : that.rentAmount != null) return false;
        if (mortgageAmount != null ? !mortgageAmount.equals(that.mortgageAmount) : that.mortgageAmount != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rentOrderId ^ (rentOrderId >>> 32));
        result = 31 * result + (int) (rentId ^ (rentId >>> 32));
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + (int) (bedroomId ^ (bedroomId >>> 32));
        result = 31 * result + (int) (custRenterId ^ (custRenterId >>> 32));
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (rentOrderState != null ? rentOrderState.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + (lateAmount != null ? lateAmount.hashCode() : 0);
        result = 31 * result + (rentAmount != null ? rentAmount.hashCode() : 0);
        result = 31 * result + (mortgageAmount != null ? mortgageAmount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
