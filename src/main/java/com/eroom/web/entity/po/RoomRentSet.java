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
    private String rentTimeType;
    private BigDecimal rentAmount;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private String state;
    private BigDecimal mortgageAmount;
    private BigDecimal lateAmount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "rent_time_type")
    public String getRentTimeType() {
        return rentTimeType;
    }

    public void setRentTimeType(String rentTimeType) {
        this.rentTimeType = rentTimeType;
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

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    @Column(name = "late_amount")
    public BigDecimal getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(BigDecimal lateAmount) {
        this.lateAmount = lateAmount;
    }


}
