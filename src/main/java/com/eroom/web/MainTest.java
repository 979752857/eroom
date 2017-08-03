package com.eroom.web;

/**
 * Created by tendy on 2017/8/3.
 */
public class MainTest {

    public static void main(String[] arg){
//        String message = "恭喜您已经通过认证！已经有一大波乘客等着你接单啦，来看看吧。http://dwz.cn/6ll8JA";
        String message = "您好，您未通过车主认证。请尽快更新重新提交，感谢您的支持。http://dwz.cn/6ll8JA";
        message = message.substring(0, message.indexOf("http"));
        System.out.println(message);
    }
}
