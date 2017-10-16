package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/9/28.
 */
@Entity
@Table(name = "task_running", schema = "eroom", catalog = "")
public class TaskRunning {
    private Long id;
    private String tableName;
    private String columnName;
    private String origin;
    private String newValue;
    private Date changeTime;
    private Date createTime;
    private String remark;
    private String mainColumn;
    private long mainId;
    private String state;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "column_name")
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Basic
    @Column(name = "origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Basic
    @Column(name = "new_value")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Basic
    @Column(name = "change_time")
    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
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
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "main_column")
    public String getMainColumn() {
        return mainColumn;
    }

    public void setMainColumn(String mainColumn) {
        this.mainColumn = mainColumn;
    }

    @Basic
    @Column(name = "main_id")
    public long getMainId() {
        return mainId;
    }

    public void setMainId(long mainId) {
        this.mainId = mainId;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TaskRunning{" +
                "id=" + id +
                ", table='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", origin='" + origin + '\'' +
                ", newValue='" + newValue + '\'' +
                ", changeTime=" + changeTime +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", mainColumn='" + mainColumn + '\'' +
                ", mainId=" + mainId +
                ", state='" + state + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
