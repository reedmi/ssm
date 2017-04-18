package com.ssm.mq.recover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;


public class MessageRecover implements MessageRecoverer {

    private static Logger LOG = LoggerFactory.getLogger(MessageRecover.class);

    @Override
	public void recover(Message message, Throwable throwable) {
        LOG.error("MQ error, msg:{}", new String(message.getBody()), throwable);
        System.out.println("MessageRecover reject.");
        throw new AmqpRejectAndDontRequeueException(throwable);
    }
}