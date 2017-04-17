package com.ssm.mq.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.util.Random;

/**
 * @author reed.mi
 * @date 2017/4/17.
 */
public class MQAckListener implements ChannelAwareMessageListener {

	private final static Logger LOG = Logger.getLogger(MQAckListener.class);

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try{
			String dataInStr = new String(message.getBody(), "UTF-8");
			JSONObject json = JSON.parseObject(dataInStr);
			System.out.println("receive: \t" + json.toJSONString() + ", 休息10s.");
			Thread.sleep(10 * 1000);
			int tmp = new Random().nextInt(100);
			if(tmp % 2 == 0) {
				System.out.println(tmp + "\t没收到，要求重发");
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
			} else {
				System.out.println(tmp + "\t我收到了！");
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
