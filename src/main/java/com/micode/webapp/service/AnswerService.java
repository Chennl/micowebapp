package com.micode.webapp.service;

 
import java.util.List;

import com.micode.webapp.entity.Answer;
 
public interface AnswerService  {
	Long clearUserAnswers(String userId);
	Answer addWaitingAnswer(String userId,Integer questionId);
	Answer getWaitingAnswerByUserId(String userId);
	Answer getAnswerById(Integer id);
	Answer saveAnswer(Answer answer);
	Answer submitAnswerOption(String userId,  String optionCode);
	List<Answer> getUserAnswers(String userId);
	String getAnswerOptionTextByUser(String userId);
}
