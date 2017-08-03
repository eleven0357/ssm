package com.eleven.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eleven.domain.User;
import com.eleven.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(User user){
		String errorText = user.getErrorText();
		boolean success = true;
		
		if (StringUtils.isEmpty(user.getName())) {
			errorText = "用户名不能为空";
			success = false;
		} 
		
		if (StringUtils.isEmpty(user.getPassword())) {
			errorText = "密码不能为空";
			success = false;
		} 
		
		user.setErrorText(errorText);
		
		if (!userService.checkLogin(user) || !success) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("errorText", user.getErrorText());
			
			return new ModelAndView("error/loginError", map);
		} else {
			return new ModelAndView("index");
		}
	}
	
	
}
