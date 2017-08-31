package com.eroom.web.entity.po;

import javax.persistence.*;

/**
 * Created by tendy on 2017/8/31.
 */
@Entity
@Table(name = "base_city_bussiness", schema = "eroom", catalog = "")
public class BaseCityBussiness {
    private int id;
    private int districtId;
    private String bussinessName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "district_id")
    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "bussiness_name")
    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseCityBussiness that = (BaseCityBussiness) o;

        if (id != that.id) return false;
        if (districtId != that.districtId) return false;
        if (bussinessName != null ? !bussinessName.equals(that.bussinessName) : that.bussinessName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + districtId;
        result = 31 * result + (bussinessName != null ? bussinessName.hashCode() : 0);
        return result;
    }
}
