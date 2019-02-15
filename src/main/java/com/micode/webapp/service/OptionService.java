package com.micode.webapp.service;

import com.micode.webapp.entity.Option;

public interface OptionService {
	Option getOptionbyId(Integer id);
	Option getOptionByQuestionIdAndOptionCode(Integer questionId,String optionCode);
}
