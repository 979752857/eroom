package com.eroom.web.entity.po;
// Generated 2017-5-24 16:38:21 by Hibernate Tools 5.2.1.Final

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
 * BedroomInfo generated by hbm2java
 */
@Entity
@Table(name = "t_bedroom_info", catalog = "eroom")
public class BedroomInfo implements java.io.Serializable {

    private Long bedroomId;

    private long roomId;

    private String space;

    private String decorate;

    private String imageUrl;

    private String direction;

    private String state;

    private Date updateTime;

    private Date createTime;

    private String remark;

    public BedroomInfo() {
    }

    public BedroomInfo(long roomId, String state, Date createTime) {
        this.roomId = roomId;
        this.state = state;
        this.createTime = createTime;
    }

    public BedroomInfo(long roomId, String space, String decorate, String imageUrl,
                       String direction, String state, Date updateTime, Date createTime, String remark) {
        this.roomId = roomId;
        this.space = space;
        this.decorate = decorate;
        this.imageUrl = imageUrl;
        this.direction = direction;
        this.state = state;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.remark = remark;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bedroom_id", unique = true, nullable = false)
    public Long getBedroomId() {
        return this.bedroomId;
    }

    public void setBedroomId(Long bedroomId) {
        this.bedroomId = bedroomId;
    }

    @Column(name = "room_id", nullable = false)
    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Column(name = "space", length = 8)
    public String getSpace() {
        return this.space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    @Column(name = "decorate", length = 8)
    public String getDecorate() {
        return this.decorate;
    }

    public void setDecorate(String decorate) {
        this.decorate = decorate;
    }

    @Column(name = "image_url", length = 128)
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "direction", length = 8)
    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Column(name = "state", nullable = false, length = 4)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "remark", length = 128)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
