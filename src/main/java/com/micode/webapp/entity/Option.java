package com.micode.webapp.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="xch_option")
@Entity
public class Option implements Serializable {
	private static final long serialVersionUID = 306294443534548661L;
	@Id
	 @Column(name="id")
	 private Integer id;
 	 @Column(name="question_id")
 	 private Integer  questionId;
	 @Column(name="option_code",length=10)
	 private String optionCode;
	 @Column(name="option_text",length=255)
	 private String optionText;
	 @Column(name="next_question_id")
	 private Integer nextQuestionId;
	 @Column(name="option_order")
	 private Integer optionOrder;
}