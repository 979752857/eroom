package com.eroom.web.entity.vo.base;

public class SessionVo {
    private Long custId;

    private String tenantNo;

    private String openid;
    
    public Long getCustId() {
        return custId;
    }

    public String getTenantNo() {
        return tenantNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public void setTenantNo(String tenantNo) {
        this.tenantNo = tenantNo;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "SessionVo [custId=" + custId + ", tenantNo=" + tenantNo + ", openid=" + openid
                + "]";
    }

}
