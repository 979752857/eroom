package com.eroom.web.entity.po;

import javax.persistence.*;

/**
 * Created by tendy on 2017/9/5.
 */
@Entity
@Table(name = "base_city_district", schema = "eroom", catalog = "")
public class BaseCityDistrict {
    private long id;
    private Long cityId;
    private String districtName;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
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

        BaseCityDistrict that = (BaseCityDistrict) o;

        if (id != that.id) return false;
        if (cityId != null ? !cityId.equals(that.cityId) : that.cityId != null) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        return result;
    }
}
