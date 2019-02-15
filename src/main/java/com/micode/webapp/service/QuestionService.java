package com.micode.webapp.service;



import java.util.List;

import com.micode.webapp.entity.Question;

public interface  QuestionService {
	
	Question getFirstQuestionByQuestionnaireId(Integer questionnaireId);
 	Question getQuestionById(Integer id);
 	
 	Question addQuestion(Question question);
 	List<Question> addQuestions(List<Question> questions);
 	void  deleteQuestion(Integer id);
 	int  deleteByQuestionnaireId(Integer questionnaireId);
//	//Survey getSurveyById(long surveyId);
//
//	void deleteAnswersByUser(String userId);
//
//	void addAnswer(String userId,long questionId);
//	void addAnswer(String userId,Question question);
//	
//	Answer getWaitAnswer(String userId);
//	void submitAnswer(Answer answer);
//	Option getOptionByOptionCode(long questionId,String optionCode);
//	String getSubmintedAnswerDisplayText(String userId);
//	
//	
//	//wrap
//	Question startNewSurvey(String userId,long surveyId);
//	Question startNextQuestion(String userId, long questionId);
//	String  getSurveyInstruction(String userId);
	
}
