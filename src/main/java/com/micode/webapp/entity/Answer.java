package com.micode.webapp.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="xch_answer",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Entity
public class Answer implements Serializable {
 
	private static final long serialVersionUID = -3252039087976447069L;
 
	 @Id
	 @Column(name="id")
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 @Column(name="user_id",length=50)
	 private String userId;
 	 @Column(name="question_id")
 	 private Integer questionId;
	 @Column(name="next_question_id")
	 private Integer nextQuestionId;
	 
	 @OneToMany(cascade = CascadeType.ALL,mappedBy="answer")//,orphanRemoval = true)
	// @JoinColumn(name="answer_id") 
	 @NotFound(action=NotFoundAction.IGNORE)
	 private Set<AnswerOption> answerOptions;
	 
	 @Column(name="comment",length=100)
	 private String comment;
	 @Column(name="update_date")
	 private LocalDateTime updateDate;
	 
}