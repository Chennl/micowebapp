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
@Table(name="xch_instruction")
@Entity
public class Instruction implements Serializable {
	private static final long serialVersionUID = 306294443534548661L;
	@Id
	 @Column(name="id")
	 private Integer id;
	 @Column(name="questionnaire_id")
	 private Integer questionnaireId;
	 @Column(name="answer_opion_reg",length=50)
	 private String  answerOpionReg;
	 @Column(name="instruction_text")
	 private String instructionText;
	 @Column(name="image_file",length=100)
	 private String imageFile;
	 @Column(name="link",length=100)
	 private String link;
}
 
