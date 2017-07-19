package com.eroom.web.constants;

public class WechatConstants {

    public static class WechatNoticeTemplet {
        /**
         * 模板类型
         */
        public static class TempletType {
            // 2001：未支付通知
            public static final String NOT_PAID = "2001";

            // 2002：支付成功
            public static final String PAY_SUCCESS = "2002";

            // 3001:名师诊股
            public static final String CHECK_STOCK = "3001";
        }
    }

    public static class WechatNotice {
        /**
         * 状态
         */
        public static class State {
            // 0：未推送
            public static final Integer NOT_SEND = 0;

            // 1：已推送
            public static final Integer SEND_SUCCES = 1;

            // 2：推送失败
            public static final Integer SEND_FAIL = 2;
        }
    }

}
