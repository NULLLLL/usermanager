/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.usermanager.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.usermanager.base.service.ShiroDbRealm.ShiroUser;
import com.usermanager.user.entity.User;
import com.usermanager.user.repository.UserDao;
import com.util.DateUtil;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private UserDao userDao;

	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public void registerUser(User user) {
		entryptPassword(user);
		user.setRegisterDate(DateUtil.getNowTimestamp());
		userDao.save(user);
	}

	public void updateUser(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);
	}

	public int deleteUser(long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			return 2;
		}
		User user = userDao.findOne(id);
		if (user == null)
			return 0;
		userDao.delete(id);
		return 1;

	}

	public List<Map<String, Object>> getUserList(int pageSize, int pageNo, String order) {
		List<User> userList = userDao.getUserList(pageSize, pageNo, order);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(userList)) {
			for (User user : userList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("loginName", user.getLoginName());
				map.put("name", user.getName());
				map.put("registerDate", user.getRegisterDate().toString());
				list.add(map);
			}
		}
		return list;
	}

	public int editPassword(long userId) {
		return 1;
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

}
