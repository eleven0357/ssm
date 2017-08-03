package com.eleven.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleven.dao.UserDao;
import com.eleven.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean checkLogin(User user) {
		User realUser = userDao.selectUserByName(user.getName());
		
		String errorText = user.getErrorText();
		
		if (realUser == null) {
			errorText = "用户名不存在";
			user.setErrorText(errorText);
			return false;
		}
		
		String password = encryptPassword(user.getName(), user.getPassword());
		if (!password.equals(realUser.getPassword())) {
			errorText = "密码错误";
			user.setErrorText(errorText);
			return false;
		}
		
		return true;
	}

	/**
	 * 加密
	 * @param name
	 * @param password
	 * @return
	 */
	private String encryptPassword(String name, String password){
		// 1. 先对name的hashcode值进行md5散列
		String salt = DigestUtils.md5Hex(String.valueOf(name.hashCode()));
		// 2. 以加密后的值作为随机数加盐
		String newPassword = password + salt;
		// 3. 以两者相加的值散列得到最终的密码
		return DigestUtils.md5Hex(newPassword);
	}
	
	
//	public static void main(String[] args) {
//		String password = encryptPassword("eleven", "123456");
//		System.out.println(password);
//	}
}
