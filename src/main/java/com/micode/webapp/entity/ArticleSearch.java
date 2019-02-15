package com.micode.webapp.entity;


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="xch_article_search",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Entity
public class ArticleSearch implements Serializable {
 
	private static final long serialVersionUID = -3252039087976447069L;
 

	@Id
	 @Column(name="id")
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer id;
 	 @Column(name="article_no")
 	 private String articleNo;
 	 @Column(name="keyword")
 	 private String keyword;
	 @Column(name="search_order")
	 private Integer search_order;
 
	

}