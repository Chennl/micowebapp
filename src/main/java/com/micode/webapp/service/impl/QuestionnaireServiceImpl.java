package com.micode.webapp.service.impl;

import com.micode.webapp.service.QuestionnaireService;

public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Override
	public Integer searchQuestionnaire(String keyword) {
		Integer questionnaireId=100;
		if(keyword.trim().equals("感冒")||keyword.trim().equals("ganmao"))
			questionnaireId=100;
		return questionnaireId;
	}

}
