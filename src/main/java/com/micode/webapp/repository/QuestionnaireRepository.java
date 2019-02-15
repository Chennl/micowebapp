package com.micode.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.micode.webapp.entity.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository< Questionnaire, Integer>{
	Questionnaire findByName(String name);
}
