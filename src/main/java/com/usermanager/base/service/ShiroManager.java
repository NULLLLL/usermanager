package com.usermanager.base.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usermanager.base.service.ShiroDbRealm.ShiroUser;
import com.usermanager.user.entity.User;
import com.usermanager.user.repository.UserDao;

/**
 * shiro manager
 * 获取当前登陆者的信息
 *
 */
@Component
public class ShiroManager {

	@Autowired
	private UserDao userDao;

	/**
	 * 根据登陆名查询系统用户
	 * @param loginName
	 * @return
	 */
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	/**
	 * 获取当前登陆者
	 * @return
	 */
	public ShiroUser getCurrentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取当前登陆者的id.
	 */
	public Long getCurrentUserId() {
		return getCurrentUser().id;
	}

	/**
	 * 获取当前登陆者的name.
	 */
	public String getCurrentUserName() {
		return getCurrentUser().name;
	}

}
