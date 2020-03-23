package com.huayu.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class MqController {
	@RequestMapping("/produce")
	public String produce(){
		try {
			ProducerUtils.send();
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "发送成功";
	}
}
