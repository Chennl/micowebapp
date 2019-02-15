package com.micode.webapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micode.webapp.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{

	@Transactional
    Long deleteByUserId(String userId);
	Answer findByUserIdAndNextQuestionId(String userId,int nextQuestionId);
	List<Answer> findByUserId(String userid);
}
