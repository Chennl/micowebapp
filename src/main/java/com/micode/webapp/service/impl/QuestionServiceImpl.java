package com.micode.webapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.micode.webapp.entity.Question;
import com.micode.webapp.repository.QuestionRepository;
import com.micode.webapp.service.QuestionService;



@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{
	@Autowired 
	QuestionRepository questionRepository;
	@Override
	public Question getFirstQuestionByQuestionnaireId(Integer questionnaireId) {
		int questionOrder = 1;
		return questionRepository.findByQuestionnaireIdAndQuestionOrder(questionnaireId,questionOrder);
	}
	@Override
	public Question getQuestionById(Integer id) {
		return questionRepository.getOne(id);
	}
	@Override
	public Question addQuestion(Question question) {
		return questionRepository.saveAndFlush(question);
	}	
 
	@Override
	public List<Question> addQuestions(List<Question> questions) {
		return questionRepository.saveAll(questions);
	}
	@Override
	public void deleteQuestion(Integer id) {	
		  questionRepository.deleteById(id);
	}
 
	@Override
	public int deleteByQuestionnaireId(Integer questionnaireId) {
		return questionRepository.deleteByQuestionnaireId(questionnaireId);
		
	}


 

}