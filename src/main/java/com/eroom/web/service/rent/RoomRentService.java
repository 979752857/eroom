package com.eroom.web.service.rent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.entity.bo.LocationRangeBo;
import com.eroom.web.entity.bo.RoomRentBo;
import com.eroom.web.entity.vo.rent.RoomDetailVo;
import com.eroom.web.service.base.BaseSubwayStationService;
import com.eroom.web.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.entity.po.RoomRent;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.service.system.SystemCfgService;

@Service
public class RoomRentService {

    @Resource
    private RoomRentDao roomRentDao;

    @Resource
    private RoomBookDao roomBookDao;

    @Resource
    private SystemCfgService systemCfgService;

    @Resource
    private BaseSubwayStationService baseSubwayStationService;

    /**
     * 获取热门房源
     * 
     * @return
     * @throws Exception
     */
    public List<RoomRentVo> getRoomRentHot(int limit) throws Exception {
        return roomRentDao.getTRoomRentVoHot(limit);
    }

    /**
     * 获取所有房源
     * 
     * @return
     * @throws Exception
     */
    public List<RoomRentVo> getRoomRent(RoomRentBo roomRentBo) throws Exception {
        if(roomRentBo == null){
            roomRentBo = new RoomRentBo();
        }
        if(roomRentBo.getStationId() != null){
            LocationRangeBo rangeBo = baseSubwayStationService.getLocationRange(roomRentBo.getStationId());
            roomRentBo.setLocationRange(rangeBo);
        }
        return roomRentDao.getTRoomRentVo(roomRentBo);
    }

    /**
     * 获取所有房源
     *
     * @return
     * @throws Exception
     */
    public List<RoomRentVo> getRoomRent(RoomRentBo roomRentBo, int curPage) throws Exception {
        if(roomRentBo == null){
            roomRentBo = new RoomRentBo();
        }
        Map<String, Object> map = new HashMap<>();
        Long page = 0L;
        Long totle = roomRentDao.countRoomRentVo(roomRentBo);
        if(totle != null){
            page = totle/ RoomConstants.LAST_ROOM_LIMIT;
            if(totle%RoomConstants.LAST_ROOM_LIMIT != 0){
                page += 1;
            }
        }
        if(roomRentBo.getStationId() != null){
            LocationRangeBo rangeBo = baseSubwayStationService.getLocationRange(roomRentBo.getStationId());
            roomRentBo.setLocationRange(rangeBo);
        }
        List<RoomRentVo> list = roomRentDao.getTRoomRentVo(roomRentBo, RoomConstants.LAST_ROOM_LIMIT, curPage);
        map.put("list", list);
        map.put("totle", totle);
        map.put("page", page);
        map.put("curPage", curPage);
        return list;
    }

    /**
     * 通过custid获取租客租住的房屋TRoomRent
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public RoomRent getTRoomRentByCustId(Long custId) throws Exception {
        RoomRent t = roomRentDao.getTRoomRent(custId, RoomConstants.RoomRent.RentState.RENTED);
        return t;
    }

    /**
     * 通过custid获取租客租住的房屋RoomRentVo
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getRoomRentVoByCustId(Long custId) throws Exception {
        List<RoomRentVo> list = roomRentDao.getTRoomRentVo(custId);
        return list;
    }
    
    /**
     * 通过custid获取房东租住的房屋RoomRentVo
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getFdRoomRentVoAll(Long custId) throws Exception {
        List<RoomRentVo> list = roomRentDao.getFdTRoomRentVo(custId, null);
        return list;
    }
    
    /**
     * 通过custid获取房东出租中的房屋RoomRentVo
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getFdRoomRentVoRenting(Long custId) throws Exception {
        List<RoomRentVo> list = roomRentDao.getFdTRoomRentVo(custId, RoomConstants.RoomRent.RentState.RENTING);
        return list;
    }
    
    /**
     * 通过custid获取房东租住中的房屋RoomRentVo
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public List<RoomRentVo> getFdRoomRentVoRented(Long custId) throws Exception {
        List<RoomRentVo> list = roomRentDao.getFdTRoomRentVo(custId, RoomConstants.RoomRent.RentState.RENTED);
        return list;
    }

    /**
     * 通过custid获取最先到期租客租住的房屋RoomRentVo
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public RoomRentVo getLastRoomRentVoByCustId(Long custId) throws Exception {
        RoomRentVo list = roomRentDao.getLastTRoomRentVo(custId);
        return list;
    }

    /**
     * 通过rentid获取出租房源信息
     *
     * @param rentId
     * @return
     * @throws Exception
     * @author tendy
     */
    public RoomDetailVo getRoomDetailVoByRentId(Long rentId) throws Exception {
        if(rentId == null){
            throw new BusinessException(SystemConstants.ExceptionMsg.PARAM_NULL_EXCEPTION_MSG);
        }
        RoomDetailVo vo = roomRentDao.getRoomDetailVo(rentId);
        return vo;
    }

    /**
     * 获取位置的房源筛选条件
     * @return
     */
    public Map<String, String> getRoomLocationCondition(){
        Map<String, String> map = new HashMap<>();

        return map;
    }

    /**
     * 修改出租房源信息的状态数据
     * @param rentId
     * @return
     */
    public RoomRent updateRoomRentPaid(Long rentId) throws Exception {
        RoomRent roomRent = roomRentDao.get(RoomRent.class, rentId);
        roomRent.setRentState(RoomConstants.RoomRent.RentState.RENTED);
        roomRent = roomRentDao.save(roomRent);
        return roomRent;
    }
}
