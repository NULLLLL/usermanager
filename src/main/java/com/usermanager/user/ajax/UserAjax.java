package com.usermanager.user.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usermanager.user.service.AccountService;

@Component
public class UserAjax {

	@Autowired
	private AccountService accountService;

	public int delUser(long userId) {
		return accountService.deleteUser(userId);
	}

	public int editPassword(long id, String password) {
		return accountService.editPassword(id, password);
	}

	public int editUserInfo(long id, String name, String loginName) {
		return accountService.enitUserInfo(id, name, loginName);
	}

}
