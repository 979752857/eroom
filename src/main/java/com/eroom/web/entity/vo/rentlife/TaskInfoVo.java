package com.eroom.web.entity.vo.rentlife;

import java.util.Date;

public class TaskInfoVo {

    private Long id;

    private long roomId;

    private long executeCustId;
    
    private String executeCustName;

    private long custId;
    
    private String custName;

    private String content;

    private String taskList;

    private Date startTime;
    
    private Date updateTime;

    private Date endTime;

    private String remark;

    private String type;
    
    private String taskState;

    public TaskInfoVo() {
    }

    public TaskInfoVo(Long id, long roomId, long executeCustId, String executeCustName, long custId,
            String custName, String content, String taskList, Date startTime,
            Date endTime, String remark, String type, Date updateTime) {
        super();
        this.id = id;
        this.roomId = roomId;
        this.executeCustId = executeCustId;
        this.executeCustName = executeCustName;
        this.custId = custId;
        this.custName = custName;
        this.content = content;
        this.taskList = taskList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
        this.type = type;
        this.updateTime = updateTime;
    }

    public TaskInfoVo(Long id, long roomId, long executeCustId, String executeCustName, long custId,
            String custName, String content, String taskList, Date startTime,
            Date endTime, String remark, String type, Date updateTime, String taskState) {
        super();
        this.id = id;
        this.roomId = roomId;
        this.executeCustId = executeCustId;
        this.executeCustName = executeCustName;
        this.custId = custId;
        this.custName = custName;
        this.content = content;
        this.taskList = taskList;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.endTime = endTime;
        this.remark = remark;
        this.type = type;
        this.taskState = taskState;
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

    public long getExecuteCustId() {
        return executeCustId;
    }

    public void setExecuteCustId(long executeCustId) {
        this.executeCustId = executeCustId;
    }

    public String getExecuteCustName() {
        return executeCustName;
    }

    public void setExecuteCustName(String executeCustName) {
        this.executeCustName = executeCustName;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTaskList() {
        return taskList;
    }

    public void setTaskList(String taskList) {
        this.taskList = taskList;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

}
