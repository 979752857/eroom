package com.eroom.web.service.cust;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eroom.web.constants.SystemConstants;
import com.eroom.web.dao.cust.TCustRoomCollectDao;
import com.eroom.web.entity.po.CustRoomCollect;
import com.eroom.web.entity.vo.rent.RoomRentVo;
import com.eroom.web.utils.util.DateUtil;

@Service
public class CustCenterService {

    @Resource
    private TCustRoomCollectDao tCustRoomCollectDao;
    
    public List<RoomRentVo> getCustRoomCollect(Long custId) throws Exception{
        return tCustRoomCollectDao.getCollectTRoomRentVo(custId);
    }
    
    public CustRoomCollect getCustRoomCollect(Long custId, Long rentId) throws Exception{
        return tCustRoomCollectDao.getTCustRoomCollect(custId, rentId);
    }
    
    public CustRoomCollect deleteCustRoomCollect(Long collectId) throws Exception{
        CustRoomCollect t = tCustRoomCollectDao.get(CustRoomCollect.class, collectId);
        t.setState(SystemConstants.State.INACTIVE);
        tCustRoomCollectDao.update(t);
        return t;
    }
    
    public CustRoomCollect addCustRoomCollect(Long custId, Long rentId) throws Exception{
        CustRoomCollect t = new CustRoomCollect();
        t.setCustId(custId);
        t.setRentId(rentId);
        t.setState(SystemConstants.State.ACTIVE);
        t.setCreateTime(DateUtil.getCurrentDate());
        return tCustRoomCollectDao.save(t);
    }
    
}
