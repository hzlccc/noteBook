package com.huayu.elasticsearch.sync;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import com.alibaba.fastjson.JSONObject;
import com.huayu.bean.user.User;
import com.huayu.user.mapper.UserMapper;

class Task implements Callable<String>{
	private static final String INDEX = "user";
	private static final String TYPE = "type";
	private RestHighLevelClient client;
	private List<User> users;
	private UserMapper userMapper;
	public Task(RestHighLevelClient client,List<User> users, UserMapper userMapper){
		this.client= client;
		this.users = users;
		this.userMapper = userMapper;
	}
	
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for (User user : users) {
			String userId = user.getCId();
			user.setShgx(userMapper.getShgx(userId));
			user.setSrjl(userMapper.getSrjl(userId));
		}
		for (User user : users) {
			String userId = user.getCId();

			if (StringUtils.isEmpty(INDEX)){
	            return "请指定索引名称";
	        }

	        try {
	        	String jsonString = JSONObject.toJSONString(user);
	            //索引请求
	            IndexRequest indexRequest = new IndexRequest(INDEX, TYPE,userId).source(jsonString, XContentType.JSON);

	            IndexResponse indexResponse = client.index(indexRequest);
				System.out.println(Thread.currentThread().getName()+"===同步成功，用户标识:"+userId);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		return Thread.currentThread().getName()+"已完成同步任务";
    }
		
		
	
	
}