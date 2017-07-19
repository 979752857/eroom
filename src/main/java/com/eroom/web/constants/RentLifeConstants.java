package com.eroom.web.constants;

public class RentLifeConstants {

    /**
     * 显示3条最新留言
     */
    public static final int LAST_ROOM_MESSAGE_LIMIT = 3;
    /**
     * 显示3条最新任务
     */
    public static final int LAST_TASK_MESSAGE_LIMIT = 3;
    
    public static class TaskInfo{
        public static class TaskState{
            /**
             * 待完成
             */
            public static final String WAITTING = "00";
            /**
             * 已完成
             */
            public static final String FINISH = "01";
            /**
             * 未完成
             */
            public static final String UNFINISH = "02";
        }
    }
}
