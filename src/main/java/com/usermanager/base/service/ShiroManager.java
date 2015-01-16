package com.usermanager.base.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usermanager.base.service.ShiroDbRealm.ShiroUser;
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
	 * 获取当前登陆者
	 * @return
	 */
	public ShiroUser getCurrentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

}
