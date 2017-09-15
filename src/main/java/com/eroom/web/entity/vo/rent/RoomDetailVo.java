package com.eroom.web.entity.vo.rent;

import java.math.BigDecimal;
import java.util.Date;

public class RoomDetailVo {

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
	private String direction;
	private String environment;
	private String year;
	private String buildType;
	private String heating;
	private String greening;
	private String config;
	private String state;
	private Date updateTime;
	private String floor;
	private String aroundInfo;
	private String traffice;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RoomDetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomDetailVo(Long id, long roomId, long custOwnerId, long bedroomId, Long custRenterId,
			String bedroomImageUrl, String roomImageUrl, String name, BigDecimal price, String roomType, String space,
			String decorate, String direction, String environment, String year, String buildType, String heating,
			String greening, String config, String state, Date updateTime, String floor, String aroundInfo, String traffice, String address) {
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
		this.direction = direction;
		this.environment = environment;
		this.year = year;
		this.buildType = buildType;
		this.heating = heating;
		this.greening = greening;
		this.config = config;
		this.state = state;
		this.updateTime = updateTime;
		this.floor = floor;
		this.aroundInfo = aroundInfo;
		this.traffice = traffice;
		this.address = address;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getHeating() {
		return heating;
	}

	public void setHeating(String heating) {
		this.heating = heating;
	}

	public String getGreening() {
		return greening;
	}

	public void setGreening(String greening) {
		this.greening = greening;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getAroundInfo() {
		return aroundInfo;
	}

	public void setAroundInfo(String aroundInfo) {
		this.aroundInfo = aroundInfo;
	}

	public String getTraffice() {
		return traffice;
	}

	public void setTraffice(String traffice) {
		this.traffice = traffice;
	}

	@Override
	public String toString() {
		return "RoomDetailVo{" +
				"id=" + id +
				", roomId=" + roomId +
				", custOwnerId=" + custOwnerId +
				", bedroomId=" + bedroomId +
				", custRenterId=" + custRenterId +
				", bedroomImageUrl='" + bedroomImageUrl + '\'' +
				", roomImageUrl='" + roomImageUrl + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", roomType='" + roomType + '\'' +
				", space='" + space + '\'' +
				", decorate='" + decorate + '\'' +
				", direction='" + direction + '\'' +
				", environment='" + environment + '\'' +
				", year='" + year + '\'' +
				", buildType='" + buildType + '\'' +
				", heating='" + heating + '\'' +
				", greening='" + greening + '\'' +
				", config='" + config + '\'' +
				", state='" + state + '\'' +
				", updateTime=" + updateTime +
				", floor='" + floor + '\'' +
				", aroundInfo='" + aroundInfo + '\'' +
				", traffice='" + traffice + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
