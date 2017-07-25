package com.eroom.web.entity.po;

import javax.persistence.*;

/**
 * Created by tendy on 2017/7/25.
 */
@Entity
@Table(name = "base_province_city", schema = "eroom", catalog = "")
public class BaseProvinceCityEntity {
    private int id;
    private String provinceName;
    private String provinceEname;
    private String cityName;
    private String cityEname;
    private int state;
    private Byte hot;
    private Byte cityLevel;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Basic
    @Column(name = "province_ename")
    public String getProvinceEname() {
        return provinceEname;
    }

    public void setProvinceEname(String provinceEname) {
        this.provinceEname = provinceEname;
    }

    @Basic
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "city_ename")
    public String getCityEname() {
        return cityEname;
    }

    public void setCityEname(String cityEname) {
        this.cityEname = cityEname;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "hot")
    public Byte getHot() {
        return hot;
    }

    public void setHot(Byte hot) {
        this.hot = hot;
    }

    @Basic
    @Column(name = "city_level")
    public Byte getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(Byte cityLevel) {
        this.cityLevel = cityLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseProvinceCityEntity that = (BaseProvinceCityEntity) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (provinceName != null ? !provinceName.equals(that.provinceName) : that.provinceName != null) return false;
        if (provinceEname != null ? !provinceEname.equals(that.provinceEname) : that.provinceEname != null)
            return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (cityEname != null ? !cityEname.equals(that.cityEname) : that.cityEname != null) return false;
        if (hot != null ? !hot.equals(that.hot) : that.hot != null) return false;
        if (cityLevel != null ? !cityLevel.equals(that.cityLevel) : that.cityLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (provinceName != null ? provinceName.hashCode() : 0);
        result = 31 * result + (provinceEname != null ? provinceEname.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (cityEname != null ? cityEname.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (hot != null ? hot.hashCode() : 0);
        result = 31 * result + (cityLevel != null ? cityLevel.hashCode() : 0);
        return result;
    }
}
