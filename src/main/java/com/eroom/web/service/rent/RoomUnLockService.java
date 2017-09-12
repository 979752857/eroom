package com.eroom.web.service.rent;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.entity.po.RoomBook;
import com.eroom.web.entity.po.RoomRent;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        RoomBook t = roomBookDao.get(RoomBook.class, bookId);
        if(!checkApply(t)){
            return "error";
        }
        pw = "success";
        return pw;
    }

    /**
     * 判断预约信息是否合法
     * @param roomBook
     * @return
     */
    private boolean checkApply(RoomBook roomBook) throws Exception {
        //判断数据状态是否为已授权状态
        if(!RoomConstants.RoomBook.ApplyState.AGREE.equals(roomBook.getApplyState())){
            logger.info("预约房间未获得授权： bookId-"+ roomBook.getBookId()+" 状态信息："+ roomBook.getApplyState());
            return false;
        }

        //判断时间是否过期
        if(roomBook.getStartTime() != null && roomBook.getEndTime() != null){
            Date nowTime = DateUtil.getCurrentDate();
            if(!nowTime.after(roomBook.getStartTime()) || !nowTime.before(roomBook.getEndTime())){
                logger.info("预约房间的时间段错误： startTime-"+DateUtil.getDateString(roomBook.getStartTime(), DateUtil.YYYY_MM_DD_HH_MM_SS)+" endTime-"+DateUtil.getDateString(roomBook.getEndTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
                return false;
            }
        }else{
            logger.info("预约房间的预约日期错误： startTime-"+ roomBook.getStartTime()+" endTime-"+ roomBook.getEndTime());
            return false;
        }

        //判断房屋是否已经出租
        RoomRent roomRent = roomRentDao.get(RoomRent.class, roomBook.getRentId());
        if(!RoomConstants.RoomRent.RentState.RENTING.equals(roomRent.getRentState())){
            logger.info("预约房间不在出租状态： rentState-"+ roomRent.getRentState());
            return false;
        }

        return true;
    }
}
