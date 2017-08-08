package com.eroom.web.constants;

public class PayConstants {
    public static class PayOrder{

        /**
         * 订单列表全部订单显示限制
         */
        public static Integer ORDER_ALL_LIMIT = 20;

        public static class OrderState{
            /**
             * 待支付
             */
            public static final String WAITING = "01";
            /**
             * 已支付
             */
            public static final String FINISH = "02";
            /**
             * 支付取消
             */
            public static final String CANCEL = "03";
            /**
             * 支付失败
             */
            public static final String FAIL = "04";
        }
    }
}
