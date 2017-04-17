package com.ssm.mq;

import com.ssm.base.Junit4Test;
import com.ssm.mq.sender.MQSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class TestQueue extends Junit4Test {

	@Autowired
    MQSender mqSender;

    @Test
    public void send(){
        Map<String, Object> msg = new HashMap<>();
        msg.put("data", "Hello, RabbitMQ.");
        mqSender.send(msg);
    }
}