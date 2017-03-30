package com.ssm.user;

import com.ssm.base.Junit4Test;
import com.ssm.base.obervers.EmailPublisher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @author reed.mi
 * @date 2017/3/30.
 */
public class EmailJunitTest extends Junit4Test {

	@Autowired
	EmailPublisher emailPublisher;

	@Test
	public void send() {
		int i = 0;
		while (++i < 40) {
			int sleep = new Random().nextInt(10);
			System.out.println(">>发送一个请求给Listener." + sleep);
			emailPublisher.send(sleep + "", Thread.currentThread().getName());
			System.out.println(">>我继续干自己的事情！");
		}
	}
}
