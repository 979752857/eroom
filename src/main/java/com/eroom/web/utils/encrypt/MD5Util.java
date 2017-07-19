package com.eroom.web.utils.encrypt;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static MD5Util instance = null;

    private char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F' };

    private MD5Util() {
    }

    public synchronized static MD5Util getInstance() {
        try {
            instance = new MD5Util();
        } catch (Exception e) {
            return null;
        }
        return instance;
    }

    public static String md5(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        return getInstance().getStringHash(str);
    }

    public String getStringHash(String source) {
        String hash = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(source.getBytes());
            hash = getStreamHash(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String getFileHash(String file) {
        String hash = null;
        try {
            FileInputStream in = new FileInputStream(file);
            hash = getStreamHash(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    public String getStreamHash(InputStream stream) {
        String hash = null;
        byte[] buffer = new byte[1024];
        BufferedInputStream in = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new BufferedInputStream(stream);
            int numRead = 0;
            while ((numRead = in.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            in.close();
            hash = toHexString(md5.digest());
        } catch (Exception e) {
            if (in != null)
                try {
                    in.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            e.printStackTrace();
        }
        return hash;
    }

    private String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    public static byte[] getmd5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(data);
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCreateOpenId(String ip, String content) {
        Date date = new Date();
        return MD5Util.md5("mtrade" + date.getTime() + ip + content);
    }

    public static boolean isRealPassword(String accessPassword, String encryptPassword) {
        boolean isRealPassword = false;
        if (encryptPassword.equals(MD5Util.md5(accessPassword))) {
            isRealPassword = true;
        }
        return isRealPassword;
    }

    /**
     * 签名字符串
     * 
     * @param text
     *            需要签名的字符串
     * @param key
     *            密钥
     * @param input_charset
     *            编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f" };

}
