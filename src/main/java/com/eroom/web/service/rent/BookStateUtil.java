package com.eroom.web.service.rent;

import com.eroom.web.constants.RoomConstants;
import com.eroom.web.entity.vo.rent.RoomBookVo;
import com.eroom.web.service.system.SystemCfgService;
import com.eroom.web.utils.util.CollectionUtil;
import com.eroom.web.utils.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查预约状态的变更
 * 1、申请状态1小时候自动变为授权状态
 * 2、授权状态看房时间过期自动变为已过期状态
 */
@Component
public class BookStateUtil {

    private static final Log logger = LogFactory.getLog(BookStateUtil.class);

    private Map<String,Integer> map = new HashMap<String, Integer>();
    {
        map.put(RoomConstants.RoomBook.ApplyState.APPLYING, 0);
        map.put(RoomConstants.RoomBook.ApplyState.AGREE, 1);
        map.put(RoomConstants.RoomBook.ApplyState.REFUSE, 2);
        map.put(RoomConstants.RoomBook.ApplyState.FINISH, 3);
        map.put(RoomConstants.RoomBook.ApplyState.LOOKING, 4);
        map.put(RoomConstants.RoomBook.ApplyState.TIMEOUT, 8);
        map.put(RoomConstants.RoomBook.ApplyState.CANCEL, 9);
    }

    @Resource
    private SystemCfgService systemCfgService;

    private long applyingChangeTime = 0;

    /**
     * 检查预定状态是否转换
     * @param list
     * @return
     */
    public List<RoomBookVo> checkRoomBookVoState(List<RoomBookVo> list){
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        for(int i = 0; i<list.size(); i++){
            RoomBookVo vo  = list.get(i);
            vo = checkRoomBookVoState(vo);
            list.set(i, vo);
        }
        return list;
    }

    /**
     * 检查预定状态是否转换
     * @param vo
     * @return
     */
    public RoomBookVo checkRoomBookVoState(RoomBookVo vo){
        if(vo == null){
            return vo;
        }
        switch (map.get(vo.getApplyState())){
            case 0 :    //RoomConstants.RoomBook.ApplyState.APPLYING
                Date updateTime = vo.getUpdateTime();
                if(updateTime == null){
                    logger.warn("预约更新时间出错：" + vo.toString());
                    break;
                }
                long diffSecond = DateUtil.getSecondDif(DateUtil.getSysDate(), DateUtil.getTimestamp(updateTime));
                if(diffSecond >= getApplyingChangeTime()){
                    vo.setApplyState(RoomConstants.RoomBook.ApplyState.AGREE);
                }
                break;
            case 1 :    //RoomConstants.RoomBook.ApplyState.AGREE

                break;
            case 2 :    //RoomConstants.RoomBook.ApplyState.REFUSE

                break;
            case 3 :    //RoomConstants.RoomBook.ApplyState.FINISH
                Date startTime = vo.getStartTime();
                if(startTime == null){
                    logger.warn("预约开始时间出错：" + vo.toString());
                    break;
                }
                Date now = DateUtil.getCurrentDate();
                if(now.after(startTime)){
                    vo.setApplyState(RoomConstants.RoomBook.ApplyState.LOOKING);
                }
            case 4 :    //RoomConstants.RoomBook.ApplyState.LOOKING
                Date endTime = vo.getEndTime();
                if(endTime == null){
                    logger.warn("预约结束时间出错：" + vo.toString());
                    break;
                }
                Date nowTime = DateUtil.getCurrentDate();
                if(nowTime.after(endTime)){
                    vo.setApplyState(RoomConstants.RoomBook.ApplyState.TIMEOUT);
                }
                break;
            case 8 :    //RoomConstants.RoomBook.ApplyState.CANCEL

                break;
            case 9 :    //RoomConstants.RoomBook.ApplyState.TIMEOUT

                break;
            default:

                break;
        }
        return vo;
    }

    public long getApplyingChangeTime() {
        if(applyingChangeTime == 0){
            String value = null;
            try {
                value = systemCfgService.getCfgValue(RoomConstants.ROOM_BOOK, RoomConstants.BOOK_WAIT_TIME_LIMIT);
            } catch (Exception e) {
                value = "99999999";
                logger.error("获取预约申请自动变更时间失败:"+e.toString());
            }
            applyingChangeTime = Long.valueOf(value);
        }
        return applyingChangeTime;
    }
}
