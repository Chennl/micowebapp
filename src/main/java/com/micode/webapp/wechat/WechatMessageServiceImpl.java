package com.micode.webapp.wechat;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.micode.webapp.entity.Answer;
import com.micode.webapp.entity.Instruction;
import com.micode.webapp.entity.Option;
import com.micode.webapp.entity.Question;
import com.micode.webapp.repository.QuestionnaireRepository;
import com.micode.webapp.service.AnswerService;
import com.micode.webapp.service.ArticleSearchService;
import com.micode.webapp.service.InstructionService;
import com.micode.webapp.service.OptionService;
import com.micode.webapp.service.QuestionService;
import com.micode.webapp.wechat.model.ReplyMessage;
import com.micode.webapp.wechat.model.RequestMessage;
@Service
public class WechatMessageServiceImpl implements WechatMessageService {
	private Logger logger = LoggerFactory.getLogger(WechatMessageService.class);
	@Autowired
	QuestionService questionService;
	@Autowired
	AnswerService answerService;
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	@Autowired
	OptionService optionService;
	@Autowired
	InstructionService instructionService;
	@Autowired 
	ArticleSearchService articleSearchService;
	
	@Override
	public RequestMessage parseRequestMessage(String wxMsgXml) throws Exception {
		XmlMapper xmlMapper = new XmlMapper();
		RequestMessage requestMessage = xmlMapper.readValue(wxMsgXml, RequestMessage.class);
		return requestMessage;
	}
	@Override
	public  String getReplyMessageXml(ReplyMessage message) {
		ObjectMapper xmlMapper = new XmlMapper();
		try {
			xmlMapper.setVisibility(xmlMapper.getSerializationConfig().getDefaultVisibilityChecker()
					.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
					.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
					.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
					.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
			return xmlMapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
	@Override
	public  String getReplyTextMessage(String content, String fromUserName, String toUserName) {
		ReplyMessage msg = new ReplyMessage(fromUserName, toUserName);
		msg.setMsgType("text");
		msg.setContent(content);
		return getReplyMessageXml(msg);
	}
	
	@Override
	public String replyTextMessage(RequestMessage requestMessage) {
		StringBuffer replyMsg = new StringBuffer();
		String receiveContent = requestMessage.getContent().trim();
		String openId = requestMessage.getFromUserName();
		String wxServiceId=requestMessage.getToUserName();
		String returnXml ="";
		
		logger.info("Recieved message from "+ openId +":"+ receiveContent);
		
		if(receiveContent.equals("")) {
			replyMsg.append("别闹了，这谁看得懂啊！");
			returnXml = getReplyTextMessage(replyMsg.toString(), requestMessage.getFromUserName(),
					requestMessage.getToUserName());
			return returnXml;
		}
			 
	 
		if (receiveContent.equals("感冒") || receiveContent.equals("ganmao")) {
			
			Integer questionnaireId=100;	
			answerService.clearUserAnswers(openId);
			Question question = questionService.getFirstQuestionByQuestionnaireId(questionnaireId);

		
//			AccessToken token = WechatUtils.getAccessToken();
//			WechatUserInfo wechatUserInfo =WechatUtils.getUserInfo( openId,token.getToken());
//			if(wechatUserInfo != null) {
//				String nickName =wechatUserInfo.getNickname();
//				replyMsg.append("您好["+nickName+"],我是柠檬！\r\n 请告诉我病人当前情况：\r\n");
//			}else {
//				replyMsg.append("您好,我是柠檬！\r\n 请告诉我病人当前情况：\r\n");
//			}
			replyMsg.append("您好,我是柠檬！\r\n 请告诉我病人当前情况：\r\n");
			answerService.addWaitingAnswer(openId, question.getId());
			//replyMsg.append(question.getQuestionDisplayText());
			replyMsg.append(question.getQuestionDisplayText());
			replyMsg.append(question.getOptionDisplayText());					
			returnXml = getReplyTextMessage(replyMsg.toString(), wxServiceId,openId);
			return returnXml;
		}else  if(" 1,2,3,4,5,6,7,8,9".indexOf(receiveContent)>0){
			String optionCode = receiveContent.toLowerCase();
			//CheckResult checkResult = checkResultService.getCurrentCheckResult(openId);			
			Answer answer = answerService.getWaitingAnswerByUserId(openId);
			//已完成全部答卷。
			if(answer ==null) {	
				replyMsg.append("你还没开始咨询柠檬或上次咨询已结束。如果您需要再次咨询，请发送'感冒'，谢谢！\r\n" );
				returnXml = getReplyTextMessage(replyMsg.toString(), wxServiceId,openId);
			}else{ //还未完成答卷
 								
				Integer questionId = answer.getQuestionId();
 					Option option = optionService.getOptionByQuestionIdAndOptionCode(questionId, optionCode);
					if(option ==null) {//答案无效
						replyMsg.append("温馨提醒：请您重新输入正确的选项。" );
						returnXml = getReplyTextMessage(replyMsg.toString(), wxServiceId,openId);
					}else {
						Integer nextQuestionId=option.getNextQuestionId();
						answer = answerService.submitAnswerOption(openId, optionCode);
						
						if(nextQuestionId>0 && nextQuestionId<99998) {
							//Question question = surveyService.startNextQuestion(openId,nextQuestionId);
							answerService.addWaitingAnswer(openId,nextQuestionId);
							Question question = questionService.getQuestionById(nextQuestionId);
							replyMsg.append(question.getQuestionDisplayText());
							replyMsg.append(question.getOptionDisplayText());							
							returnXml = getReplyTextMessage(replyMsg.toString(), wxServiceId,openId);
						}else{
							Instruction instruction = instructionService.getInstructionById(140);
							String answerOptionText = answerService.getAnswerOptionTextByUser(openId);
							Long createTime = new Date().getTime();
							Document document = DocumentHelper.createDocument();
							Element root = document.addElement("xml");// 创建根节点
							root.addElement("ToUserName").addCDATA(openId);
							root.addElement("FromUserName").addCDATA(wxServiceId);
							root.addElement("CreateTime").setText(createTime.toString());
							root.addElement("MsgType").addCDATA("news");
							root.addElement("ArticleCount").setText("1");
							Element article = root.addElement("Articles");
							Element item = article.addElement("item");
							item.addElement("Title").addCDATA("感谢你完成回答，感冒助手给你建议");
							
							String instructionText = String.format(instruction.getInstructionText(), answerOptionText);
							String url = instruction.getLink();
							String imgUrl = instruction.getImageFile();
							item.addElement("Description").addCDATA(instructionText);
							item.addElement("PicUrl").addCDATA(imgUrl);
							item.addElement("Url").addCDATA(url);
							returnXml = root.asXML();
						} 
 					}
									 
			}

		}
		else {
			String keyword = receiveContent;
			String articleNo =articleSearchService.searchArticle(keyword);
			
			if(articleNo.length()>0) {
				replyMsg.append(" <a href=\"http://www.0011.com.cn/Atricle/ColumnList.aspx?ColumnId=").append(articleNo).append("\" >请点击链接查阅你可能需要的信息： ").append(keyword).append("</a>");
				returnXml = getReplyTextMessage(replyMsg.toString(), requestMessage.getToUserName(),requestMessage.getFromUserName());
			}
			else {
				replyMsg.append("不好意思，没找到你要的信息。 请点击查阅链接并在打开网页下方“疾病”，“症状”查看你可能需要的信息：  <a href=\"http://www.0011.com.cn/Atricle/Column.aspx?t=0").append(articleNo).append("\" >").append(keyword).append("</a>");
				returnXml = getReplyTextMessage(replyMsg.toString(), requestMessage.getToUserName(),requestMessage.getFromUserName());
			}
			//replyMsg.append("不好意思，柠檬不明白你的意思，请选择答案！");
			
		}
		
		return returnXml; 
	}
	@Override
	public String responseWechatMessage(String wechatMessageXML) {
		RequestMessage requestMessage;
		try {
			requestMessage = parseRequestMessage(wechatMessageXML);
			String ss =replyTextMessage(requestMessage);
			return ss;
		} catch (Exception e) {
			 
			e.printStackTrace();
			return "";
		}

		
	}
	 
}
