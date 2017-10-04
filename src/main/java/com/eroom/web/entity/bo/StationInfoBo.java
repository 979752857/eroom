package com.eroom.web.entity.bo;

/**
 * Created by tendy on 2017/10/2.
 */
public class StationInfoBo {

    private Long stationId;

    private Long subwayId;

    private String stationName;

    private String subwayName;

    public StationInfoBo(Long stationId, Long subwayId, String stationName, String subwayName) {
        this.stationId = stationId;
        this.subwayId = subwayId;
        this.stationName = stationName;
        this.subwayName = subwayName;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Long subwayId) {
        this.subwayId = subwayId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getSubwayName() {
        return subwayName;
    }

    public void setSubwayName(String subwayName) {
        this.subwayName = subwayName;
    }
}
