package com.ssm.job;

import java.io.Serializable;

/**
 * @author reed.mi
 * @date 2017/4/25.
 */
public class CronTask implements Serializable {

	public void start() {
		System.out.println("start >>>>>");
	}
}
