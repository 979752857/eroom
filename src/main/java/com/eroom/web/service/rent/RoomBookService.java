package com.eroom.web.service.rent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.entity.po.TRoomBook;
import com.eroom.web.entity.po.TRoomRent;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.service.system.SystemCfgService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.util.DateUtil;

@Service
public class RoomBookService {

    @Resource
    private RoomRentDao roomRentDao;

    @Resource
    private RoomBookDao roomBookDao;

    @Resource
    private SystemCfgService systemCfgService;
    
    /**
     * 取消预约看房
     * 
     * @return
     * @throws Exception
     */
    public void deleteRoomBook(Long bookId) throws Exception {
        TRoomBook t = roomBookDao.get(TRoomBook.class, bookId);
        t.setApplyState(RoomConstants.RoomBook.ApplyState.CANCEL);
        roomBookDao.update(t);
    }
    
    /**
     * 拒绝预约看房
     * 
     * @return
     * @throws Exception
     */
    public void updateRefuseRoomBook(Long bookId) throws Exception {
        TRoomBook t = roomBookDao.get(TRoomBook.class, bookId);
        t.setApplyState(RoomConstants.RoomBook.ApplyState.REFUSE);
        roomBookDao.update(t);
    }
    
    /**
     * 授权预约看房
     * 
     * @return
     * @throws Exception
     */
    public void updateAgreeRoomBook(Long bookId) throws Exception {
        TRoomBook t = roomBookDao.get(TRoomBook.class, bookId);
        t.setApplyState(RoomConstants.RoomBook.ApplyState.AGREE);
        roomBookDao.update(t);
    }

    /**
     * 获取申请预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getRoomBookApply(Long custId) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add(RoomConstants.RoomBook.ApplyState.APPLYING);
        list.add(RoomConstants.RoomBook.ApplyState.AGREE);
        return roomBookDao.getRoomBookVo(custId, list);
    }
    
    /**
     * 房东获取申请预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getFdRoomBookApply(Long custId) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add(RoomConstants.RoomBook.ApplyState.APPLYING);
        return roomBookDao.getFdRoomBookVo(custId, list);
    }

    /**
     * 获取已完成预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getRoomBookFinish(Long custId) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add(RoomConstants.RoomBook.ApplyState.FINISH);
        return roomBookDao.getRoomBookVo(custId, list);
    }
    
    /**
     * 房东获取已授权预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getFdRoomBookFinish(Long custId) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add(RoomConstants.RoomBook.ApplyState.AGREE);
        return roomBookDao.getRoomBookVo(custId, list);
    }
    
    /**
     * 获取所有预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getRoomBookAll(Long custId) throws Exception {
        return roomBookDao.getRoomBookVo(custId, null);
    }
    
    /**
     * 房东获取所有预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getFdRoomBookAll(Long custId) throws Exception {
        return roomBookDao.getFdRoomBookVo(custId, null);
    }

    /**
     * 申请预约看房
     * 
     * @return
     * @throws Exception
     */
    public void addApplyBook(Long custId, Long rentId, Date time) throws Exception {
        if (time == null) {
            throw new BusinessException("没有获取到预约时间");
        }
        TRoomRent tRoomRent = roomRentDao.get(TRoomRent.class, rentId);
        // 判断数据是否有效
        if (!SystemConstants.State.ACTIVE.equals(tRoomRent.getState())) {
            throw new BusinessException("无效的租房信息");
        }
        // 判断房屋是否已经出租
        if (!RoomConstants.RoomRent.RentState.RENTING.equals(tRoomRent.getRentState())) {
            throw new BusinessException("此房间暂未出租");
        }
        // 判断是否已经预约过
        String limitStr = systemCfgService.getCfgValue(SystemConstants.SystemCfg.CfgType.ROOM_BOOK,
                SystemConstants.SystemCfg.CfgCode.BOOK_LIMIT);
        Date endTime = new Date(time.getTime() + Long.parseLong(limitStr) * 1000);
        TRoomBook t = roomBookDao.getTRoomBookApplied(custId, tRoomRent.getRoomId(), time, endTime);
        if (t != null) {
            throw new BusinessException("这个时间段已经预约过啦");
        }

        TRoomBook tRoomBook = new TRoomBook();
        tRoomBook.setBedRoomId(tRoomRent.getBedroomId());
        tRoomBook.setRoomId(tRoomRent.getRoomId());
        tRoomBook.setCreateTime(DateUtil.getCurrentDate());
        tRoomBook.setCustOwnerId(tRoomRent.getCustOwnerId());
        tRoomBook.setCustRenterId(custId);
        tRoomBook.setRentId(rentId);
        tRoomBook.setStartTime(time);
        tRoomBook.setEndTime(endTime);
        tRoomBook.setState(SystemConstants.State.ACTIVE);
        tRoomBook.setApplyState(RoomConstants.RoomBook.ApplyState.APPLYING);
        tRoomBook.setBedRoomId(tRoomRent.getBedroomId());
        roomBookDao.save(tRoomBook);
    }
}
