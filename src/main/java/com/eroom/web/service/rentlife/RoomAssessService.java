package com.eroom.web.service.rentlife;

import com.eroom.web.dao.room.RoomAssessDao;
import com.eroom.web.entity.po.RoomAssess;
import com.eroom.web.entity.vo.rentlife.RoomAssessVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoomAssessService {

    @Resource
    private RoomAssessDao roomAssessDao;

    /**
     * @author  Hone
     * @create  2017/8/20 17:33
     * @desc  添加评价
     **/
    public RoomAssess addAssess(Long targetId, Long custId, Double level, String content, String type,
                                MultipartFile[] files) throws Exception {
        //文件处理

        RoomAssess assess = new RoomAssess();
        assess.setTargetId(targetId);
        assess.setCustId(custId);
        assess.setContent(content);
        assess.setLevel(level);
        assess.setType(type);
        assess.setCreateTime(new Date());
        return roomAssessDao.save(assess);
    }

    public List<RoomAssessVo> getRoomAssess(Long targetId) throws Exception {
        List<RoomAssessVo> roomAssessVos = roomAssessDao.getRoomAssesses(targetId);
        return roomAssessVos;
    }
}
