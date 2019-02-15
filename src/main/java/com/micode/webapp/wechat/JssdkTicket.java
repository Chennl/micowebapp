package com.micode.webapp.wechat;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JssdkTicket {	 	
	private String ticket;
    private int expiresin;
    private String errmsg;
    private int errcode;
 }

//{
//"errcode":0,
//"errmsg":"ok",
//"ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKdvsdshFKA",
//"expires_in":7200
//}
