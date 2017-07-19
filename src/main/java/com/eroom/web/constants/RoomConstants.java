package com.eroom.web.constants;

public class RoomConstants {

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
    }
    
    public static class RoomBook{
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
             * 取消
             */
            public static final String CANCEL = "04";
        }
    }
}
