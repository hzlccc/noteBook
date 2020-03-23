package com.huayu.user;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huayu.bean.user.Shgx;
import com.huayu.bean.user.User;
import com.huayu.elasticsearch.search.SearchService;
import com.huayu.elasticsearch.sync.EsService;
import com.huayu.user.service.AnalogDataService;
import com.huayu.user.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private EsService esService;
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private AnalogDataService analogDataService;
	
	@RequestMapping("/allUsers")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/shgx")
	public List<Shgx> getShgx(@RequestParam("userId")String userId){
		return userService.getShgx(userId);
	}
	
	@RequestMapping("/sync")
	public void sync() throws InterruptedException, ExecutionException{
		esService.sync();
	}
	
	@RequestMapping("/sync2")
	public void sync2() throws InterruptedException, ExecutionException{
		esService.sync2();
	}
	
	@RequestMapping("/sync3")
	public void sync3() throws InterruptedException, ExecutionException{
		esService.sync3();
	}
	
	@RequestMapping("/syncBulk")
	public void syncBulk() throws InterruptedException, ExecutionException{
		esService.syncBulk();
	}
	
	
	@RequestMapping("/update")
	public void update(String userId) throws InterruptedException, ExecutionException{
		esService.update(userId);
	}
	
	@RequestMapping("/Analogdata")
	public void analogData(){
		analogDataService.analog();
	}
	
	@RequestMapping("/search")
	public void search(){
		//searchService.search();
	}
	
}
