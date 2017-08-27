package com.eroom.web.constants;

/**
 * 支付常量
 */
public class PaymentConstants {
    
    /**
     * 显示3条最新缴费明细记录
     */
    public static final int LIMIT = 3;
    /**
     * 显示默认显示15条缴费明细记录
     */
    public static final int PAYDETAI_LIMIT = 15;

    public static final class PAY_CHANNEL {

        public static final String WX = "1";

        public static final String ZFB = "2";

    }

    public static final class STATUS {

        public static final String WAIT_PAY = "00";

        public static final String PAYING = "01";

        public static final String PAYED_SUCCESS = "02";

        public static final String PAYED_FAILED = "03";

        public static final String PAYED_EXCEPTION = "04";

        public static final String WAIT_REFUND = "10";

        public static final String REFUNDING = "11";

        public static final String REFUND_SUCCESS = "12";

        public static final String REFUND_FAIL = "13";

        public static final String REFUND_EXCEPTION = "14";

        public static final String WAIT_WITHDRAW = "21";

        public static final String WITHDRAW_SUCCESS = "22";

        public static final String WITHDRAW_FAILED = "23";

    }

    public static final class PAY_TYPE {

        // 充值
        public static final String IN = "1";

        // 提现
        public static final String OUT = "2";

        // 退款
        public static final String RETURN = "3";

        // 支付
        public static final String PAY = "4";

    }

    public static final class WEIXIN_TRADE_TYPE {
        public static final String NATIVE = "NATIVE";

        public static final String JSAPI = "JSAPI";

        public static final String APP = "APP";
    }

    public static final class WX_RETURN_CODE {
        public static final String SUCCESS = "SUCCESS";

        public static final String FAIL = "FAIL";
    }

    public static final class SCAN_RETURN_CODE {
        public static final String SUCCESS = "0";
    }

    public static final class STATE {

        public static final String NO_CHECK = "1";

        public static final String CHECK_SUCCESS = "2";

        public static final String CHECK_FAILED = "3";

    }

    public static final class DEAL_STATE {

        public static final String WAIT_DEAL = "1";

        public static final String DEAL_SUCCESS = "2";

        public static final String DEAL_FAILED = "3";

    }

}
