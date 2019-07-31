package ru.job4j.trigger;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Trigger.
 * <p>
 * Start class "Start".
 * <p>
 * Created by ZubovVP on 29.07.2019
 * zubovvp@yadndex.ru
 */
public class Trigger {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(Start.class).build();
        CronTrigger crontrigger = TriggerBuilder.newTrigger().withSchedule(
                CronScheduleBuilder.cronSchedule("0 */30 * ? * *")).build();
        scheduler.start();
        scheduler.scheduleJob(job, crontrigger);
    }
}
