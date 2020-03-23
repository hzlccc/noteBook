package com.huayu.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import com.alibaba.fastjson.JSON;

public class ProducerUtils {
	
	
	
    public static void send() throws MQClientException {
        //DefaultMQProducer producer = new DefaultMQProducer("test-group");
        TransactionMQProducer producer = new TransactionMQProducer("test-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.setInstanceName("rmq-instance");
       
        producer.start();
        try {
            
                User user = new User();
                user.setLoginName("hzl");
                user.setPwd("123");
                Message message = new Message("log-topic", "user-tag",JSON.toJSONString(user).getBytes());
                System.out.println("生产者发送消息:"+JSON.toJSONString(user));
                producer.send(message);
            
 
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}


