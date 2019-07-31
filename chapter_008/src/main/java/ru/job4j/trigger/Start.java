package ru.job4j.trigger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.persistence.Persistence;

/**
 * Start.
 * <p>
 * Created by ZubovVP on 29.07.2019
 * zubovvp@yadndex.ru
 */
public class Start implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(Start.class);


    /**
     * Every 30 minuets delete reserve of seats from database.
     *
     * @param jobExecutionContext - jobExecutionContext.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Persistence persistence = Persistence.getInstance();
        LOG.info("--------Delete all reserve tickets--------");
        LOG.info("--------START--------");
        persistence.deleteReserve();
        LOG.info("---------FINISH---------");
    }
}
