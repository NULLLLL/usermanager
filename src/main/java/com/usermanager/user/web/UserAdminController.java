/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.usermanager.user.web;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usermanager.user.service.AccountService;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin")
public class UserAdminController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/user")
	public String list() {
		return "account/adminUserList";
	}

	@RequestMapping(value = "/userTable")
	@ResponseBody
	public JSONArray getUserTable(@RequestParam(value = "_", required = false) String _, @RequestParam(value = "limit") int pageSize,
			@RequestParam(value = "offset") int pageNo, @RequestParam(value = "order") String order) {
		return JSONArray.fromObject(accountService.getUserList(pageSize, pageNo, order));
	}
	/*@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		User user = accountService.getUser(id);
		accountService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
		return "redirect:/admin/user";
	}*/

}
