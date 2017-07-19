package com.eroom.web.entity.vo.rent;

import java.math.BigDecimal;
import java.util.Date;

public class RoomRentVo {

	private Long id;
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
	private Date endTime;
	private Date createTime;
    private String rentState;

	public RoomRentVo(Long id, long roomId, long custOwnerId, long bedroomId, Long custRenterId, String bedroomImageUrl,
			String roomImageUrl, String name, BigDecimal price, String roomType, String space, String decorate) {
		super();
		this.id = id;
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
	}

	public RoomRentVo(Long id, long roomId, long custOwnerId, long bedroomId, Long custRenterId,
            String bedroomImageUrl, String roomImageUrl, String name, BigDecimal price,
            String roomType, String space, String decorate, Date endTime) {
        super();
        this.id = id;
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
        this.endTime = endTime;
    }
	
    public RoomRentVo(Long id, long roomId, long custOwnerId, long bedroomId, Long custRenterId,
            String bedroomImageUrl, String roomImageUrl, String name, BigDecimal price,
            String roomType, String space, String decorate, Date endTime, Date createTime,
            String rentState) {
        super();
        this.id = id;
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
        this.endTime = endTime;
        this.createTime = createTime;
        this.rentState = rentState;
    }

    public RoomRentVo() {
		super();
	}

	@Override
	public String toString() {
		return "RoomRentVo [id=" + id + ", roomId=" + roomId + ", custOwnerId=" + custOwnerId + ", bedroomId="
				+ bedroomId + ", custRenterId=" + custRenterId + ", bedroomImageUrl=" + bedroomImageUrl
				+ ", roomImageUrl=" + roomImageUrl + ", name=" + name + ", price=" + price + ", roomType=" + roomType
				+ ", space=" + space + ", decorate=" + decorate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRentState() {
        return rentState;
    }

    public void setRentState(String rentState) {
        this.rentState = rentState;
    }

}
