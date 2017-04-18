package com.ssm.mq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author reed.mi
 * @date 2017/4/17.
 */
public class MQRetryListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("MQRetryListener receive：\t" + message.toString());
	}
}
