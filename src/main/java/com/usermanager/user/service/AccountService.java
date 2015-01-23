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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.usermanager.base.service.ShiroManager;
import com.usermanager.user.ajax.UserVo;
import com.usermanager.user.entity.User;
import com.usermanager.user.repository.UserDao;
import com.util.DateUtil;
import com.util.StringHelper;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private ShiroManager shiroManager;

	@Transactional(readOnly = true)
	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public void registerUser(User user) {
		entryptPassword(user);
		user.setRegisterDate(DateUtil.getNowTimestamp());
		userDao.save(user);
	}

	public User checkUserLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public int createUser(UserVo vo) {
		if (vo == null) {
			return 0;
		}
		User checkUserLoginName = checkUserLoginName(vo.getLoginName());
		if (checkUserLoginName != null)
			return 0;
		User user = new User();
		user.setLoginName(vo.getLoginName());
		user.setName(vo.getName());
		user.setPlainPassword(vo.getPassword());
		user.setRegisterDate(DateUtil.getNowTimestamp());
		user.setEmail(vo.getEmail().toCharArray());
		entryptPassword(user);
		userDao.save(user);
		return 1;
	}

	public int enitUserInfo(long id, String name, String loginName) {
		if (id <= 0)
			return 0;
		User user = userDao.findOne(id);
		if (user == null)
			return 0;
		if (StringHelper.isNotEmpty(name))
			user.setName(name);
		if (StringHelper.isNotEmpty(loginName))
			user.setName(loginName);
		userDao.save(user);
		return 1;

	}

	public int deleteUser(long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", shiroManager.getCurrentUser().getLoginName());
			return 2;
		}
		User user = userDao.findOne(id);
		if (user == null)
			return 0;
		userDao.delete(id);
		return 1;

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getUserList(String params) {
		List<User> userList = userDao.getUserList(params);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(userList)) {
			for (User user : userList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("loginName", user.getLoginName());
				map.put("name", user.getName());
				map.put("registerDate", user.getRegisterDate() == null ? "" : String.valueOf(user.getRegisterDate()));
				map.put("email", user.getEmail() == null ? "" : String.valueOf(user.getEmail()));
				list.add(map);
			}
		}
		return list;
	}

	public int editPassword(long id, String password) {
		User user = userDao.findOne(id);
		if (user == null)
			return 0;
		user.setPlainPassword(password);
		entryptPassword(user);
		userDao.save(user);
		return 1;
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(long id) {
		return id == 1;
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
