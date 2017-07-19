package com.eroom.web.dao.rentlife;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.eroom.web.constants.RentLifeConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.vo.rentlife.TaskInfoVo;

@Repository
public class RoomTaskDao extends BaseDao {

    /**
     * 获取最新任务信息
     * 
     * @return List<TaskInfoVo>
     * @throws Exception
     * @author tendy
     */
    public List<TaskInfoVo> getLastTaskInfoVo(Long custId, int limit) throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rentlife.TaskInfoVo( "
                + "tti.taskId, tti.roomId, tti.executeCustId, tcie.name, tti.custId, tci.name, tti.content, "
                + "tti.taskList, tti.state, tti.startTime, tti.endTime, tti.remark, tti.type, tti.updateTime, tti.taskState "
                + ") from TCustInfo tci, TCustInfo tcie, TRoomTask tti "
                + "where tti.executeCustId = :executeCustId and tti.custId = tci.custId and tti.executeCustId = tcie.custId and tti.taskState = :taskState order by tti.updateTime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("executeCustId", custId);
        params.put("taskState", RentLifeConstants.TaskInfo.TaskState.WAITTING);

        List<TaskInfoVo> list = this.getPageList(hql, params, 0, limit);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }

    /**
     * 获取近一个月任务信息
     * 
     * @return List<TaskInfoVo>
     * @throws Exception
     * @author tendy
     */
    public List<TaskInfoVo> getMonthTaskInfoVo(Long custId, Date time) throws Exception {
        String hql = "select new com.eroom.web.entity.vo.rentlife.TaskInfoVo( "
                + "tti.taskId, tti.roomId, tti.executeCustId, tcie.name, tti.custId, tci.name, tti.content, "
                + "tti.taskList, tti.state, tti.startTime, tti.endTime, tti.remark, tti.type, tti.updateTime, tti.taskState "
                + ") from TCustInfo tci, TCustInfo tcie, TRoomTask tti "
                + "where tti.executeCustId = :executeCustId and tti.endTime > :time and tti.custId = tci.custId and tti.executeCustId = tcie.custId and tti.taskState = :taskState order by tti.updateTime desc ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("executeCustId", custId);
        params.put("time", time);
        params.put("taskState", RentLifeConstants.TaskInfo.TaskState.WAITTING);

        List<TaskInfoVo> list = this.getList(hql, params);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }
}
