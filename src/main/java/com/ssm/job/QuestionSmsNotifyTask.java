package com.ssm.job;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author reed.mi
 * @date 2017/4/24.
 */
@Component
public class QuestionSmsNotifyTask implements InitializingBean {

	private final static String CRON = "0/5 * * * * ?"; //每天晚上8点执行一次

	private final static String JOB_SMS_NOTIFY = "JOB_SMS_NOTIFY";
	private final static String JOB_GROUP_SMS_NOTIFY = "JOB_GROUP_SMS_NOTIFY";

	private final static String TRIGGER_SMS_NOTIFY = "TRIGGER_SMS_NOTIFY";
	private final static String TRIGGER_GROUP_SMS_NOTIFY = "TRIGGER_GROUP_SMS_NOTIFY";

	@Autowired
	private Scheduler scheduler;

	@Override
	public void afterPropertiesSet() throws Exception {
		CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(TRIGGER_SMS_NOTIFY, TRIGGER_GROUP_SMS_NOTIFY);
		if(cronTrigger == null) {
			JobDetail jobDetail = new JobDetail(JOB_SMS_NOTIFY, JOB_GROUP_SMS_NOTIFY, QuestionSmsNotifyJob.class);
			cronTrigger = new CronTrigger(TRIGGER_SMS_NOTIFY, TRIGGER_GROUP_SMS_NOTIFY, CRON);
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
	}
}
