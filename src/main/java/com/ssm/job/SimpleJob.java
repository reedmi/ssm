package com.ssm.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author reed.mi
 * @date 2017/4/22.
 */
public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		System.out.println("In SimpleJob - executing its JOB at "
//				+ new Date() + " by " + context.getTrigger().getJobKey());
	}
}
