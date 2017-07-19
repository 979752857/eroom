package com.eroom.web.entity.vo.rent;

import java.math.BigDecimal;
import java.util.Date;

public class RoomBookVo {

    private Long bookId;

    private Long rentId;

    private long roomId;

    private long custOwnerId;

    private long bedroomId;

    private Long custRenterId;

    private String bedroomImageUrl;

    private String roomImageUrl;

    private String name;

    private BigDecimal price;

    private String roomType;

    private String space;

    private String decorate;

    private String applyState;

    private Date startTime;

    private Date endTime;

    public RoomBookVo(Long bookId, Long rentId, long roomId, long custOwnerId, long bedroomId,
            Long custRenterId, String bedroomImageUrl, String roomImageUrl, String name,
            BigDecimal price, String roomType, String space, String decorate, String applyState,
            Date startTime, Date endTime) {
        super();
        this.bookId = bookId;
        this.rentId = rentId;
        this.roomId = roomId;
        this.custOwnerId = custOwnerId;
        this.bedroomId = bedroomId;
        this.custRenterId = custRenterId;
        this.bedroomImageUrl = bedroomImageUrl;
        this.roomImageUrl = roomImageUrl;
        this.name = name;
        this.price = price;
        this.roomType = roomType;
        this.space = space;
        this.decorate = decorate;
        this.applyState = applyState;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getCustOwnerId() {
        return custOwnerId;
    }

    public void setCustOwnerId(long custOwnerId) {
        this.custOwnerId = custOwnerId;
    }

    public long getBedroomId() {
        return bedroomId;
    }

    public void setBedroomId(long bedroomId) {
        this.bedroomId = bedroomId;
    }

    public Long getCustRenterId() {
        return custRenterId;
    }

    public void setCustRenterId(Long custRenterId) {
        this.custRenterId = custRenterId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
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

}
