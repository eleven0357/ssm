package com.eleven.dao;

import com.eleven.domain.User;

public interface UserDao {
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	User selectUserByName(String  name);
}
