/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.usermanager.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.usermanager.user.entity.User;

public interface UserDao extends CrudRepository<User, Long>, UserDaoCustom {
	User findByLoginName(String loginName);
}
