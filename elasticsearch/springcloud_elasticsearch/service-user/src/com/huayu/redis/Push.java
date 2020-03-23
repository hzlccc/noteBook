package com.huayu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class Push {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("push")
	public void push(){
		
		redisService.add("a", "你真的很好");
	}
}
