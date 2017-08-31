package com.eroom.web.service.base;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.base.BaseSubwayStationDao;
import com.eroom.web.entity.bo.LocationRangeBo;
import com.eroom.web.entity.po.BaseSubwayStation;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.util.LocationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tendy on 2017/8/31.
 */
@Service
public class BaseSubwayStationService extends BaseService {

    @Resource
    private BaseSubwayStationDao baseSubwayStationDao;

    /**
     * 根据地铁站id获取范围信息
     * @param stationId
     * @return
     */
    public LocationRangeBo getLocationRange(Long stationId) throws Exception {
        BaseSubwayStation station = baseSubwayStationDao.getBaseSubwayStation(stationId);
        LocationRangeBo locationRange = LocationUtil.getAroundPrecision(station.getLat(), station.getLon(), SystemConstants.STATION_RANGE);
        return locationRange;
    }
}
