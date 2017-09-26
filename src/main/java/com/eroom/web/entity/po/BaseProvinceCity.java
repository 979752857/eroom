package com.eroom.web.entity.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tendy on 2017/9/5.
 */
@Entity
@Table(name = "base_province_city", schema = "eroom", catalog = "")
public class BaseProvinceCity {
    private long id;
    private String provinceName;
    private String provinceEname;
    private String cityName;
    private String cityEname;
    private int state;
    private Date serviceTime;
    private Integer baseFee;
    private String rule;
    private String pinganProvinceCode;
    private Integer baiduCityCode;
    private Byte hot;
    private Byte cityLevel;

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
    @Column(name = "service_time")
    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Basic
    @Column(name = "base_fee")
    public Integer getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Integer baseFee) {
        this.baseFee = baseFee;
    }

    @Basic
    @Column(name = "rule")
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Basic
    @Column(name = "pingan_province_code")
    public String getPinganProvinceCode() {
        return pinganProvinceCode;
    }

    public void setPinganProvinceCode(String pinganProvinceCode) {
        this.pinganProvinceCode = pinganProvinceCode;
    }

    @Basic
    @Column(name = "baidu_city_code")
    public Integer getBaiduCityCode() {
        return baiduCityCode;
    }

    public void setBaiduCityCode(Integer baiduCityCode) {
        this.baiduCityCode = baiduCityCode;
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

        BaseProvinceCity that = (BaseProvinceCity) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (provinceName != null ? !provinceName.equals(that.provinceName) : that.provinceName != null) return false;
        if (provinceEname != null ? !provinceEname.equals(that.provinceEname) : that.provinceEname != null)
            return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (cityEname != null ? !cityEname.equals(that.cityEname) : that.cityEname != null) return false;
        if (serviceTime != null ? !serviceTime.equals(that.serviceTime) : that.serviceTime != null) return false;
        if (baseFee != null ? !baseFee.equals(that.baseFee) : that.baseFee != null) return false;
        if (rule != null ? !rule.equals(that.rule) : that.rule != null) return false;
        if (pinganProvinceCode != null ? !pinganProvinceCode.equals(that.pinganProvinceCode) : that.pinganProvinceCode != null)
            return false;
        if (baiduCityCode != null ? !baiduCityCode.equals(that.baiduCityCode) : that.baiduCityCode != null)
            return false;
        if (hot != null ? !hot.equals(that.hot) : that.hot != null) return false;
        if (cityLevel != null ? !cityLevel.equals(that.cityLevel) : that.cityLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (provinceName != null ? provinceName.hashCode() : 0);
        result = 31 * result + (provinceEname != null ? provinceEname.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (cityEname != null ? cityEname.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (serviceTime != null ? serviceTime.hashCode() : 0);
        result = 31 * result + (baseFee != null ? baseFee.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (pinganProvinceCode != null ? pinganProvinceCode.hashCode() : 0);
        result = 31 * result + (baiduCityCode != null ? baiduCityCode.hashCode() : 0);
        result = 31 * result + (hot != null ? hot.hashCode() : 0);
        result = 31 * result + (cityLevel != null ? cityLevel.hashCode() : 0);
        return result;
    }
}
