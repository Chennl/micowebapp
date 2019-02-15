package com.micode.webapp.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="xch_question",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Entity
public class Question implements Serializable {
 
	private static final long serialVersionUID = -3252039087976447069L;
 
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)  
	 @Column(name="id")
	 private int id;
	 @Column(name="questionnaire_id")
	 private int questionnaireId;
	 @Column(name="question_code",length=10)
	 private String questionCode;
	 @Column(name="question_text",length=255,nullable=false)
	 private String  questionText;
	 @Column(name="comment",length=100)
	 private  String comment;
	
	 @OneToMany(fetch = FetchType.EAGER)
	 @JoinColumn(name="question_id")
	 @OrderBy(" option_order asc")
	 private Set<Option> optionSet;
	 @Column(name="question_order")
	 private int questionOrder;
	 
	 

	/**
	 * @return
	 */
	public String getQuestionDisplayText() {
		return "\r\n问题 " +getQuestionCode().trim()+": "+ getQuestionText();
	}
	public String getOptionDisplayText() {
		StringBuilder builder = new StringBuilder("");
		for(Option option:this.optionSet) {
			String str="\t"+option.getOptionCode()+"."+option.getOptionText()+"\r\n";
			builder.append(str);
		}
		return  "\r\n选项:\r\n\t" +builder.toString().trim();
	}


	public long getQuestionOrder() {
		return questionOrder;
	}


	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}
	 

}