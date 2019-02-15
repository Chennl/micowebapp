package com.micode.webapp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.micode.webapp.wechat.WechatMessageService;
import com.micode.webapp.wechat.WechatUtils;

@RestController
public class WechatController {
	Logger logger = LoggerFactory.getLogger(WechatController.class);
	@Autowired
	WechatMessageService wechatMessageService;
	
	
	@RequestMapping(value = "/wxpublic/wxsvr", method = RequestMethod.GET)
	public void checkSignature(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String msgSignature = request.getParameter("signature");
			String msgTimestamp = request.getParameter("timestamp");
			String msgNonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if (WechatUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
				response.getWriter().write(echostr);
				logger.info("Succeeded to verify the signature!");
			} else {
				response.getWriter().write("");
				logger.info("Failed to verify the signature!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	} 
	@RequestMapping(value="/wxpublic/wxsvr",method=RequestMethod.POST)
	public String WechatMessageHandler(@RequestBody String wxMsgXml) {
		logger.info("receive message:"+wxMsgXml);
		String returnXml = wechatMessageService.responseWechatMessage(wxMsgXml);
		logger.info("response message:"+returnXml);
		return returnXml;
	}

	@RequestMapping("/wxpublic/hello")
	public String userProfile() {
	    return String.format("user %s", "chennl");
	}

	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String Test(HttpServletRequest request, HttpServletResponse response) {

//		Answer b = answerService.submitAnswerOption(userId,optionCode);
//		Answer a = answerService.getWaitingAnswerByUserId(userId);
//		 
//		long questionId = a.getQuestionId();
//		long answerId =  a.getId();
//		String optionCode ="2";
//		
//		Option option =  optionService.getOptionByQuestionIdAndOptionCode(questionId, optionCode);
//		AnswerOption ao = new AnswerOption(0,answerId,option,LocalDateTime.now());
//		 Set<AnswerOption> answerOptions= a.getAnswerOptions();
//		 if(answerOptions==null) {
//			 answerOptions = new HashSet<AnswerOption>();
//			 answerOptions.add(ao);
//		 }else
//			 answerOptions.add(ao);
//		 
//		 a.setAnswerOptions(answerOptions);
//		 a.setUserId(LocalTime.now().toString()); 
//		 Answer b = answerService.saveAnswer(a);
//		 
		 
//		 System.out.print(b.getId());
		//Question q = questionService.getQuestionById(101);
		//String ss = q.getQuestionText();
//		return b.getUserId();
		return "";
	}
}
