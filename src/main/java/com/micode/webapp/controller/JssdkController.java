package com.micode.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.micode.webapp.wechat.Sign;
import com.micode.webapp.wechat.WechatConstants;
import com.micode.webapp.wechat.WechatUtils;

@Controller
public class JssdkController {

	@GetMapping("/jssdk.jsp")
	public String getLocation(Model model) {
		 
 //		String access_token = WechatUtils.getAccessToken().getToken();
 //		String jsapi_ticket = WechatUtils.getJssdkTicket(access_token).getTicket();
//        // 注意 URL 一定要动态获取，不能 hardcode
 		String jsapi_ticket = "kgt8ON7yVITDhtdwci0qeYmSUL3e15xxgEdh1ZV-phOMpVGqjCQ2PpsVXha75yE_pvxVI9zJbC6pwN2I7ZIRLQ";
		String url = "http://www.ewushi.cn/jssdk.jsp";
		Map map =Sign.sign(jsapi_ticket, url);
		map.put("appid", WechatConstants.APPID);

		model.addAttribute("jsapi_map", map);
		return "jssdk";
	}
}
