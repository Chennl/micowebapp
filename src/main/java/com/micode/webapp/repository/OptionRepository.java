package com.micode.webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
 

import com.micode.webapp.entity.Option;

public interface OptionRepository extends JpaRepository<Option, Integer>{
	Option findByQuestionIdAndOptionCode(Integer questionId,String optionCode);
	
 
}
 