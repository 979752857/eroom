package com.eroom.web.constants;

/**
 * Created by tendy on 2017/9/28.
 */
public class TaskRunningConstants {

    public static class Table{

        public static class RoomBook{

            public final static String TABLE_NAME = "room_book";

            public final static String MAIN_COLUMN = "book_id";
            /**
             * 预约申请状态
             */
            public final static String APPLY_STATE = "apply_state";
        }
    }

    public static class State{
        /**
         * 待执行
         */
        public final static String WAITING = "01";
        /**
         * 已执行
         */
        public final static String FINISH = "02";
        /**
         * 执行未处理
         */
        public final static String THROW = "03";
        /**
         * 删除
         */
        public final static String DELETE = "09";
        /**
         * 失败
         */
        public final static String FAIL = "04";
    }
}
