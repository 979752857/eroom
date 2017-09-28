package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/9/28.
 */
@Entity
@Table(name = "task_running", schema = "eroom", catalog = "")
public class TaskRunning {
    private int id;
    private String table;
    private String column;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "table")
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Basic
    @Column(name = "column")
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskRunning that = (TaskRunning) o;

        if (id != that.id) return false;
        if (mainId != that.mainId) return false;
        if (table != null ? !table.equals(that.table) : that.table != null) return false;
        if (column != null ? !column.equals(that.column) : that.column != null) return false;
        if (origin != null ? !origin.equals(that.origin) : that.origin != null) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (changeTime != null ? !changeTime.equals(that.changeTime) : that.changeTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (mainColumn != null ? !mainColumn.equals(that.mainColumn) : that.mainColumn != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (column != null ? column.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (changeTime != null ? changeTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (mainColumn != null ? mainColumn.hashCode() : 0);
        result = 31 * result + (int) (mainId ^ (mainId >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskRunning{" +
                "id=" + id +
                ", table='" + table + '\'' +
                ", column='" + column + '\'' +
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
