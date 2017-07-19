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
 * SystemStaffRoleRel generated by hbm2java
 */
@Entity
@Table(name = "system_staff_role_rel", catalog = "eroom")
public class SystemStaffRoleRel implements java.io.Serializable {

    private Long srRelId;

    private String createStaffNo;

    private Date createTime;

    private long roleId;

    private long staffId;

    private String state;

    private String updateStaffNo;

    private Date updateTime;

    public SystemStaffRoleRel() {
    }

    public SystemStaffRoleRel(Date createTime, long roleId, long staffId, String state) {
        this.createTime = createTime;
        this.roleId = roleId;
        this.staffId = staffId;
        this.state = state;
    }

    public SystemStaffRoleRel(String createStaffNo, Date createTime, long roleId, long staffId,
            String state, String updateStaffNo, Date updateTime) {
        this.createStaffNo = createStaffNo;
        this.createTime = createTime;
        this.roleId = roleId;
        this.staffId = staffId;
        this.state = state;
        this.updateStaffNo = updateStaffNo;
        this.updateTime = updateTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "sr_rel_id", unique = true, nullable = false)
    public Long getSrRelId() {
        return this.srRelId;
    }

    public void setSrRelId(Long srRelId) {
        this.srRelId = srRelId;
    }

    @Column(name = "create_staff_no", length = 16)
    public String getCreateStaffNo() {
        return this.createStaffNo;
    }

    public void setCreateStaffNo(String createStaffNo) {
        this.createStaffNo = createStaffNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "role_id", nullable = false)
    public long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "staff_id", nullable = false)
    public long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    @Column(name = "state", nullable = false, length = 4)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "update_staff_no", length = 16)
    public String getUpdateStaffNo() {
        return this.updateStaffNo;
    }

    public void setUpdateStaffNo(String updateStaffNo) {
        this.updateStaffNo = updateStaffNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", length = 19)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
