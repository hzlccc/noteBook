package com.huayu.eureka.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huayu.eureka.bean.User;
import com.huayu.eureka.user.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUser")
	public List<User> get(){
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String register(@RequestBody String data){
		System.out.println("用户注册");
		User user = JSONObject.parseObject(data, User.class);
		String result = userService.register(user);
		return result;
	}
	
	@RequestMapping("/sync")
	public  void sync(){
		userService.sync();
	}
	
}
