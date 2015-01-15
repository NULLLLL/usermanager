package com.usermanager.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.usermanager.BaseTest;
import com.usermanager.user.service.AccountService;

public class AccountServiceTest extends BaseTest {

	@Autowired
	private AccountService accountService;

	@Ignore
	@Test
	public void delUser() {
		int actual = accountService.deleteUser(2);
		Assert.assertEquals(1, actual);
	}
}
