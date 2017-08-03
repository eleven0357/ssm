package com.eleven.service;

import com.eleven.domain.User;

public interface UserService {
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	boolean checkLogin(User user);
}
