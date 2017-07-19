package com.eroom.web.constants;

public class CustConstants {

    public static final String CUST_SESSION = "cust_session";

    public static class TAccountBook {

        public static class BookItemId {
            /**
             * 用户资金
             */
            public static final String CUSTMONEY = "1000";

        }
    }
    
    /**
     * 微信地址跳转的常量定义
     */
    public static class Login {
        // 股票首页
        public static final String STOCK_INDEX = "1";

        // 服务首页
        public static final String SERVICE_INDEX = "2";

        // 个人中心
        public static final String PERSONAL_CENTER = "3";

        // 直播空间
        public static final String LIVE_ZONE = "4";

        // 每日签到
        public static final String DAY_SIGN_IN = "5";

        // 名家看点
        public static final String STOCK_INFO = "6";

        // 名师诊股
        public static final String DIAGNOSE_STK = "7";

        // 名师早参
        public static final String MASTERS_EARLY = "8";

        // 投资日历
        public static final String INVEST_CALENDAR = "9";
    }

    public static class CmCust {

        public static class State {
            // 正常状态
            public static final String NOLMAL = "1";

            // 注销状态
            public static final String LOGOUT = "0";
        }

        /** 认证标识 */
        public static class AuthFlag {
            // 未认证
            public static final String NOT_AUTH = "0";

            // 已手机认证
            public static final String AUTHED = "1";
        }

        public static class AgentFlag {
            // 是经纪人
            public static final String YES = "1";

            // 不是经纪人
            public static final String NO = "0";
        }

        public static class AgentType {
            public static final String ONE = "1";

            public static final String TWO = "2";

            public static final String THREE = "3";
        }

        /**
         * 客户来源
         */
        public static class FromType {
            // tel：电话
            public static final String TEL = "tel";

            // net：网络
            public static final String NET = "net";

            // prox：代理
            public static final String PROX = "prox";

            // callout：呼出
            public static final String CALLOUT = "callout";

            // callin：呼入
            public static final String CALLIN = "callin";

            // other：其他
            public static final String OTHER = "other";

            // weixin：微信
            public static final String WEIXIN = "weixin";
        }

        public static class CustState {
            // 1：未成交
            public static final String UNSETTLED = "1";

            // 2：已成交
            public static final String TRADED = "2";

            // 3：已作废
            public static final String CANCELLED = "3";
        }

        /**
         * 分配销售标识
         */
        public static class AssignSaleFlag {
            // 1：已分配
            public static final String ASSIGNED = "1";

            // 0：未分配
            public static final String UNDISTRIBUTED = "0";
        }

        /**
         * 分配售后标识
         */
        public static class AssignServiceFlag {
            // 1：已分配
            public static final String ASSIGNED = "1";

            // 0：未分配
            public static final String UNDISTRIBUTED = "0";
        }
    }

    public static class TAccountBookDetail {

        public static class BusiType {
            /**
             * 下单支出
             */
            public static final String ORDER_PAY = "210000";

            /**
             * 退单返回
             */
            public static final String CANCEL_BACK = "300000";
        }

        public static class InOutFlag {
            /**
             * 入金
             */
            public static final String IN = "1";

            /**
             * 出金
             */
            public static final String OUT = "0";

        }
    }
}
