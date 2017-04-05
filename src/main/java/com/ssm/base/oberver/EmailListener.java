package com.ssm.base.oberver;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author reed.mi
 * @date 2017/3/29.
 */
@Component
public class EmailListener implements ApplicationListener<EmailEvent> {

	@Override
	public void onApplicationEvent(EmailEvent emailEvent) {
		try {
			System.out.println("<<先休息5妙后在处理吧，累死了！");
			Thread.sleep(5 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		emailEvent.handle();
	}
}
