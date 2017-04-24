package com.ssm.scheduler;

import com.ssm.base.NGTest;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by ReedMi on 2017/4/24.
 */
public class TaskTest extends NGTest {

    @Autowired
    Scheduler scheduler;

    @Test
    public void test() throws Exception {
        JobDetail jobDetail = new JobDetail("jname", "jgroup", CronJob.class);
        Trigger trigger = scheduler.getTrigger("tname", "tgroup");
        if(trigger == null) {
            trigger = new CronTrigger("tname", "tgroup", "0/5 * * * * ?");
            scheduler.addJob(jobDetail, true);
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            scheduler.resumeTrigger("tname", "tgroup");
            scheduler.resumeJob("jname", "jgroup");
        }
    }
}
