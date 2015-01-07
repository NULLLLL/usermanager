/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.usermanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.usermanager.entity.User;

public interface UserDao extends CrudRepository<User, Long>, UserDaoCustom {
	User findByLoginName(String loginName);
}
