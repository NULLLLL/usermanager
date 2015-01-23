package com.usermanager.user.service;

import java.util.List;
import java.util.Map;

import com.usermanager.user.ajax.UserVo;
import com.usermanager.user.entity.User;

public interface AccountService {

	List<User> getAllUser();

	User getUser(Long id);

	User findUserByLoginName(String loginName);

	void registerUser(User user);

	User checkUserLoginName(String loginName);

	int createUser(UserVo vo);

	int enitUserInfo(long id, String name, String loginName);

	int deleteUser(long id);

	List<Map<String, Object>> getUserList(String params);

	int editPassword(long id, String password);

}
