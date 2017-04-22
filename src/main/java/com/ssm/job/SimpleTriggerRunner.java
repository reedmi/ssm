package com.ssm.job;

import org.quartz.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author reed.mi
 * @date 2017/4/22.
 */
@Component
public class SimpleTriggerRunner implements InitializingBean {

	@Autowired
	private Scheduler scheduler;

	public void startA() throws SchedulerException {
		JobDetail jobDetail = scheduler.getJobDetail(new JobKey("JOB_A", "Group_A"));
		if(jobDetail != null) {
			return;
		}
		jobDetail = newJob(SimpleJob.class).withIdentity("JOB_A", "Group_A").build();
		Trigger trigger = newTrigger()
							.withIdentity("Trigger_A", "Group_A")
							.withDescription("Trigger desc A.")
							.withSchedule(cronSchedule("0/5 * * * * ?"))
							.build();
		scheduler.scheduleJob(jobDetail, trigger);
	}

	public void startB() throws SchedulerException {
		JobDetail jobDetail = scheduler.getJobDetail(new JobKey("JOB_B", "Group_B"));
		if(jobDetail != null) {
			return;
		}
		jobDetail = newJob(SimpleJob.class).withIdentity("JOB_B", "Group_B").build();
		Trigger trigger = newTrigger()
				.withIdentity("Trigger_B", "Group_B")
				.withDescription("Trigger desc B.")
				.withSchedule(cronSchedule("0/10 * * * * ?"))
				.build();
		scheduler.scheduleJob(jobDetail, trigger);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		startA();
		startB();
	}
}
