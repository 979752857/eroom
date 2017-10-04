package com.eroom.web.constants;

public class RoomConstants {

    /**
     * 显示10条数据，其他加载更多
     */
    public static final int LAST_ROOM_LIMIT = 20;

    public static String ROOM_BOOK = "ROOM_BOOK";

    public static String BOOK_WAIT_TIME_LIMIT = "BOOK_WAIT_TIME_LIMIT";

    public static class RoomRent{
        public static class RentState{
            /**
             * 出租中
             */
            public static final String RENTING = "01";
            /**
             * 租住中
             */
            public static final String RENTED = "02";
            /**
             * 配置中
             */
            public static final String CONFIG = "03";
        }
        public static class RentType{
            /**
             * 整租
             */
            public static final String WHOLE_RENT = "01";
            /**
             * 合租
             */
            public static final String JOINT_RENT = "02";
            /**
             * 短租
             */
            public static final String SHORT_RENT = "03";
        }
    }

    public static class RoomRentSet{
        public static class RentTimeType{
            /**
             * 年租
             */
            public static final String RENT_YEAR = "01";
            public static final int RENT_YEAR_PAY_NUM = 12;
            /**
             * 月租
             */
            public static final String RENT_MOUNTH = "02";
            public static final int RENT_MOUNTH_PAY_NUM = 1;
            /**
             * 周租
             */
            public static final String RENT_WEEK = "03";
            public static final int RENT_WEEK_PAY_NUM = 1;
            /**
             * 3日租
             */
            public static final String RENT_THREEDAY = "04";
            public static final int RENT_THREEDAY_PAY_NUM = 1;
        }
    }
    
    public static class RoomBook{

        /**
         * 预约1小时后自动授权时间(秒)
         */
        public static int AGREE_TIME = 3600;

        public static class ApplyState{
            /**
             * 申请中
             */
            public static final String APPLYING = "00";
            /**
             * 授权
             */
            public static final String AGREE = "01";
            /**
             * 拒绝
             */
            public static final String REFUSE = "02";
            /**
             * 结束
             */
            public static final String FINISH = "03";
            /**
             * 看房中
             */
            public static final String LOOKING = "04";
            /**
             * 取消
             */
            public static final String CANCEL = "09";
            /**
             * 过期
             */
            public static final String TIMEOUT = "08";
        }
    }

    public static class RoomCondition{
        /**
         * 地铁为筛选条件
         */
        public static final String SUBWAY = "subway";
        /**
         * 行政区为筛选条件
         */
        public static final String DISTRICT = "district";

    }
}
