package com.ssm.base.oberver;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;

/**
 * @author reed.mi
 * @date 2017/3/30.
 */
@Service
public class EmailPublisher implements ApplicationContextAware {

	private ApplicationContext context;

	@Autowired
	EmailListener emailListener;

	@Autowired
	@Qualifier("applicationEventMulticaster")
	private ApplicationEventMulticaster applicationEventMulticaster;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		applicationEventMulticaster.addApplicationListener(emailListener);
	}

	public void send(String address, String text) {
		EmailEvent emailEvent = new EmailEvent(this, address, text);
		this.applicationEventMulticaster.multicastEvent(emailEvent);
	}
}
