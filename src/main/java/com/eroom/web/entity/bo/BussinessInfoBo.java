package com.eroom.web.entity.bo;

/**
 * Created by tendy on 2017/10/2.
 */
public class BussinessInfoBo {

    private Long bussinessId;

    private Long districtId;

    private String bussinessName;

    private String districtName;

    public BussinessInfoBo(Long bussinessId, Long districtId, String bussinessName, String districtName) {
        this.bussinessId = bussinessId;
        this.districtId = districtId;
        this.bussinessName = bussinessName;
        this.districtName = districtName;
    }

    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
