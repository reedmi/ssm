package com.ssm.mq.sender;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author reed.mi
 * @date 2017/4/16.
 */
@Service
public class MQSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	private final static Logger LOG = Logger.getLogger(MQSender.class);

	@Value("#{rabbitmq['ops.demo.ssm.exchange']}")
	private String messageExchange;

	@Value("#{rabbitmq['ops.demo.ssm.routing.key']}")
	private String messageRoutingKey;

	public void send(Object object) {
		try {
			System.out.println("send: \t" + object);
			amqpTemplate.convertAndSend(messageExchange, messageRoutingKey, object);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

}
