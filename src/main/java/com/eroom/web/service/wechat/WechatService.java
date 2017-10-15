package com.eroom.web.service.wechat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.eroom.web.service.BaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.eroom.web.entity.po.SystemBase;
import com.eroom.web.entity.vo.wechat.PhotoVo;
import com.eroom.web.service.system.SystemBaseService;
import com.eroom.web.utils.util.HttpUtil;
import com.eroom.web.utils.util.StringUtil;
import com.eroom.web.utils.weixin.EmojiUtil;

@Service
public class WechatService extends BaseService{
	
    @Resource
    private SystemBaseService systemBaseService;

    // 微信请求获取二维码
    public String getQRCode(String access_token, String tenantNo, Long custId) {
        JSONObject jsonStr = new JSONObject();
        jsonStr.put("action_name", "QR_LIMIT_STR_SCENE");

        Map<String, String> scene_str = new HashMap<String, String>();
        scene_str.put("scene_str", tenantNo + "," + custId);
        Map<String, Map<String, String>> scene = new HashMap<String, Map<String, String>>();
        scene.put("scene", scene_str);
        jsonStr.put("action_info", scene);
        String getQRcodeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="
                + access_token;
        String contentStr = HttpUtil.httpReq(getQRcodeUrl, jsonStr.toString(), "POST");

        JSONObject content = JSONObject.parseObject(contentStr);
        return content.getString("ticket");
    }

    /**
     * 获取openid access_token
     * 
     * @param systemBase
     * @param code
     * @return
     * @throws IOException
     * @author zhangyouming
     */
    public Map<String, String> getParamForWechat(SystemBase systemBase, String code)
            throws IOException {
        loginfo("通过code获取用户openid   systemBase:{}   code:{}  ", systemBase.toString(), code);
        String appid = systemBase.getAppid();// 获取本地appid
        String appsecret = systemBase.getAppscret();// 获取本地appsecret
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret="
                + appsecret + "&code=" + code + "&grant_type=authorization_code";
        String json = HttpUtil.httpReq(url, "", "GET");// 获取授权信息
        JSONObject js = JSONObject.parseObject(json);
        Map<String, String> returnContent = new HashMap<String, String>();
        returnContent.put("openid", js.getString("openid"));
        returnContent.put("access_token", js.getString("access_token"));
        returnContent.put("scope", js.getString("scope"));
        return returnContent;
    }

    /**
     * 通过openid获取用户信息
     * 
     * @param token
     * @param openid
     * @return
     * @throws IOException
     * @author zhangyouming
     */
    public JSONObject getWeixinInfo(String token, String openid) throws IOException {
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid="
                + openid + "&lang=zh_CN";
        String infojson = HttpUtil.getHttpContent(infoUrl);
        JSONObject js = JSONObject.parseObject(infojson);
        // 过滤微信昵称特殊字符
        String nickName = js.getString("nickname");
        if (StringUtil.isBlank(nickName)) {
            nickName = "*";
        } else {
            nickName = EmojiUtil.filterEmoji(nickName);
        }
        return js;
    }

    /**
     * 获取基础access_token
     * @return
     * @throws Exception 
     */
    public String getBasisAccessToken() throws Exception {
    	return systemBaseService.getBasisAccessToken();
    }

    /**
     * 获取wxjs所需apiticket
     * @throws Exception 
     */
    public String getApiTiket() throws Exception {
    	return systemBaseService.getApiTiket();
    }

    /* 发送模板消息 */
    public void sendTemplate(String temp) throws Exception {
        String access_token = this.getBasisAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + access_token;
        // 发送模板消息
        HttpUtil.httpReq(url, temp, "POST");
    }

    /* 获取永久素材列表 */
    public JSONObject getMessageList(String messageType) throws Exception {
        String access_token = this.getBasisAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="
                + access_token;
        JSONObject data = new JSONObject();
        data.put("type", messageType);
        data.put("offset", 0);
        data.put("count", 20);
        String json = HttpUtil.httpReq(url, data.toString(), "POST");// 获取授权信息
        JSONObject js = JSONObject.parseObject(json);
        return js;
    }

    /* 发送消息 */
    public void sendMessage(String msgType, String mediaId, String openid)
            throws Exception {
        String access_token = this.getBasisAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
                + access_token;
        JSONObject data = new JSONObject();

        data.put("touser", openid);

        JSONObject content = new JSONObject();
        if ("text".equals(msgType)) {
            content.put("content", mediaId);
        } else {
            content.put("media_id", mediaId);
        }
        data.put(msgType, content);
        data.put("msgtype", msgType);
        HttpUtil.httpReq(url, data.toString(), "POST");// 获取授权信息
    }

    // 下载临时素材
    public PhotoVo getMedia(String mediaId) throws Exception {
        String access_token = this.getBasisAccessToken();
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("MEDIA_ID", mediaId);
        PhotoVo photo = new PhotoVo();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            // 根据内容类型获取扩展名
            String fileExt = ".jpg";

            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = conn.getInputStream().read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] in2b = swapStream.toByteArray();
            photo.setFile(in2b);
            photo.setFileName(mediaId + fileExt);
            logger.info("照片下载成功，文件名为：" + mediaId + fileExt);
        } catch (Exception e) {
            logger.error("文件下载失败:");
        }
        return photo;
    }
}
