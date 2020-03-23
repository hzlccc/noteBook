package com.huayu.eureka.elasticsearch.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huayu.eureka.bean.Shgx;
import com.huayu.eureka.bean.User;
import com.huayu.eureka.elasticsearch.sync.service.SyncService;
import com.huayu.eureka.user.service.UserService;
@RestController
@RequestMapping("/es")
public class EsController {

	@Autowired
	private UserService userService;
	@Autowired
	private SyncService syncService;
	
	@RequestMapping("/sync")
	public List<User> sync(){
		List<User> users = userService.getAllUsers();
		for (User user : users) {
			List<Shgx> shgx = userService.getShgx(user.getCId());
			user.setShgx(shgx);
		}
		syncService.sync(users);
		return users;
	}
}
