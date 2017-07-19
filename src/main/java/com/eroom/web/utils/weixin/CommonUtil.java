package com.eroom.web.utils.weixin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.eroom.web.constants.PaymentConstants;
import com.eroom.web.utils.encrypt.MD5Util;
import com.eroom.web.utils.util.ConfigUtil;

/**
 * 
 * Title: MALL <br>
 * Description: <br>
 * Date: 2017年2月9日 <br>
 * Copyright (c) 2017 MALL <br>
 * 
 * @author zhangxd
 */
@SuppressWarnings("deprecation")
public class CommonUtil {

    private static Logger logger = Logger.getLogger(CommonUtil.class);

    // 连接超时时间，默认10秒
    private static int socketTimeoutConfig = 10000;

    // 传输超时时间，默认30秒
    private static int connectTimeoutConfig = 30000;

    // 请求器的配置
    @SuppressWarnings("unused")
    private static RequestConfig requestConfig;

    // HTTP请求器
    private static CloseableHttpClient httpClient;

    /**
     * 单向认证，无需校验本地证书
     * 
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @author zhangxw
     * @ApiDocMethod
     */
    private static void init() throws IOException, KeyStoreException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyManagementException {

        httpClient = createSSLClientDefault();

        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeoutConfig)
                .setConnectTimeout(connectTimeoutConfig).build();

    }

    /**
     * 获取https默认连接
     * 
     * @return
     * @author zhangxw
     * @ApiDocMethod
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustStrategy() {
                        // 信任所有
                        public boolean isTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            logger.error("获取连接异常:" + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("获取连接异常:" + e.getMessage(), e);
        } catch (KeyStoreException e) {
            logger.error("获取连接异常:" + e.getMessage(), e);
        }
        return HttpClients.createDefault();

    }

    @SuppressWarnings("rawtypes")
    public static String createSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + ConfigUtil.getProperty("WEIXIN_API_KEY"));
        logger.info("转换前参数:" + sb);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        logger.info("packge签名:" + sign);
        return sign;

    }

    @SuppressWarnings("rawtypes")
    public static String createSign(SortedMap<String, String> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        logger.info("转换前参数:" + sb);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        logger.info("packge签名:" + sign);
        return sign;

    }

    @SuppressWarnings("rawtypes")
    public static String getRequestXml(SortedMap<String, String> parameters)
            throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append("<" + k + ">" + v + "</" + k + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String getPayNo(String url, String xmlParam) {
        HttpPost httpost = getPostMethod(url);
        String prepay_id = "";
        try {
            init();
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = httpClient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("jsonStr:" + jsonStr);
            Map map = doXMLParse(jsonStr);
            String return_code = String.valueOf(map.get("return_code"));
            String result_code = String.valueOf(map.get("result_code"));
            if (PaymentConstants.WX_RETURN_CODE.SUCCESS.equals(return_code)
                    && PaymentConstants.WX_RETURN_CODE.SUCCESS.equals(result_code)) {
                prepay_id = (String) map.get("prepay_id");
            } else {
                prepay_id = map.get("return_msg") + ",des:" + map.get("err_code_des");
            }
        } catch (Exception e) {
            logger.error("微信请求异常:" + e.getMessage(), e);
        } finally {
            httpost.abort();
        }
        return prepay_id;
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * 
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> doXMLParse(String strxml) throws Exception {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map<String, String> m = new HashMap<String, String>();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        // 关闭流
        in.close();

        return m;
    }

    public static InputStream String2Inputstream(String str) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(str.getBytes("utf-8"));
    }

    /**
     * 获取子结点的xml
     * 
     * @param children
     * @return String
     */
    @SuppressWarnings("rawtypes")
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    /**
     * 发送httpsPost请求
     * 
     * @param url
     * @param xmlParam
     * @param cerPath
     * @param password
     * @return
     * @throws Exception
     * @author zhangxw
     */
    public static Map<String, String> doHttpsPost(String url, String xmlParam, String cerPath,
            String password) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        System.setProperty("javax.net.debug", "ssl,handshake");
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(cerPath));
        logger.info("instream：" + instream);
        try {
            keyStore.load(instream, password.toCharArray());// 设置证书密码
        } catch (CertificateException e) {
            logger.error("获取连接异常:" + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("获取连接异常:" + e.getMessage(), e);
        } finally {
            instream.close();
        }
        logger.info("keyStore：" + keyStore);
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, password.toCharArray()).build();
        logger.info("sslcontext：" + sslcontext);
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        logger.info("sslsf：" + sslsf);
        CloseableHttpClient httpsclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        logger.info("httpsclient：" + httpsclient);
        try {

            HttpPost httpost = getPostMethod(url);
            logger.info("httpost：" + httpost);
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));

            CloseableHttpResponse response = httpsclient.execute(httpost);
            logger.info("response：" + response);
            try {
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                logger.info("返回报文：" + jsonStr);
                map = doXMLParse(jsonStr);
            } catch (Exception e) {
                logger.error("报文解析异常：" + e.getLocalizedMessage(), e);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("https请求异常：" + e.getLocalizedMessage(), e);
        } finally {
            httpsclient.close();
        }
        return map;
    }

    /**
     * 模拟浏览器post提交
     * 
     * @param url
     * @return
     */
    public static HttpPost getPostMethod(String url) {
        HttpPost pmethod = new HttpPost(url); // 设置响应头信息
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Accept", "*/*");
        pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        pmethod.addHeader("Host", "api.mch.weixin.qq.com");
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        return pmethod;
    }

    // 获取路径
    public static String getUrl() {
        return CommonUtil.getDomain() + "/security/";
    }

    // 获取当前环境域名
    public static String getDomain() {
        return ConfigUtil.getProperty("domain_url");
    }

}
