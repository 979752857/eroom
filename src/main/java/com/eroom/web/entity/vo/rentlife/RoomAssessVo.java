package com.eroom.web.entity.vo.rentlife;

import java.util.Date;

public class RoomAssessVo {

    private String targetId;//目标id

    private String level;//评分

    private String content;//评价内容

    private String imgUrls;//图片URL

    private Date createTime;//创建时间

    private String custId;//创建者id

    private String nickname;//昵称

    public RoomAssessVo() {
    }

    public RoomAssessVo(String targetId, String level, String content,
            String imgUrls, Date createTime, String custId, String nickname) {
        this.targetId = targetId;
        this.level = level;
        this.content = content;
        this.imgUrls = imgUrls;
        this.createTime = createTime;
        this.custId = custId;
        this.nickname = nickname;
    }

    public RoomAssessVo(Long targetId, Double level, String content,
            String imgUrls, Date createTime, Long custId, String nickname) {
        this.targetId = targetId.toString();
        this.level = level.toString();
        this.content = content;
        this.imgUrls = imgUrls;
        this.createTime = createTime;
        this.custId = custId.toString();
        this.nickname = nickname;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
