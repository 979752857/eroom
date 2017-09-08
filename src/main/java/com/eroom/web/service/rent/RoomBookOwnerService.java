package com.eroom.web.service.rent;

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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomBookOwnerService {

    @Resource
    private RoomBookDao roomBookDao;

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
     * 房东获取所有预约信息
     * 
     * @return
     * @throws Exception
     */
    public List<RoomBookVo> getFdRoomBookAll(Long custId) throws Exception {
        return roomBookDao.getFdRoomBookVo(custId, null);
    }
}
