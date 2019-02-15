package com.micode.webapp.service.impl;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micode.webapp.entity.Answer;
import com.micode.webapp.entity.AnswerOption;
import com.micode.webapp.entity.Option;
import com.micode.webapp.repository.AnswerRepository;
import com.micode.webapp.service.AnswerService;
import com.micode.webapp.service.OptionService;
 
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	OptionService optionService;

//	@Override
//	public void Update(Answer answer) {
//		Answer entity = this.getAnswer(answer.getId());
//		if(entity!= null) {
//			entity.setOption(answer.getOption());
//			entity.setNextQuestionId(answer.getNextQuestionId());
//			entity.setUpdateDate(LocalDateTime.now());
//			answerRepository.saveAndFlush(entity);
//		}
//		
//	}

 

	@Override
	public Long clearUserAnswers(String userId) {
		return answerRepository.deleteByUserId(userId);
	}

	@Override
	public Answer addWaitingAnswer(String userId,Integer questionId) {
		Answer answer =  new Answer(0,userId,questionId, 0,null,"",LocalDateTime.now());
		return answerRepository.saveAndFlush(answer);
	}

	@Override
	public Answer getWaitingAnswerByUserId(String userId) {
		return answerRepository.findByUserIdAndNextQuestionId(userId, 0);
	}

	@Override
	public Answer getAnswerById(Integer id) {
		try {
			return answerRepository.getOne(id);
		}catch(EntityNotFoundException enfe) 
		{
			//System.err.print(enfe.getMessage());
			return null;
		}
	 
	}

	@Override
	public Answer saveAnswer(Answer answer) {
		return answerRepository.saveAndFlush(answer);
		
	}

	@Override
	public Answer submitAnswerOption(String userId,String optionCode) {

		Answer answser = this.getWaitingAnswerByUserId(userId);
		Integer questionId = answser.getQuestionId();
			
		Option option =  optionService.getOptionByQuestionIdAndOptionCode(questionId, optionCode);
		AnswerOption ao = new AnswerOption();
		ao.setId(0);
		ao.setOption(option);
	
		ao.setAnswer(answser);
		
		Set<AnswerOption> answerOptions= answser.getAnswerOptions();
		if(answerOptions==null) {
			 answerOptions = new HashSet<AnswerOption>();
		 }
		answerOptions.add(ao);
		answser.setAnswerOptions(answerOptions);
		answser.setNextQuestionId(option.getNextQuestionId());
		return saveAnswer(answser);
	}

	@Override
	public List<Answer> getUserAnswers(String userId) {
		return   answerRepository.findByUserId(userId);
	}
	
	@Override
	public String getAnswerOptionTextByUser(String userId) {
		List<Answer> list = answerRepository.findByUserId(userId);
		StringBuilder answerOptionTextBuilder = new StringBuilder();
		Iterator<Answer> it= list.iterator();
		while(it.hasNext()) {
			Answer answer =it.next();
			Set<AnswerOption> aoSet = answer.getAnswerOptions();
			Iterator<AnswerOption> aoit= aoSet.iterator();
			while(aoit.hasNext()) {
				answerOptionTextBuilder.append(aoit.next().getOption().getOptionText()).append("、");
			}
		}
		String returnText = answerOptionTextBuilder.toString();
		if(returnText.length()>0)
			returnText =returnText.substring(0, returnText.length() -1);
		
		return returnText;
	}


}
