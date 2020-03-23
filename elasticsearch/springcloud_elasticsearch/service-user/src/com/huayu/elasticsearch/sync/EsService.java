package com.huayu.elasticsearch.sync;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.huayu.bean.user.Shgx;
import com.huayu.bean.user.Srjl;
import com.huayu.bean.user.User;
import com.huayu.elasticsearch.ElasticSearchUtil;
import com.huayu.user.mapper.UserMapper;
import com.huayu.utils.SplitUtil;

@Service
public class EsService {
	
	private static final String INDEX = "user";
	private static final String TYPE = "type";
	@Autowired
	private UserMapper userMapper;
	
	private static int count = 0;
	
	public void sync() throws InterruptedException, ExecutionException {//16000->1429秒
		System.out.println("===========================同步方案开始====================================");
		Date start = new Date();
		
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		CompletionService<String> service = CompletionServiceUtil.getService();
		
		List<User> allUsers = userMapper.getAllUsers();
		List<List<User>> splitList = SplitUtil.splitUser(allUsers,1000);
		for (List<User> users : splitList) {
			System.out.println("=======================提交同步任务=======================================");
			service.submit(new Task(client,users,userMapper));
			count++;
		}
		CompletionServiceUtil.result(count);
		System.out.println("同步完成");
		Date end = new Date();
		
		System.out.println("同步完成:::多线程方式：：："+(end.getTime()-start.getTime())/1000);
		System.out.println();

		
	}
	
	public void sync2() {
		// TODO Auto-generated method stub
		Date start = new Date();
		List<User> allUsers = userMapper.getAllUsers();
		for (User user : allUsers) {
			user.setShgx(userMapper.getShgx(user.getCId()));
			user.setSrjl(userMapper.getSrjl(user.getCId()));

		}
		
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		
		for (User user : allUsers) {
			String id = user.getCId();
		 	 try {
	        	String jsonString = JSONObject.toJSONString(user);
	            //索引请求
	            IndexRequest indexRequest = new IndexRequest(INDEX, TYPE,id).source(jsonString, XContentType.JSON);
	
		        client.index(indexRequest);
				System.out.println(Thread.currentThread().getName()+"===正在进行同步，用户标识:"+id);

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		Date end = new Date();
		
		System.out.println("同步完成:::同步方式：：："+(end.getTime()-start.getTime())/1000);
		System.out.println();
	}  

	
	public void update(String userId) throws InterruptedException, ExecutionException {
		User user = userMapper.getUserById(userId);
		if(user ==null){
			System.out.println("该用户不存在！");
			return;
		}
		user.setShgx(userMapper.getShgx(user.getCId()));
		user.setSrjl(userMapper.getSrjl(user.getCId()));

		
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		String jsonString = JSONObject.toJSONString(user);
        //索引请求
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE,userId).source(jsonString, XContentType.JSON);

        try {
			IndexResponse index2 = client.index(indexRequest);
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"===同步成功，用户标识:"+userId);

	}

	public void sync3() {//16000->736秒
		System.out.println("===========================同步方案3====================================");

		System.out.println("===========================同步方案开始====================================");
		Date start = new Date();
		
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		CompletionService<String> service = CompletionServiceUtil.getService();
		List<User> allUsers = userMapper.getAllUsers();
		List<Shgx> allShgx = userMapper.getAllShgx();
		List<Srjl> allSrjl = userMapper.getAllSrjl();
		
		Map<String, List<Shgx>> shgxMap = new HashMap<String, List<Shgx>>();
		Map<String, List<Srjl>> srjlMap = new HashMap<String, List<Srjl>>();
		
		System.out.println("处理社会关系开始");
		for (Shgx shgx : allShgx) {
			List<Shgx> list = shgxMap.get(shgx.getCUserId());
			if(list==null){
				List<Shgx> temp = new ArrayList<Shgx>();
				temp.add(shgx);
				shgxMap.put(shgx.getCUserId(), temp);
			}else{
				list.add(shgx);
				
				shgxMap.put(shgx.getCUserId(), list);
			}
			
		}
		System.out.println("处理社会关系结束");
		
		System.out.println("处理收入记录开始");
		for (Srjl srjl : allSrjl) {
			List<Srjl> list = srjlMap.get(srjl.getCUserID());
			if(list==null){
				List<Srjl> temp = new ArrayList<Srjl>();
				temp.add(srjl);
				srjlMap.put(srjl.getCUserID(), temp);
			}else{
				list.add(srjl);
				srjlMap.put(srjl.getCUserID(), list);
			}
			
		}
		System.out.println("处理收入记录结束");
		
		System.out.println("设置社会关系、收入记录开始");

		for (User user : allUsers) {
			String userId = user.getCId();
			user.setShgx(shgxMap.get(userId));
			user.setSrjl(srjlMap.get(userId));
		}
		System.out.println("设置社会关系、收入记录结束");

		int task_num = allUsers.size()/5;
		System.out.println(task_num);
		List<List<User>> splitList = SplitUtil.splitUser(allUsers,task_num);
		for (List<User> users : splitList) {
			System.out.println("=======================提交同步任务=======================================");
			service.submit(new Task2(client,users));
			count++;
		}
		try {
			CompletionServiceUtil.result(count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("同步完成");
		Date end = new Date();
		
		System.out.println("同步完成:::多线程方式：：："+(end.getTime()-start.getTime())/1000);
		System.out.println();
		
	}
	
	public void syncBulk() {
		System.out.println("===========================同步方案3====================================");

		System.out.println("===========================同步方案开始====================================");
		Date start = new Date();
		
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		CompletionService<String> service = CompletionServiceUtil.getService();
		List<User> allUsers = userMapper.getAllUsers();
		List<Shgx> allShgx = userMapper.getAllShgx();
		List<Srjl> allSrjl = userMapper.getAllSrjl();
		
		Map<String, List<Shgx>> shgxMap = new HashMap<String, List<Shgx>>();
		Map<String, List<Srjl>> srjlMap = new HashMap<String, List<Srjl>>();
		
		System.out.println("处理社会关系开始");
		for (Shgx shgx : allShgx) {
			List<Shgx> list = shgxMap.get(shgx.getCUserId());
			if(list==null){
				List<Shgx> temp = new ArrayList<Shgx>();
				temp.add(shgx);
				shgxMap.put(shgx.getCUserId(), temp);
			}else{
				list.add(shgx);
				
				shgxMap.put(shgx.getCUserId(), list);
			}
			
		}
		System.out.println("处理社会关系结束");
		
		System.out.println("处理收入记录开始");
		for (Srjl srjl : allSrjl) {
			List<Srjl> list = srjlMap.get(srjl.getCUserID());
			if(list==null){
				List<Srjl> temp = new ArrayList<Srjl>();
				temp.add(srjl);
				srjlMap.put(srjl.getCUserID(), temp);
			}else{
				list.add(srjl);
				srjlMap.put(srjl.getCUserID(), list);
			}
			
		}
		System.out.println("处理收入记录结束");
		
		System.out.println("设置社会关系、收入记录开始");

		for (User user : allUsers) {
			String userId = user.getCId();
			user.setShgx(shgxMap.get(userId));
			user.setSrjl(srjlMap.get(userId));
		}
		System.out.println("设置社会关系、收入记录结束");

		List<List<User>> splitList = SplitUtil.splitUser(allUsers,5000);
		
		
		for (List<User> users : splitList) {
			System.out.println("=======================提交同步任务=======================================");
			service.submit(new Task3(client,users));
			count++;
		}
		try {
			CompletionServiceUtil.result(count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("同步完成");
		Date end = new Date();
		
		System.out.println("同步完成:::多线程方式：：："+(end.getTime()-start.getTime())/1000);
		System.out.println();
		
	}
}
