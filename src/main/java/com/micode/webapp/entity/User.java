package com.micode.webapp.entity;

 


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="xch_user",uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 306294443534548661L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="id")
	 private Integer id;
	 
	 @NotEmpty(message="密码不能为空")
 	 @Column(name="password",length=128)
 	 private String  password;
 	 
	 @Column(name="is_superuser")
	 private Integer Issuperuser;
	 
	 @Column(name="username",length=150)
	 private String userName;
	 
	 
	 @Column(name="email",length=254)
	 private String email;
	 
	 
	 @Column(name="is_active",length=255)
	 private  Integer isActive;
	 
	 
	 @Column(name="last_login")
	 private Date lastLogin;
	 
	 @Column(name="date_joined")
	 private Date dateJoined;
	 
}



 
 