package com.huayu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.huayu.bean.code.Code;
import com.huayu.bean.code.PCode;
import com.huayu.user.service.UserService;

@Service
public class SystemCache {

	private static ApplicationContext context;
	
	private static Map<String, Map<String, Code>> codes = null;
	
	@PostConstruct
	private void init(){
		context = ApplicationContextHolder.getApplicationContext();
		codes = new HashMap<String, Map<String,Code>>();
		initSystemCodes();
	}
	private void initSystemCodes() {
		UserService userService = context.getBean(UserService.class);
		
		List<PCode> pcode = userService.getPCode();
		
		for (PCode p : pcode) {
			
			String cPid = p.getCPid();
			
			List<Code> code  = userService.getCodeByPid(cPid);
			
			Map<String, Code> map = null;
			for (Code c : code) {
				map = new HashMap<>();
				map.put(c.getCCode(), c);
			}
			codes.put(cPid, map);
		}
		
	}
	
	
	
}
