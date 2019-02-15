package com.micode.webapp.wechat;

public class WechatConstants {
	public static final String TOKEN = "7242609C8AEF41F88622A80AFCBE4E83";
	//XCH:	wxe5c0f537d7c604c6
	//  1ce3b5a1f9af31ba3668e8c2b4194a56
	public final static String APPID="wx743b2ddda1bec8b8";
	public final static String APPSECRET="76da573cba7fb93b7f2bb3a4e03540a9";
	 /**
	     * 用于获取当前与微信公众号交互的用户信息的接口（一般是用第一个接口地址）
     */
    public static final String GET_WECHAT_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
    public final static String GetPageUsersUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
 
    /**
	     * 用于进行网页授权验证的接口URL，通过这个才可以得到opendID等字段信息
     */
    public final static String GET_WEBAUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
 
    /**
	     * 用于进行当点击按钮的时候，能够在网页授权之后获取到code，再跳转到自己设定的一个URL路径上的接口，这个主要是为了获取之后于
	     * 获取openId的接口相结合
	     * 注意：参数：toselfURL  表示的是当授权成功后，跳转到的自己设定的页面，所以这个要根据自己的需要进行修改
     */
    public final static String Get_WEIXINPAGE_Code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=toselfURL&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
    /**
	     * 获取access_token的URL
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    public static final String JSSDK_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    
    
 
}
