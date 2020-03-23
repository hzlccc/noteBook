package com.huayu.eureka.elasticsearch.sync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huayu.eureka.bean.User;


@Service
public class SyncService {

	public void sync(List<User> users) {
		for (User user : users) {
			Object json = JSONObject.toJSON(user);
			System.out.println("==================================================");
			System.out.println(json);
			System.out.println("==================================================");
		}
		

	}

}
