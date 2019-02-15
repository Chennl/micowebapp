package com.micode.webapp.service.impl;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micode.webapp.service.OptionService;



import com.micode.webapp.entity.Option;

import com.micode.webapp.repository.OptionRepository;


@Service
@Transactional
public class OptionServiceImpl implements OptionService{

	@Autowired
	private OptionRepository optionRepository;


	@Override
	public Option getOptionbyId(Integer id) {
		return optionRepository.getOne(id);
	}


	@Override
	public Option getOptionByQuestionIdAndOptionCode(Integer questionId, String optionCode) {
		return optionRepository.findByQuestionIdAndOptionCode(questionId,optionCode);
	 
	}
	

 
}


