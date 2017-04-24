package com.ssm.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by ReedMi on 2017/4/24.
 */
public class CronJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
    }
}
