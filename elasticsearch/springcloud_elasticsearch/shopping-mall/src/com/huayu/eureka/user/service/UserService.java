package com.huayu.eureka.user.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huayu.eureka.bean.Shgx;
import com.huayu.eureka.bean.User;


@FeignClient("service-user")
public interface UserService {

	@RequestMapping("/user/allUsers")
	public List<User> getAllUsers();
	
	@RequestMapping("/user/shgx")
	public List<Shgx> getShgx(@RequestParam("userId")String userId);

	@RequestMapping("/user/register")
	public String register(User user);

	@RequestMapping("/user/sync")
	public void sync();
}
