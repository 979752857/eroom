package com.eroom.web.service.base;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.base.BaseCityBussinessDao;
import com.eroom.web.dao.base.BaseSubwayDao;
import com.eroom.web.dao.base.BaseSubwayStationDao;
import com.eroom.web.entity.bo.BussinessInfoBo;
import com.eroom.web.entity.bo.LocationRangeBo;
import com.eroom.web.entity.bo.StationInfoBo;
import com.eroom.web.entity.po.BaseSubwayStation;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.util.LocationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tendy on 2017/8/31.
 */
@Service
public class BaseSubwayStationService extends BaseService {

    @Resource
    private BaseSubwayStationDao baseSubwayStationDao;

    @Resource
    private BaseCityBussinessDao baseCityBussinessDao;

    @Resource
    private BaseSubwayDao baseSubwayDao;

    /**
     * 根据地铁站id获取范围信息
     * @param stationId
     * @return
     */
    public LocationRangeBo getLocationRange(Long stationId) throws Exception {
        BaseSubwayStation station = baseSubwayStationDao.getBaseSubwayStation(stationId);
        if(station == null || station.getLat() == null || station.getLon() == null){
            return null;
        }
        LocationRangeBo locationRange = LocationUtil.getAroundPrecision(station.getLat(), station.getLon(), SystemConstants.STATION_RANGE);
        return locationRange;
    }

    /**
     * 获取城市的地铁站信息
     * @return
     */
    public Map<String, Object> getSubwayStation(Long cityId) throws Exception {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("不限", RoomConstants.RoomCondition.DISTRICT);

        List<StationInfoBo> subwayList = baseSubwayStationDao.getBaseSubwayStationByCityId(cityId);
        Map<String, Object> subwayMap = new LinkedHashMap<>();
        subwayMap.put("不限", RoomConstants.RoomCondition.SUBWAY);
        for(StationInfoBo item : subwayList){
            Map<String, Object> stationMap = new LinkedHashMap<>();
            if(null == subwayMap.get(item.getSubwayName())){
                stationMap.put("不限", RoomConstants.RoomCondition.SUBWAY+"-"+item.getSubwayId());
                subwayMap.put(item.getSubwayName(), stationMap);
            }
            stationMap = (Map<String, Object>) subwayMap.get(item.getSubwayName());
            stationMap.put(item.getStationName(), RoomConstants.RoomCondition.SUBWAY+"-"+item.getSubwayId()+"-"+item.getStationId());
        }
        map.put("地铁", subwayMap);

        List<BussinessInfoBo> bussinessList = baseCityBussinessDao.getBaseCityBussinessByCityId(cityId);
        Map<String, Object> districtMap = new LinkedHashMap<>();
        districtMap.put("不限", RoomConstants.RoomCondition.DISTRICT);
        for(BussinessInfoBo item : bussinessList){
            Map<String, Object> bussinessMap = new LinkedHashMap<>();
            if(null == districtMap.get(item.getDistrictName())){
                bussinessMap.put("不限", RoomConstants.RoomCondition.DISTRICT+"-"+item.getDistrictId());
                districtMap.put(item.getDistrictName(), bussinessMap);
            }
            bussinessMap = (Map<String, Object>) districtMap.get(item.getDistrictName());
            bussinessMap.put(item.getBussinessName(), RoomConstants.RoomCondition.DISTRICT+"-"+item.getDistrictId()+"-"+item.getBussinessId());
        }
        map.put("商圈", districtMap);
        return map;
    }
}
