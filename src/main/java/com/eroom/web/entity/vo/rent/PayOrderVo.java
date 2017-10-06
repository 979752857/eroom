package com.eroom.web.entity.vo.rent;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tendy on 2017/10/3.
 */
public class PayOrderVo {
    private long payOrderId;
    private long rentOrderId;
    private long custRenterId;
    private long roomId;
    private long bedroomId;
    private long rentId;
    private BigDecimal amount;
    private BigDecimal lateAmount;
    private BigDecimal mortgageAmount;
    private BigDecimal rentAmount;
    private Date startTime;
    private Date endTime;
    private int length;
    private String type;
    private String payOrderState;
    private String bedroomImageUrl;
    private String roomImageUrl;
    private String name;
    private String roomType;
    private String space;
    private String decorate;
    private String address;
    private int phase;

    public PayOrderVo(long payOrderId, long rentOrderId, long custRenterId, long roomId, long bedroomId, long rentId, BigDecimal amount, BigDecimal lateAmount, BigDecimal mortgageAmount, BigDecimal rentAmount, Date startTime, Date endTime, int length, String type, String payOrderState, String bedroomImageUrl, String roomImageUrl, String name, String roomType, String space, String decorate) {
        this.payOrderId = payOrderId;
        this.rentOrderId = rentOrderId;
        this.custRenterId = custRenterId;
        this.roomId = roomId;
        this.bedroomId = bedroomId;
        this.rentId = rentId;
        this.amount = amount;
        this.lateAmount = lateAmount;
        this.mortgageAmount = mortgageAmount;
        this.rentAmount = rentAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.length = length;
        this.type = type;
        this.payOrderState = payOrderState;
        this.bedroomImageUrl = bedroomImageUrl;
        this.roomImageUrl = roomImageUrl;
        this.name = name;
        this.roomType = roomType;
        this.space = space;
        this.decorate = decorate;
    }

    public PayOrderVo(long payOrderId, long rentOrderId, long custRenterId, long roomId, long bedroomId, long rentId, BigDecimal amount, BigDecimal lateAmount, BigDecimal mortgageAmount, BigDecimal rentAmount, Date startTime, Date endTime, int length, String type, String payOrderState, String bedroomImageUrl, String roomImageUrl, String name, String roomType, String space, String decorate, int phase) {
        this.payOrderId = payOrderId;
        this.rentOrderId = rentOrderId;
        this.custRenterId = custRenterId;
        this.roomId = roomId;
        this.bedroomId = bedroomId;
        this.rentId = rentId;
        this.amount = amount;
        this.lateAmount = lateAmount;
        this.mortgageAmount = mortgageAmount;
        this.rentAmount = rentAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.length = length;
        this.type = type;
        this.payOrderState = payOrderState;
        this.bedroomImageUrl = bedroomImageUrl;
        this.roomImageUrl = roomImageUrl;
        this.name = name;
        this.roomType = roomType;
        this.space = space;
        this.decorate = decorate;
        this.phase = phase;
    }

    public PayOrderVo(long payOrderId, long rentOrderId, long custRenterId, long roomId, long bedroomId, long rentId, BigDecimal amount, BigDecimal lateAmount, BigDecimal mortgageAmount, BigDecimal rentAmount, Date startTime, Date endTime, int length, String type, String payOrderState, String bedroomImageUrl, String roomImageUrl, String name, String roomType, String space, String decorate, int phase, String address) {
        this.payOrderId = payOrderId;
        this.rentOrderId = rentOrderId;
        this.custRenterId = custRenterId;
        this.roomId = roomId;
        this.bedroomId = bedroomId;
        this.rentId = rentId;
        this.amount = amount;
        this.lateAmount = lateAmount;
        this.mortgageAmount = mortgageAmount;
        this.rentAmount = rentAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.length = length;
        this.type = type;
        this.payOrderState = payOrderState;
        this.bedroomImageUrl = bedroomImageUrl;
        this.roomImageUrl = roomImageUrl;
        this.name = name;
        this.roomType = roomType;
        this.space = space;
        this.decorate = decorate;
        this.phase = phase;
        this.address = address;
    }

    public long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public long getRentOrderId() {
        return rentOrderId;
    }

    public void setRentOrderId(long rentOrderId) {
        this.rentOrderId = rentOrderId;
    }

    public long getCustRenterId() {
        return custRenterId;
    }

    public void setCustRenterId(long custRenterId) {
        this.custRenterId = custRenterId;
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

    public long getRentId() {
        return rentId;
    }

    public void setRentId(long rentId) {
        this.rentId = rentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(BigDecimal lateAmount) {
        this.lateAmount = lateAmount;
    }

    public BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayOrderState() {
        return payOrderState;
    }

    public void setPayOrderState(String payOrderState) {
        this.payOrderState = payOrderState;
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

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
