package com.eroom.web.utils.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eroom.web.utils.encrypt.MD5Util;
import org.apache.commons.lang.StringUtils;

public class StringUtil {

    public static boolean isBlank(String str) {
        if (null == str) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static String restrictLength(String strSrc, int iMaxLength) {
        if (strSrc == null) {
            return null;
        }
        if (iMaxLength <= 0) {
            return strSrc;
        }
        String strResult = strSrc;
        byte[] b = null;
        int iLength = strSrc.length();
        if (iLength > iMaxLength) {
            strResult = strResult.substring(0, iMaxLength);
            iLength = iMaxLength;
        }
        while (true) {
            b = strResult.getBytes();
            if (b.length <= iMaxLength) {
                break;
            }
            iLength--;
            strResult = strResult.substring(0, iLength);
        }
        return strResult;
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int num = random.nextInt(str.length());
            buf.append(str.charAt(num));
        }

        return buf.toString();
    }

    /**
     * 获取随机短信验证码
     * 
     * @return
     * @author tendy
     */
    public static String getRandomCode() {
        String str = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(str.length());
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    /**
     * 左补齐
     * 
     * @param target
     *            目标字符串
     * @param fix
     *            补齐字符
     * @param length
     *            目标长度
     * @return
     */
    public static String lPad(String target, String fix, int length) {
        if (target == null || fix == null || !(target.length() < length))
            return target;
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < length - target.length(); i++) {
            newStr.append(fix);
        }
        return newStr.append(target).toString();
    }

    /**
     * 右补齐
     * 
     * @param target
     *            目标字符串
     * @param fix
     *            补齐字符
     * @param length
     *            目标长度
     * @return
     */
    public static String rPad(String target, String fix, int length) {
        if (target == null || fix == null || !(target.length() < length))
            return target;
        StringBuffer newStr = new StringBuffer();
        newStr.append(target);
        for (int i = 0; i < length - target.length(); i++) {
            newStr.append(fix);
        }
        return newStr.toString();
    }

    /**
     * 字符串数据join操作
     * 
     * @param strs
     * @param spi
     * @return
     * @author zhoubo
     */
    public static String join(String[] strs, String spi) {
        StringBuffer buf = new StringBuffer();
        int step = 0;
        for (String str : strs) {
            buf.append(str);
            if (step++ < strs.length - 1)
                buf.append(spi);
        }
        return buf.toString();
    }

    // 默认值为无
    public static String toString2(Object obj) {
        if (obj == null) {
            return "无";
        } else if (obj == "") {
            return "无";
        }
        return obj.toString();
    }

    /**
     * 固网号码去除 区号-号码 中间的横杠 010-88018802
     * 
     * @param str
     * @return
     * @author mayt
     */
    public static String replaceServiceNumBar(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("-");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 是否为有理数，例：<BR>
     * 123<BR>
     * 1.23<BR>
     * -1.23<BR>
     * 
     * @param str
     * @return
     * @author baixin
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 检查参数
     * 
     * @param param
     * @param paramMd5
     * @param key
     * @return
     * @author LiangMeng
     */
    public static boolean checkParam(String param, String paramMd5, String key) {
        String paramMd5New = MD5Util.sign(param, key, "utf-8");
        return paramMd5New.equals(paramMd5);
    }

    /**
     * 生成随机数 return String
     */
    public static String getRandomNumStr() {
        return DateUtil.getDateString(new Date(), "yyyyMMddHHmmss") + getRandomNum(4) + "";
    }

    public static int getRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    // 截取两个特定字符串直接的值
    public static String getCenterStr(String completeStr, String firstStr, String lastStr) {
        int first = completeStr.indexOf(firstStr);
        int last = completeStr.indexOf(lastStr);
        return completeStr.substring(first + 1, last);
    }

    /**
     * 判断传入字符串是否为空
     * @param params
     * @return
     */
    public static boolean isNullStringParam(String... params){
        boolean flag = true;
        for(String param : params) {
            if(StringUtils.isBlank(param)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
