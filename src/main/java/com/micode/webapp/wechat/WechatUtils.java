package com.micode.webapp.wechat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micode.webapp.common.AesException;
import com.micode.webapp.common.SHA1;
import com.micode.webapp.entity.Answer;
import com.micode.webapp.entity.AnswerOption;


import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WechatUtils {
	
	/**
	 * 验证Token
	 * 
	 * @param msgSignature 签名串，对应URL参数的signature
	 * @param timeStamp    时间戳，对应URL参数的timestamp
	 * @param nonce        随机串，对应URL参数的nonce
	 *
	 * @return 是否为安全签名
	 * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
	 */
	public static boolean verifyUrl(String msgSignature, String timeStamp, String nonce) throws AesException {
		String signature = SHA1.getSHA1(WechatConstants.TOKEN, timeStamp, nonce);
 		if (!signature.equals(msgSignature)) {
 			throw new AesException(AesException.ValidateSignatureError);
 		}
		return true;
	}
//https://mp.weixin.qq.com/advanced/advanced?action=dev&t=advanced/dev&token=1216998165&lang=zh_CN
	//1ce3b5a1f9af31ba3668e8c2b4194a56
    public static WechatUserInfo getUserInfo(String opendID,String token){
	    RestTemplate restTemplate = new RestTemplate();
	    String url = WechatConstants.GET_WECHAT_USER_URL.replace("ACCESS_TOKEN" , token);
	    url = url.replace("OPENID" ,opendID);
	    WechatUserInfo wechatUserInfo = null;//restTemplate.getForObject(url, WechatUserInfo.class);
	    ResponseEntity<String> response  = restTemplate.getForEntity(url, String.class);
 
	    if(response.getStatusCode() ==HttpStatus.OK) {

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			try {
				root = mapper.readTree(response.getBody());
				JsonNode errcode = root.path("errcode");
				if(errcode.isMissingNode()) {
					JsonNode nickname = root.path("nickname");
					JsonNode sex = root.path("sex");
					wechatUserInfo = new  WechatUserInfo();
					wechatUserInfo.setNickname(nickname.textValue());
					wechatUserInfo.setSex(sex.asInt(0));
					log.info("获取微信用户信息:%s", response.getBody());
				}else
				{
					log.info("获取微信用户信息异常:%s", response.getBody());
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return wechatUserInfo;
}
	
	public static AccessToken getAccessToken() {
		RestTemplate restTemplate = new RestTemplate();
		AccessToken accessToken=null;
		String access_token_url = WechatConstants.ACCESS_TOKEN_URL.replace("APPID",WechatConstants.APPID).replaceAll("APPSECRET", WechatConstants.APPSECRET);
		ResponseEntity<String> response
		  = restTemplate.getForEntity(access_token_url, String.class);
		if(response.getStatusCode() ==HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			try {
				root = mapper.readTree(response.getBody());
				JsonNode access_token = root.path("access_token");
				JsonNode expires_in = root.path("expires_in");
				accessToken= new  AccessToken();
				accessToken.setToken(access_token.textValue());
				accessToken.setExpireIn(expires_in.asInt());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return accessToken;
	}
	
	public static JssdkTicket getJssdkTicket(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
		JssdkTicket jsapiTicket=null;
		String jsapi_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		ResponseEntity<String> response
		  = restTemplate.getForEntity(jsapi_url, String.class);
		if(response.getStatusCode() ==HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root;
			try {
				root = mapper.readTree(response.getBody());
				JsonNode ticket = root.path("ticket");
				JsonNode expires_in = root.path("expires_in");
				JsonNode errmsg = root.path("errmsg");
				JsonNode errcode = root.path("errcode");
				jsapiTicket= new  JssdkTicket();
				jsapiTicket.setTicket(ticket.textValue());
				jsapiTicket.setExpiresin(expires_in.asInt());
				jsapiTicket.setErrcode(errcode.asInt());
				jsapiTicket.setErrmsg(errmsg.textValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsapiTicket;
	}

}
