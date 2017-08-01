package com.eroom.web.service.rent;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.entity.po.TRoomBook;
import com.eroom.web.entity.po.TRoomRent;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.service.BaseService;
import com.eroom.web.service.system.SystemCfgService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomUnLockService extends BaseService {

    @Resource
    private RoomRentDao roomRentDao;

    @Resource
    private RoomBookDao roomBookDao;

    /**
     * 获取预约房间的密码
     * 
     * @return
     * @throws Exception
     */
    public String getRoomPassword(Long bookId) throws Exception {
        String pw = "";
        TRoomBook t = roomBookDao.get(TRoomBook.class, bookId);
        if(!checkApply(t)){
            return "error";
        }
        pw = "success";
        return pw;
    }

    /**
     * 判断预约信息是否合法
     * @param tRoomBook
     * @return
     */
    private boolean checkApply(TRoomBook tRoomBook) throws Exception {
        //判断数据状态是否为已授权状态
        if(!RoomConstants.RoomBook.ApplyState.AGREE.equals(tRoomBook.getApplyState())){
            logger.info("预约房间未获得授权： bookId-"+tRoomBook.getBookId()+" 状态信息："+tRoomBook.getApplyState());
            return false;
        }

        //判断时间是否过期
        if(tRoomBook.getStartTime() != null && tRoomBook.getEndTime() != null){
            Date nowTime = DateUtil.getCurrentDate();
            if(!nowTime.after(tRoomBook.getStartTime()) || !nowTime.before(tRoomBook.getEndTime())){
                logger.info("预约房间的时间段错误： startTime-"+DateUtil.getDateString(tRoomBook.getStartTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)+" endTime-"+DateUtil.getDateString(tRoomBook.getEndTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
                return false;
            }
        }else{
            logger.info("预约房间的预约日期错误： startTime-"+tRoomBook.getStartTime()+" endTime-"+tRoomBook.getEndTime());
            return false;
        }

        //判断房屋是否已经出租
        TRoomRent tRoomRent = roomRentDao.get(TRoomRent.class, tRoomBook.getRentId());
        if(!RoomConstants.RoomRent.RentState.RENTING.equals(tRoomRent.getRentState())){
            logger.info("预约房间不在出租状态： rentState-"+tRoomRent.getRentState());
            return false;
        }

        return true;
    }
}
