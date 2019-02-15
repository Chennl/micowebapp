package com.micode.webapp.wechat;


import com.micode.webapp.wechat.model.ReplyMessage;
import com.micode.webapp.wechat.model.RequestMessage;

public interface WechatMessageService {
	String responseWechatMessage(String wechatMessageXML);
	RequestMessage parseRequestMessage(String wxMsgXml) throws Exception;
	String getReplyMessageXml(ReplyMessage message);
	String getReplyTextMessage(String content, String fromUserName, String toUserName);
	String replyTextMessage(RequestMessage requestMessage);

}
