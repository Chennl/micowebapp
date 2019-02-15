package com.micode.webapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micode.webapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserName(String userName);

}
