package com.eroom.web.service.rent;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.entity.po.TRoomRent;
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
    public List<RoomRentVo> getRoomRent() throws Exception {
        return roomRentDao.getTRoomRentVo();
    }

    /**
     * 通过custid获取租客租住的房屋TRoomRent
     * 
     * @param custId
     * @return
     * @throws Exception
     * @author tendy
     */
    public TRoomRent getTRoomRentByCustId(Long custId) throws Exception {
        TRoomRent t = roomRentDao.getTRoomRent(custId, RoomConstants.RoomRent.RentState.RENTED);
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

}
