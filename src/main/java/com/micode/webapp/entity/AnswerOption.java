package com.micode.webapp.entity;

import java.io.Serializable;


 
import javax.persistence.Column;
import javax.persistence.Entity;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="xch_answer_option")
@Entity
public class AnswerOption implements Serializable {
	private static final long serialVersionUID = -3252039087976447069L;
	 @Id
	 @Column(name="id")
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
	 

	 @ManyToOne
	 @JoinColumn(name = "answer_id")
	 private Answer answer;
	 
	 @OneToOne
	 @OrderBy("option_order")
	 @JoinColumn(name="option_id")
	 private Option option;
	
	 
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof AnswerOption )) return false;
	        return id != null && id.equals(((AnswerOption) o).id);
	    }
	    @Override
	    public int hashCode() {
	        return 31;
	    }
	 	
}