package com.eroom.web.entity.po;

import javax.persistence.*;

/**
 * Created by tendy on 2017/7/25.
 */
@Entity
@Table(name = "base_city_district", schema = "eroom", catalog = "")
public class BaseCityDistrictEntity {
    private int id;
    private int cityId;
    private String districtName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "district_name")
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseCityDistrictEntity that = (BaseCityDistrictEntity) o;

        if (id != that.id) return false;
        if (cityId != that.cityId) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + cityId;
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        return result;
    }
}
