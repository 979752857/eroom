package com.eroom.web.constants;

/**
 * 系统常量配置信息
 */
public class SystemConstants {

    /**
     * 显示10条数据，其他加载更多
     */
    public static final int LAST_DATA_LIMIT = 10;

    /**
     * 显示地铁站区域范围（米）
     */
    public static final int STATION_RANGE = 2000;

    /**
     * redis中相关的key或前后缀
     */
    public static class RedisKey {
        /**
         * 缓存key值前缀
         */
        public static final String CACHE_KEY_PREFIX = "EROOM.";

        /**
         * 访问UIP服务路径前缀(与其他应用保持一致)
         */
        public static final String REQ_UIP_URL_PREFIX = "REQ_UIP_URL_PREFIX";

        /**
         * 联系客服图片的当前索引(key为租户编码,value为具体值)
         */
        public static final String CONTACT_US_IMG_IDX = "EROOM.MEDIA_ID.INDEX";
    }
    
    public static class Wechat{
    	public static class AccessToken{
    		/**
    		 * accesstoken失效时间
    		 */
    		public static final int TIME_OUT = 100;
    	}
    }

    /**
     * 状态
     */
    public static class State {
        // 已生效
        public static final String ACTIVE = "1";

        // 已失效
        public static final String INACTIVE = "0";
    }

    /**
     * 系统参数表
     */
    public static class SystemCfg {
        public static class CfgType {
            
            // 预约看房
            public static final String ROOM_BOOK = "ROOM_BOOK";
            
        }

        public static class CfgCode {

            // 时间限制配置
            public static final String BOOK_LIMIT = "BOOK_LIMIT";

        }
    }

    /**
     * 参数定义表
     */
    public static class SystemParam {
        public static class ParamType {
        }

        public static class ParamSubType {
        }
    }

    public static class ExceptionMsg {
        /**
         * 方法获取成功返回码
         */
        public static String SUCCESS_CODE = "0";

        public static String SUCCESS_MSG = "success";

        /**
         * 方法获取参数出现异常
         */
        public static String PARAM_NULL_EXCEPTION_MSG = "参数错误";

        public static String PARAM_NULL_EXCEPTION_CODE = "102";

        /**
         * 数据库查询异常
         */
        public static String SYS_ERROR_EXCEPTION_MSG = "系统异常";

        public static String SYS_ERROR_EXCEPTION_CODE = "101";
    }
    /**
     * 返回code
     */
    public static class ResultCode {

        //成功
        public static final int SUCCESS = 0;

        //失败
        public static final int FAIL = 1;
    }
}