package com.huayu.elasticsearch.sync;

import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import com.alibaba.fastjson.JSONObject;
import com.huayu.bean.user.User;

class Task3 implements Callable<String>{
	private static final String INDEX = "user";
	private static final String TYPE = "type";
	private RestHighLevelClient client;
	private List<User> users;
	public Task3(RestHighLevelClient client,List<User> users){
		this.client= client;
		this.users = users;
	}
	
	
	@Override
	public String call() throws Exception {
		
		BulkRequest bulkRequest = new BulkRequest();

		for (User user : users) {
			String userId = user.getCId();

			if (StringUtils.isEmpty(INDEX)){
	            return "请指定索引名称";
	        }

	        String jsonString = JSONObject.toJSONString(user);
			//索引请求
			IndexRequest indexRequest = new IndexRequest(INDEX, TYPE,userId).source(jsonString, XContentType.JSON);
			bulkRequest.add(indexRequest);
			
			
			//IndexResponse indexResponse = client.index(indexRequest);
			
			
			//System.out.println(Thread.currentThread().getName()+"===同步成功，用户标识:"+userId);
		}
		
        BulkResponse bulk = client.bulk(bulkRequest);
        
		return Thread.currentThread().getName()+"已完成同步任务";
    }
		
		
	
	
}