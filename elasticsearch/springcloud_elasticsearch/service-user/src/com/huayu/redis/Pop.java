package com.huayu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class Pop {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/pop")
	public void Pop(){
		
		String string = redisService.get("a");
		System.out.println(string);
	}
}
