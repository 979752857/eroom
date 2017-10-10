package com.eroom.web.entity.vo.rent;

import com.eroom.web.entity.po.RentOrder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/9/12.
 */
public class RentOrderVo {
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
    private String payType;
    private long rentSetId;
    private String rentType;
    private String rentTimeType;
    private String type;
    private int payPhase;
    private int totlePhase;
    private BigDecimal paidAmount;
    private Date paidEndTime;
    private String bedroomImageUrl;
    private String roomImageUrl;
    private String name;
    private String roomType;
    private String space;
    private String decorate;
    private String address;

    public RentOrderVo(){}

    public RentOrderVo(long rentOrderId, long rentId, long roomId, long bedroomId, long custRenterId, BigDecimal amount, Date createTime, Date updateTime, String rentOrderState, String remark, Date startTime, Date endTime, int length, BigDecimal lateAmount, BigDecimal rentAmount, BigDecimal mortgageAmount, String payType, long rentSetId, String rentType, String rentTimeType, String type, int payPhase, int totlePhase, BigDecimal paidAmount, Date paidEndTime, String bedroomImageUrl, String roomImageUrl, String name, String roomType, String space, String decorate, String address) {
        this.rentOrderId = rentOrderId;
        this.rentId = rentId;
        this.roomId = roomId;
        this.bedroomId = bedroomId;
        this.custRenterId = custRenterId;
        this.amount = amount;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.rentOrderState = rentOrderState;
        this.remark = remark;
        this.startTime = startTime;
        this.endTime = endTime;
        this.length = length;
        this.lateAmount = lateAmount;
        this.rentAmount = rentAmount;
        this.mortgageAmount = mortgageAmount;
        this.payType = payType;
        this.rentSetId = rentSetId;
        this.rentType = rentType;
        this.rentTimeType = rentTimeType;
        this.type = type;
        this.payPhase = payPhase;
        this.totlePhase = totlePhase;
        this.paidAmount = paidAmount;
        this.paidEndTime = paidEndTime;
        this.bedroomImageUrl = bedroomImageUrl;
        this.roomImageUrl = roomImageUrl;
        this.name = name;
        this.roomType = roomType;
        this.space = space;
        this.decorate = decorate;
        this.address = address;
    }

    public long getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(long rentOrderId) {
        this.rentOrderId = rentOrderId;
    }

    public long getRentId() {
        return rentId;
    }

    public void setRentId(long rentId) {
        this.rentId = rentId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getBedroomId() {
        return bedroomId;
    }

    public void setBedroomId(long bedroomId) {
        this.bedroomId = bedroomId;
    }

    public long getCustRenterId() {
        return custRenterId;
    }

    public void setCustRenterId(long custRenterId) {
        this.custRenterId = custRenterId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRentOrderState() {
        return rentOrderState;
    }

    public void setRentOrderState(String rentOrderState) {
        this.rentOrderState = rentOrderState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public BigDecimal getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(BigDecimal lateAmount) {
        this.lateAmount = lateAmount;
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    public BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public long getRentSetId() {
        return rentSetId;
    }

    public void setRentSetId(long rentSetId) {
        this.rentSetId = rentSetId;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getRentTimeType() {
        return rentTimeType;
    }

    public void setRentTimeType(String rentTimeType) {
        this.rentTimeType = rentTimeType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPayPhase() {
        return payPhase;
    }

    public void setPayPhase(int payPhase) {
        this.payPhase = payPhase;
    }

    public int getTotlePhase() {
        return totlePhase;
    }

    public void setTotlePhase(int totlePhase) {
        this.totlePhase = totlePhase;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getPaidEndTime() {
        return paidEndTime;
    }

    public void setPaidEndTime(Date paidEndTime) {
        this.paidEndTime = paidEndTime;
    }

    public String getBedroomImageUrl() {
        return bedroomImageUrl;
    }

    public void setBedroomImageUrl(String bedroomImageUrl) {
        this.bedroomImageUrl = bedroomImageUrl;
    }

    public String getRoomImageUrl() {
        return roomImageUrl;
    }

    public void setRoomImageUrl(String roomImageUrl) {
        this.roomImageUrl = roomImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getDecorate() {
        return decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
