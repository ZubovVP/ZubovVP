package ru.job4j.parser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Trigger.
 *
 * Start class "Start".
 *
 * Created by ZubovVP on 06.11.2018
 * zubovvp@yadndex.ru
 */
public class Trigger {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(Start.class).build();
        CronTrigger crontrigger = TriggerBuilder.newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule("0 0 12 * * ?")).build();
        scheduler.start();
        scheduler.scheduleJob(job, crontrigger);
    }
}
