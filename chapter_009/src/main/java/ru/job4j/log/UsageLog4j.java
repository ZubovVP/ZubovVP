package ru.job4j.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.01.2021.
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Vitaly";
        int age = 28;
        double weight = 74.1;
        boolean male = true;
        short height = 178;
        LOG.debug("Name : {}, age :{}, weight : {}, height : {}, male : {}", name, age, weight, height, male);
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
