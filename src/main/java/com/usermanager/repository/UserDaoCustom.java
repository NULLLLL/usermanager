package com.usermanager.repository;

import java.util.List;

import com.usermanager.entity.User;

public interface UserDaoCustom {
	
	public List<User> getUserList(int pageSize, int pageNo, String order);

}
