package com.eroom.web.utils.weixin.message.resp;

import java.util.Date;

import com.eroom.web.utils.weixin.MessageUtil;


/**
* @version V1.0
 */
public class ImageMessage extends BaseMessage {

	// 图片素材
	private Media Image;
	
	public ImageMessage(){}
    public ImageMessage(String fromUserName, String toUserName)
    {
        this.setFromUserName(fromUserName);
        this.setToUserName(toUserName);
        this.setCreateTime(new Date().getTime());
        this.setMsgType(MessageUtil.ResponseMsgType.RESP_MESSAGE_TYPE_IMAGE);
    }
	
    public Media getImage() {
        return Image;
    }

    public void setImage(Media image) {
        Image = image;
    }

}
