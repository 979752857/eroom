package com.eroom.web.service.cust;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.cust.TCustRoomCollectDao;
import com.eroom.web.entity.po.TCustRoomCollect;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.utils.util.DateUtil;

@Service
public class CustCenterService {

    @Resource
    private TCustRoomCollectDao tCustRoomCollectDao;
    
    public List<RoomRentVo> getCustRoomCollect(Long custId) throws Exception{
        return tCustRoomCollectDao.getCollectTRoomRentVo(custId);
    }
    
    public TCustRoomCollect getCustRoomCollect(Long custId, Long rentId) throws Exception{
        return tCustRoomCollectDao.getTCustRoomCollect(custId, rentId);
    }
    
    public TCustRoomCollect deleteCustRoomCollect(Long collectId) throws Exception{
        TCustRoomCollect t = tCustRoomCollectDao.get(TCustRoomCollect.class, collectId);
        t.setState(SystemConstants.State.INACTIVE);
        tCustRoomCollectDao.update(t);
        return t;
    }
    
    public TCustRoomCollect addCustRoomCollect(Long custId, Long rentId) throws Exception{
        TCustRoomCollect t = new TCustRoomCollect();
        t.setCustId(custId);
        t.setRentId(rentId);
        t.setState(SystemConstants.State.ACTIVE);
        t.setCreateTime(DateUtil.getCurrentDate());
        return tCustRoomCollectDao.save(t);
    }
    
}
