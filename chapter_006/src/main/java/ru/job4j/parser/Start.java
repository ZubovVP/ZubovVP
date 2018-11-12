package ru.job4j.parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Start.
 * <p>
 * Created by ZubovVP on 01.11.2018
 * zubovvp@yadndex.ru
 */
public class Start implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(Start.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("--------START--------");
        try (DataBase db = new DataBase()) {
            Boolean result = db.checkTable();
            Downloader dw = new Downloader();
            List<Offer> offers = dw.download(result);
            db.add(offers);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info("---------FINISH---------");
    }
}
