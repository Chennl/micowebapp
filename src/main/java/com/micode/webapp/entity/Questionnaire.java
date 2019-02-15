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
@Table(name="xch_questionnaire",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Entity
public class Questionnaire  implements Serializable {
			private static final long serialVersionUID = -3252039087976447069L;
		 
			 @Id
			 @GeneratedValue(strategy=GenerationType.AUTO)  
			 @Column(name="id")
			 private Integer id;
			 
			 @Column(name="name",length=50,nullable=false)
			 private String  name;
			 @Column(name="comment",length=100)
			 private  String comment;
		 
			 @OneToMany(fetch = FetchType.EAGER)
			 @JoinColumn(name="questionnaire_id")
			 @OrderBy(" question_code asc")
			 private Set<Question> questionSet;
}
