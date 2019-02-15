package com.micode.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micode.webapp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
  
    Question findByQuestionnaireIdAndQuestionOrder(Integer questionnaireId,Integer questionareOrder);

    public int deleteByQuestionnaireId(Integer questionnaireId);
 
}
