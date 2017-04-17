package com.ssm.mq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MQListenter implements MessageListener {

    @Override
	public void onMessage(Message msg) {
        try{
            System.out.println("receive:\t" + msg.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}