package com.micode.webapp.service;

import com.micode.webapp.entity.User;

public interface UserService {
	User getUserbyId(Integer id);
	User getUserByUserName(String userName);

}
