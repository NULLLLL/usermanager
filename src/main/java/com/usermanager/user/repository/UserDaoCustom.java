package com.usermanager.user.repository;

import java.util.List;

import com.usermanager.user.entity.User;

public interface UserDaoCustom {
	
	public List<User> getUserList(String params);

}
