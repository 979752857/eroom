package com.eroom.web.constants;

/**
 * 系统常量配置信息
 */
public class SystemConstants {

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
}