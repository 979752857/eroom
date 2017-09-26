package com.eroom.web.entity.po;

import javax.persistence.*;

/**
 * Created by tendy on 2017/9/5.
 */
@Entity
@Table(name = "base_city_bussiness", schema = "eroom", catalog = "")
public class BaseCityBussiness {
    private long id;
    private long districtId;
    private String bussinessName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "district_id")
    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
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
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (districtId ^ (districtId >>> 32));
        result = 31 * result + (bussinessName != null ? bussinessName.hashCode() : 0);
        return result;
    }
}
