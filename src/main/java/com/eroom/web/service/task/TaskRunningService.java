package com.eroom.web.service.task;

import com.eroom.web.dao.task.TaskRunningDao;
import com.eroom.web.entity.po.TaskRunning;
import com.eroom.web.service.BaseService;
import com.eroom.web.utils.exception.BusinessException;
import com.eroom.web.utils.exception.LoginTimeOutException;
import com.eroom.web.utils.exception.SystemException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskRunningService extends BaseService {

    @Resource
    private TaskRunningDao taskRunningDao;

    public List<TaskRunning> getTaskRunningList() throws Exception {
        return taskRunningDao.getTaskRunningList();
    }

    public int updateDataBySql(TaskRunning taskRunning) throws Exception {
        return taskRunningDao.updateDataBySql(taskRunning);
    }

    public TaskRunning updateTaskRunning(TaskRunning taskRunning) throws Exception {
        taskRunningDao.update(taskRunning);
        return taskRunning;
    }
}