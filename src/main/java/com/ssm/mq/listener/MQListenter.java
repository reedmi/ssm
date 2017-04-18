package com.ssm.mq.listener;

import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MQListenter implements MessageListener {

    private final static Logger LOG = Logger.getLogger(MQListenter.class);

    @Override
	public void onMessage(Message message) {
        System.out.println("MQListenter receive：\t" + message.toString());
        throw new AmqpRejectAndDontRequeueException("MQListenter throws exception. " + message.toString());
    }

}