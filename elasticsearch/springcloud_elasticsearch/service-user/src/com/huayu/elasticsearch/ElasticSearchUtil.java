package com.huayu.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


public class ElasticSearchUtil {

	public static RestHighLevelClient getRestHighLevelClient(String ip,int port){
		RestHighLevelClient client = null;
		client =  new RestHighLevelClient(RestClient.builder(
					 new HttpHost(ip,port, "http")));
		return client;	
	}
}
