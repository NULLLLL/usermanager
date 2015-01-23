package com.usermanager.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.usermanager.BaseTest;
import com.usermanager.user.ajax.UserVo;
import com.usermanager.user.entity.User;
import com.usermanager.user.service.AccountService;

//@TransactionConfiguration(defaultRollback = false)
public class AccountServiceTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);

	@Autowired
	private AccountService accountService;

	@Test
	public void delUser() {
		List<User> allUser = accountService.getAllUser();
		for (User user : allUser) {
			if (user.getId() > 2) {
				accountService.deleteUser(user.getId());
			}
		}
	}

	@Test
	public void createUser() {
		List<Integer> list = new ArrayList<Integer>();
		logger.info("start");
		for (int i = 10000; i < 60000; i++) {
			UserVo vo = new UserVo();
			vo.setName("user" + i);
			vo.setLoginName("usr" + i);
			vo.setPassword("111111");
			vo.setEmail("wang");
			int result = accountService.createUser(vo);
			logger.info("***********************" + i);
			list.add(result);
		}
		logger.info("complete");
		Assert.assertFalse(list.contains(0));
	}
}
