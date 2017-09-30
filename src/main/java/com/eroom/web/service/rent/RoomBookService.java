package com.eroom.web.service.rent;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.constants.SystemConstants;
import com.eroom.web.constants.TaskRunningConstants;
import com.eroom.web.dao.rent.RoomBookDao;
import com.eroom.web.dao.rent.RoomRentDao;
import com.eroom.web.dao.task.TaskRunningDao;
import com.eroom.web.entity.po.RoomBook;
import com.eroom.web.entity.po.RoomRent;
import com.eroom.web.entity.po.TaskRunning;
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
public class RoomBookService extends BaseService{

    @Resource
    private RoomRentDao roomRentDao;

    @Resource
    private RoomBookDao roomBookDao;

    @Resource
    private TaskRunningDao taskRunningDao;

    @Resource
    private SystemCfgService systemCfgService;
    
    /**
     * 取消预约看房
     * 
     * @return
     * @throws Exception
     */
    public void deleteRoomBook(Long bookId) throws Exception {
        RoomBook t = roomBookDao.get(RoomBook.class, bookId);
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
        RoomBook t = roomBookDao.get(RoomBook.class, bookId);
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
        RoomBook t = roomBookDao.get(RoomBook.class, bookId);
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
        RoomRent roomRent = roomRentDao.get(RoomRent.class, rentId);
        // 判断房屋是否已经出租
        if (!RoomConstants.RoomRent.RentState.RENTING.equals(roomRent.getRentState())) {
            throw new BusinessException("此房间暂未出租");
        }
        // 判断时间是否早于当前时间
        if (DateUtil.getCurrentDate().after(time)){
            throw new BusinessException("预约时间不能早于当前时间");
        }
        // 判断是否已经预约过
        String limitStr = systemCfgService.getCfgValue(SystemConstants.SystemCfg.CfgType.ROOM_BOOK,
                SystemConstants.SystemCfg.CfgCode.BOOK_LIMIT);
        Date endTime = new Date(time.getTime() + Long.parseLong(limitStr) * 1000);
        RoomBook t = roomBookDao.getTRoomBookApplied(custId, roomRent.getRoomId(), time, endTime);
        if (t != null) {
            throw new BusinessException("这个时间段已经预约过啦");
        }

        RoomBook tRoomBook = new RoomBook();
        tRoomBook.setBedRoomId(roomRent.getBedroomId());
        tRoomBook.setRoomId(roomRent.getRoomId());
        tRoomBook.setCreateTime(DateUtil.getCurrentDate());
        tRoomBook.setCustOwnerId(roomRent.getCustOwnerId());
        tRoomBook.setCustRenterId(custId);
        tRoomBook.setRentId(rentId);
        tRoomBook.setStartTime(time);
        tRoomBook.setEndTime(endTime);
        tRoomBook.setApplyState(RoomConstants.RoomBook.ApplyState.APPLYING);
        tRoomBook.setBedRoomId(roomRent.getBedroomId());
        tRoomBook.setUpdateTime(DateUtil.getCurrentDate());
        tRoomBook = roomBookDao.save(tRoomBook);

        //添加定时任务，预约状态改为授权状态
        TaskRunning taskRunning = new TaskRunning();
        taskRunning.setState(TaskRunningConstants.State.WAITING);
        taskRunning.setCreateTime(DateUtil.getCurrentDate());
        taskRunning.setChangeTime(DateUtil.getOffsetSecondsDate(DateUtil.getCurrentDate(), RoomConstants.RoomBook.AGREE_TIME));
        taskRunning.setColumn(TaskRunningConstants.Table.RoomBook.APPLY_STATE);
        taskRunning.setTable(TaskRunningConstants.Table.RoomBook.TABLE_NAME);
        taskRunning.setMainColumn(TaskRunningConstants.Table.RoomBook.MAIN_COLUMN);
        taskRunning.setMainId(tRoomBook.getBookId());
        taskRunning.setOrigin(tRoomBook.getApplyState());
        taskRunning.setNewValue(RoomConstants.RoomBook.ApplyState.AGREE);
        taskRunningDao.save(taskRunning);

        //添加定时任务，授权状态改为看房中状态
        taskRunning = new TaskRunning();
        taskRunning.setState(TaskRunningConstants.State.WAITING);
        taskRunning.setCreateTime(DateUtil.getCurrentDate());
        taskRunning.setChangeTime(tRoomBook.getStartTime());
        taskRunning.setColumn(TaskRunningConstants.Table.RoomBook.APPLY_STATE);
        taskRunning.setTable(TaskRunningConstants.Table.RoomBook.TABLE_NAME);
        taskRunning.setMainColumn(TaskRunningConstants.Table.RoomBook.MAIN_COLUMN);
        taskRunning.setMainId(tRoomBook.getBookId());
        taskRunning.setOrigin(RoomConstants.RoomBook.ApplyState.AGREE);
        taskRunning.setNewValue(RoomConstants.RoomBook.ApplyState.LOOKING);
        taskRunningDao.save(taskRunning);

        //添加定时任务，改为过期状态
        taskRunning = new TaskRunning();
        taskRunning.setState(TaskRunningConstants.State.WAITING);
        taskRunning.setCreateTime(DateUtil.getCurrentDate());
        taskRunning.setChangeTime(tRoomBook.getEndTime());
        taskRunning.setColumn(TaskRunningConstants.Table.RoomBook.APPLY_STATE);
        taskRunning.setTable(TaskRunningConstants.Table.RoomBook.TABLE_NAME);
        taskRunning.setMainColumn(TaskRunningConstants.Table.RoomBook.MAIN_COLUMN);
        taskRunning.setMainId(tRoomBook.getBookId());
        taskRunning.setOrigin(RoomConstants.RoomBook.ApplyState.LOOKING);
        taskRunning.setNewValue(RoomConstants.RoomBook.ApplyState.TIMEOUT);
        taskRunningDao.save(taskRunning);

    }

}
