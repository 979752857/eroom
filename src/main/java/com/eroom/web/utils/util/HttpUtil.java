package com.eroom.web.utils.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtil {
    private static Log log = LogFactory.getLog(HttpUtil.class);

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            log.info("请求URL：" + realUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                log.info(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            log.info("响应内容：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    public static String sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new Exception(ex);
            }
        }
        return result;
    }

    public static String getHttpContent(String url) throws IOException {
        String val = null;
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse resp = httpClient.execute(get);
            InputStream in = resp.getEntity().getContent();
            val = covertToString(in);
        } finally {
            get.releaseConnection();
        }

        return val;
    }

    /**
     * 过滤XML文件的空白字符
     */
    private static String covertToString(InputStream stream) throws IOException {
        return new String(covertToByteArray(stream), "UTF-8");

    }

    private static byte[] covertToByteArray(InputStream stream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc = 0;
        while ((rc = stream.read(buff, 0, 1024)) != -1) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    public static String httpReq(String serviceUrl, String parameter, String restMethod) {
        try {
            // 如果请求方法为PUT,POST和DELETE设置DoOutput为真
            if (!HTTPReqMethod.METHOD_GET.equals(restMethod)) {
                URL url = new URL(serviceUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(restMethod);
                con.setDoOutput(true);
                if (!HTTPReqMethod.METHOD_DELETE.equals(restMethod)) { // 请求方法为PUT或POST时执行
                    OutputStream os = con.getOutputStream();
                    os.write(parameter.getBytes("UTF-8"));
                    os.close();
                }
                // 获取返回结果
                String encoding = con.getContentEncoding();
                InputStream is = con.getInputStream();
                int read = -1;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((read = is.read()) != -1) {
                    baos.write(read);
                }
                byte[] data = baos.toByteArray();
                baos.close();
                String content = null;
                if (encoding != null) {
                    content = new String(data, encoding);
                } else {
                    content = new String(data);
                }
                con.disconnect();
                return content;
            } else { // 请求方法为GET时执行
                URL url = new URL((serviceUrl).trim());
                java.net.URLEncoder.encode(url.toString(), "UTF-8");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod(HTTPReqMethod.METHOD_GET);
                connection.connect();
                // 接收返回请求
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(), "UTF-8"));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String responseData = buffer.toString();
                connection.disconnect();
                return responseData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取token失败");
        }
    }

    public static class HTTPReqMethod {
        public final static String METHOD_GET = "GET";

        public final static String METHOD_PUT = "PUT";

        public final static String METHOD_DELETE = "DELETE";

        public final static String METHOD_POST = "POST";
    }
}