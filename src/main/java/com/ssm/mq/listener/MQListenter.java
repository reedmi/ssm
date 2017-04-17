package com.ssm.mq.listener;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MQListenter implements MessageListener {

    private final static Logger LOG = Logger.getLogger(MQListenter.class);

    @Override
	public void onMessage(Message msg) {
        try{
            System.out.println("MQListenter receive：\t" + msg.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}