package com.eroom.web.entity.vo.rentlife;

import java.util.Date;

public class RoomMessageVo {

    private long roomId;
    private long custId;
    private String custName;
    private String custImage;
    private String content;
    private Date createTime;
    private String state;
    
    public RoomMessageVo(long roomId, long custId, String custName, String custImage,
            String content, Date createTime, String state) {
        super();
        this.roomId = roomId;
        this.custId = custId;
        this.custName = custName;
        this.custImage = custImage;
        this.content = content;
        this.createTime = createTime;
        this.state = state;
    }
    public long getRoomId() {
        return roomId;
    }
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
    public long getCustId() {
        return custId;
    }
    public void setCustId(long custId) {
        this.custId = custId;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustImage() {
        return custImage;
    }
    public void setCustImage(String custImage) {
        this.custImage = custImage;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "RoomMessageVo [roomId=" + roomId + ", custId=" + custId + ", custName=" + custName
                + ", custImage=" + custImage + ", content=" + content + ", createTime=" + createTime
                + ", state=" + state + "]";
    }
    
}
