package com.eroom.web.entity.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "t_room_assess", schema = "eroom", catalog = "")
public class TRoomAssess {
    private long assessId;

    private Long targetId;

    private Long custId;

    private String content;

    private Double level;

    private String imgUrls;

    private Date createTime;

    private String type;

    @Id
    @Column(name = "assess_id")
    public long getAssessId() {
        return assessId;
    }

    public void setAssessId(long assessId) {
        this.assessId = assessId;
    }

    @Basic
    @Column(name = "target_id")
    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Basic
    @Column(name = "cust_id")
    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "level")
    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    @Basic
    @Column(name = "img_urls")
    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TRoomAssess that = (TRoomAssess) o;

        if (assessId != that.assessId)
            return false;
        if (targetId != null ? !targetId.equals(that.targetId) : that.targetId != null)
            return false;
        if (custId != null ? !custId.equals(that.custId) : that.custId != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null)
            return false;
        if (level != null ? !level.equals(that.level) : that.level != null)
            return false;
        if (imgUrls != null ? !imgUrls.equals(that.imgUrls) : that.imgUrls != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (assessId ^ (assessId >>> 32));
        result = 31 * result + (targetId != null ? targetId.hashCode() : 0);
        result = 31 * result + (custId != null ? custId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (imgUrls != null ? imgUrls.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
