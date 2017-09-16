package com.eroom.web.entity.po;
// Generated 2017-5-24 16:38:21 by Hibernate Tools 5.2.1.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PayDetail generated by hbm2java
 */
@Entity
@Table(name = "t_pay_detail", catalog = "eroom")
public class PayDetail implements java.io.Serializable {

    private Long payDetailId;

    private long roomId;

    private long custId;

    private String type;

    private BigDecimal amount;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private String state;

    public PayDetail() {
    }

    public PayDetail(long roomId, long custId, Date createTime, String state) {
        this.roomId = roomId;
        this.custId = custId;
        this.createTime = createTime;
        this.state = state;
    }

    public PayDetail(long roomId, long custId, String type, BigDecimal amount, Date createTime,
                     Date updateTime, String remark, String state) {
        this.roomId = roomId;
        this.custId = custId;
        this.type = type;
        this.amount = amount;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "pay_detail_id", unique = true, nullable = false)
    public Long getPayDetailId() {
        return this.payDetailId;
    }

    public void setPayDetailId(Long payDetailId) {
        this.payDetailId = payDetailId;
    }

    @Column(name = "room_id", nullable = false)
    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Column(name = "cust_id", nullable = false)
    public long getCustId() {
        return this.custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    @Column(name = "type", length = 8)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "amount", precision = 10)
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "remark", length = 225)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "state", nullable = false, length = 4)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

}