package com.micode.webapp.wechat;

public class WechatUserInfoUtils {
	 private static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	 /**
	     * 获取微信用户账号的相关信息
	     * @param opendID  用户的openId，这个通过当用户进行了消息交互的时候，才有
	     * @return
	     */
//	    public static String getUserInfo(String opendID){
//	        AccessToken accessToken = WeiXinUtils.getAccessToken();
//	        //获取access_token
//	        String token = accessToken.getToken();
//	        String url = GET_USERINFO_URL.replace("ACCESS_TOKEN" , token);
//	        url = url.replace("OPENID" ,opendID);
//	        JSONObject jsonObject = WeiXinUtils.doGetStr(url);
//	        return jsonObject.toString();
//	    }

 
}
