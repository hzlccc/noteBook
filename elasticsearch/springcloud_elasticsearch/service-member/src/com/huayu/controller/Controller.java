package com.huayu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Controller {
	
	@RequestMapping("/getUser")
	public String getUser(){
		return "niho";
	}

}
