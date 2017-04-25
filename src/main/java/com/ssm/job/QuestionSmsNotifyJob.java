package com.ssm.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * @author reed.mi
 * @date 2017/4/24.
 */
public class QuestionSmsNotifyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("aaaaaa");
	}
}
