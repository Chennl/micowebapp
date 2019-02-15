package com.micode.webapp.service.impl;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micode.webapp.service.UserService;



import com.micode.webapp.entity.User;

import com.micode.webapp.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;


	@Override
	public User getUserbyId(Integer id) {
		return userRepository.getOne(id);
	}


	@Override
	public User getUserByUserName(String userName) {
		 
		return userRepository.findByUserName(userName);
	}
 
	

 
}


