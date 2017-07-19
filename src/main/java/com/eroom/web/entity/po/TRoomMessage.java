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
 * TRoomMessage generated by hbm2java
 */
@Entity
@Table(name = "t_room_message", catalog = "eroom")
public class TRoomMessage implements java.io.Serializable {

    private Long messageId;

    private long roomId;

    private long custId;

    private String content;

    private Date createTime;

    private Date updateTime;

    private String state;

    public TRoomMessage() {
    }

    public TRoomMessage(long roomId, long custId, Date createTime, String state) {
        this.roomId = roomId;
        this.custId = custId;
        this.createTime = createTime;
        this.state = state;
    }

    public TRoomMessage(long roomId, long custId, String content, Date createTime, Date updateTime,
            String state) {
        this.roomId = roomId;
        this.custId = custId;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "message_id", unique = true, nullable = false)
    public Long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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

    @Column(name = "content", length = 225)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Column(name = "state", nullable = false, length = 4)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
