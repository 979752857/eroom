package com.eroom.web.dao.task;

import com.eroom.web.constants.TaskRunningConstants;
import com.eroom.web.dao.BaseDao;
import com.eroom.web.entity.po.TaskRunning;
import com.eroom.web.utils.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRunningDao extends BaseDao {

    /**
     * 获取需要执行的数据
     * @return TaskRunning
     * @throws Exception
     * @author tendy
     */
    public List<TaskRunning> getTaskRunningList() throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("from TaskRunning where state = :state and changeTime <= :changeTime order by createTime desc");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", TaskRunningConstants.State.WAITING);
        params.put("changeTime", DateUtil.getCurrentDate());

        List<TaskRunning> list = this.getList(hql.toString(), params);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 获取用户支付订单
     *
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public int updateDataBySql(TaskRunning taskRunning) throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sql.append("update "+taskRunning.getTable()+" set "+taskRunning.getColumn()+" = (case when "+taskRunning.getColumn()+"=:origin then :value else :origin1 end) where "+taskRunning.getMainColumn()+" = :id");
        params.put("origin", taskRunning.getOrigin());
        params.put("value", taskRunning.getNewValue());
        params.put("origin1", taskRunning.getOrigin());
        params.put("id", taskRunning.getMainId());
        int num = this.executeUpdateSql(sql.toString(), params);
        return num;
    }

    /**
     * 删除指定任务
     * @return PayOrder
     * @throws Exception
     * @author tendy
     */
    public int deleteTaskRunning(TaskRunning taskRunning) throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<String, Object>();
        sql.append("update task_running set state = :state where main_id = :mainId and origin = :origin and new_value = :newValue and table = :table and column = :column ");
        params.put("mainId", taskRunning.getMainId());
        params.put("origin", taskRunning.getOrigin());
        params.put("newValue", taskRunning.getNewValue());
        params.put("table", taskRunning.getTable());
        params.put("column", taskRunning.getColumn());
        params.put("state", TaskRunningConstants.State.DELETE);
        int num = this.executeUpdateSql(sql.toString(), params);
        return num;
    }
	
}
